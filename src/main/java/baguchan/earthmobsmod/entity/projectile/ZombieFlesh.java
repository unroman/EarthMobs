package baguchan.earthmobsmod.entity.projectile;

import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class ZombieFlesh extends ThrowableItemProjectile {
	public ZombieFlesh(EntityType<? extends ZombieFlesh> p_37391_, Level p_37392_) {
		super(p_37391_, p_37392_);
	}

	public ZombieFlesh(Level p_37399_, LivingEntity p_37400_) {
		super(ModEntities.ZOMBIE_FLESH.get(), p_37400_, p_37399_);
	}

	public ZombieFlesh(Level p_37394_, double p_37395_, double p_37396_, double p_37397_) {
		super(ModEntities.ZOMBIE_FLESH.get(), p_37395_, p_37396_, p_37397_, p_37394_);
	}

	protected Item getDefaultItem() {
		return Items.ROTTEN_FLESH;
	}

	private ParticleOptions getParticle() {
		return new ItemParticleOption(ParticleTypes.ITEM, Items.ROTTEN_FLESH.getDefaultInstance());
	}

	public void handleEntityEvent(byte p_37402_) {
		if (p_37402_ == 3) {
			ParticleOptions particleoptions = this.getParticle();

			for (int i = 0; i < 8; ++i) {
				this.level.addParticle(particleoptions, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
			}
		}

	}

	protected void defineSynchedData() {
		super.defineSynchedData();
	}

	public void addAdditionalSaveData(CompoundTag p_36881_) {
		super.addAdditionalSaveData(p_36881_);
	}

	public void readAdditionalSaveData(CompoundTag p_36875_) {
		super.readAdditionalSaveData(p_36875_);
	}

	protected void onHitEntity(EntityHitResult p_37404_) {
		super.onHitEntity(p_37404_);
		Entity entity = p_37404_.getEntity();

		if (entity instanceof LivingEntity && entity.hurt(DamageSource.thrown(this, this.getOwner()), 3)) {
			((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.HUNGER, 60), entity);
		}
	}

	protected void onHit(HitResult p_37488_) {
		super.onHit(p_37488_);
		if (!this.level.isClientSide) {
			this.level.broadcastEntityEvent(this, (byte) 3);
			this.playSound(SoundEvents.SLIME_BLOCK_BREAK, 0.4F, 1.0F);
			this.discard();
		}

	}
}