package baguchan.earthmobsmod.data;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EarthMobsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		/*event.getGenerator().addProvider(true, new BlockstateGenerator(event.getGenerator(), event.getExistingFileHelper()));
		event.getGenerator().addProvider(true, new ItemModelGenerator(event.getGenerator(), event.getExistingFileHelper()));
		*/
		BlockTagsProvider blocktags = new BlockTagGenerator(event.getGenerator().getPackOutput(), event.getLookupProvider(), event.getExistingFileHelper());
		event.getGenerator().addProvider(event.includeServer(), blocktags);
		event.getGenerator().addProvider(event.includeServer(), new ItemTagGenerator(event.getGenerator().getPackOutput(), event.getLookupProvider(), blocktags, event.getExistingFileHelper()));
		event.getGenerator().addProvider(event.includeServer(), new EntityTagGenerator(event.getGenerator().getPackOutput(), event.getLookupProvider(), event.getExistingFileHelper()));
		event.getGenerator().addProvider(event.includeServer(), new WorldGenerator(event.getGenerator().getPackOutput(), event.getLookupProvider()));
	}
}