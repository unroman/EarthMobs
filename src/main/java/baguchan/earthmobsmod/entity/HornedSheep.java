package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.registry.ModLootTables;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.level.Level;

public class HornedSheep extends Sheep {
	public HornedSheep(EntityType<? extends Sheep> p_29806_, Level p_29807_) {
		super(p_29806_, p_29807_);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.25D, true));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
	}

	public void aiStep() {
		this.updateSwingTime();
		super.aiStep();
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Sheep.createAttributes().add(Attributes.ATTACK_DAMAGE, 4.0D).add(Attributes.ATTACK_KNOCKBACK, (double) 0.5F);
	}

	public ResourceLocation getDefaultLootTable() {
		if (this.isSheared()) {
			return this.getType().getDefaultLootTable();
		} else {
			switch (this.getColor()) {
				case WHITE:
				default:
					return ModLootTables.HORNED_SHEEP_WHITE;
				case ORANGE:
					return ModLootTables.HORNED_SHEEP_ORANGE;
				case MAGENTA:
					return ModLootTables.HORNED_SHEEP_MAGENTA;
				case LIGHT_BLUE:
					return ModLootTables.HORNED_SHEEP_LIGHT_BLUE;
				case YELLOW:
					return ModLootTables.HORNED_SHEEP_YELLOW;
				case LIME:
					return ModLootTables.HORNED_SHEEP_LIME;
				case PINK:
					return ModLootTables.HORNED_SHEEP_PINK;
				case GRAY:
					return ModLootTables.HORNED_SHEEP_GRAY;
				case LIGHT_GRAY:
					return ModLootTables.HORNED_SHEEP_LIGHT_GRAY;
				case CYAN:
					return ModLootTables.HORNED_SHEEP_CYAN;
				case PURPLE:
					return ModLootTables.HORNED_SHEEP_PURPLE;
				case BLUE:
					return ModLootTables.HORNED_SHEEP_BLUE;
				case BROWN:
					return ModLootTables.HORNED_SHEEP_BROWN;
				case GREEN:
					return ModLootTables.HORNED_SHEEP_GREEN;
				case RED:
					return ModLootTables.HORNED_SHEEP_RED;
				case BLACK:
					return ModLootTables.HORNED_SHEEP_BLACK;
			}
		}
	}
}
