package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.BoulderingZombieModel;
import baguchan.earthmobsmod.client.model.LobberZombieModel;
import baguchan.earthmobsmod.client.render.layer.ZombieOuterLayer;
import baguchan.earthmobsmod.entity.LobberDrowned;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LobberDrownedRenderer extends AbstractZombieRenderer<LobberDrowned, ZombieModel<LobberDrowned>> {
	private static final ResourceLocation LOCATION = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/lobber_zombie/lobber_drowned.png");
	private static final ResourceLocation OUTER_LOCATION = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/lobber_zombie/lobber_drowned_outer_layer.png");


	public LobberDrownedRenderer(EntityRendererProvider.Context p_173964_) {
		super(p_173964_, new LobberZombieModel<>(p_173964_.bakeLayer(ModModelLayers.LOBBER_DROWNED)), new ZombieModel(p_173964_.bakeLayer(ModelLayers.ZOMBIE_INNER_ARMOR)), new ZombieModel(p_173964_.bakeLayer(ModelLayers.ZOMBIE_OUTER_ARMOR)));
		this.addLayer(new ZombieOuterLayer<>(this, new BoulderingZombieModel<>(p_173964_.bakeLayer(ModModelLayers.LOBBER_DROWNED_OUTER)), OUTER_LOCATION));
	}

	public ResourceLocation getTextureLocation(Zombie p_114115_) {
		return LOCATION;
	}
}
