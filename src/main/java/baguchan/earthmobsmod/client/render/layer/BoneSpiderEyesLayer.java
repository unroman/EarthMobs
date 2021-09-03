package baguchan.earthmobsmod.client.render.layer;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class BoneSpiderEyesLayer<T extends Entity, M extends SpiderModel<T>> extends EyesLayer<T, M> {
	private static final RenderType SPIDER_EYES = RenderType.eyes(new ResourceLocation(EarthMobsMod.MODID, "textures/entity/bone_spider/bone_spider_eye.png"));

	public BoneSpiderEyesLayer(RenderLayerParent<T, M> p_117507_) {
		super(p_117507_);
	}

	public RenderType renderType() {
		return SPIDER_EYES;
	}
}