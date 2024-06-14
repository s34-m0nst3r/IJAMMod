package com.idtech.item;


import com.idtech.ModTab;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


import com.idtech.entity.projectiles.FarreachBlasterEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;




public class FarreachBlaster extends Item {


    //Declaring properties of the item
    private static Properties properties = new Item.Properties().tab(ModTab.INSTANCE);
    //Creating item instance of this item
    public static Item INSTANCE = new FarreachBlaster(properties).setRegistryName("farreachblaster");


    //Constructor
    public FarreachBlaster(Properties properties) {
        super(properties);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level levelIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        levelIn.playSound((Player)null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (levelIn.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!levelIn.isClientSide) {
            FarreachBlasterEntity projectile = new FarreachBlasterEntity(levelIn, playerIn);
            projectile.setItem(itemstack);
            projectile.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0F, 1.5F, 1.0F);
            levelIn.addFreshEntity(projectile);
        }




        if (!playerIn.getAbilities().instabuild) {
            //Removes the item from the inventory
            itemstack.shrink(1);
        }




        return InteractionResultHolder.sidedSuccess(itemstack, levelIn.isClientSide());


    }
}