package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.fluid.MudFluid;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;

public class ModFluidProperties {
	public static final MudFluid.Properties MUD_PROPERTIES = new MudFluid.Properties(ModFluids.MUD, ModFluids.MUD_FLOW, FluidAttributes.builder(new ResourceLocation(EarthMobsMod.MODID, "block/mud"), new ResourceLocation(EarthMobsMod.MODID, "block/flow_mud"))).bucket(ModItems.MUD_BUCKET).block(ModBlocks.MUD);

}
