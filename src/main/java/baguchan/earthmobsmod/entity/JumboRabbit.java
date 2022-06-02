package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.level.Level;

public class JumboRabbit extends Rabbit {
	public JumboRabbit(EntityType<? extends Rabbit> p_29656_, Level p_29657_) {
		super(p_29656_, p_29657_);
	}

	@Override
	public Rabbit getBreedOffspring(ServerLevel p_149035_, AgeableMob p_149036_) {
		return ModEntities.JUMBO_RABBIT.get().create(p_149035_);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, (double) 0.32F);
	}
}
