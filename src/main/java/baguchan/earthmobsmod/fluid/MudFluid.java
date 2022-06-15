package baguchan.earthmobsmod.fluid;

import baguchan.earthmobsmod.registry.ModFluidTypes;
import baguchan.earthmobsmod.registry.ModFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public abstract class MudFluid extends ForgeFlowingFluid {

	protected MudFluid(Properties properties) {
		super(properties);
	}

	@Override
	public FluidType getFluidType() {
		return ModFluidTypes.MUD.get();
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


	private void fizz(LevelAccessor p_76213_, BlockPos p_76214_) {
		p_76213_.levelEvent(1501, p_76214_, 0);
	}

	private void fizzWithWater(LevelAccessor p_76213_, BlockPos p_76214_) {
		p_76213_.levelEvent(1501, p_76214_, 0);
		p_76213_.levelEvent(2001, p_76214_, Block.getId(Blocks.MUD.defaultBlockState()));
	}

	protected void spreadTo(LevelAccessor p_76220_, BlockPos p_76221_, BlockState p_76222_, Direction p_76223_, FluidState p_76224_) {
		FluidState fluidstate = p_76220_.getFluidState(p_76221_);
		if (fluidstate.is(FluidTags.WATER)) {
			if (p_76222_.getBlock() instanceof LiquidBlock) {
				p_76220_.setBlock(p_76221_, Blocks.MUD.defaultBlockState(), 3);
			}

			this.fizzWithWater(p_76220_, p_76221_);
			return;
		}

		if (fluidstate.is(FluidTags.LAVA)) {
			if (p_76222_.getBlock() instanceof LiquidBlock) {
				p_76220_.setBlock(p_76221_, Blocks.DIRT.defaultBlockState(), 3);
			}

			this.fizz(p_76220_, p_76221_);
			return;
		}

		super.spreadTo(p_76220_, p_76221_, p_76222_, p_76223_, p_76224_);
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