package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.effect.HyperSparkEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EarthMobsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEffects {
	public static final MobEffect HYPER_SPARK = new HyperSparkEffect(MobEffectCategory.BENEFICIAL, 0xDA784A);
	public static final Potion HYPER_SPARK_POTION = new Potion(new MobEffectInstance(HYPER_SPARK, 3600));
	public static final Potion LONG_HYPER_SPARK_POTION = new Potion(new MobEffectInstance(HYPER_SPARK, 9600));

	@SubscribeEvent
	public static void registerEffects(RegistryEvent.Register<MobEffect> event) {
		event.getRegistry().register(HYPER_SPARK.setRegistryName("hyper_spark"));
	}

	@SubscribeEvent
	public static void registerPotions(RegistryEvent.Register<Potion> event) {
		event.getRegistry().register(HYPER_SPARK_POTION.setRegistryName("hyper_spark"));
		event.getRegistry().register(LONG_HYPER_SPARK_POTION.setRegistryName("long_hyper_spark"));
		init();
	}

	public static ItemStack createPotion(Potion potion) {
		return PotionUtils.setPotion(new ItemStack(Items.POTION), potion);
	}

	public static void init() {
		BrewingRecipeRegistry.addRecipe(Ingredient.of(createPotion(Potions.SWIFTNESS)), Ingredient.of(ModItems.HYPER_RABBIT_FOOT), createPotion(HYPER_SPARK_POTION));
		BrewingRecipeRegistry.addRecipe(Ingredient.of(createPotion(HYPER_SPARK_POTION)), Ingredient.of(Items.REDSTONE), createPotion(LONG_HYPER_SPARK_POTION));
	}
}
