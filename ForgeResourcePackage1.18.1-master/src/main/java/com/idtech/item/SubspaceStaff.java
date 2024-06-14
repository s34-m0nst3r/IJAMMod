package com.idtech.item;
import com.idtech.ModTab;
import com.idtech.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SubspaceStaff extends Item{

    //Declaring properties of the item
    private static Properties properties = new Item.Properties().tab(ModTab.INSTANCE);
    //Creating item instance of this item
    public static Item INSTANCE = new SubspaceStaff(properties).setRegistryName("subspacestaff");

    //Constructor
    public SubspaceStaff(Properties properties) {
        super(properties);
    }

    //Creating a method to run when using the item
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {
        //Get what block the player is looking at
        BlockPos blockPos = Utils.getBlockAtCursor(playerIn, 1000.0d, true);
        if(blockPos != null){
            //Teleport the player to that block
            playerIn.teleportTo(blockPos.getX(), blockPos.getY(), blockPos.getZ());
            //Prevent fall damage
            playerIn.fallDistance = 0.0F;
        }

        ItemStack itemstack = playerIn.getItemInHand(handIn);
        return InteractionResultHolder.pass(itemstack);

    }
}
