package com.besson.tutorialmod.entity.client;

import com.besson.tutorialmod.TutorialMod;
import com.besson.tutorialmod.entity.custom.TigerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class TigerRenderer extends MobEntityRenderer<TigerEntity, TigerModel<TigerEntity>> {
    public static final Identifier TEXTURE = Identifier.of(TutorialMod.MOD_ID, "textures/entity/tiger.png");
    public TigerRenderer(EntityRendererFactory.Context context) {
        super(context, new TigerModel<>(context.getPart(ModModelLayers.TIGER)), 0.5f);
    }

    @Override
    public Identifier getTexture(TigerEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(TigerEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if (livingEntity.isBaby()) {
            matrixStack.scale(0.5F, 0.5F, 0.5F);
        } else {
            matrixStack.scale(1.0F, 1.0F, 1.0F);
        }
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
