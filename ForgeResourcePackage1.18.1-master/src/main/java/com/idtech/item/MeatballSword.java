package com.idtech.item;
import com.idtech.ModTab;
import com.idtech.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class MeatballSword extends SwordItem {

    //Declaring properties of the item
    private static Properties properties = new Properties().tab(ModTab.INSTANCE);
    //Creating item instance of this item
    public static Item INSTANCE = new MeatballSword(Tiers.WOOD, 2, 1, properties).setRegistryName("meatballsword");

    //Constructor
    public MeatballSword(Tiers tier, int speed, float damage, Properties properties) {
        super(tier, speed, damage, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level levelIn, Player playerIn, InteractionHand handIn) {
        BlockPos location = Utils.getBlockAtCursor(playerIn, 200.0d, true);
        int size = 6;
        EntityType animals[] = new EntityType[size];
        animals[0] = EntityType.CHICKEN;
        animals[1] = EntityType.COW;
        animals[2] = EntityType.PIG;
        animals[3] = EntityType.SHEEP;
        animals[4] = EntityType.RABBIT;
        animals[5] = EntityType.HOGLIN;

        int rand = levelIn.random.nextInt(6);

        if(location != null) {
            Entity newAnimal = animals[rand].create(levelIn);
            Utils.spawnEntity(levelIn, newAnimal, location);
            return InteractionResultHolder.success(playerIn.getItemInHand(handIn));
        }

        return InteractionResultHolder.fail(playerIn.getItemInHand(handIn));


    }
}