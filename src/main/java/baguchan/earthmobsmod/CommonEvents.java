package baguchan.earthmobsmod;

import baguchan.earthmobsmod.registry.ModBlocks;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EarthMobsMod.MODID)
public class CommonEvents {

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
}
