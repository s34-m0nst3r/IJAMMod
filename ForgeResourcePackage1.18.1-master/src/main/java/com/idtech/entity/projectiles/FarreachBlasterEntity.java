package com.idtech.entity.projectiles;

import com.idtech.BaseMod;
//import com.idtech.entity.PortalEntity;
import com.idtech.item.IridiumShuriken;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class FarreachBlasterEntity extends ThrowableItemProjectile implements ItemSupplier {

    public static EntityType<? extends ThrowableItemProjectile> TYPE = (EntityType<FarreachBlasterEntity>)
            EntityType.Builder.<FarreachBlasterEntity>of(FarreachBlasterEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F).build("farreachblasterentity")
                    .setRegistryName(BaseMod.MODID, "farreachblasterentity");

    public FarreachBlasterEntity(EntityType<FarreachBlasterEntity> type, Level level) {
        super(type, level);
    }

    public FarreachBlasterEntity(Level level, LivingEntity entity) {
        super(TYPE, entity, level);
    }

    @Override
    protected Item getDefaultItem() {
        return IridiumShuriken.INSTANCE;
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);

        if (!this.level.isClientSide) {
            if (hitResult instanceof BlockHitResult blockHitResult) {
                BlockPos hitPos = blockHitResult.getBlockPos();
                PortalEntity portal = new PortalEntity(this.level, hitPos);
                this.level.addFreshEntity(portal);
            }
            this.remove(RemovalReason.DISCARDED);
        }
    }

    @Override
    protected void defineSynchedData() {}

    @Override
    public Packet<?> getAddEntityPacket() {
        return null;
    }
}