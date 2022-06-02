package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.JumboRabbitModel;
import baguchan.earthmobsmod.entity.JumboRabbit;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class JumboRabbitRenderer<T extends JumboRabbit> extends MobRenderer<T, JumboRabbitModel<T>> {
	private static final ResourceLocation RABBIT_BROWN_LOCATION = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/jumbo_rabbit/jumbo_rabbit.png");
	private static final ResourceLocation RABBIT_WHITE_LOCATION = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/hyper_rabbit/white.png");
	private static final ResourceLocation RABBIT_GOLD_LOCATION = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/hyper_rabbit/gold.png");

	public JumboRabbitRenderer(EntityRendererProvider.Context p_173952_) {
		super(p_173952_, new JumboRabbitModel<>(p_173952_.bakeLayer(ModModelLayers.JUMBO_RABBIT)), 0.3F);
	}

	@Override
	protected void scale(T p_115314_, PoseStack p_115315_, float p_115316_) {
		float scale = p_115314_.isBaby() ? 0.4F : 0.6F;
		p_115315_.scale(scale, scale, scale);
		super.scale(p_115314_, p_115315_, p_115316_);
	}

	@Override
	public ResourceLocation getTextureLocation(T p_115803_) {
		String s = ChatFormatting.stripFormatting(p_115803_.getName().getString());

		switch (p_115803_.getRabbitType()) {
			case 0:
			default:
				return RABBIT_BROWN_LOCATION;
			case 1:
				return RABBIT_WHITE_LOCATION;
			case 2:
				return RABBIT_GOLD_LOCATION;
		}
	}
}