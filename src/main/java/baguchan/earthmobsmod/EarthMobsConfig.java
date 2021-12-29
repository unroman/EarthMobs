package baguchan.earthmobsmod;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber(modid = EarthMobsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EarthMobsConfig {
	public static final Common COMMON;
	public static final ForgeConfigSpec COMMON_SPEC;

	public static int woolyCowSpawnRate;
	public static int cluckshroomSpawnRate;
	public static int hornedSheepSpawnRate;
	public static int boneSpiderSpawnRate;
	public static int hyperRabbitSpawnRate;

	static {
		Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = specPair.getRight();
		COMMON = specPair.getLeft();
	}

	public static void bakeConfig() {
		woolyCowSpawnRate = COMMON.woolyCowSpawnRate.get();
		cluckshroomSpawnRate = COMMON.cluckshroomSpawnRate.get();
		hornedSheepSpawnRate = COMMON.hornedSheepSpawnRate.get();
		boneSpiderSpawnRate = COMMON.boneSpiderSpawnRate.get();
		hyperRabbitSpawnRate = COMMON.hyperRabbitSpawnRate.get();
	}


	@SubscribeEvent
	public static void onModConfigEvent(final ModConfigEvent.Loading configEvent) {
		if (configEvent.getConfig().getSpec() == EarthMobsConfig.COMMON_SPEC) {
			bakeConfig();
		}
	}

	@SubscribeEvent
	public static void onModConfigEvent(final ModConfigEvent.Reloading configEvent) {
		if (configEvent.getConfig().getSpec() == EarthMobsConfig.COMMON_SPEC) {
			bakeConfig();
		}
	}

	public static class Common {
		public final ForgeConfigSpec.IntValue woolyCowSpawnRate;
		public final ForgeConfigSpec.IntValue cluckshroomSpawnRate;
		public final ForgeConfigSpec.IntValue hornedSheepSpawnRate;
		public final ForgeConfigSpec.IntValue boneSpiderSpawnRate;
		public final ForgeConfigSpec.IntValue hyperRabbitSpawnRate;

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
					.defineInRange("Hyper Rabbit SpawnRate", 10, 0, 100);
			boneSpiderSpawnRate = builder
					.translation(EarthMobsMod.MODID + ".config.boneSpiderSpawnRate")
					.comment("Changed Bone Spider SpawnRate. [0 ~ 1000]")
					.defineInRange("Bone Spider SpawnRate", 10, 0, 1000);
		}
	}

}