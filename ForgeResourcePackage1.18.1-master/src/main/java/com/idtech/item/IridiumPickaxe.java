package com.idtech.item;
import com.idtech.BaseMod;
import com.idtech.ModTab;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;

public class IridiumPickaxe extends PickaxeItem{
    //PROPERTIES
    private static Properties properties = new Item.Properties().tab(ModTab.INSTANCE);

    //CONSTRUCTOR
    public IridiumPickaxe(Tier tier, int attackDamageIn, float attackSpeedIn, Properties properties){
        super(tier, attackDamageIn, attackSpeedIn, properties);
    }

    //TIER
    public static Tier tier = new ForgeTier(2, 895, 7.0F, 2.5F, 18, null, ()->{return Ingredient.of(ItemMod.IRIDIUM_INGOT);});

    //INSTANCE
    public static Item INSTANCE = new IridiumPickaxe(tier, 1, -2.8f, new Properties().tab(ModTab.INSTANCE)).setRegistryName(BaseMod.MODID,"iridiumpickaxe");


}
