package baguchan.earthmobsmod.world.biome;

import baguchan.earthmobsmod.registry.ModBiomeModifiers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

public record EarthBiomeModifier(HolderSet<Biome> biomes, MobSpawnSettings.SpawnerData spawn,
								 MobCategory category) implements BiomeModifier {
	@Override
	public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
		if (phase == Phase.ADD && this.biomes.contains(biome) && !biome.containsTag(Tags.Biomes.IS_MODIFIED) && (category != MobCategory.MONSTER || !biome.is(Biomes.MUSHROOM_FIELDS))) {
			builder.getMobSpawnSettings().addSpawn(category, spawn);

		}
	}

	public static Codec<EarthBiomeModifier> makeCodec() {
		return RecordCodecBuilder.create(builder -> builder
				.group(Biome.LIST_CODEC.fieldOf("biomes").forGetter(EarthBiomeModifier::biomes),
						MobSpawnSettings.SpawnerData.CODEC.fieldOf("spawn").forGetter(EarthBiomeModifier::spawn),
						MobCategory.CODEC.fieldOf("mob_category").forGetter(EarthBiomeModifier::category))
				.apply(builder, EarthBiomeModifier::new));
	}

	@Override
	public Codec<? extends BiomeModifier> codec() {
		return ModBiomeModifiers.EARTH_ENTITY_MODIFIER_TYPE.get();
	}
}
