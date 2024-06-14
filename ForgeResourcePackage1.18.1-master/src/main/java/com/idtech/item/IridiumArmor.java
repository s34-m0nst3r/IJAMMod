package com.idtech.item;

import com.idtech.BaseMod;
import com.idtech.ModTab;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;

import javax.annotation.Nullable;

public class IridiumArmor extends ArmorItem {

    private static Properties properties = new Properties().tab(ModTab.INSTANCE);
    private static ArmorMaterial iridiumMaterial = ItemUtils.buildArmorMaterial("iridium", 22, new int[]{3,7,6,3} ,5, SoundEvents.ARMOR_EQUIP_IRON, 1.0f, 0f,"examplemod:iridiumingot");

    //Constructor
    public IridiumArmor(ArmorMaterial material, EquipmentSlot slot, Properties properties) {
        super(material, slot, properties);
    }

    //Creating armor instances
    public static final Item HELM = new IridiumArmor(iridiumMaterial, EquipmentSlot.HEAD, (properties)).setRegistryName(BaseMod.MODID, "iridiumhelm");
    public static final Item CHEST = new IridiumArmor(iridiumMaterial, EquipmentSlot.CHEST, (properties)).setRegistryName(BaseMod.MODID, "iridiumchest");
    public static final Item LEGS = new IridiumArmor(iridiumMaterial, EquipmentSlot.LEGS, (properties)).setRegistryName(BaseMod.MODID, "iridiumlegs");
    public static final Item FEET = new IridiumArmor(iridiumMaterial, EquipmentSlot.FEET, (properties)).setRegistryName(BaseMod.MODID, "iridiumfeet");


    @Nullable @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        if(slot == EquipmentSlot.LEGS){

            return "examplemod:textures/models/armor/iridium_armor_layer_2.png";
        }
        else {
            return "examplemod:textures/models/armor/iridium_armor_layer_1.png";
        }
    }
}
