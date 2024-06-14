package com.idtech.item;

import com.idtech.ModTab;
import net.minecraft.world.item.*;

public class Mace extends SwordItem{
    private static Properties properties = new   Properties().tab(ModTab.INSTANCE);

    public static Item INSTANCE = new Mace(Tiers.IRON, 2, 5,   properties).setRegistryName("mace");

    public Mace(Tier tier, int speed, float damage, Properties properties) {
        super(tier, speed, damage, properties);
    }
}
