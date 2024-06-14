package com.idtech.block;

import com.idtech.BaseMod;
import com.idtech.ModTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

public class Brimslate extends Block{

    //NOTE: TO BE MINEABLE WITH THE RIGHT TOOLS THESE ORES NEED TO BE ADDED TO THE TAGS FILE

    private static Properties properties = Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(4.0f).destroyTime(1.5f);

    //static instances for registration
    public static final Block INSTANCE = new Brimslate(properties).setRegistryName(BaseMod.MODID, "brimslate");
    public static final Item ITEM = BlockUtils.createBlockItem(INSTANCE, ModTab.INSTANCE);

    //Constructor
    public Brimslate(Properties properties){
        super(properties);
    }

}