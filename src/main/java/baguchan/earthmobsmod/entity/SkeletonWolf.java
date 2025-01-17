package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.registry.ModEntities;
import baguchan.earthmobsmod.registry.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BegGoal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NonTameRandomTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.Tags;

public class SkeletonWolf extends Wolf {
	public SkeletonWolf(EntityType<? extends SkeletonWolf> p_30369_, Level p_30370_) {
		super(p_30369_, p_30370_);
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
		this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
		this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));
		this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
		this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(9, new BegGoal(this, 8.0F));
		this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
		this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
		this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
		this.targetSelector.addGoal(5, new NonTameRandomTargetGoal<>(this, Player.class, true, livingEntity -> {
			return this.isWorstCondition();
		}));
		this.targetSelector.addGoal(6, new NonTameRandomTargetGoal<>(this, Turtle.class, false, Turtle.BABY_ON_LAND_SELECTOR));
		this.targetSelector.addGoal(8, new ResetUniversalAngerTargetGoal<>(this, true));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Wolf.createAttributes().add(Attributes.MAX_HEALTH, 12.0F).add(Attributes.ATTACK_DAMAGE, 3.0D);
	}

	protected boolean isWorstCondition() {
		return this.getLightLevelDependentMagicValue() < 0.4F;
	}

	@Override
	public void setTame(boolean p_30443_) {
		super.setTame(p_30443_);
		if (p_30443_) {
			this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(24.0D);
			this.setHealth(24.0F);
		} else {
			this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(12.0D);
		}

		this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(5.0D);
	}

	public InteractionResult mobInteract(Player p_30412_, InteractionHand p_30413_) {
		ItemStack itemstack = p_30412_.getItemInHand(p_30413_);
		Item item = itemstack.getItem();
		if (this.level.isClientSide) {
			boolean flag = this.isOwnedBy(p_30412_) || this.isTame() || itemstack.is(Tags.Items.BONES) && !this.isTame() && !this.isAngry();
			return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
		} else {
			if (this.isTame()) {
				if ((itemstack.is(Tags.Items.BONES) || itemstack.is(Items.ROTTEN_FLESH)) && this.getHealth() < this.getMaxHealth()) {
					if (!p_30412_.getAbilities().instabuild) {
						itemstack.shrink(1);
					}

					this.heal(2);
					this.gameEvent(GameEvent.ITEM_INTERACT_START);
					return InteractionResult.SUCCESS;
				}

				if (this.isFood(itemstack)) {
					return InteractionResult.PASS;
				}

			} else if ((itemstack.is(Tags.Items.BONES) || itemstack.is(Items.ROTTEN_FLESH)) && !this.isAngry()) {
				if (!p_30412_.getAbilities().instabuild) {
					itemstack.shrink(1);
				}

				if (this.random.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, p_30412_)) {
					this.tame(p_30412_);
					this.navigation.stop();
					this.setTarget((LivingEntity) null);
					this.setOrderedToSit(true);
					this.level.broadcastEntityEvent(this, (byte) 7);
				} else {
					this.level.broadcastEntityEvent(this, (byte) 6);
				}

				return InteractionResult.SUCCESS;
			}

			return super.mobInteract(p_30412_, p_30413_);
		}
	}

	@Override
	public SkeletonWolf getBreedOffspring(ServerLevel p_149088_, AgeableMob p_149089_) {
		SkeletonWolf skeletonWolf = ModEntities.SKELETON_WOLF.get().create(p_149088_);

		if (this.isTame()) {
			skeletonWolf.setOwnerUUID(this.getOwnerUUID());
			skeletonWolf.setTame(true);
		}
		return skeletonWolf;
	}

	@Override
	public boolean isFood(ItemStack p_30440_) {
		return false;
	}

	@Override
	public boolean removeWhenFarAway(double p_27598_) {
		return !isTame();
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEAD;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		if (this.isAngry()) {
			return ModSounds.SKELETON_WOLF_GROWL.get();
		} else if (this.random.nextInt(3) == 0) {
			return this.isTame() && this.getHealth() < 10.0F ? ModSounds.SKELETON_WOLF_WHINE.get() : ModSounds.SKELETON_WOLF_PANTING.get();
		} else {
			return ModSounds.SKELETON_WOLF_BARK.get();
		}
	}

	protected SoundEvent getHurtSound(DamageSource p_30424_) {
		return ModSounds.SKELETON_WOLF_HURT.get();
	}

	protected SoundEvent getDeathSound() {
		return ModSounds.SKELETON_WOLF_DEATH.get();
	}

	protected void playStepSound(BlockPos p_30415_, BlockState p_30416_) {
		this.playSound(ModSounds.SKELETON_WOLF_STEP.get(), 0.15F, 1.0F);
	}

	public static boolean isDarkEnoughToSpawn(ServerLevelAccessor p_33009_, BlockPos p_33010_, RandomSource p_33011_) {
		if (p_33009_.getBrightness(LightLayer.SKY, p_33010_) > p_33011_.nextInt(32)) {
			return false;
		} else if (p_33009_.getBrightness(LightLayer.BLOCK, p_33010_) > 0) {
			return false;
		} else {
			int i = p_33009_.getLevel().isThundering() ? p_33009_.getMaxLocalRawBrightness(p_33010_, 10) : p_33009_.getMaxLocalRawBrightness(p_33010_);
			return i <= p_33011_.nextInt(8);
		}
	}

	public static boolean checkSkeletonWolfSpawnRules(EntityType<? extends SkeletonWolf> p_33018_, ServerLevelAccessor p_33019_, MobSpawnType p_33020_, BlockPos p_33021_, RandomSource p_33022_) {
		return p_33019_.getBlockState(p_33021_.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON) && isDarkEnoughToSpawn(p_33019_, p_33021_, p_33022_) && checkMobSpawnRules(p_33018_, p_33019_, p_33020_, p_33021_, p_33022_);
	}
}
