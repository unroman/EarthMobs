package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.item.SmellyEggItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EarthMobsMod.MODID);


	public static final RegistryObject<Item> SMELLY_EGG = ITEMS.register("smelly_egg", () -> new SmellyEggItem((new Item.Properties()).tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> BONE_SHARD = ITEMS.register("bone_shard", () -> new Item((new Item.Properties()).tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> BONE_SPIDER_EYE = ITEMS.register("bone_spider_eye", () -> new Item((new Item.Properties()).tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> HORN = ITEMS.register("horn", () -> new Item((new Item.Properties()).tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> HYPER_RABBIT_FOOT = ITEMS.register("hyper_rabbit_foot", () -> new Item((new Item.Properties()).tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> MUD_BUCKET = ITEMS.register("mud_bucket", () -> new BucketItem(ModFluids.MUD, (new Item.Properties()).stacksTo(1).tab(CreativeModeTab.TAB_MISC)));


	public static final RegistryObject<Item> CLUCK_SHROOM_SPAWNEGG = ITEMS.register("cluck_shroom_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.CLUCK_SHROOM, 0xB52C17, 0xDC883B, (new Item.Properties()).tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> WOOLY_COW_SPAWNEGG = ITEMS.register("wooly_cow_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.WOOLY_COW, 0xDB8948, 0xFFDBB6, (new Item.Properties()).tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> HORNED_SHEEP_SPAWNEGG = ITEMS.register("horned_sheep_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.HORNED_SHEEP, 15198183, 16758197, (new Item.Properties()).tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> HYPER_RABBIT_SPAWNEGG = ITEMS.register("hyper_rabbit_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.HYPER_RABBIT, 0xDA784A, 0xF4BF83, (new Item.Properties()).tab(CreativeModeTab.TAB_MISC)));


	public static final RegistryObject<Item> BONE_SPIDER_SPAWNEGG = ITEMS.register("bone_spider_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.BONE_SPIDER, 0x461C2E, 0x6130B7, (new Item.Properties()).tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> STRAY_BONE_SPIDER_SPAWNEGG = ITEMS.register("stray_bone_spider_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.STRAY_BONE_SPIDER, 0x20112F, 0x30B6B2, (new Item.Properties()).tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> VILER_WITCH_SPAWNEGG = ITEMS.register("viler_witch_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.VILER_WITCH, 0x111322, 0x37464D, (new Item.Properties()).tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> BOULDERING_ZOMBIE_SPAWNEGG = ITEMS.register("bouldering_zombie_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.BOULDERING_ZOMBIE, 0x384242, 0x52261A, (new Item.Properties()).tab(CreativeModeTab.TAB_MISC)));


}