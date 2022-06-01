package baguchan.earthmobsmod.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.pathfinder.AmphibiousNodeEvaluator;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Random;

import static net.minecraft.world.entity.monster.Monster.isDarkEnoughToSpawn;

public class TropicalSlime extends Slime {
	public static final EntityDataAccessor<CompoundTag> DATA_FISHS = SynchedEntityData.defineId(TropicalSlime.class, EntityDataSerializers.COMPOUND_TAG);

	public static final String TAG_FISH_VARIANT = "FishVariant";
	public static final String TAG_FISH_LIST = "FishList";

	public TropicalSlime(EntityType<? extends Slime> p_33588_, Level p_33589_) {
		super(p_33588_, p_33589_);
		this.moveControl = new TropicalSlime.SlimeMoveControl(this);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_FISHS, new CompoundTag());
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

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Nullable
	public CompoundTag getFishData() {
		return this.entityData.get(DATA_FISHS);
	}

	protected void setFishData(CompoundTag p_36363_) {
		this.entityData.set(DATA_FISHS, p_36363_);
	}

	protected void randomFishData() {
		int i;
		int j;
		int k;
		int l;
		i = this.random.nextInt(2);
		j = this.random.nextInt(6);
		k = this.random.nextInt(15);
		l = this.random.nextInt(15);

		this.addFishData(i | j << 8 | k << 16 | l << 24);
	}

	protected void addFishData(int variant) {
		CompoundTag fishTag = new CompoundTag();
		ListTag listnbt = new ListTag();
		if (this.getFishData() != null) {
			fishTag = this.getFishData();
			if (fishTag.get(TAG_FISH_LIST) != null) {
				listnbt = (ListTag) fishTag.get(TAG_FISH_LIST);
			}
		}
		CompoundTag compoundnbt1 = new CompoundTag();
		compoundnbt1.putInt(TAG_FISH_VARIANT, variant);
		listnbt.add(compoundnbt1);
		fishTag.put(TAG_FISH_LIST, listnbt);
	}

	@Override
	public void remove(RemovalReason p_149847_) {
		super.remove(p_149847_);
		CompoundTag compoundTag = this.getFishData();

		if (compoundTag != null && compoundTag.get(TAG_FISH_LIST) != null) {
			int i = this.getSize();
			ListTag listTag = (ListTag) compoundTag.get(TAG_FISH_LIST);

			float f = (float) i / 4.0F;
			for (int l = 0; l < listTag.size(); ++l) {
				float f1 = ((float) (l % 2) - 0.5F) * f;
				float f2 = ((float) (l / 2) - 0.5F) * f;
				TropicalFish fish = EntityType.TROPICAL_FISH.create(this.level);
				if (this.isPersistenceRequired()) {
					fish.setPersistenceRequired();
				}
				fish.setVariant(((CompoundTag) listTag.get(l)).getInt(TAG_FISH_VARIANT));
				fish.setInvulnerable(this.isInvulnerable());
				fish.moveTo(this.getX() + (double) f1, this.getY() + 0.5D, this.getZ() + (double) f2, this.random.nextFloat() * 360.0F, 0.0F);
				this.level.addFreshEntity(fish);
			}
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag p_33619_) {
		super.addAdditionalSaveData(p_33619_);
		if (this.getFishData() != null) {
			p_33619_.put("FishData", this.getFishData());
		}
	}

	@Override
	public void readAdditionalSaveData(CompoundTag p_33607_) {
		super.readAdditionalSaveData(p_33607_);
		if (p_33607_.get("FishData") != null) {
			this.setFishData(p_33607_.getCompound("FishData"));
		}
	}

	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_30023_, DifficultyInstance p_30024_, MobSpawnType p_30025_, @Nullable SpawnGroupData p_30026_, @Nullable CompoundTag p_30027_) {
		p_30026_ = super.finalizeSpawn(p_30023_, p_30024_, p_30025_, p_30026_, p_30027_);

		int size = Mth.clamp(this.getSize(), 1, 5);
		for (int i = 0; i < size; i++) {
			this.randomFishData();
		}
		return p_30026_;
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
				//add this. because wantedY used in TropicalSlime special case
				((TropicalSlime.SlimeMoveControl) this.slime.getMoveControl()).setWantedY(livingentity.getY());
			}


			((TropicalSlime.SlimeMoveControl) this.slime.getMoveControl()).setDirection(this.slime.getYRot(), this.slime.isDealsDamage());
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
			this.nodeEvaluator = new AmphibiousNodeEvaluator(false);
			return new PathFinder(this.nodeEvaluator, p_30298_);
		}

		public boolean isStableDestination(BlockPos p_30300_) {
			return !this.level.getBlockState(p_30300_.below()).isAir();
		}
	}

	public void travel(Vec3 p_32394_) {
		if (this.isEffectiveAi() && this.isInWater()) {
			this.moveRelative(0.01F, p_32394_);
			this.move(MoverType.SELF, this.getDeltaMovement());
			this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
		} else {
			super.travel(p_32394_);
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

		public void setWantedY(double y) {
			this.wantedY = y;
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
				if (this.slime.isInWater() && !this.mob.isOnGround()) {

					float f1 = (float) (this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED));

					double d1 = this.wantedY - this.mob.getY();
					//add Y movement
					if (Math.abs(d1) > (double) 1.0E-5F) {
						this.mob.setYya(d1 > 0.0D ? f1 : -f1);
					}
					this.slime.setSpeed(Mth.lerp(0.125F, this.slime.getSpeed(), f1));

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
		private final TropicalSlime slime;
		private float chosenDegrees;
		private int nextRandomizeTime;

		public SlimeRandomDirectionGoal(TropicalSlime p_33679_) {
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
