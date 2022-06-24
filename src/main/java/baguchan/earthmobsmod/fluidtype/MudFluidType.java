package baguchan.earthmobsmod.fluidtype;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.IFluidTypeRenderProperties;
import net.minecraftforge.fluids.FluidType;

import java.util.function.Consumer;

public class MudFluidType extends FluidType {
	public MudFluidType(FluidType.Properties properties) {
		super(properties);
	}

	@Override
	public boolean move(FluidState state, LivingEntity entity, Vec3 movementVector, double gravity) {
		boolean flag = entity.getDeltaMovement().y <= 0.0D;
		double d8 = entity.getY();

		entity.moveRelative(0.02F, movementVector);
		entity.move(MoverType.SELF, entity.getDeltaMovement());

		if (entity.getFluidTypeHeight(this) <= entity.getFluidJumpThreshold()) {
			entity.setDeltaMovement(entity.getDeltaMovement().multiply(0.75D, (double) 0.8F, 0.75D));
			Vec3 vec33 = entity.getFluidFallingAdjustedMovement(gravity, flag, entity.getDeltaMovement());
			entity.setDeltaMovement(vec33);
		} else {
			entity.setDeltaMovement(entity.getDeltaMovement().scale(0.75D));
		}

		if (!entity.isNoGravity()) {
			entity.setDeltaMovement(entity.getDeltaMovement().add(0.0D, -gravity / 4.0D, 0.0D));
		}

		Vec3 vec34 = entity.getDeltaMovement();
		if (entity.horizontalCollision && entity.isFree(vec34.x, vec34.y + (double) 0.6F - entity.getY() + d8, vec34.z)) {
			entity.setDeltaMovement(vec34.x, (double) 0.3F, vec34.z);
		}

		return true;
	}

	@Override
	public void initializeClient(Consumer<IFluidTypeRenderProperties> consumer) {
		consumer.accept(new IFluidTypeRenderProperties() {
			private static final ResourceLocation TEXTURE_STILL = new ResourceLocation(EarthMobsMod.MODID, "block/mud");
			private static final ResourceLocation TEXTURE_FLOW = new ResourceLocation(EarthMobsMod.MODID, "block/flow_mud");
			private static final ResourceLocation TEXTURE_OVERLAY = new ResourceLocation(EarthMobsMod.MODID, "textures/block/mud.png");

			@Override
			public ResourceLocation getStillTexture() {
				return TEXTURE_STILL;
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return TEXTURE_FLOW;
			}

			@Override
			public ResourceLocation getRenderOverlayTexture(Minecraft mc) {
				return TEXTURE_OVERLAY;
			}
		});
	}
}
