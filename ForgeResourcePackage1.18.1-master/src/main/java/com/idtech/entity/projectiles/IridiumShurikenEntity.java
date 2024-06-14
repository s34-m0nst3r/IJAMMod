package com.idtech.entity.projectiles;

import com.idtech.BaseMod;
import com.idtech.item.IridiumShuriken;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class IridiumShurikenEntity extends ThrowableItemProjectile implements ItemSupplier {

    public static EntityType<? extends ThrowableItemProjectile> TYPE = (EntityType<IridiumShurikenEntity>)
            EntityType.Builder.<IridiumShurikenEntity>of(IridiumShurikenEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F).build("iridiumshurikenentity")
                    .setRegistryName(BaseMod.MODID, "iridiumshurikenentity");


    public IridiumShurikenEntity(EntityType<IridiumShurikenEntity> type, Level level) {
        super(type, level);
    }

    public IridiumShurikenEntity(Level level, LivingEntity entity) {
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
            if (hitResult instanceof EntityHitResult entityHitResult) {
                Entity entity = entityHitResult.getEntity();
                entity.hurt(DamageSource.thrown(this, this.getOwner()), 5.0F); // Deal 5 damage
            }
        }
        this.remove(RemovalReason.DISCARDED);
    }


}

