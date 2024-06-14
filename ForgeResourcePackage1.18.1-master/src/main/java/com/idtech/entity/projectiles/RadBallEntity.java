package com.idtech.entity.projectiles;

import com.idtech.BaseMod;
import com.idtech.item.RadBall;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class RadBallEntity extends ThrowableItemProjectile implements ItemSupplier {

    public static EntityType<? extends ThrowableItemProjectile> TYPE = (EntityType<RadBallEntity>)
            EntityType.Builder.<RadBallEntity>of(RadBallEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F).build("radballentity")
                    .setRegistryName(BaseMod.MODID, "radballentity");

    private CompoundTag capturedMobData;
    private boolean capturedMob = false;

    public RadBallEntity(EntityType<RadBallEntity> type, Level level) {
        super(type, level);
    }

    public RadBallEntity(Level level, LivingEntity entity) {
        super(TYPE, entity, level);
    }

    @Override
    protected Item getDefaultItem() {
        return RadBall.INSTANCE;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (!this.level.isClientSide) {
            if (this.capturedMobData == null) {
                Entity entity = result.getEntity();
                if (entity instanceof Mob) {
                    captureMob((Mob) entity);
                    System.out.println("Hit!");
                    this.discard();
                }
            }
            else
            {
                summonCapturedMob(result.getLocation());
            }
        }
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level.isClientSide && this.capturedMobData != null) {
            summonCapturedMob(result.getLocation());
            System.out.println("onHit!");
            this.discard();
        }
    }

    private void captureMob(Mob mob) {
        this.capturedMobData = new CompoundTag();
        mob.saveWithoutId(this.capturedMobData); // Save the mob data without its ID to avoid resummoning the same entity
        this.capturedMobData.putString("id", EntityType.getKey(mob.getType()).toString());


        // Drop the RadBall item with captured mob data
        ItemStack radBallWithMob = new ItemStack(RadBall.INSTANCE);
        radBallWithMob.getOrCreateTag().put("CapturedMob", this.capturedMobData);
        this.level.addFreshEntity(new ItemEntity(this.level, this.getX(), this.getY(), this.getZ(), radBallWithMob));
        mob.remove(Entity.RemovalReason.KILLED); // Ensure the mob is removed from the world
        mob.discard();
        capturedMob = true;
        System.out.println("Captured!");
        this.discard();
    }

    private void summonCapturedMob(Vec3 location) {
        if (this.capturedMobData != null && !capturedMob) {
            EntityType<?> type = EntityType.byString(this.capturedMobData.getString("id")).orElse(null);
            if (type != null) {
                Entity entity = type.create(this.level);
                if (entity != null) {
                    System.out.println("Summoned a mob!");
                    entity.load(this.capturedMobData);
                    entity.moveTo(location.x, location.y, location.z, this.getYRot(), this.getXRot());
                    this.level.addFreshEntity(entity);
                }
            }
        }
    }

    public void setCapturedMobData(CompoundTag capturedMobData) {
        this.capturedMobData = capturedMobData;
    }

    public CompoundTag getCapturedMobData() {
        return capturedMobData;
    }
}