package baguchan.earthmobsmod.mixin;

import baguchan.earthmobsmod.api.IOnMud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements IOnMud {
	public LivingEntityMixin(EntityType<?> p_19870_, Level p_19871_) {
		super(p_19870_, p_19871_);
	}

	@Inject(method = "travel", at = @At("HEAD"), cancellable = true)
	public void travel(Vec3 p_21280_, CallbackInfo callbackInfo) {
		double d0 = 0.05D;
		boolean flag = this.getDeltaMovement().y <= 0.0D;
		FluidState fluidstate = this.level.getFluidState(this.blockPosition());
		if (this.isEffectiveAi() || this.isControlledByLocalInstance()) {
			if (this.isOnMud() && this.isAffectedByFluids() && !this.canStandOnFluid(fluidstate)) {
				double d9 = this.getY();
				float f4 = this.isSprinting() ? 0.85F : this.getWaterSlowDown() * 0.95F;
				float f6 = 0.02F;

				this.moveRelative(f6, p_21280_);
				this.move(MoverType.SELF, this.getDeltaMovement());
				Vec3 vec36 = this.getDeltaMovement();

				this.setDeltaMovement(vec36.multiply((double) f4, (double) 0.75F, (double) f4));
				Vec3 vec32 = this.getFluidFallingAdjustedMovement(d0, flag, this.getDeltaMovement());
				this.setDeltaMovement(vec32);

				Vec3 vec34 = this.getDeltaMovement();
				if (this.horizontalCollision && this.isFree(vec34.x, vec34.y + (double) 0.6F - this.getY() + d9, vec34.z)) {
					this.setDeltaMovement(vec34.x, (double) 0.3F, vec34.z);
				}
				callbackInfo.cancel();
			}
		}
	}

	@Shadow
	protected float getWaterSlowDown() {
		return 0;
	}

	@Shadow
	public boolean isEffectiveAi() {
		return false;
	}

	@Shadow
	public Vec3 getFluidFallingAdjustedMovement(double p_20995_, boolean p_20996_, Vec3 p_20997_) {
		return null;
	}

	@Shadow
	public boolean canStandOnFluid(FluidState fluidstate) {
		return false;
	}

	@Shadow
	protected boolean isAffectedByFluids() {
		return false;
	}
}
