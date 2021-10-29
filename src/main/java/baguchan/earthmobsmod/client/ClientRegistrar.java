package baguchan.earthmobsmod.client;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.model.*;
import baguchan.earthmobsmod.client.render.*;
import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = EarthMobsMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistrar {
	@SubscribeEvent
	public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(ModEntities.CLUCK_SHROOM, CluckShroomRender::new);
		event.registerEntityRenderer(ModEntities.WOOLY_COW, WoolyCowRenderer::new);
		event.registerEntityRenderer(ModEntities.HORNED_SHEEP, HornedSheepRenderer::new);
		event.registerEntityRenderer(ModEntities.MELON_GOLEM, MelonGolemRenderer::new);

		event.registerEntityRenderer(ModEntities.BONE_SPIDER, BoneSpiderRender::new);
		event.registerEntityRenderer(ModEntities.STRAY_BONE_SPIDER, StrayBoneSpiderRender::new);
		event.registerEntityRenderer(ModEntities.VILER_WITCH, VilerWitchRenderer::new);

		event.registerEntityRenderer(ModEntities.SMELLY_EGG, ThrownItemRenderer::new);
		event.registerEntityRenderer(ModEntities.BONE_SHARD, ThrownItemRenderer::new);
		event.registerEntityRenderer(ModEntities.MELON_SEED, ThrownItemRenderer::new);
	}

	@SubscribeEvent
	public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ModModelLayers.CLUCK_SHROOM, CluckShroomModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.WOOLY_COW, WoolyCowModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.HORNED_SHEEP, HornedSheepModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.BONE_SPIDER, BoneSpiderModel::createSpiderBodyLayer);
		event.registerLayerDefinition(ModModelLayers.STRAY_BONE_SPIDER, BoneSpiderModel::createSpiderBodyLayer);
		event.registerLayerDefinition(ModModelLayers.VILER_WITCH, VilerWitchModel::createBodyLayer);
	}
}
