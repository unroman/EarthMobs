package baguchan.earthmobsmod.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.brewing.IBrewingRecipe;

import javax.annotation.Nonnull;

public class ModBrewingRecipe implements IBrewingRecipe {
	@Nonnull
	private final ItemStack input;
	@Nonnull
	private final ItemStack ingredient;
	@Nonnull
	private final ItemStack output;

	public ModBrewingRecipe(ItemStack input, ItemStack ingredient, ItemStack output) {
		this.input = input;
		this.ingredient = ingredient;
		this.output = output;
	}

	@Override
	public boolean isInput(@Nonnull ItemStack stack) {
		return ItemStack.isSameItemSameTags(stack, input);
	}

	@Override
	public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
		return isInput(input) && isIngredient(ingredient) ? getOutput().copy() : ItemStack.EMPTY;
	}

	public ItemStack getInput() {
		return input;
	}

	public ItemStack getIngredient() {
		return ingredient;
	}

	public ItemStack getOutput() {
		return output;
	}

	@Override
	public boolean isIngredient(ItemStack ingredient) {
		return ItemStack.isSameItemSameTags(this.ingredient, ingredient);
	}
}
