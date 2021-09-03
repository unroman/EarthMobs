package baguchan.earthmobsmod.event;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EarthMobsMod.MODID)
public class BiomeEvent {
	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void loadingBiome(BiomeLoadingEvent event) {
		if (event.getName() != null) {
			ResourceKey<Biome> biome = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());

			if (event.getName().toString().contains("minecraft:mushroom_fields") || event.getName().toString().contains("minecraft:mushroom_field_shore")) {
				event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntities.CLUCK_SHROOM, 5, 2, 3));
			}

			if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OVERWORLD) && (
					BiomeDictionary.hasType(biome, BiomeDictionary.Type.SNOWY)) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.RIVER)) {
				event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntities.WOOLY_COW, 10, 3, 6));
			}

			if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OVERWORLD) && (
					BiomeDictionary.hasType(biome, BiomeDictionary.Type.SPOOKY)) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.RIVER)) {
				event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntities.BONE_SPIDER, 50, 2, 3));
			}
		}
	}
}
