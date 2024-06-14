package com.idtech.entity.projectiles;

import com.idtech.BaseMod;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(value = Dist.CLIENT)
public class PortalEntity extends Painting {
    public static EntityType<PortalEntity> TYPE = (EntityType<PortalEntity>)
            EntityType.Builder.<PortalEntity>of(PortalEntity::new, MobCategory.MISC)
                    .sized(1.0F, 2.0F).build("portalentity")
                    .setRegistryName(BaseMod.MODID, "portalentity");

    private static final List<PortalEntity> portals = new ArrayList<>();

    public PortalEntity(EntityType<? extends Painting> type, Level level) {
        super(type, level);
    }

    public PortalEntity(Level level, BlockPos pos) {
        super(TYPE, level);
        this.setPos(pos.getX(), pos.getY(), pos.getZ());
        managePortals(this);
    }

    private void managePortals(PortalEntity newPortal) {
        if (portals.size() >= 2) {
            portals.remove(0).remove(RemovalReason.DISCARDED); // Remove the oldest portal if we already have 2
        }
        portals.add(newPortal);
    }

    public static void teleportPlayer(Level level, Player player) {
        if (portals.size() == 2) {
            PortalEntity portalA = portals.get(0);
            PortalEntity portalB = portals.get(1);
            BlockPos targetPos = portalA.blockPosition().equals(player.blockPosition()) ? portalB.blockPosition() : portalA.blockPosition();
            player.teleportTo(targetPos.getX(), targetPos.getY(), targetPos.getZ());
        }
    }

    @Override
    public void remove(RemovalReason reason) {
        super.remove(reason);
        portals.remove(this);
    }

    @Override
    protected void defineSynchedData() {}

    @Override
    public Packet<?> getAddEntityPacket() {
        return null;
    }
}