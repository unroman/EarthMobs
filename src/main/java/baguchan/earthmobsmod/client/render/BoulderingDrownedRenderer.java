package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.BoulderingZombieModel;
import baguchan.earthmobsmod.client.render.layer.ZombieOuterLayer;
import baguchan.earthmobsmod.entity.BoulderingDrowned;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BoulderingDrownedRenderer extends AbstractZombieRenderer<BoulderingDrowned, ZombieModel<BoulderingDrowned>> {
	private static final ResourceLocation LOCATION = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/bouldering_zombie/bouldering_drowned.png");
	private static final ResourceLocation OUTER_LOCATION = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/bouldering_zombie/bouldering_drowned_outer_layer.png");

	public BoulderingDrownedRenderer(EntityRendererProvider.Context p_173964_) {
		super(p_173964_, new BoulderingZombieModel<>(p_173964_.bakeLayer(ModModelLayers.BOULDERING_DROWNED)), new ZombieModel(p_173964_.bakeLayer(ModelLayers.ZOMBIE_INNER_ARMOR)), new ZombieModel(p_173964_.bakeLayer(ModelLayers.ZOMBIE_OUTER_ARMOR)));
		this.addLayer(new ZombieOuterLayer<>(this, new BoulderingZombieModel<>(p_173964_.bakeLayer(ModModelLayers.BOULDERING_DROWNED_OUTER)), OUTER_LOCATION));
	}

	public ResourceLocation getTextureLocation(Zombie p_114115_) {
		return LOCATION;
	}
}
