package baguchan.earthmobsmod.client;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.model.*;
import baguchan.earthmobsmod.client.render.*;
import baguchan.earthmobsmod.client.render.layer.MuddyPigMudLayer;
import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.renderer.entity.PigRenderer;
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
		event.registerEntityRenderer(ModEntities.CLUCK_SHROOM.get(), CluckShroomRender::new);
		event.registerEntityRenderer(ModEntities.WOOLY_COW.get(), WoolyCowRenderer::new);
		event.registerEntityRenderer(ModEntities.HORNED_SHEEP.get(), HornedSheepRenderer::new);
		event.registerEntityRenderer(ModEntities.HYPER_RABBIT.get(), HyperRabbitRenderer::new);
		event.registerEntityRenderer(ModEntities.MELON_GOLEM.get(), MelonGolemRenderer::new);

		event.registerEntityRenderer(ModEntities.BONE_SPIDER.get(), BoneSpiderRender::new);
		event.registerEntityRenderer(ModEntities.STRAY_BONE_SPIDER.get(), StrayBoneSpiderRender::new);
		event.registerEntityRenderer(ModEntities.VILER_WITCH.get(), VilerWitchRenderer::new);

		event.registerEntityRenderer(ModEntities.SMELLY_EGG.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(ModEntities.BONE_SHARD.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(ModEntities.MELON_SEED.get(), ThrownItemRenderer::new);
	}

	@SubscribeEvent
	public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ModModelLayers.CLUCK_SHROOM, CluckShroomModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.WOOLY_COW, WoolyCowModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.HORNED_SHEEP, HornedSheepModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.HORNED_SHEEP_FUR, HornedSheepModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.HYPER_RABBIT, HyperRabbitModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.MUDDY_PIG, () -> MuddyPigModel.createBodyLayer(CubeDeformation.NONE));
		event.registerLayerDefinition(ModModelLayers.BONE_SPIDER, BoneSpiderModel::createSpiderBodyLayer);
		event.registerLayerDefinition(ModModelLayers.STRAY_BONE_SPIDER, BoneSpiderModel::createSpiderBodyLayer);
		event.registerLayerDefinition(ModModelLayers.VILER_WITCH, VilerWitchModel::createBodyLayer);
	}

	@SubscribeEvent
	public static void registerEntityRenders(EntityRenderersEvent.AddLayers event) {
		Minecraft.getInstance().getEntityRenderDispatcher().renderers.values().forEach(r -> {
			if (r instanceof PigRenderer) {
				((PigRenderer) r).addLayer(new MuddyPigMudLayer((PigRenderer) r, event.getEntityModels()));
			}
		});
	}
}
