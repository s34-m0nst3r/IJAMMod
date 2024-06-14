package com.idtech.item;

import com.idtech.ModTab;
import com.idtech.entity.projectiles.FarreachBlasterEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ItemMod {

    //BASIC ITEMS
    public static final Item STRUCTURE_GEL = ItemUtils.buildBasicItem("structuregel", ModTab.INSTANCE);
    public static final Item GEL_ORE = ItemUtils.buildBasicItem("gelore", ModTab.INSTANCE);
    public static final Item RAW_IRIDIUM = ItemUtils.buildBasicItem("rawiridium", ModTab.INSTANCE);
    public static final Item IRIDIUM_INGOT = ItemUtils.buildBasicItem("iridiumingot", ModTab.INSTANCE);
    public static final Item IRIDIUM_CORE = ItemUtils.buildBasicItem("iridiumcore", ModTab.INSTANCE);
    public static final Item FARREACH_EGG = ItemUtils.buildBasicItem("farreachegg", ModTab.INSTANCE);

    //FOODS
    public static FoodProperties radiationpie = (new FoodProperties.Builder().nutrition(5).saturationMod(1.4f).effect(new MobEffectInstance(MobEffects.POISON, 500, 0), 0.7f).build());
    public static Item RADIATION_PIE = ItemUtils.buildFoodItem("radiationpie", radiationpie);
    public static FoodProperties infinitemeatball = (new FoodProperties.Builder().nutrition(10).saturationMod(3f).effect(new MobEffectInstance(MobEffects.ABSORPTION, 1000, 1), 0.3f).build());
    public static Item INFINITE_MEATBALL = ItemUtils.buildFoodItem("infinitemeatball", infinitemeatball);

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {

        //BASIC ITEMS
        event.getRegistry().register(STRUCTURE_GEL);
        event.getRegistry().register(GEL_ORE);
        event.getRegistry().register(RAW_IRIDIUM);
        event.getRegistry().register(IRIDIUM_INGOT);
        event.getRegistry().register(IRIDIUM_CORE);
        //event.getRegistry().register(FARREACH_EGG);

        // ITEMS
        event.getRegistry().register(SubspaceStaff.INSTANCE);
        event.getRegistry().register(LightningShaker.INSTANCE);
        event.getRegistry().register(MeatballSword.INSTANCE);

        // TOOLS
        event.getRegistry().register(IridiumSword.INSTANCE);
        event.getRegistry().register(IridiumPickaxe.INSTANCE);
        event.getRegistry().register(IridiumAxe.INSTANCE);
        event.getRegistry().register(IridiumShovel.INSTANCE);
        event.getRegistry().register(IridiumHoe.INSTANCE);
        event.getRegistry().register(Mace.INSTANCE);


        // FOOD
        event.getRegistry().register(RADIATION_PIE);
        event.getRegistry().register(INFINITE_MEATBALL);

        // ARMOR
        event.getRegistry().register(IridiumArmor.HELM);
        event.getRegistry().register(IridiumArmor.CHEST);
        event.getRegistry().register(IridiumArmor.LEGS);
        event.getRegistry().register(IridiumArmor.FEET);

        //PROJECTILES
        event.getRegistry().register(BombArrowItem.INSTANCE);
        event.getRegistry().register(IridiumShuriken.INSTANCE);
        event.getRegistry().register(RadBall.INSTANCE);
        //event.getRegistry().register(FarreachBlaster.INSTANCE);

    }
}
