package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.entity.projectile.BoneShard;
import baguchan.earthmobsmod.registry.ModEntities;
import baguchan.earthmobsmod.registry.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Collection;
import java.util.EnumSet;

public class BoneSpider extends Spider implements RangedAttackMob {
	private static final EntityDataAccessor<Boolean> DATA_STRAY_CONVERSION_ID = SynchedEntityData.defineId(BoneSpider.class, EntityDataSerializers.BOOLEAN);
	public static final String CONVERSION_TAG = "StrayConversionTime";
	private int inPowderSnowTime;
	private int conversionTime;

	public BoneSpider(EntityType<? extends BoneSpider> p_33786_, Level p_33787_) {
		super(p_33786_, p_33787_);
		this.xpReward = 10;
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.getEntityData().define(DATA_STRAY_CONVERSION_ID, false);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 24.0D).add(Attributes.ATTACK_DAMAGE, 3.0F).add(Attributes.MOVEMENT_SPEED, (double) 0.3F).add(Attributes.ARMOR, 10.0F).add(Attributes.FOLLOW_RANGE, 18.0F);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(2, new BoneSpiderAttackGoal(this));
	}

	public boolean isFreezeConverting() {
		return this.getEntityData().get(DATA_STRAY_CONVERSION_ID);
	}

	public void setFreezeConverting(boolean p_149843_) {
		this.entityData.set(DATA_STRAY_CONVERSION_ID, p_149843_);
	}

	public boolean isShaking() {
		return this.isFreezeConverting();
	}

	public void tick() {
		if (!this.level.isClientSide && this.isAlive() && !this.isNoAi()) {
			if (this.isFreezeConverting()) {
				--this.conversionTime;
				if (this.conversionTime < 0) {
					this.doFreezeConversion();
				}
			} else if (this.isInPowderSnow) {
				++this.inPowderSnowTime;
				if (this.inPowderSnowTime >= 140) {
					this.startFreezeConversion(300);
				}
			} else {
				this.inPowderSnowTime = -1;
			}
		}

		super.tick();
	}

	public void addAdditionalSaveData(CompoundTag p_149836_) {
		super.addAdditionalSaveData(p_149836_);
		p_149836_.putInt("StrayConversionTime", this.isFreezeConverting() ? this.conversionTime : -1);
	}

	public void readAdditionalSaveData(CompoundTag p_149833_) {
		super.readAdditionalSaveData(p_149833_);
		if (p_149833_.contains("StrayConversionTime", 99) && p_149833_.getInt("StrayConversionTime") > -1) {
			this.startFreezeConversion(p_149833_.getInt("StrayConversionTime"));
		}

	}

	public void startFreezeConversion(int p_149831_) {
		this.conversionTime = p_149831_;
		this.entityData.set(DATA_STRAY_CONVERSION_ID, true);
	}

	protected void doFreezeConversion() {
		this.convertTo(ModEntities.STRAY_BONE_SPIDER.get(), true);
		if (!this.isSilent()) {
			this.level.levelEvent((Player) null, 1048, this.blockPosition(), 0);
		}

	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.BONE_SPIDER_SAY.get();
	}

	protected SoundEvent getHurtSound(DamageSource p_30424_) {
		return ModSounds.BONE_SPIDER_HURT.get();
	}

	protected SoundEvent getDeathSound() {
		return ModSounds.BONE_SPIDER_DEATH.get();
	}

	protected void playStepSound(BlockPos p_30415_, BlockState p_30416_) {
		this.playSound(ModSounds.BONE_SPIDER_WALK.get(), 0.15F, 1.0F);
	}

	public boolean canFreeze() {
		return false;
	}

	@Override
	public boolean isInvertedHealAndHarm() {
		return true;
	}

	static class BoneSpiderAttackGoal extends Goal {
		private final BoneSpider spider;
		private int attackStep;
		private int attackTime;
		private int lastSeen;

		public BoneSpiderAttackGoal(BoneSpider p_32247_) {
			this.spider = p_32247_;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		}

		public boolean canUse() {
			LivingEntity livingentity = this.spider.getTarget();
			return livingentity != null && livingentity.isAlive() && this.spider.canAttack(livingentity);
		}

		public void start() {
			this.attackStep = 0;
		}

		public void stop() {
			this.lastSeen = 0;
		}

		public void tick() {
			--this.attackTime;
			LivingEntity livingentity = this.spider.getTarget();
			if (livingentity != null) {
				boolean flag = this.spider.getSensing().hasLineOfSight(livingentity);
				if (flag) {
					this.lastSeen = 0;
				} else {
					++this.lastSeen;
				}

				double d0 = this.spider.distanceToSqr(livingentity);
				if (d0 < 32.0D) {
					if (!flag) {
						return;
					}
					this.attackStep = 0;

					if (d0 < 4.0D + this.spider.getBbWidth() && this.attackTime <= 0) {
						this.attackTime = 20;
						this.spider.doHurtTarget(livingentity);
					}

					this.spider.getLookControl().setLookAt(livingentity, 10.0F, 10.0F);
					this.spider.getNavigation().moveTo(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0F);
				} else if (d0 < this.getFollowDistance() * this.getFollowDistance() && flag) {
					if (this.attackStep == 1) {
						if (this.attackTime == 15) {
							this.spider.playSound(ModSounds.BONE_SPIDER_SPIT.get(), this.spider.getSoundVolume(), 0.4F / (this.spider.getRandom().nextFloat() * 0.4F + 0.8F));
						}
					}
					if (this.attackTime <= 0) {
						++this.attackStep;
						if (this.attackStep == 1) {
							this.attackTime = 30;
						} else if (this.attackStep <= 3) {
							this.attackTime = 15;
							if (this.attackStep <= 2) {
								this.spider.playSound(ModSounds.BONE_SPIDER_SPIT.get(), this.spider.getSoundVolume(), 0.4F / (this.spider.getRandom().nextFloat() * 0.4F + 0.8F));
							}
						} else {
							this.attackTime = 10;
							this.attackStep = 0;
						}

						if (this.attackStep > 1) {
							double d4 = Math.sqrt(Math.sqrt(d0)) * 0.5D;

							this.spider.performRangedAttack(livingentity, attackTime);
						}
					}

					this.spider.getLookControl().setLookAt(livingentity, 10.0F, 10.0F);
					this.spider.getNavigation().stop();
				} else if (this.lastSeen < 5) {
					this.spider.getNavigation().moveTo(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0F);
				}

				super.tick();
			}
		}

		private double getFollowDistance() {
			return this.spider.getAttributeValue(Attributes.FOLLOW_RANGE);
		}
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
				bone.addEffect(new MobEffectInstance(mobEffectInstance.getEffect(), mobEffectInstance.getDuration() / 4, 0));
			}
		}
		this.level.addFreshEntity(bone);
	}

	@Override
	public float getScale() {
		return this.isBaby() ? 0.6F : 1.0F;
	}
}
