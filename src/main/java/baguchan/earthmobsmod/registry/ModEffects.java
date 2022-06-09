package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.effect.HyperSparkEffect;
import baguchan.earthmobsmod.recipe.ModBrewingRecipe;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = EarthMobsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEffects {
	public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, EarthMobsMod.MODID);
	public static final DeferredRegister<Potion> POTION = DeferredRegister.create(ForgeRegistries.POTIONS, EarthMobsMod.MODID);


	public static final RegistryObject<MobEffect> HYPER_SPARK = MOB_EFFECTS.register("hyper_spark", () -> new HyperSparkEffect(MobEffectCategory.BENEFICIAL, 0xDA784A));
	public static final RegistryObject<Potion> HYPER_SPARK_POTION = POTION.register("hyper_spark", () -> new Potion(new MobEffectInstance(Objects.requireNonNull(HYPER_SPARK.get()), 3600)));
	public static final RegistryObject<Potion> LONG_HYPER_SPARK_POTION = POTION.register("long_hyper_spark", () -> new Potion(new MobEffectInstance(Objects.requireNonNull(HYPER_SPARK.get()), 9600)));

	public static ItemStack createPotion(Potion potion) {
		return PotionUtils.setPotion(new ItemStack(Items.POTION), potion);
	}

	public static void init() {
		BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(createPotion(Potions.SWIFTNESS), new ItemStack(ModItems.HYPER_RABBIT_FOOT.get()), createPotion(HYPER_SPARK_POTION.get())));
		BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(createPotion(HYPER_SPARK_POTION.get()), new ItemStack(Items.REDSTONE), createPotion(LONG_HYPER_SPARK_POTION.get())));
	}
}
