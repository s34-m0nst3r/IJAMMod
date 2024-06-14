package com.idtech.entity;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.monster.Zombie;


public class ScorchRendererFactory implements EntityRendererProvider<Zombie> {

    public static ScorchRendererFactory INSTANCE = new ScorchRendererFactory();

    @Override
    public EntityRenderer<Zombie> create(Context manager) {
        return new ScorchRenderer(manager);
    }
}