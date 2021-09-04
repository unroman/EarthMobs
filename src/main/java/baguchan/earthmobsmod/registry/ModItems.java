package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.item.SmellyEggItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EarthMobsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {
	public static final Item SMELLY_EGG = new SmellyEggItem((new Item.Properties()).tab(CreativeModeTab.TAB_MISC));
	public static final Item BONE_SHARD = new Item((new Item.Properties()).tab(CreativeModeTab.TAB_MISC));
	public static final Item BONE_SPIDER_EYE = new Item((new Item.Properties()).tab(CreativeModeTab.TAB_MISC));

	public static final Item CLUCK_SHROOM_SPAWNEGG = new SpawnEggItem(ModEntities.CLUCK_SHROOM, 0xB52C17, 0xDC883B, (new Item.Properties()).tab(CreativeModeTab.TAB_MISC));
	public static final Item WOOLY_COW_SPAWNEGG = new SpawnEggItem(ModEntities.WOOLY_COW, 0xDB8948, 0xFFDBB6, (new Item.Properties()).tab(CreativeModeTab.TAB_MISC));

	public static final Item BONE_SPIDER_SPAWNEGG = new SpawnEggItem(ModEntities.BONE_SPIDER, 0x461C2E, 0x6130B7, (new Item.Properties()).tab(CreativeModeTab.TAB_MISC));
	public static final Item VILER_WITCH_SPAWNEGG = new SpawnEggItem(ModEntities.VILER_WITCH, 0x111322, 0x37464D, (new Item.Properties()).tab(CreativeModeTab.TAB_MISC));

	@SubscribeEvent
	public static void registerItem(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(SMELLY_EGG.setRegistryName("smelly_egg"));
		event.getRegistry().register(BONE_SHARD.setRegistryName("bone_shard"));
		event.getRegistry().register(BONE_SPIDER_EYE.setRegistryName("bone_spider_eye"));

		event.getRegistry().register(CLUCK_SHROOM_SPAWNEGG.setRegistryName("cluck_shroom_spawn_egg"));
		event.getRegistry().register(WOOLY_COW_SPAWNEGG.setRegistryName("wooly_cow_spawn_egg"));

		event.getRegistry().register(BONE_SPIDER_SPAWNEGG.setRegistryName("bone_spider_spawn_egg"));
		event.getRegistry().register(VILER_WITCH_SPAWNEGG.setRegistryName("viler_witch_spawn_egg"));
	}
}