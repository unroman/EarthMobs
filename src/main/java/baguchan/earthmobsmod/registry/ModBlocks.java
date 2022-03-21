package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.block.CarvedMelonBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EarthMobsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks {
	public static final Block CARVED_MELON = new CarvedMelonBlock(BlockBehaviour.Properties.of(Material.VEGETABLE, MaterialColor.COLOR_ORANGE).strength(1.0F).sound(SoundType.WOOD));
	public static final Block CARVED_MELON_SHOOT = new CarvedMelonBlock(BlockBehaviour.Properties.of(Material.VEGETABLE, MaterialColor.COLOR_ORANGE).strength(1.0F).sound(SoundType.WOOD));

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> registry) {
		registry.getRegistry().register(CARVED_MELON.setRegistryName("carved_melon"));
		registry.getRegistry().register(CARVED_MELON_SHOOT.setRegistryName("carved_melon_shoot"));
	}

	@SubscribeEvent
	public static void registerItem(RegistryEvent.Register<Item> event) {
		ModItems.register(event, new BlockItem(CARVED_MELON, (new Item.Properties()).tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
		ModItems.register(event, new BlockItem(CARVED_MELON_SHOOT, (new Item.Properties())));
	}
}
