package baguchan.earthmobsmod.world.biome;

import baguchan.earthmobsmod.registry.ModBiomeModifiers;
import baguchan.earthmobsmod.world.features.ModEarthPlacements;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

public class MudBiomeModifier implements BiomeModifier {
	public static final MudBiomeModifier INSTANCE = new MudBiomeModifier();

	@Override
	public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
		if (phase == Phase.ADD && (biome.is(Biomes.MANGROVE_SWAMP) || biome.is(Tags.Biomes.IS_SWAMP) && biome.is(BiomeTags.IS_OVERWORLD))) {
			builder.getGenerationSettings().addFeature(GenerationStep.Decoration.LAKES, ModEarthPlacements.LAKE_MUD_SURFACE);
		}
	}

	@Override
	public Codec<? extends BiomeModifier> codec() {
		return ModBiomeModifiers.EARTH_ENTITY_MODIFIER_TYPE.get();
	}
}
