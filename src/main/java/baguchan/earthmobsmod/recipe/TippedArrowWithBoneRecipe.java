package baguchan.earthmobsmod.recipe;

import baguchan.earthmobsmod.registry.ModItems;
import baguchan.earthmobsmod.registry.ModRecipes;
import com.google.common.collect.Lists;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class TippedArrowWithBoneRecipe extends CustomRecipe {
	public TippedArrowWithBoneRecipe(ResourceLocation p_44503_) {
		super(p_44503_);
	}

	public boolean matches(CraftingContainer p_44515_, Level p_44516_) {
		List<Item> list = Lists.newArrayList();
		List<Item> list2 = Lists.newArrayList();
		if (p_44515_.getWidth() >= 2 && p_44515_.getHeight() >= 2) {
			if (ForgeRegistries.ITEMS.tags().getTag(Tags.Items.RODS_WOODEN) == null) {
				return false;
			}


			for (int i = 0; i < p_44515_.getWidth(); ++i) {
				for (int j = 0; j < p_44515_.getHeight(); ++j) {
					ItemStack itemstack = p_44515_.getItem(i + j * p_44515_.getWidth());


					if (j == 0) {
						if (itemstack.is(ModItems.BONE_SHARD.get())) {
							list.add(itemstack.getItem());
						}
					} else if (!itemstack.is(Items.FEATHER)) {
						list.add(itemstack.getItem());
					} else if (!itemstack.is(Tags.Items.RODS_WOODEN)) {
						list2.add(itemstack.getItem());
					}
				}
			}

			return list.contains(Items.FEATHER) && list.contains(ModItems.BONE_SHARD.get()) && list2.size() == 1 && ForgeRegistries.ITEMS.tags().getTag(Tags.Items.RODS_WOODEN).contains(list2.get(0));
		} else {
			return false;
		}
	}

	public ItemStack assemble(CraftingContainer p_44513_) {
		for (int i = 0; i < p_44513_.getContainerSize(); ++i) {
			ItemStack itemstack = p_44513_.getItem(i);
			if (itemstack.is(Items.LINGERING_POTION)) {

				ItemStack itemstack1 = new ItemStack(Items.TIPPED_ARROW, 8);
				PotionUtils.setPotion(itemstack1, PotionUtils.getPotion(itemstack));
				PotionUtils.setCustomEffects(itemstack1, PotionUtils.getCustomEffects(itemstack));
				return itemstack1;
			}
		}
		return ItemStack.EMPTY;
	}

	public boolean canCraftInDimensions(int p_44505_, int p_44506_) {
		return p_44505_ == 3 && p_44506_ == 3;
	}

	public RecipeSerializer<?> getSerializer() {
		return ModRecipes.RECIPE_TIPPED_ARROW_WITH_BONE.get();
	}
}