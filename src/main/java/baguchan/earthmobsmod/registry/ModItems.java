package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.entity.VilerWitch;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EarthMobsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {
	public static final Item WOOLY_COW_SPAWNEGG = new SpawnEggItem(ModEntities.WOOLY_COW, 0xDB8948, 0xFFDBB6, (new Item.Properties()).tab(CreativeModeTab.TAB_MISC));

	public static final Item VILER_WITCH_SPAWNEGG = new SpawnEggItem(ModEntities.VILER_WITCH, 0x111322, 0x37464D, (new Item.Properties()).tab(CreativeModeTab.TAB_MISC));

	@SubscribeEvent
	public static void registerItem(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(WOOLY_COW_SPAWNEGG.setRegistryName("wooly_cow_spawn_egg"));
		event.getRegistry().register(VILER_WITCH_SPAWNEGG.setRegistryName("viler_witch_spawn_egg"));
	}
}