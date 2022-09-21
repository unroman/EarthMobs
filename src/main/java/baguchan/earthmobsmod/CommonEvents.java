package baguchan.earthmobsmod;

import baguchan.earthmobsmod.capability.ShadowCapability;
import baguchan.earthmobsmod.entity.BoulderingDrowned;
import baguchan.earthmobsmod.entity.BoulderingZombie;
import baguchan.earthmobsmod.entity.LobberDrowned;
import baguchan.earthmobsmod.entity.LobberZombie;
import baguchan.earthmobsmod.registry.ModBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
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
	public void onEntityJoinWorld(LivingSpawnEvent.SpecialSpawn event) {
		if (event.getEntity() instanceof final AbstractVillager villager) {
			villager.targetSelector.addGoal(1, new AvoidEntityGoal<>(villager, BoulderingDrowned.class, 8.0F, 0.6D, 0.6D));
			villager.targetSelector.addGoal(1, new AvoidEntityGoal<>(villager, BoulderingZombie.class, 8.0F, 0.6D, 0.6D));
			villager.targetSelector.addGoal(1, new AvoidEntityGoal<>(villager, LobberDrowned.class, 8.0F, 0.6D, 0.6D));
			villager.targetSelector.addGoal(1, new AvoidEntityGoal<>(villager, LobberZombie.class, 8.0F, 0.6D, 0.6D));
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
			itemStack.hurtAndBreak(1, event.getEntity(), (p_29910_) -> {
				p_29910_.broadcastBreakEvent(event.getHand());
			});
			event.getEntity().level.playSound(null, event.getPos(), SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS, 1.0F, 1.0F);
			event.getEntity().level.setBlock(event.getPos(), ModBlocks.CARVED_MELON.get().defaultBlockState(), 2);

			event.setUseItem(Event.Result.ALLOW);
		}
	}

	@SubscribeEvent
	public static void onUpdate(LivingEvent.LivingTickEvent event) {
		event.getEntity().getCapability(EarthMobsMod.SHADOW_CAP).ifPresent(shadowCapability -> {
			shadowCapability.tick(event.getEntity());
		});
	}

	@SubscribeEvent
	public static void onHurt(LivingHurtEvent event) {
		event.getEntity().getCapability(EarthMobsMod.SHADOW_CAP).ifPresent(shadowCapability -> {
			if (shadowCapability.getPercentBoost() >= 0.5F && !event.getSource().isFire() && !event.getSource().isExplosion() && !event.getSource().isBypassArmor()) {
				event.setAmount(event.getAmount() * (1.0F - shadowCapability.getPercentBoost()));
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
