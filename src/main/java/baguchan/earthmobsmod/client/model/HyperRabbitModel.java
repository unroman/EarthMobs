package baguchan.earthmobsmod.client.model;// Made with Blockbench 4.1.1
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


import baguchan.earthmobsmod.entity.HyperRabbit;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class HyperRabbitModel<T extends HyperRabbit> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart rearFootLeft;
	private final ModelPart rearFootRight;
	private final ModelPart haunchLeft;
	private final ModelPart haunchRight;
	private final ModelPart body;
	private final ModelPart frontLegLeft;
	private final ModelPart frontLegRight;
	private final ModelPart head;
	private final ModelPart earRight;
	private final ModelPart earLeft;
	private final ModelPart tail;
	private final ModelPart nose;
	private float jumpRotation;
	private static final float NEW_SCALE = 0.6F;

	public HyperRabbitModel(ModelPart root) {
		this.rearFootLeft = root.getChild("rearFootLeft");
		this.rearFootRight = root.getChild("rearFootRight");
		this.haunchLeft = root.getChild("haunchLeft");
		this.haunchRight = root.getChild("haunchRight");
		this.body = root.getChild("body");
		this.frontLegLeft = root.getChild("frontLegLeft");
		this.frontLegRight = root.getChild("frontLegRight");
		this.head = root.getChild("head");
		this.earRight = root.getChild("earRight");
		this.earLeft = root.getChild("earLeft");
		this.tail = root.getChild("tail");
		this.nose = root.getChild("nose");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition rearFootLeft = partdefinition.addOrReplaceChild("rearFootLeft", CubeListBuilder.create().texOffs(18, 14).addBox(-1.0F, 5.5F, -3.7F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 17.5F, 3.7F));

		PartDefinition rearFootRight = partdefinition.addOrReplaceChild("rearFootRight", CubeListBuilder.create().texOffs(0, 14).addBox(-1.0F, 5.5F, -3.7F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 17.5F, 3.7F));

		PartDefinition haunchLeft = partdefinition.addOrReplaceChild("haunchLeft", CubeListBuilder.create().texOffs(0, 33).addBox(-1.0F, -1.0F, 2.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 17.5F, 3.7F, -0.3491F, 0.0F, 0.0F));

		PartDefinition haunchRight = partdefinition.addOrReplaceChild("haunchRight", CubeListBuilder.create().texOffs(16, 22).addBox(-1.0F, -1.0F, 2.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 17.5F, 3.7F, -0.3491F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -2.0F, -10.0F, 5.0F, 4.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(24, 41).addBox(2.0F, -1.0F, -6.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(22, 43).addBox(-4.0F, 1.0F, -6.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 17.0F, 7.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition frontLegLeft = partdefinition.addOrReplaceChild("frontLegLeft", CubeListBuilder.create().texOffs(8, 22).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 17.0F, -1.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition frontLegRight = partdefinition.addOrReplaceChild("frontLegRight", CubeListBuilder.create().texOffs(0, 22).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 17.0F, -1.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(30, 0).addBox(-2.5F, -4.0F, -5.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(22, 41).addBox(1.0F, -5.0F, -2.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(12, 41).addBox(-2.0F, -6.0F, -4.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(6, 41).addBox(-4.0F, -2.0F, -4.0F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(0, 41).addBox(1.0F, -2.0F, -4.0F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 13.0F, -1.0F));

		PartDefinition earRight = partdefinition.addOrReplaceChild("earRight", CubeListBuilder.create().texOffs(20, 33).addBox(-1.0F, -7.0F, 0.0F, 2.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 10.0F, -1.0F, -0.8727F, 0.0F, 0.0F));

		PartDefinition earLeft = partdefinition.addOrReplaceChild("earLeft", CubeListBuilder.create().texOffs(26, 33).addBox(-3.0F, -7.0F, 0.0F, 2.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 10.0F, -1.0F));

		PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(10, 33).addBox(-1.5F, -2.5F, 0.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 14.0F, 5.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition nose = partdefinition.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(18, 41).addBox(-0.5F, -1.5F, -5.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 13.0F, -1.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = ageInTicks - (float) entity.tickCount;
		this.nose.xRot = headPitch * ((float) Math.PI / 180F);
		this.head.xRot = headPitch * ((float) Math.PI / 180F);
		this.nose.yRot = netHeadYaw * ((float) Math.PI / 180F);
		this.earRight.xRot = headPitch * ((float) Math.PI / 180F) + -0.8727F + this.nose.yRot * 0.1F;
		this.earLeft.xRot = headPitch * ((float) Math.PI / 180F) + this.nose.yRot * 0.2F;
		this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);

		this.jumpRotation = Mth.sin(entity.getJumpCompletion(f) * (float) Math.PI);
		this.haunchLeft.xRot = (this.jumpRotation * 50.0F - 21.0F) * ((float) Math.PI / 180F);
		this.haunchRight.xRot = (this.jumpRotation * 50.0F - 21.0F) * ((float) Math.PI / 180F);
		this.rearFootLeft.xRot = this.jumpRotation * 50.0F * ((float) Math.PI / 180F);
		this.rearFootRight.xRot = this.jumpRotation * 50.0F * ((float) Math.PI / 180F);
		this.frontLegLeft.xRot = (this.jumpRotation * -40.0F - 11.0F) * ((float) Math.PI / 180F);
		this.frontLegRight.xRot = (this.jumpRotation * -40.0F - 11.0F) * ((float) Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack p_103555_, VertexConsumer p_103556_, int p_103557_, int p_103558_, float p_103559_, float p_103560_, float p_103561_, float p_103562_) {
		if (this.young) {
			float f = 1.5F;
			p_103555_.pushPose();
			p_103555_.scale(0.56666666F, 0.56666666F, 0.56666666F);
			p_103555_.translate(0.0D, 1.375D, 0.125D);
			ImmutableList.of(this.head, this.earLeft, this.earRight, this.nose).forEach((p_103597_) -> {
				p_103597_.render(p_103555_, p_103556_, p_103557_, p_103558_, p_103559_, p_103560_, p_103561_, p_103562_);
			});
			p_103555_.popPose();
			p_103555_.pushPose();
			p_103555_.scale(0.4F, 0.4F, 0.4F);
			p_103555_.translate(0.0D, 2.25D, 0.0D);
			ImmutableList.of(this.rearFootLeft, this.rearFootRight, this.haunchLeft, this.haunchRight, this.body, this.frontLegLeft, this.frontLegRight, this.tail).forEach((p_103587_) -> {
				p_103587_.render(p_103555_, p_103556_, p_103557_, p_103558_, p_103559_, p_103560_, p_103561_, p_103562_);
			});
			p_103555_.popPose();
		} else {
			p_103555_.pushPose();
			p_103555_.scale(0.6F, 0.6F, 0.6F);
			p_103555_.translate(0.0D, 1.0D, 0.0D);
			ImmutableList.of(this.rearFootLeft, this.rearFootRight, this.haunchLeft, this.haunchRight, this.body, this.frontLegLeft, this.frontLegRight, this.head, this.earRight, this.earLeft, this.tail, this.nose).forEach((p_103572_) -> {
				p_103572_.render(p_103555_, p_103556_, p_103557_, p_103558_, p_103559_, p_103560_, p_103561_, p_103562_);
			});
			p_103555_.popPose();
		}

	}
}