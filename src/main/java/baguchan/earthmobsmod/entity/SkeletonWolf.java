package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.registry.ModEntities;
import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;

public class SkeletonWolf extends Wolf {
	private static final EntityDataAccessor<Optional<UUID>> DATA_TRUSTED_ID_0 = SynchedEntityData.defineId(SkeletonWolf.class, EntityDataSerializers.OPTIONAL_UUID);
	private static final EntityDataAccessor<Optional<UUID>> DATA_TRUSTED_ID_1 = SynchedEntityData.defineId(SkeletonWolf.class, EntityDataSerializers.OPTIONAL_UUID);


	public SkeletonWolf(EntityType<? extends Wolf> p_30369_, Level p_30370_) {
		super(p_30369_, p_30370_);
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
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
		this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, livingEntity -> {
			return !this.trusts(livingEntity.getUUID()) && this.isWorstCondition();
		}));
		this.targetSelector.addGoal(5, new NonTameRandomTargetGoal<>(this, Player.class, true, (Predicate<LivingEntity>) null));
		this.targetSelector.addGoal(6, new NonTameRandomTargetGoal<>(this, Turtle.class, false, Turtle.BABY_ON_LAND_SELECTOR));
		this.targetSelector.addGoal(8, new ResetUniversalAngerTargetGoal<>(this, true));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_TRUSTED_ID_0, Optional.empty());
		this.entityData.define(DATA_TRUSTED_ID_1, Optional.empty());
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Wolf.createAttributes().add(Attributes.MAX_HEALTH, 12.0F).add(Attributes.ATTACK_DAMAGE, 4.0D);
	}

	@Nullable
	protected List<UUID> getTrustedUUIDs() {
		List<UUID> list = Lists.newArrayList();
		list.add(this.entityData.get(DATA_TRUSTED_ID_0).orElse((UUID) null));
		list.add(this.entityData.get(DATA_TRUSTED_ID_1).orElse((UUID) null));
		return list;
	}

	protected void addTrustedUUID(@Nullable UUID p_28516_) {
		if (this.entityData.get(DATA_TRUSTED_ID_0).isPresent()) {
			this.entityData.set(DATA_TRUSTED_ID_1, Optional.ofNullable(p_28516_));
		} else {
			this.entityData.set(DATA_TRUSTED_ID_0, Optional.ofNullable(p_28516_));
		}
	}

	protected boolean trusts(UUID p_28530_) {
		return this.getTrustedUUIDs().contains(p_28530_);
	}

	protected boolean isWorstCondition() {
		return this.getBrightness() < 0.45F;
	}

	public void tame(Player p_21829_) {
		super.tame(p_21829_);
		if (this.getTrustedUUIDs().isEmpty() || !this.getTrustedUUIDs().contains(p_21829_.getUUID())) {
			this.addTrustedUUID(p_21829_.getUUID());
		}
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

		this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(6.0D);
	}

	public void addAdditionalSaveData(CompoundTag p_28518_) {
		super.addAdditionalSaveData(p_28518_);
		List<UUID> list = this.getTrustedUUIDs();
		ListTag listtag = new ListTag();

		for (UUID uuid : list) {
			if (uuid != null) {
				listtag.add(NbtUtils.createUUID(uuid));
			}
		}

		p_28518_.put("Trusted", listtag);
	}

	public void readAdditionalSaveData(CompoundTag p_28493_) {
		super.readAdditionalSaveData(p_28493_);
		ListTag listtag = p_28493_.getList("Trusted", 11);

		for (int i = 0; i < listtag.size(); ++i) {
			this.addTrustedUUID(NbtUtils.loadUUID(listtag.get(i)));
		}
	}

	public InteractionResult mobInteract(Player p_30412_, InteractionHand p_30413_) {
		ItemStack itemstack = p_30412_.getItemInHand(p_30413_);
		Item item = itemstack.getItem();
		if (this.level.isClientSide) {
			boolean flag = this.isOwnedBy(p_30412_) || this.isTame() || itemstack.is(Items.BONE) && !this.isTame() && !this.isAngry();
			return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
		} else {
			if (this.isTame()) {
				if (itemstack.is(Tags.Items.BONES) && this.getHealth() < this.getMaxHealth()) {
					if (!p_30412_.getAbilities().instabuild) {
						itemstack.shrink(1);
					}

					this.heal(2);
					this.gameEvent(GameEvent.MOB_INTERACT, this.eyeBlockPosition());
					return InteractionResult.SUCCESS;
				}

				if (!(item instanceof DyeItem)) {
					InteractionResult interactionresult = super.mobInteract(p_30412_, p_30413_);
					if ((!interactionresult.consumesAction() || this.isBaby()) && this.isOwnedBy(p_30412_)) {
						this.setOrderedToSit(!this.isOrderedToSit());
						this.jumping = false;
						this.navigation.stop();
						this.setTarget((LivingEntity) null);
						return InteractionResult.SUCCESS;
					}

					return interactionresult;
				}

				DyeColor dyecolor = ((DyeItem) item).getDyeColor();
				if (dyecolor != this.getCollarColor()) {
					this.setCollarColor(dyecolor);
					if (!p_30412_.getAbilities().instabuild) {
						itemstack.shrink(1);
					}

					return InteractionResult.SUCCESS;
				}
			} else if (itemstack.is(Items.BONE) && !this.isAngry()) {
				if (!p_30412_.getAbilities().instabuild) {
					itemstack.shrink(1);
				}

				if (!this.isWorstCondition() && !this.trusts(p_30412_.getUUID())) {
					if (this.random.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, p_30412_)) {
						this.addTrustedUUID(p_30412_.getUUID());
						this.level.broadcastEntityEvent(this, (byte) 14);
					} else {
						this.level.broadcastEntityEvent(this, (byte) 6);
					}
				} else {
					if (this.random.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, p_30412_)) {
						this.tame(p_30412_);
						this.navigation.stop();
						this.setTarget((LivingEntity) null);
						this.setOrderedToSit(true);
						this.level.broadcastEntityEvent(this, (byte) 7);
					} else {
						this.level.broadcastEntityEvent(this, (byte) 6);
					}
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
	public MobType getMobType() {
		return MobType.UNDEAD;
	}

	public static boolean isDarkEnoughToSpawn(ServerLevelAccessor p_33009_, BlockPos p_33010_, Random p_33011_) {
		if (p_33009_.getBrightness(LightLayer.SKY, p_33010_) > p_33011_.nextInt(32)) {
			return false;
		} else if (p_33009_.getBrightness(LightLayer.BLOCK, p_33010_) > 0) {
			return false;
		} else {
			int i = p_33009_.getLevel().isThundering() ? p_33009_.getMaxLocalRawBrightness(p_33010_, 10) : p_33009_.getMaxLocalRawBrightness(p_33010_);
			return i <= p_33011_.nextInt(8);
		}
	}

	public static boolean checkSkeletonWolfSpawnRules(EntityType<? extends SkeletonWolf> p_33018_, ServerLevelAccessor p_33019_, MobSpawnType p_33020_, BlockPos p_33021_, Random p_33022_) {
		return p_33019_.getBlockState(p_33021_.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON) && isDarkEnoughToSpawn(p_33019_, p_33021_, p_33022_) && checkMobSpawnRules(p_33018_, p_33019_, p_33020_, p_33021_, p_33022_);
	}
}
