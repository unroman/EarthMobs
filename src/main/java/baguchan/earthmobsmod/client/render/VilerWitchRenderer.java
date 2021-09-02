package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.VilerWitchModel;
import baguchan.earthmobsmod.client.render.layer.VilerWitchItemLayer;
import baguchan.earthmobsmod.entity.VilerWitch;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VilerWitchRenderer<T extends VilerWitch> extends MobRenderer<T, VilerWitchModel<T>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/viler_witch.png");

	public VilerWitchRenderer(EntityRendererProvider.Context p_173952_) {
		super(p_173952_, new VilerWitchModel<>(p_173952_.bakeLayer(ModModelLayers.VILER_WITCH)), 0.5F);
		this.addLayer(new VilerWitchItemLayer<>(this));
	}


	@Override
	public ResourceLocation getTextureLocation(T p_110775_1_) {
		return TEXTURE;
	}
}