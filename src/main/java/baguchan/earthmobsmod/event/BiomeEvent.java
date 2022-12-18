package baguchan.earthmobsmod.event;

import baguchan.earthmobsmod.EarthMobsConfig;
import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
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

			if (EarthMobsConfig.COMMON.cluckshroomSpawnRate.get() > 0) {
				if (event.getName().toString().contains("minecraft:mushroom_fields") || event.getName().toString().contains("minecraft:mushroom_field_shore")) {
					event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntities.CLUCK_SHROOM.get(), EarthMobsConfig.COMMON.cluckshroomSpawnRate.get(), 2, 3));
				}
			}

			if (EarthMobsConfig.COMMON.woolyCowSpawnRate.get() > 0) {
				if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OVERWORLD) && (
						BiomeDictionary.hasType(biome, BiomeDictionary.Type.SNOWY)) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.RIVER)) {
					event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntities.WOOLY_COW.get(), EarthMobsConfig.COMMON.woolyCowSpawnRate.get(), 3, 6));
				}
			}

			if (EarthMobsConfig.COMMON.fancyChickenSpawnRate.get() > 0) {
				if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OVERWORLD) &&
						BiomeDictionary.hasType(biome, BiomeDictionary.Type.PLAINS) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.RIVER)) {
					event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntities.FANCY_CHICKEN.get(), EarthMobsConfig.COMMON.fancyChickenSpawnRate.get(), 3, 6));
				}
			}

			if (EarthMobsConfig.COMMON.duckSpawnRate.get() > 0) {
				if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OVERWORLD) &&
						BiomeDictionary.hasType(biome, BiomeDictionary.Type.PLAINS) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.RIVER)) {
					event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntities.DUCK.get(), EarthMobsConfig.COMMON.duckSpawnRate.get(), 3, 6));
				}
			}

			if (EarthMobsConfig.COMMON.umbraCowSpawnRate.get() > 0) {
				if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OVERWORLD) && (
						BiomeDictionary.hasType(biome, BiomeDictionary.Type.SNOWY)) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.RIVER)) {
					event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntities.UMBRA_COW.get(), EarthMobsConfig.COMMON.umbraCowSpawnRate.get(), 3, 6));
				}
			}

			if (EarthMobsConfig.COMMON.hornedSheepSpawnRate.get() > 0) {
				if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OVERWORLD) &&
						BiomeDictionary.hasType(biome, BiomeDictionary.Type.MOUNTAIN) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.RIVER)) {
					event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntities.HORNED_SHEEP.get(), EarthMobsConfig.COMMON.hornedSheepSpawnRate.get(), 3, 6));
				}
			}

			if (EarthMobsConfig.COMMON.hyperRabbitSpawnRate.get() > 0) {
				if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OVERWORLD) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.RIVER) && (BiomeDictionary.hasType(biome, BiomeDictionary.Type.FOREST) || BiomeDictionary.hasType(biome, BiomeDictionary.Type.PLAINS))) {
					event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntities.HYPER_RABBIT.get(), EarthMobsConfig.COMMON.hyperRabbitSpawnRate.get(), 3, 4));
				}
			}

			if (EarthMobsConfig.COMMON.boneSpiderSpawnRate.get() > 0) {
				if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OVERWORLD) && (
						BiomeDictionary.hasType(biome, BiomeDictionary.Type.SPOOKY)) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.RIVER)) {
					event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntities.BONE_SPIDER.get(), EarthMobsConfig.COMMON.boneSpiderSpawnRate.get(), 1, 1));
				}
			}

			if (EarthMobsConfig.COMMON.boulderingZombieSpawnRate.get() > 0) {
				if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OVERWORLD) && BiomeDictionary.hasType(biome, BiomeDictionary.Type.MOUNTAIN) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.RIVER) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.VOID)) {
					event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntities.BOULDERING_ZOMBIE.get(), EarthMobsConfig.COMMON.boulderingZombieSpawnRate.get(), 4, 4));
				}
			}

			if (EarthMobsConfig.COMMON.lobberZombieSpawnRate.get() > 0) {
				if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OVERWORLD) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.RIVER) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.VOID)) {
					event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntities.LOBBER_ZOMBIE.get(), EarthMobsConfig.COMMON.lobberZombieSpawnRate.get(), 2, 4));
				}
			}

			if (EarthMobsConfig.COMMON.boulderingZombieSpawnRate.get() > 0) {
				if (biome.equals("minecraft:dripstone_caves")) {
					event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntities.BOULDERING_DROWNED.get(), EarthMobsConfig.COMMON.boulderingZombieSpawnRate.get(), 4, 4));
				}
			}

			if (EarthMobsConfig.COMMON.lobberZombieSpawnRate.get() > 0) {
				if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OVERWORLD) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.RIVER) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.VOID)) {
					event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntities.LOBBER_ZOMBIE.get(), EarthMobsConfig.COMMON.lobberZombieSpawnRate.get(), 2, 4));
				}
			}

			if (EarthMobsConfig.COMMON.lobberZombieSpawnRate.get() > 0) {
				if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OVERWORLD) && BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.VOID)) {
					event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntities.LOBBER_DROWNED.get(), EarthMobsConfig.COMMON.lobberZombieSpawnRate.get(), 2, 4));
				}
			}

			if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OVERWORLD) && BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.VOID)) {
				if (EarthMobsConfig.COMMON.tropicalSlimeSpawnRate.get() > 0) {
					event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntities.TROPICAL_SLIME.get(), EarthMobsConfig.COMMON.tropicalSlimeSpawnRate.get(), 1, 2));
				}
			}

			if (Biomes.FLOWER_FOREST.equals(biome)) {
				if (EarthMobsConfig.COMMON.moobloomSpawnRate.get() > 0) {
					event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntities.MOOBLOOM.get(), EarthMobsConfig.COMMON.moobloomSpawnRate.get(), 3, 4));
				}
				if (EarthMobsConfig.COMMON.moolipSpawnRate.get() > 0) {
					event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntities.MOOLIP.get(), EarthMobsConfig.COMMON.moolipSpawnRate.get(), 3, 4));
				}
			}

			if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OVERWORLD) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.RIVER) && BiomeDictionary.hasType(biome, BiomeDictionary.Type.FOREST)) {
				if (EarthMobsConfig.COMMON.skeletonWolfOverWorldSpawnRate.get() > 0) {
					event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntities.SKELETON_WOLF.get(), EarthMobsConfig.COMMON.skeletonWolfOverWorldSpawnRate.get(), 3, 4));
				}
			}

			if (EarthMobsConfig.COMMON.skeletonWolfNetherSpawnRate.get() > 0) {
				if (Biomes.SOUL_SAND_VALLEY.equals(biome)) {
					event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntities.SKELETON_WOLF.get(), EarthMobsConfig.COMMON.skeletonWolfNetherSpawnRate.get(), 1, 2));
					event.getSpawns().addMobCharge(ModEntities.SKELETON_WOLF.get(), 0.7, 0.15F);
				}
			}

			if (EarthMobsConfig.COMMON.witherSkeletonWolfNetherSpawnRate.get() > 0) {
				if (Biomes.SOUL_SAND_VALLEY.equals(biome)) {
					event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntities.WITHER_SKELETON_WOLF.get(), EarthMobsConfig.COMMON.witherSkeletonWolfNetherSpawnRate.get(), 1, 2));
					event.getSpawns().addMobCharge(ModEntities.WITHER_SKELETON_WOLF.get(), 0.7, 0.15F);
				}
			}

			if (EarthMobsConfig.COMMON.jumboRabbitSpawnRate.get() > 0) {
				if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OVERWORLD) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.RIVER) && (BiomeDictionary.hasType(biome, BiomeDictionary.Type.PLAINS) || BiomeDictionary.hasType(biome, BiomeDictionary.Type.FOREST) || Biomes.DESERT.equals(biome))) {
					event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntities.JUMBO_RABBIT.get(), EarthMobsConfig.COMMON.jumboRabbitSpawnRate.get(), 3, 4));
				}
			}
		}
	}
}
