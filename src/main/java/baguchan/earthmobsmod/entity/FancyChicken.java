package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.level.Level;

public class FancyChicken extends Chicken {

	public FancyChicken(EntityType<? extends FancyChicken> p_28236_, Level p_28237_) {
		super(p_28236_, p_28237_);
	}


	@Override
	public Chicken getBreedOffspring(ServerLevel p_148884_, AgeableMob p_148885_) {
		return ModEntities.FANCY_CHICKEN.get().create(p_148884_);
	}
}
