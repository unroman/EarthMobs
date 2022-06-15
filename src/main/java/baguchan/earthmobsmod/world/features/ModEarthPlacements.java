package baguchan.earthmobsmod.world.features;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

public class ModEarthPlacements {
	public static final Holder<PlacedFeature> LAKE_MUD_SURFACE = PlacementUtils.register(EarthMobsMod.MODID + ":lake_mud", ModEarthFeatures.MUD_LAKE, RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

	public static void init() {

	}
}
