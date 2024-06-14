package com.idtech.entity;

import com.idtech.entity.projectiles.FarreachBlasterEntity;
import com.idtech.entity.projectiles.FarreachBlasterRenderer;
import com.idtech.entity.projectiles.IridiumShurikenEntity;
import com.idtech.entity.projectiles.IridiumShurikenRenderer;
import com.idtech.entity.projectiles.RadBallEntity;
import com.idtech.entity.projectiles.PortalEntity;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EntityMod {

    @SubscribeEvent
    public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event){
        event.getRegistry().register(IridiumShurikenEntity.TYPE);
        event.getRegistry().register(RadBallEntity.TYPE);
        event.getRegistry().register(ScorchEntity.TYPE);
        event.getRegistry().register(FarreachBlasterEntity.TYPE);
        event.getRegistry().register(PortalEntity.TYPE);
        event.getRegistry().register(MadCap.TYPE);
    }
    @SubscribeEvent
    public static void registerEntityEggs(final RegistryEvent.Register<Item> event) {
        event.getRegistry().register(ScorchEntity.EGG);
        event.getRegistry().register(MadCap.EGG);
    }
    @SubscribeEvent
    public static void entityRenderers(final EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer((EntityType<? extends IridiumShurikenEntity>) IridiumShurikenEntity.TYPE, IridiumShurikenRenderer::new);
        event.registerEntityRenderer((EntityType<? extends FarreachBlasterEntity>) FarreachBlasterEntity.TYPE, FarreachBlasterRenderer::new);
        event.registerEntityRenderer(RadBallEntity.TYPE, (m) -> { return new ThrownItemRenderer<>(m, 1.0f, true);});


        event.registerEntityRenderer(MadCap.TYPE, MadCapRendererFactory.INSTANCE);
        event.registerEntityRenderer(ScorchEntity.TYPE, ScorchRendererFactory.INSTANCE);
    }

    // this is different than in 1.16 but everything else is the same
    // I do think this makes more sense than the other way but alas change is usually hard.
    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(ScorchEntity.TYPE, ScorchEntity.createAttributes().build());
        event.put(MadCap.TYPE, MadCap.createAttributes().build());
    }

}
