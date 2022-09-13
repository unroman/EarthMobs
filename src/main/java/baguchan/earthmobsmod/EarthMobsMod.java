package baguchan.earthmobsmod;

import baguchan.earthmobsmod.capability.ShadowCapability;
import baguchan.earthmobsmod.client.ClientRegistrar;
import baguchan.earthmobsmod.registry.ModBiomeModifiers;
import baguchan.earthmobsmod.registry.ModBlocks;
import baguchan.earthmobsmod.registry.ModEffects;
import baguchan.earthmobsmod.registry.ModEntities;
import baguchan.earthmobsmod.registry.ModFluidTypes;
import baguchan.earthmobsmod.registry.ModFluids;
import baguchan.earthmobsmod.registry.ModInteractionInformations;
import baguchan.earthmobsmod.registry.ModItems;
import baguchan.earthmobsmod.world.features.ModEarthFeatures;
import baguchan.earthmobsmod.world.features.ModEarthPlacements;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EarthMobsMod.MODID)
public class EarthMobsMod {
	public static final String MODID = "earthmobsmod";
	// Directly reference a log4j logger.
	private static final Logger LOGGER = LogManager.getLogger(MODID);

	public static Capability<ShadowCapability> SHADOW_CAP = CapabilityManager.get(new CapabilityToken<>() {
	});

	public EarthMobsMod() {
		// Register the setup method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		// Register the enqueueIMC method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
		// Register the processIMC method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

		IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

		IEventBus forgeBus = MinecraftForge.EVENT_BUS;
		ModBlocks.BLOCKS.register(modBus);
		ModEntities.ENTITIES.register(modBus);
		ModFluidTypes.FLUID_TYPES.register(modBus);
		ModFluids.FLUIDS.register(modBus);
		ModBiomeModifiers.BIOME_MODIFIER_SERIALIZERS.register(modBus);
		ModEffects.MOB_EFFECTS.register(modBus);
		ModEffects.POTION.register(modBus);
		ModItems.ITEMS.register(modBus);

		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, EarthMobsConfig.COMMON_SPEC);
		DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientRegistrar::setup));
	}

	private void setup(final FMLCommonSetupEvent event) {
		ModEntities.spawnPlacementSetup();
		ModEffects.init();
		ModInteractionInformations.init();
		ModEarthFeatures.init();
		ModEarthPlacements.init();
	}

	private void enqueueIMC(final InterModEnqueueEvent event) {

	}

	private void processIMC(final InterModProcessEvent event) {
	}
}
