package baguchan.earthmobsmod.mixin;

import baguchan.earthmobsmod.api.IOnMud;
import baguchan.earthmobsmod.registry.ModFluidTypes;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.extensions.IForgeEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin implements IOnMud, IForgeEntity {

	private boolean wasTouchingMud;

	@Inject(method = "updateInWaterStateAndDoWaterCurrentPushing", at = @At("TAIL"))
	void updateInWaterStateAndDoWaterCurrentPushing(CallbackInfo callbackInfo) {
		if (this.isInFluidType(ModFluidTypes.MUD.get())) {
			this.wasTouchingMud = true;
		} else {
			this.wasTouchingMud = false;
		}

	}

	@Shadow
	public void resetFallDistance() {
	}

	@Shadow
	public void clearFire() {
	}

	@Shadow
	public boolean updateFluidHeightAndDoFluidPushing(TagKey<Fluid> p_204032_, double p_204033_) {
		return false;
	}

	@Override
	public boolean isOnMud() {
		return wasTouchingMud;
	}
}
