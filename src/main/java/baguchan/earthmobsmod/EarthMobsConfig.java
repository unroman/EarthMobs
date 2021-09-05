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
	public static int boneSpiderSpawnRate;

	static {
		Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = specPair.getRight();
		COMMON = specPair.getLeft();
	}

	public static void bakeConfig() {
		woolyCowSpawnRate = COMMON.woolyCowSpawnRate.get();
		cluckshroomSpawnRate = COMMON.cluckshroomSpawnRate.get();
		boneSpiderSpawnRate = COMMON.boneSpiderSpawnRate.get();
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
		public final ForgeConfigSpec.IntValue boneSpiderSpawnRate;

		public Common(ForgeConfigSpec.Builder builder) {
			woolyCowSpawnRate = builder
					.translation(EarthMobsMod.MODID + ".config.woolyCowSpawnRate")
					.defineInRange("Wooly cow SpawnRate", 10, 0, 100);
			cluckshroomSpawnRate = builder
					.translation(EarthMobsMod.MODID + ".config.cluckshroomSpawnRate")
					.defineInRange("Cluckshroom SpawnRate", 5, 0, 100);
			boneSpiderSpawnRate = builder
					.translation(EarthMobsMod.MODID + ".config.boneSpiderSpawnRate")
					.defineInRange("Bone Spider SpawnRate", 30, 0, 1000);
		}
	}

}