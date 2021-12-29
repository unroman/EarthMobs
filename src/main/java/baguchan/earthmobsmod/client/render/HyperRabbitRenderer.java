package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.HyperRabbitModel;
import baguchan.earthmobsmod.entity.HyperRabbit;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HyperRabbitRenderer<T extends HyperRabbit> extends MobRenderer<T, HyperRabbitModel<T>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/hyper_rabbit/hyper_rabbit.png");

	public HyperRabbitRenderer(EntityRendererProvider.Context p_173952_) {
		super(p_173952_, new HyperRabbitModel<>(p_173952_.bakeLayer(ModModelLayers.HYPER_RABBIT)), 0.3F);
	}

	@Override
	public ResourceLocation getTextureLocation(T p_110775_1_) {
		return TEXTURE;
	}
}