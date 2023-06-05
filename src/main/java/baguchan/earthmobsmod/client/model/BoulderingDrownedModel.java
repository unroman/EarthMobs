package baguchan.earthmobsmod.client.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BoulderingDrownedModel<T extends Zombie> extends BoulderingZombieModel<T> {
	public BoulderingDrownedModel(ModelPart p_170534_) {
		super(p_170534_);
	}

	public void setupAnim(T p_102526_, float p_102527_, float p_102528_, float p_102529_, float p_102530_, float p_102531_) {
		super.setupAnim(p_102526_, p_102527_, p_102528_, p_102529_, p_102530_, p_102531_);
		if (this.leftArmPose == HumanoidModel.ArmPose.THROW_SPEAR) {
			this.leftArm.xRot = this.leftArm.xRot * 0.5F - (float) Math.PI;
			this.leftArm.yRot = 0.0F;
		}

		if (this.rightArmPose == HumanoidModel.ArmPose.THROW_SPEAR) {
			this.rightArm.xRot = this.rightArm.xRot * 0.5F - (float) Math.PI;
			this.rightArm.yRot = 0.0F;
		}

		if (this.swimAmount > 0.0F) {
			this.rightArm.xRot = this.rotlerpRad(this.swimAmount, this.rightArm.xRot, -2.5132742F) + this.swimAmount * 0.35F * Mth.sin(0.1F * p_102529_);
			this.leftArm.xRot = this.rotlerpRad(this.swimAmount, this.leftArm.xRot, -2.5132742F) - this.swimAmount * 0.35F * Mth.sin(0.1F * p_102529_);
			this.rightArm.zRot = this.rotlerpRad(this.swimAmount, this.rightArm.zRot, -0.15F);
			this.leftArm.zRot = this.rotlerpRad(this.swimAmount, this.leftArm.zRot, 0.15F);
			this.leftLeg.xRot -= this.swimAmount * 0.55F * Mth.sin(0.1F * p_102529_);
			this.rightLeg.xRot += this.swimAmount * 0.55F * Mth.sin(0.1F * p_102529_);
			this.head.xRot = 0.0F;
		}

	}
}
