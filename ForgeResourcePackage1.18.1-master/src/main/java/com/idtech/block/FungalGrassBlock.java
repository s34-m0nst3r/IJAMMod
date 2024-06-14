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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.IPlantable;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import java.util.Random;



import javax.annotation.Nullable;

public class FungalGrassBlock extends Block {

    //NOTE: TO BE MINEABLE WITH THE RIGHT TOOLS THESE ORES NEED TO BE ADDED TO THE TAGS FILE

    private static Properties properties = Properties.of(Material.GRASS).strength(0.1f).destroyTime(0.3f).randomTicks();

    //static instances for registration
    public static final Block INSTANCE = new FungalGrassBlock(properties).setRegistryName(BaseMod.MODID, "fungalgrassblock");
    public static final Item ITEM = BlockUtils.createBlockItem(INSTANCE, ModTab.INSTANCE);

    //Constructor
    public FungalGrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        return true;
    }

    @Override
    public void randomTick (BlockState blockState, ServerLevel serverLevel, BlockPos pos, Random random){
        super.randomTick(blockState, serverLevel, pos, random);
        // Adding a random chance to slow down the duplication process
        //if (random.nextFloat() < 0.1f) {  // 10% chance to duplicate
            BlockPos blockPos = Utils.findNeightborBlock(pos);
            if (blockPos != null) {
                BlockState neighborBlockState = serverLevel.getBlockState(blockPos);

                // Check if the neighboring block is grass
                if (neighborBlockState.is(Blocks.GRASS_BLOCK)) {
                    serverLevel.setBlockAndUpdate(blockPos, this.defaultBlockState());
                }
            }
        //}
    }


}