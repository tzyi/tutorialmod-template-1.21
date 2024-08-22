// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.besson.tutorialmod.entity.client;

import com.besson.tutorialmod.entity.animation.TigerAnimation;
import com.besson.tutorialmod.entity.custom.TigerEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class TigerModel<T extends TigerEntity> extends SinglePartEntityModel<T> {
	private final ModelPart tiger;
	private final ModelPart head;
	public TigerModel(ModelPart root) {
		this.tiger = root.getChild("tiger");
		this.head = tiger.getChild("head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData tiger = modelPartData.addChild("tiger", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData right_back_leg = tiger.addChild("right_back_leg", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, -4.0F, 4.0F));

		ModelPartData tail = tiger.addChild("tail", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -7.2914F, 4.9639F));

		ModelPartData cube_r1 = tail.addChild("cube_r1", ModelPartBuilder.create().uv(8, 0).cuboid(-0.5F, -4.0F, -3.9F, 1.0F, 6.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.2343F, 3.9312F, -0.7418F, 0.0F, 0.0F));

		ModelPartData left_front_leg = tiger.addChild("left_front_leg", ModelPartBuilder.create().uv(22, 21).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, -4.0F, -4.0F));

		ModelPartData body = tiger.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -2.5F, -6.0F, 6.0F, 5.0F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -5.5F, 0.0F));

		ModelPartData left_back_leg = tiger.addChild("left_back_leg", ModelPartBuilder.create().uv(16, 17).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, -4.0F, 4.0F));

		ModelPartData right_front_leg = tiger.addChild("right_front_leg", ModelPartBuilder.create().uv(0, 6).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, -4.0F, -4.0F));

		ModelPartData head = tiger.addChild("head", ModelPartBuilder.create().uv(0, 17).cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -9.0F, -7.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public ModelPart getPart() {
		return this.tiger;
	}

	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(headYaw, headPitch);

		this.animateMovement(TigerAnimation.WALK, limbAngle, limbDistance, 2f, 2.5f);
		this.updateAnimation(TigerEntity.idleAnimation, TigerAnimation.IDLE, animationProgress, 1f);
		this.updateAnimation(TigerEntity.attackAnimation, TigerAnimation.ATTACK, animationProgress, 1f);
	}

	private void setHeadAngles(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -30.0f, 30.0f);
		headPitch = MathHelper.clamp(headPitch, -25.0f, 45.0f);
		this.head.yaw = headYaw * 0.017453292f;
		this.head.pitch = headPitch * 0.017453292f;
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		tiger.render(matrices, vertices, light, overlay, color);
	}
}