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
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class ScorchEntity extends Zombie {

    //Constructor
    public ScorchEntity(EntityType<? extends Zombie> type, Level level) {
        super(type, level);
    }

    public static EntityType<ScorchEntity> TYPE = (EntityType<ScorchEntity>)
            EntityType.Builder.of(ScorchEntity::new, MobCategory.MONSTER)
                    .build("scorch")
                    .setRegistryName(BaseMod.MODID, "scorch");

    public static Item EGG = EntityUtils.buildEntitySpawnEgg(TYPE, 0xeb5834, 0xeb4131);

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE)
                .add(Attributes.ATTACK_DAMAGE,3)
                .add(Attributes.MOVEMENT_SPEED,0.23f)
                .add(Attributes.ARMOR,3)
                .add(Attributes.MAX_HEALTH,25)
                .add(Attributes.KNOCKBACK_RESISTANCE,0.2f)
                .add(Attributes.FOLLOW_RANGE,30);
    }

    @Override
    public void registerGoals() {
        this.targetSelector.addGoal(0, new FloatGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Zombie.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Spider.class, false));
        this.targetSelector.addGoal(4, new MeleeAttackGoal(this, 0.8f, false));
        this.targetSelector.addGoal(5, (new HurtByTargetGoal(this)).setAlertOthers(ScorchEntity.class));
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
