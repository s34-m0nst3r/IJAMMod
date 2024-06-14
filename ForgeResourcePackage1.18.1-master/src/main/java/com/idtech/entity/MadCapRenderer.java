package com.idtech.entity;

import com.idtech.BaseMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MadCapRenderer extends MobRenderer<MadCap, MadCapModel<MadCap>>{
    public MadCapRenderer(EntityRendererProvider.Context provider) {
        super(provider, new MadCapModel<>(provider.bakeLayer(MadCapModel.LAYER_LOCATION)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(MadCap p_114482_) {
        return new ResourceLocation(BaseMod.MODID, "textures/entity/madcap.png");
    }


}
