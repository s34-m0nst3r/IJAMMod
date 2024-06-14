package com.idtech.block;


import com.idtech.ModTab;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Material;
//import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BlockMod {

    //Basic Block
    public static final Block CASTLE_WALL = BlockUtils.createBasicBlock("castlewall", Material.STONE);
    public static final Item CASTLE_WALL_ITEM = BlockUtils.createBlockItem(CASTLE_WALL, ModTab.INSTANCE);

    public static final Block TESTBLOCK = BlockUtils.createBasicBlock("testblock", Material.STONE);
    public static final Item TESTBLOCK_ITEM = BlockUtils.createBlockItem(TESTBLOCK, ModTab.INSTANCE);


    @SubscribeEvent
    public static void registerBlockItems(RegistryEvent.Register<Item> event) {

        event.getRegistry().register(CASTLE_WALL_ITEM);
        event.getRegistry().register(TESTBLOCK_ITEM);

        event.getRegistry().register(GelOreBlock.ITEM);
        event.getRegistry().register(IridiumOreBlock.ITEM);
        event.getRegistry().register(DeepslateGelOreBlock.ITEM);
        event.getRegistry().register(DeepslateIridiumOreBlock.ITEM);
        event.getRegistry().register(GelSpringBlock.ITEM);
        event.getRegistry().register(FungalGrassBlock.ITEM);
        event.getRegistry().register(BlockOfIridium.ITEM);
        event.getRegistry().register(BlockOfGel.ITEM);
        event.getRegistry().register(Brimstone.ITEM);
        event.getRegistry().register(Brimslate.ITEM);
        event.getRegistry().register(HotCoalsBlock.ITEM);




    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {

        event.getRegistry().register(CASTLE_WALL);
        event.getRegistry().register(TESTBLOCK);

        event.getRegistry().register(GelOreBlock.INSTANCE);
        event.getRegistry().register(IridiumOreBlock.INSTANCE);
        event.getRegistry().register(DeepslateGelOreBlock.INSTANCE);
        event.getRegistry().register(DeepslateIridiumOreBlock.INSTANCE);
        event.getRegistry().register(GelSpringBlock.INSTANCE);
        event.getRegistry().register(FungalGrassBlock.INSTANCE);
        event.getRegistry().register(BlockOfIridium.INSTANCE);
        event.getRegistry().register(BlockOfGel.INSTANCE);
        event.getRegistry().register(Brimstone.INSTANCE);
        event.getRegistry().register(Brimslate.INSTANCE);
        event.getRegistry().register(HotCoalsBlock.INSTANCE);


    }
}





