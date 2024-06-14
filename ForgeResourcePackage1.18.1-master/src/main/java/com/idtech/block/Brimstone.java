package com.idtech.block;

import com.idtech.BaseMod;
import com.idtech.ModTab;
import com.idtech.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import javax.annotation.Nullable;

public class Brimstone extends Block{

    //NOTE: TO BE MINEABLE WITH THE RIGHT TOOLS THESE ORES NEED TO BE ADDED TO THE TAGS FILE

    private static Properties properties = Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(2.0f).destroyTime(0.8f);

    //static instances for registration
    public static final Block INSTANCE = new Brimstone(properties).setRegistryName(BaseMod.MODID, "brimstone");
    public static final Item ITEM = BlockUtils.createBlockItem(INSTANCE, ModTab.INSTANCE);

    //Constructor
    public Brimstone(Properties properties){
        super(properties);
    }

}