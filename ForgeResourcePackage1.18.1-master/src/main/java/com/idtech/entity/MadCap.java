package com.idtech.entity;

import com.idtech.BaseMod;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;



public class MadCap extends Silverfish {

    public static EntityType<MadCap> TYPE = (EntityType<MadCap>)
            EntityType.Builder.of(MadCap::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8).build("madcap").setRegistryName(BaseMod.MODID, "madcap");

    public static Item EGG = EntityUtils.buildEntitySpawnEgg(TYPE, 0xcc1b0e , 0xcc802f);

    public MadCap(EntityType<? extends Silverfish> entityIn, Level levelIn) {
        super(entityIn, levelIn);
    }

    public static AttributeSupplier.Builder createAttributes()
    {
        return Monster.createMonsterAttributes()
                .add(Attributes.ATTACK_DAMAGE,1)
                .add(Attributes.MOVEMENT_SPEED,0.15f)
                .add(Attributes.ARMOR,9)
                .add(Attributes.MAX_HEALTH,40)
                .add(Attributes.KNOCKBACK_RESISTANCE,0f)
                .add(Attributes.FOLLOW_RANGE,5)
                ;
    }

    @Override
    public void registerGoals() {
        this.targetSelector.addGoal(0, new FloatGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, false));
        this.targetSelector.addGoal(4, new MeleeAttackGoal(this, 0.8f, false));
        this.targetSelector.addGoal(5, (new HurtByTargetGoal(this)).setAlertOthers(MadCap.class));
        this.targetSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    @Override
    public boolean doHurtTarget(Entity p_34276_) {
        boolean flag = super.doHurtTarget(p_34276_);
        if (flag) {
            float f = this.level.getCurrentDifficultyAt(this.blockPosition()).getEffectiveDifficulty();
            if (this.random.nextFloat() < f * 0.3F) {
                p_34276_.setSecondsOnFire(2 * (int)f);
            }
        }
        return flag;
    }

}
