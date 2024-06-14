package com.idtech;

import com.idtech.item.ItemMod;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModTab extends CreativeModeTab {

    public static CreativeModeTab INSTANCE = new ModTab("seas_mod");

    public ModTab(String label){
        super(label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ItemMod.IRIDIUM_CORE);
    }
}