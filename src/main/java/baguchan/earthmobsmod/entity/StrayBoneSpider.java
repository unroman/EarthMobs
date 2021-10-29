package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.entity.projectile.BoneShard;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.Collection;

public class StrayBoneSpider extends BoneSpider {
	public StrayBoneSpider(EntityType<? extends StrayBoneSpider> p_33786_, Level p_33787_) {
		super(p_33786_, p_33787_);
	}

	public void performRangedAttack(LivingEntity p_29912_, float p_29913_) {
		BoneShard bone = new BoneShard(this.level, this);
		double d1 = p_29912_.getX() - this.getX();
		double d2 = p_29912_.getEyeY() - this.getEyeY();
		double d3 = p_29912_.getZ() - this.getZ();
		bone.shoot(d1, d2, d3, 1.4F, 2.0F + p_29913_);

		Collection<MobEffectInstance> collection = this.getActiveEffects();
		if (!collection.isEmpty()) {
			for (MobEffectInstance mobEffectInstance : this.getActiveEffects()) {
				bone.addEffect(new MobEffectInstance(mobEffectInstance.getEffect(), 200, 0));
			}
		}
		bone.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200));

		this.playSound(SoundEvents.LLAMA_SPIT, 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
		this.level.addFreshEntity(bone);
	}
}
