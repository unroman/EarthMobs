package baguchan.earthmobsmod.client.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BoulderingZombieModel<T extends Zombie> extends ZombieModel<T> {
	public BoulderingZombieModel(ModelPart p_170534_) {
		super(p_170534_);
	}

	public static LayerDefinition createBodyLayer(CubeDeformation p_170536_) {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(p_170536_, 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-4.0F, -2.0F, -2.0F, 5.0F, 12.0F, 4.0F, p_170536_), PartPose.offset(-5.0F, 2.0F + 0.0F, 0.0F));
		partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -2.0F, 5.0F, 12.0F, 4.0F, p_170536_), PartPose.offset(5.0F, 2.0F + 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		if (entity.onClimbable()) {
			this.leftArm.xRot -= Mth.sin(0.25F * ageInTicks) * 0.225F;
			this.rightArm.xRot += Mth.sin(0.25F * ageInTicks) * 0.225F;
			this.leftLeg.xRot = -0.4F - Mth.sin(0.25F * ageInTicks) * 0.225F;
			this.rightLeg.xRot = -0.4F + Mth.sin(0.25F * ageInTicks) * 0.225F;
		}
	}
}
