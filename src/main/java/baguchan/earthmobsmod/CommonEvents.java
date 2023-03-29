package baguchan.earthmobsmod;

import baguchan.earthmobsmod.block.CarvedMelonBlock;
import baguchan.earthmobsmod.capability.ShadowCapability;
import baguchan.earthmobsmod.entity.*;
import baguchan.earthmobsmod.registry.ModBlocks;
import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EarthMobsMod.MODID)
public class CommonEvents {
	@SubscribeEvent
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.register(ShadowCapability.class);
	}

	@SubscribeEvent
	public void onEntityJoinWorld(MobSpawnEvent.FinalizeSpawn event) {
		if (event.getEntity() instanceof final AbstractVillager villager) {
			villager.targetSelector.addGoal(1, new AvoidEntityGoal<>(villager, BoulderingDrowned.class, 8.0F, 0.8D, 0.6D));
			villager.targetSelector.addGoal(1, new AvoidEntityGoal<>(villager, BoulderingZombie.class, 8.0F, 0.8D, 0.6D));
			villager.targetSelector.addGoal(1, new AvoidEntityGoal<>(villager, LobberDrowned.class, 8.0F, 0.8D, 0.6D));
			villager.targetSelector.addGoal(1, new AvoidEntityGoal<>(villager, LobberZombie.class, 8.0F, 0.8D, 0.6D));
		}
	}

	@SubscribeEvent
	public static void onAttachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof LivingEntity) {
			event.addCapability(new ResourceLocation(EarthMobsMod.MODID, "shadow"), new ShadowCapability());
		}
	}

	@SubscribeEvent
	public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		ItemStack itemStack = event.getEntity().getItemInHand(event.getHand());
		if (itemStack.getItem() instanceof ShearsItem && event.getEntity().level.getBlockState(event.getPos()).getBlock() == Blocks.MELON) {
			Direction direction = event.getHitVec().getDirection();
			if (direction != Direction.DOWN && direction != Direction.UP) {
				itemStack.hurtAndBreak(1, event.getEntity(), (p_29910_) -> {
					p_29910_.broadcastBreakEvent(event.getHand());
				});
				event.getEntity().level.playSound(null, event.getPos(), SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS, 1.0F, 1.0F);

				event.getEntity().level.setBlock(event.getPos(), ModBlocks.CARVED_MELON.get().defaultBlockState().setValue(CarvedMelonBlock.FACING, direction), 2);

				event.setUseItem(Event.Result.ALLOW);
			}
		}
	}

	@SubscribeEvent
	public static void onUpdate(LivingEvent.LivingTickEvent event) {
		event.getEntity().getCapability(EarthMobsMod.SHADOW_CAP).ifPresent(shadowCapability -> {
			shadowCapability.tick(event.getEntity());
		});
	}

	@SubscribeEvent
	public static void onLightning(EntityStruckByLightningEvent event) {
		if (event.getEntity() instanceof Pig pig) {
			if (event.getEntity().getType() != ModEntities.ZOMBIFIED_PIG.get()) {
				ZombifiedPig zombifiedpig = ModEntities.ZOMBIFIED_PIG.get().create(event.getEntity().getLevel());
				zombifiedpig.moveTo(pig.getX(), pig.getY(), pig.getZ(), pig.getYRot(), pig.getXRot());
				zombifiedpig.setNoAi(pig.isNoAi());
				zombifiedpig.setBaby(pig.isBaby());
				if (pig.hasCustomName()) {
					zombifiedpig.setCustomName(pig.getCustomName());
					zombifiedpig.setCustomNameVisible(pig.isCustomNameVisible());
				}

				zombifiedpig.setPersistenceRequired();
				net.minecraftforge.event.ForgeEventFactory.onLivingConvert(pig, zombifiedpig);
				event.getEntity().getLevel().addFreshEntity(zombifiedpig);
				pig.discard();
				event.setCanceled(true);
			} else {
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public static void onHurt(LivingHurtEvent event) {
		event.getEntity().getCapability(EarthMobsMod.SHADOW_CAP).ifPresent(shadowCapability -> {
			if (shadowCapability.getPercentBoost() >= 0.5F && !event.getSource().is(DamageTypeTags.BYPASSES_ARMOR) && !event.getSource().is(DamageTypeTags.IS_EXPLOSION) && !event.getSource().is(DamageTypeTags.IS_FIRE)) {
				event.setAmount(event.getAmount() * (1.0F - shadowCapability.getPercentBoost()));
				if (shadowCapability.getPercentBoost() > 0.9F) {
					event.setCanceled(true);
				}
			}
		});
	}

	@SubscribeEvent
	public static void onLivingKnockback(LivingKnockBackEvent event) {
		event.getEntity().getCapability(EarthMobsMod.SHADOW_CAP).ifPresent(shadowCapability -> {
			if (shadowCapability.getPercentBoost() >= 0.5F) {
				event.setCanceled(true);
			}
		});
	}
}
