package baguchan.earthmobsmod.world.features;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.registry.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class ModEarthFeatures {
	public static final Holder<ConfiguredFeature<LakeFeature.Configuration, ?>> MUD_LAKE = FeatureUtils.register(EarthMobsMod.MODID + ":mud_spring", Feature.LAKE, new LakeFeature.Configuration(BlockStateProvider.simple(ModBlocks.MUD.get().defaultBlockState()), BlockStateProvider.simple(Blocks.MUD.defaultBlockState())));

	public static void init() {

	}

}