package com.idtech.block;

import com.idtech.BaseMod;
import com.idtech.ModTab;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import javax.annotation.Nullable;


public class GelSpringBlock extends Block {

    private static Properties properties = Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(2.0f).destroyTime(1f);

    //static instances for registration
    public static final Block INSTANCE = new GelSpringBlock(properties).setRegistryName(BaseMod.MODID, "gelspringblock");
    public static final Item ITEM = BlockUtils.createBlockItem(INSTANCE, ModTab.INSTANCE);

    //Constructor
    public GelSpringBlock(Properties properties){
        super(properties);
    }

    //Spring the player into the air
    @Override
    public void stepOn(Level levelIn, BlockPos posIn, BlockState blockStateIn, Entity entityIn) {
        super.stepOn(levelIn, posIn, blockStateIn, entityIn);

        entityIn.setDeltaMovement(0,2,0);
    }

}
