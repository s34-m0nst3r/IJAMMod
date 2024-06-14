package com.idtech.item;
import com.idtech.ModTab;
import com.idtech.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class LightningShaker extends Item{

    //Declaring properties of the item
    private static Properties properties = new Item.Properties().tab(ModTab.INSTANCE);
    //Creating item instance of this item
    public static Item INSTANCE = new LightningShaker(properties).setRegistryName("lightningshaker");

    //Constructor
    public LightningShaker(Properties properties) {
        super(properties);
    }

    //Creating a method to run when using the item
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {
        //Get what block the player is looking at
        BlockPos blockPos = Utils.getBlockAtCursor(playerIn, 200.0d, true);
        if(blockPos != null){
            Utils.createExplosion(level, blockPos, 0.5f);
            Utils.strikeLightning(level, blockPos);
            spawnDragonBreathProjectile(level, playerIn, blockPos);
        }

        ItemStack itemstack = playerIn.getItemInHand(handIn);
        return InteractionResultHolder.pass(itemstack);
    }

    private void spawnDragonBreathProjectile(Level level, Player player, BlockPos targetPos) {
        // Calculate direction towards the target position
        double d0 = targetPos.getX() - player.getX();
        double d1 = targetPos.getY() - (player.getY() + player.getEyeHeight());
        double d2 = targetPos.getZ() - player.getZ();
        double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
        double motionX = 0.5D * (d0 / d3); // Adjust the speed of dragon breath
        double motionY = 0.5D * (d1 / d3);
        double motionZ = 0.5D * (d2 / d3);

        // Spawn dragon breath projectiles towards the target position
        for (int i = 0; i < 5; ++i) {
            double posX = player.getX() + motionX * i;
            double posY = player.getY() + player.getEyeHeight() + motionY * i;
            double posZ = player.getZ() + motionZ * i;
            // Spawn dragon breath projectile (particle effect)
            level.addParticle(ParticleTypes.DRAGON_BREATH, posX, posY, posZ, motionX, motionY, motionZ);
        }

        // Spawn dragon breath particles around the explosion point
        for (int i = 0; i < 20; i++) { // Adjust the number of particles as needed
            double posX = targetPos.getX() + (Math.random() - 0.5) * 2; // Randomize position around the explosion point
            double posY = targetPos.getY() + (Math.random() - 0.5) * 2;
            double posZ = targetPos.getZ() + (Math.random() - 0.5) * 2;
            level.addParticle(ParticleTypes.DRAGON_BREATH, posX, posY, posZ, motionX, motionY, motionZ);
        }

        // Create lingering effects at the target position
        level.addParticle(ParticleTypes.DRAGON_BREATH, targetPos.getX() + 0.5, targetPos.getY() + 0.5, targetPos.getZ() + 0.5, 0, 0, 0);
    }
}
