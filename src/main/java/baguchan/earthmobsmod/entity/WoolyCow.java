package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.level.Level;

public class WoolyCow extends Cow {
	public WoolyCow(EntityType<? extends Cow> p_28285_, Level p_28286_) {
		super(p_28285_, p_28286_);
	}

	public Cow getBreedOffspring(ServerLevel p_148884_, AgeableMob p_148885_) {
		return ModEntities.WOOLY_COW.create(p_148884_);
	}
}
