package baguchan.earthmobsmod.client.model;
// Made with Blockbench 4.0.1
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


import baguchan.earthmobsmod.entity.HornedSheep;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.SheepModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class HornedSheepModel<T extends HornedSheep> extends SheepModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart horn;
	private float headXRot;

	public HornedSheepModel(ModelPart root) {
		super(root);
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.horn = this.head.getChild("horn");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = QuadrupedModel.createBodyMesh(12, CubeDeformation.NONE);
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -4.0F, -6.0F, 6.0F, 6.0F, 8.0F), PartPose.offset(0.0F, 6.0F, -8.0F));
		partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(22, 10).addBox(-4.0F, -10.0F, -7.0F, 8.0F, 16.0F, 6.0F), PartPose.offsetAndRotation(0.0F, 5.0F, 2.0F, ((float) Math.PI / 2F), 0.0F, 0.0F));

		PartDefinition horn = head.addOrReplaceChild("horn", CubeListBuilder.create().texOffs(28, 0).addBox(-2.0F, 3.0F, -4.5F, 7.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(28, 0).addBox(-2.0F, -7.0F, -4.5F, 7.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.head.xRot = this.headXRot;
		this.horn.visible = entity.hasHorn();
	}

	public void prepareMobModel(T p_103687_, float p_103688_, float p_103689_, float p_103690_) {
		super.prepareMobModel(p_103687_, p_103688_, p_103689_, p_103690_);
		this.headXRot = p_103687_.getHeadEatAngleScale(p_103690_);

		if (this.attackTime > 0) {
			this.headXRot = -0.95F * Mth.sin(this.attackTime * (float) Math.PI);
		}
	}
}