package baguchan.earthmobsmod;

import baguchan.earthmobsmod.capability.ShadowCapability;
import baguchan.earthmobsmod.registry.ModBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
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
	public static void onAttachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof LivingEntity) {
			event.addCapability(new ResourceLocation(EarthMobsMod.MODID, "shadow"), new ShadowCapability());
		}
	}

	@SubscribeEvent
	public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		ItemStack itemStack = event.getPlayer().getItemInHand(event.getHand());
		if (itemStack.getItem() instanceof ShearsItem && event.getPlayer().level.getBlockState(event.getPos()).getBlock() == Blocks.MELON) {
			itemStack.hurtAndBreak(1, event.getPlayer(), (p_29910_) -> {
				p_29910_.broadcastBreakEvent(event.getHand());
			});
			event.getPlayer().level.playSound(null, event.getPos(), SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS, 1.0F, 1.0F);
			event.getPlayer().level.setBlock(event.getPos(), ModBlocks.CARVED_MELON.defaultBlockState(), 2);

			event.setUseItem(Event.Result.ALLOW);
		}
	}

	@SubscribeEvent
	public static void onUpdate(LivingEvent.LivingUpdateEvent event) {
		event.getEntityLiving().getCapability(EarthMobsMod.SHADOW_CAP).ifPresent(shadowCapability -> {
			shadowCapability.tick(event.getEntityLiving());
		});
	}

	@SubscribeEvent
	public static void onDamaged(LivingDamageEvent event) {
		event.getEntityLiving().getCapability(EarthMobsMod.SHADOW_CAP).ifPresent(shadowCapability -> {
			if (shadowCapability.getPercentBoost() >= 0.5F && !event.getSource().isExplosion() && !event.getSource().isBypassArmor()) {
				event.setAmount(event.getAmount() / (1.1F - shadowCapability.getPercentBoost()));
				event.setCanceled(true);
			}
		});
	}
}
