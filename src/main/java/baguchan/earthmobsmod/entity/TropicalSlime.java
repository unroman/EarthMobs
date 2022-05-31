package baguchan.earthmobsmod.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.pathfinder.AmphibiousNodeEvaluator;
import net.minecraft.world.level.pathfinder.PathFinder;

import java.util.EnumSet;
import java.util.Random;

import static net.minecraft.world.entity.monster.Monster.isDarkEnoughToSpawn;

public class TropicalSlime extends Slime {
	public TropicalSlime(EntityType<? extends Slime> p_33588_, Level p_33589_) {
		super(p_33588_, p_33589_);
		this.moveControl = new TropicalSlime.SlimeMoveControl(this);
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(2, new TropicalSlime.SlimeAttackGoal(this));
		this.goalSelector.addGoal(3, new TropicalSlime.SlimeRandomDirectionGoal(this));
		this.goalSelector.addGoal(5, new TropicalSlime.SlimeKeepOnJumpingGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, (p_33641_) -> {
			return Math.abs(p_33641_.getY() - this.getY()) <= 4.0D;
		}));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
	}

	@Override
	protected PathNavigation createNavigation(Level p_21480_) {
		return new TropicalPathNavigation(this, p_21480_);
	}

	public static boolean checkTropicalSpawnRules(EntityType<TropicalSlime> p_32350_, ServerLevelAccessor p_32351_, MobSpawnType p_32352_, BlockPos p_32353_, Random p_32354_) {
		if (!p_32351_.getFluidState(p_32353_.below()).is(FluidTags.WATER)) {
			return false;
		} else {
			Holder<Biome> holder = p_32351_.getBiome(p_32353_);
			boolean flag = p_32351_.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn(p_32351_, p_32353_, p_32354_) && (p_32352_ == MobSpawnType.SPAWNER || p_32351_.getFluidState(p_32353_).is(FluidTags.WATER));
			if (!holder.is(Biomes.RIVER) && !holder.is(Biomes.FROZEN_RIVER)) {
				return p_32354_.nextInt(40) == 0 && isDeepEnoughToSpawn(p_32351_, p_32353_) && flag;
			} else {
				return p_32354_.nextInt(15) == 0 && flag;
			}
		}
	}

	private static boolean isDeepEnoughToSpawn(LevelAccessor p_32367_, BlockPos p_32368_) {
		return p_32368_.getY() < p_32367_.getSeaLevel() - 5;
	}

	float getSoundPitch() {
		float f = this.isTiny() ? 1.4F : 0.8F;
		return ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * f;
	}

	static class SlimeAttackGoal extends Goal {
		private final TropicalSlime slime;
		private int growTiredTimer;

		public SlimeAttackGoal(TropicalSlime p_33648_) {
			this.slime = p_33648_;
			this.setFlags(EnumSet.of(Goal.Flag.LOOK));
		}

		public boolean canUse() {
			LivingEntity livingentity = this.slime.getTarget();
			if (livingentity == null) {
				return false;
			} else {
				return !this.slime.canAttack(livingentity) ? false : this.slime.getMoveControl() instanceof TropicalSlime.SlimeMoveControl;
			}
		}

		public void start() {
			this.growTiredTimer = reducedTickDelay(300);
			super.start();
		}

		public boolean canContinueToUse() {
			LivingEntity livingentity = this.slime.getTarget();
			if (livingentity == null) {
				return false;
			} else if (!this.slime.canAttack(livingentity)) {
				return false;
			} else {
				return --this.growTiredTimer > 0;
			}
		}

		public boolean requiresUpdateEveryTick() {
			return true;
		}

		public void tick() {
			LivingEntity livingentity = this.slime.getTarget();
			if (livingentity != null) {
				this.slime.lookAt(livingentity, 10.0F, 10.0F);
			}

			((TropicalSlime.SlimeMoveControl) this.slime.getMoveControl()).setDirection(this.slime.getYRot(), this.slime.isDealsDamage());
		}
	}

	static class SlimeFloatGoal extends Goal {
		private final Slime slime;

		public SlimeFloatGoal(Slime p_33655_) {
			this.slime = p_33655_;
			this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
			p_33655_.getNavigation().setCanFloat(true);
		}

		public boolean canUse() {
			return (this.slime.isInWater() || this.slime.isInLava()) && this.slime.getMoveControl() instanceof TropicalSlime.SlimeMoveControl;
		}

		public boolean requiresUpdateEveryTick() {
			return true;
		}

		public void tick() {
			if (this.slime.getRandom().nextFloat() < 0.8F) {
				this.slime.getJumpControl().jump();
			}

			((TropicalSlime.SlimeMoveControl) this.slime.getMoveControl()).setWantedMovement(1.2D);
		}
	}

	static class SlimeKeepOnJumpingGoal extends Goal {
		private final Slime slime;

		public SlimeKeepOnJumpingGoal(Slime p_33660_) {
			this.slime = p_33660_;
			this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
		}

		public boolean canUse() {
			return !this.slime.isPassenger();
		}

		public void tick() {
			((TropicalSlime.SlimeMoveControl) this.slime.getMoveControl()).setWantedMovement(1.0D);
		}
	}

	static class TropicalPathNavigation extends WaterBoundPathNavigation {
		TropicalPathNavigation(TropicalSlime p_30294_, Level p_30295_) {
			super(p_30294_, p_30295_);
		}

		protected boolean canUpdatePath() {
			return true;
		}

		protected PathFinder createPathFinder(int p_30298_) {
			this.nodeEvaluator = new AmphibiousNodeEvaluator(true);
			return new PathFinder(this.nodeEvaluator, p_30298_);
		}

		public boolean isStableDestination(BlockPos p_30300_) {
			return !this.level.getBlockState(p_30300_.below()).isAir();
		}
	}

	static class SlimeMoveControl extends MoveControl {
		private float yRot;
		private int jumpDelay;
		private final TropicalSlime slime;
		private boolean isAggressive;

		public SlimeMoveControl(TropicalSlime p_33668_) {
			super(p_33668_);
			this.slime = p_33668_;
			this.yRot = 180.0F * p_33668_.getYRot() / (float) Math.PI;
		}

		public void setDirection(float p_33673_, boolean p_33674_) {
			this.yRot = p_33673_;
			this.isAggressive = p_33674_;
		}

		public void setWantedMovement(double p_33671_) {
			this.speedModifier = p_33671_;
			this.operation = MoveControl.Operation.MOVE_TO;
		}

		public void tick() {
			this.mob.setYRot(this.rotlerp(this.mob.getYRot(), this.yRot, 90.0F));
			this.mob.yHeadRot = this.mob.getYRot();
			this.mob.yBodyRot = this.mob.getYRot();
			if (this.operation != MoveControl.Operation.MOVE_TO) {
				this.mob.setZza(0.0F);
			} else {
				this.operation = MoveControl.Operation.WAIT;
				if (this.slime.isInWater()) {
					if (this.operation != MoveControl.Operation.MOVE_TO || this.slime.getNavigation().isDone()) {
						this.slime.setSpeed(0.0F);
						return;
					}

					double d0 = this.wantedX - this.slime.getX();
					double d1 = this.wantedY - this.slime.getY();
					double d2 = this.wantedZ - this.slime.getZ();
					double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
					d1 /= d3;
					float f = (float) (Mth.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
					this.slime.setYRot(this.rotlerp(this.slime.getYRot(), f, 90.0F));
					this.slime.yBodyRot = this.slime.getYRot();
					float f1 = (float) (this.speedModifier * this.slime.getAttributeValue(Attributes.MOVEMENT_SPEED));
					float f2 = Mth.lerp(0.125F, this.slime.getSpeed(), f1);
					this.slime.setSpeed(f2);
					this.slime.setDeltaMovement(this.slime.getDeltaMovement().add((double) f2 * d0 * 0.005D, (double) f2 * d1 * 0.1D, (double) f2 * d2 * 0.005D));
				} else if (this.mob.isOnGround()) {
					this.mob.setSpeed((float) (this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
					if (this.jumpDelay-- <= 0) {
						this.jumpDelay = this.slime.getJumpDelay();
						if (this.isAggressive) {
							this.jumpDelay /= 3;
						}

						this.slime.getJumpControl().jump();
						if (this.slime.doPlayJumpSound()) {
							this.slime.playSound(this.slime.getJumpSound(), this.slime.getSoundVolume(), this.slime.getSoundPitch());
						}
					} else {
						this.slime.xxa = 0.0F;
						this.slime.zza = 0.0F;
						this.mob.setSpeed(0.0F);
					}
				} else {
					this.mob.setSpeed((float) (this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
				}

			}
		}
	}

	static class SlimeRandomDirectionGoal extends Goal {
		private final Slime slime;
		private float chosenDegrees;
		private int nextRandomizeTime;

		public SlimeRandomDirectionGoal(Slime p_33679_) {
			this.slime = p_33679_;
			this.setFlags(EnumSet.of(Goal.Flag.LOOK));
		}

		public boolean canUse() {
			return this.slime.getTarget() == null && (this.slime.isOnGround() || this.slime.isInWater() || this.slime.isInLava() || this.slime.hasEffect(MobEffects.LEVITATION)) && this.slime.getMoveControl() instanceof TropicalSlime.SlimeMoveControl;
		}

		public void tick() {
			if (--this.nextRandomizeTime <= 0) {
				this.nextRandomizeTime = this.adjustedTickDelay(40 + this.slime.getRandom().nextInt(60));
				this.chosenDegrees = (float) this.slime.getRandom().nextInt(360);
			}

			((TropicalSlime.SlimeMoveControl) this.slime.getMoveControl()).setDirection(this.chosenDegrees, false);
		}
	}
}
