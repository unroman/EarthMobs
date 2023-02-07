package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.registry.ModEntities;
import baguchan.earthmobsmod.registry.ModItems;
import baguchan.earthmobsmod.registry.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class Duck extends Chicken {

	public Duck(EntityType<? extends Duck> p_28236_, Level p_28237_) {
		super(p_28236_, p_28237_);
	}

	@Nullable
	@Override
	public ItemEntity spawnAtLocation(ItemLike p_19999_) {
		//override to duck egg
		if (p_19999_.asItem() == Items.EGG) {
			p_19999_ = ModItems.DUCK_EGG.get();
		}

		return super.spawnAtLocation(p_19999_);
	}

	@Override
	public void aiStep() {
		super.aiStep();
		if (this.isInWater()) {
			this.flapping = 0;
			this.flap = 0;
			this.flapSpeed = 0;
			this.oFlap = 0;
			this.oFlapSpeed = 0;
		}
	}

	@Override
	public Chicken getBreedOffspring(ServerLevel p_148884_, AgeableMob p_148885_) {
		return ModEntities.DUCK.get().create(p_148884_);
	}

	@Override
	protected float getWaterSlowDown() {
		return 0.85F;
	}


	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.DUCK_IDLE.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource p_28262_) {
		return ModSounds.DUCK_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.DUCK_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos p_28254_, BlockState p_28255_) {
		this.playSound(ModSounds.DUCK_STEP.get(), 0.15F, 1.0F);
	}
}
