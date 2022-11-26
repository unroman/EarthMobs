package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

public class WitherSkeletonWolf extends SkeletonWolf {
	public WitherSkeletonWolf(EntityType<? extends WitherSkeletonWolf> p_30369_, Level p_30370_) {
		super(p_30369_, p_30370_);
		this.setPathfindingMalus(BlockPathTypes.LAVA, 8.0F);
	}

	@Override
	public WitherSkeletonWolf getBreedOffspring(ServerLevel p_149088_, AgeableMob p_149089_) {
		WitherSkeletonWolf skeletonWolf = ModEntities.WITHER_SKELETON_WOLF.get().create(p_149088_);

		if (this.isTame()) {
			skeletonWolf.setOwnerUUID(this.getOwnerUUID());
			skeletonWolf.setTame(true);
		}
		return skeletonWolf;
	}

	@Override
	public boolean canBeAffected(MobEffectInstance p_31495_) {
		return p_31495_.getEffect() == MobEffects.WITHER ? false : super.canBeAffected(p_31495_);
	}

	@Override
	public boolean doHurtTarget(Entity p_34169_) {
		if (!super.doHurtTarget(p_34169_)) {
			return false;
		} else {
			if (p_34169_ instanceof LivingEntity) {
				((LivingEntity) p_34169_).addEffect(new MobEffectInstance(MobEffects.WITHER, 200), this);
			}

			return true;
		}
	}
}
