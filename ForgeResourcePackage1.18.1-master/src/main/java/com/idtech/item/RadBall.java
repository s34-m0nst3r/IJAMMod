package com.idtech.item;

import com.idtech.ModTab;
import com.idtech.entity.projectiles.RadBallEntity;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RadBall extends Item {

    private static Properties properties = new Properties().tab(ModTab.INSTANCE);

    public static Item INSTANCE = new RadBall(properties).setRegistryName("radball");

    public RadBall(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level levelIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        CompoundTag tag = itemstack.getTag();

        levelIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (levelIn.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!levelIn.isClientSide) {
            RadBallEntity projectile = new RadBallEntity(levelIn, playerIn);
            projectile.setItem(itemstack);
            if (tag != null && tag.contains("CapturedMob")) {
                projectile.setCapturedMobData(tag.getCompound("CapturedMob"));
            }
            projectile.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0F, 1.5F, 1.0F);
            levelIn.addFreshEntity(projectile);
            if (!playerIn.getAbilities().instabuild) {
                itemstack.shrink(1);
            }
        }

        return InteractionResultHolder.sidedSuccess(itemstack, levelIn.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        CompoundTag tag = stack.getTag();
        if (tag != null && tag.contains("CapturedMob")) {
            CompoundTag mobTag = tag.getCompound("CapturedMob");
            EntityType<?> type = EntityType.byString(mobTag.getString("id")).orElse(null);
            if (type != null) {
                String localizedMobName = new TranslatableComponent(type.getDescriptionId()).getString();
                tooltip.add(new TranslatableComponent("Captured Mob: " + localizedMobName));
            }
            else {
                tooltip.add(new TranslatableComponent("Captured Mob: " + mobTag));
            }
        }
        else {
            tooltip.add(new TranslatableComponent("No mob captured."));
        }

        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslatableComponent("Use this item to capture mobs."));
            tooltip.add(new TranslatableComponent("Throw again to release the captured mob."));
        } else {
            tooltip.add(new TranslatableComponent("Hold SHIFT for more information."));
        }
    }
}