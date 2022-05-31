package baguchan.earthmobsmod;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber(modid = EarthMobsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EarthMobsConfig {
	public static final Common COMMON;
	public static final ForgeConfigSpec COMMON_SPEC;

	static {
		Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = specPair.getRight();
		COMMON = specPair.getLeft();
	}

	public static class Common {
		public final ForgeConfigSpec.IntValue woolyCowSpawnRate;
		public final ForgeConfigSpec.IntValue cluckshroomSpawnRate;
		public final ForgeConfigSpec.IntValue hornedSheepSpawnRate;
		public final ForgeConfigSpec.IntValue boneSpiderSpawnRate;
		public final ForgeConfigSpec.IntValue hyperRabbitSpawnRate;
		public final ForgeConfigSpec.IntValue moobloomSpawnRate;
		public final ForgeConfigSpec.IntValue moolipSpawnRate;

		public final ForgeConfigSpec.IntValue boulderingZombieSpawnRate;
		public final ForgeConfigSpec.IntValue lobberZombieSpawnRate;

		public final ForgeConfigSpec.IntValue tropicalSlimeSpawnRate;

		public Common(ForgeConfigSpec.Builder builder) {
			woolyCowSpawnRate = builder
					.translation(EarthMobsMod.MODID + ".config.woolyCowSpawnRate")
					.comment("Changed WoolyCow SpawnRate. [0 ~ 100]")
					.defineInRange("Wooly cow SpawnRate", 10, 0, 100);
			cluckshroomSpawnRate = builder
					.translation(EarthMobsMod.MODID + ".config.cluckshroomSpawnRate")
					.comment("Changed Cluckshroom SpawnRate. [0 ~ 100]")
					.defineInRange("Cluckshroom SpawnRate", 5, 0, 100);
			hornedSheepSpawnRate = builder
					.translation(EarthMobsMod.MODID + ".config.hornedSheepSpawnRate")
					.comment("Changed Horned Sheep SpawnRate. [0 ~ 100]")
					.defineInRange("Horned Sheep SpawnRate", 10, 0, 100);
			hyperRabbitSpawnRate = builder
					.translation(EarthMobsMod.MODID + ".config.hyperRabbitSpawnRate")
					.comment("Changed Hyper Rabbit SpawnRate. [0 ~ 100]")
					.defineInRange("Hyper Rabbit SpawnRate", 5, 0, 100);
			moobloomSpawnRate = builder
					.translation(EarthMobsMod.MODID + ".config.moobloomSpawnRate")
					.comment("Changed Moobloom SpawnRate. [0 ~ 100]")
					.defineInRange("Moobloom SpawnRate", 10, 0, 100);
			moolipSpawnRate = builder
					.translation(EarthMobsMod.MODID + ".config.moolipSpawnRate")
					.comment("Changed Moolip SpawnRate. [0 ~ 100]")
					.defineInRange("Moolip SpawnRate", 10, 0, 100);
			boneSpiderSpawnRate = builder
					.translation(EarthMobsMod.MODID + ".config.boneSpiderSpawnRate")
					.comment("Changed Bone Spider SpawnRate. [0 ~ 1000]")
					.defineInRange("Bone Spider SpawnRate", 10, 0, 1000);
			boulderingZombieSpawnRate = builder
					.translation(EarthMobsMod.MODID + ".config.boulderingZombieSpawnRate")
					.comment("Changed BoulderingZombie SpawnRate. [0 ~ 1000]")
					.defineInRange("BoulderingZombie SpawnRate", 20, 0, 1000);
			lobberZombieSpawnRate = builder
					.translation(EarthMobsMod.MODID + ".config.lobberZombieSpawnRate")
					.comment("Changed LobberZombie SpawnRate. [0 ~ 1000]")
					.defineInRange("LobberZombie SpawnRate", 20, 0, 1000);
			tropicalSlimeSpawnRate = builder
					.translation(EarthMobsMod.MODID + ".config.tropicalSlimeSpawnRate")
					.comment("Changed TropicalSlime SpawnRate. [0 ~ 1000]")
					.defineInRange("TropicalSlime SpawnRate", 2, 0, 1000);
		}
	}

}