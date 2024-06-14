package com.idtech.block;
import com.idtech.BaseMod;
import com.idtech.ModTab;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import javax.annotation.Nullable;

public class IridiumOreBlock extends Block{

    private static Properties properties = Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(2.0f).destroyTime(1f);

    //static instances for registration
    public static final Block INSTANCE = new IridiumOreBlock(properties).setRegistryName(BaseMod.MODID, "iridiumoreblock");
    public static final Item ITEM = BlockUtils.createBlockItem(INSTANCE, ModTab.INSTANCE);

    //Constructor
    public IridiumOreBlock(Properties properties){
        super(properties);

    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState blockState, @Nullable BlockEntity blockEntity, ItemStack stack) {
        super.playerDestroy(level, player, pos, blockState, blockEntity, stack);
        this.popExperience((ServerLevel)level, pos, 10);
    }


}
