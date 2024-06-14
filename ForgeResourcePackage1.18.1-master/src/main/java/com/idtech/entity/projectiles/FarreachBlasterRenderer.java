package com.idtech.entity.projectiles;


import com.idtech.BaseMod;
import com.idtech.item.FarreachBlaster;
import com.idtech.item.ItemMod;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;


public class FarreachBlasterRenderer extends EntityRenderer<FarreachBlasterEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("examplemod", "textures/entity/iridiumshuriken.png");
    private final ItemRenderer itemRenderer;


    public FarreachBlasterRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.itemRenderer = context.getItemRenderer();
    }


    @Override
    public void render(FarreachBlasterEntity entity, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int packedLight) {
        //matrixStack.pushPose();
        // Apply rotations to ensure proper orientation
        // Right now this is set up for my shurikens, but this can be modified!
        //matrixStack.mulPose(Vector3f.YP.rotationDegrees(entity.getYRot()+ 90.0F));
        //matrixStack.mulPose(Vector3f.XP.rotationDegrees(entity.getXRot() + 90.0F));


        //Change this to be the item you want to be thrown!
        ItemStack itemStack = new ItemStack(ItemMod.FARREACH_EGG);


        itemRenderer.renderStatic(itemStack, ItemTransforms.TransformType.GROUND, packedLight, OverlayTexture.NO_OVERLAY, matrixStack, buffer, entity.getId());


        //matrixStack.popPose();
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }


    @Override
    public ResourceLocation getTextureLocation(FarreachBlasterEntity entity) {
        return TEXTURE;
    }
}
