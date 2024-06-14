package com.idtech.entity;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class MadCapRendererFactory implements EntityRendererProvider<MadCap> {

    public static MadCapRendererFactory INSTANCE = new MadCapRendererFactory();

    @Override
    public EntityRenderer<MadCap> create(Context manager) {
        return new MadCapRenderer(manager);
    }
}