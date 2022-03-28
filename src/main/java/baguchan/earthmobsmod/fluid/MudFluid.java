package baguchan.earthmobsmod.fluid;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.registry.ModFluids;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public abstract class MudFluid extends ForgeFlowingFluid {

	protected MudFluid(Properties properties) {
		super(properties);
	}

	protected FluidAttributes createAttributes() {
		return FluidAttributes.builder(new ResourceLocation(EarthMobsMod.MODID, "block/mud"), new ResourceLocation(EarthMobsMod.MODID, "block/flow_mud"))
				.overlay(new ResourceLocation(EarthMobsMod.MODID, "block/mud_overlay"))
				.sound(SoundEvents.BUCKET_FILL, SoundEvents.BUCKET_EMPTY)
				.build(this);
	}

	@Override
	public int getTickDelay(LevelReader level) {
		return 20;
	}

	@Override
	protected boolean isRandomlyTicking() {
		return true;
	}

	public boolean isSame(Fluid p_207187_1_) {
		return p_207187_1_ == ModFluids.MUD.get() || p_207187_1_ == ModFluids.MUD_FLOW.get();
	}

	protected float getExplosionResistance() {
		return 100.0F;
	}


	public int getDropOff(LevelReader p_76252_) {
		return p_76252_.dimensionType().ultraWarm() ? 2 : 3;
	}

	public static class Flowing extends MudFluid {
		public Flowing(Properties properties) {
			super(properties);
			registerDefaultState(getStateDefinition().any().setValue(LEVEL, 7));
		}

		protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
			super.createFluidStateDefinition(builder);
			builder.add(LEVEL);
		}

		public int getAmount(FluidState state) {
			return state.getValue(LEVEL);
		}

		public boolean isSource(FluidState state) {
			return false;
		}
	}

	public static class Source extends MudFluid {
		public Source(Properties properties) {
			super(properties);
		}

		public int getAmount(FluidState state) {
			return 8;
		}

		public boolean isSource(FluidState state) {
			return true;
		}
	}
}