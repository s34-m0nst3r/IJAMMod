package com.idtech.item;

import com.idtech.ModTab;
import com.idtech.entity.projectiles.BombArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BombArrowItem extends ArrowItem{

    private static Properties properties = new Properties().tab(ModTab.INSTANCE);

    //Constructor
    public BombArrowItem(Properties properties) {
        super (properties);
    }

    public static Item INSTANCE = new BombArrowItem(properties).setRegistryName("bombarrow");


    @Override
    public AbstractArrow createArrow(Level levelIn, ItemStack stack, LivingEntity shooter) {
        BombArrow arrowEntity = new BombArrow(levelIn, shooter);
        return arrowEntity;
    }
}
