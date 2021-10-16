package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.entity.*;
import baguchan.earthmobsmod.entity.projectile.BoneShard;
import baguchan.earthmobsmod.entity.projectile.MelonSeed;
import baguchan.earthmobsmod.entity.projectile.SmellyEgg;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EarthMobsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {

	public static final EntityType<CluckShroom> CLUCK_SHROOM = EntityType.Builder.of(CluckShroom::new, MobCategory.CREATURE).sized(0.4F, 0.7F).build(prefix("cluck_shroom"));
	public static final EntityType<WoolyCow> WOOLY_COW = EntityType.Builder.of(WoolyCow::new, MobCategory.CREATURE).sized(0.9F, 1.4F).build(prefix("wooly_cow"));
	public static final EntityType<MelonGolem> MELON_GOLEM = EntityType.Builder.of(MelonGolem::new, MobCategory.MISC).sized(0.7F, 1.9F).immuneTo(Blocks.POWDER_SNOW).build(prefix("melon_golem"));


	public static final EntityType<BoneSpider> BONE_SPIDER = EntityType.Builder.of(BoneSpider::new, MobCategory.MONSTER).sized(1.4F, 0.9F).build(prefix("bone_spider"));
	public static final EntityType<StrayBoneSpider> STRAY_BONE_SPIDER = EntityType.Builder.of(StrayBoneSpider::new, MobCategory.MONSTER).sized(1.4F, 0.9F).build(prefix("stray_bone_spider"));

	public static final EntityType<VilerWitch> VILER_WITCH = EntityType.Builder.of(VilerWitch::new, MobCategory.MONSTER).sized(0.6F, 1.95F).build(prefix("viler_witch"));

	public static final EntityType<SmellyEgg> SMELLY_EGG = EntityType.Builder.<SmellyEgg>of(SmellyEgg::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build(prefix("smelly_egg"));
	public static final EntityType<BoneShard> BONE_SHARD = EntityType.Builder.<BoneShard>of(BoneShard::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build(prefix("bone_shard"));
	public static final EntityType<MelonSeed> MELON_SEED = EntityType.Builder.<MelonSeed>of(MelonSeed::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build(prefix("melon_seeds"));


	private static String prefix(String path) {
		return EarthMobsMod.MODID + "." + path;
	}

	@SubscribeEvent
	public static void registerEntity(RegistryEvent.Register<EntityType<?>> event) {
		event.getRegistry().register(CLUCK_SHROOM.setRegistryName("cluck_shroom"));
		event.getRegistry().register(WOOLY_COW.setRegistryName("wooly_cow"));
		event.getRegistry().register(MELON_GOLEM.setRegistryName("melon_golem"));


		event.getRegistry().register(BONE_SPIDER.setRegistryName("bone_spider"));
		event.getRegistry().register(STRAY_BONE_SPIDER.setRegistryName("stray_bone_spider"));
		event.getRegistry().register(VILER_WITCH.setRegistryName("viler_witch"));

		event.getRegistry().register(SMELLY_EGG.setRegistryName("smelly_egg"));
		event.getRegistry().register(BONE_SHARD.setRegistryName("bone_shard"));
		event.getRegistry().register(MELON_SEED.setRegistryName("melon_seeds"));


		Raid.RaiderType.create("viler_witch", VILER_WITCH, new int[]{0, 0, 1, 0, 1, 1, 2, 1});

		SpawnPlacements.register(CLUCK_SHROOM, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CluckShroom::checkCluckShroomSpawnRules);
		SpawnPlacements.register(WOOLY_COW, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
		SpawnPlacements.register(MELON_GOLEM, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
		SpawnPlacements.register(BONE_SPIDER, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
		SpawnPlacements.register(STRAY_BONE_SPIDER, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
		SpawnPlacements.register(VILER_WITCH, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
	}

	@SubscribeEvent
	public static void registerEntityAttribute(EntityAttributeCreationEvent event) {
		event.put(CLUCK_SHROOM, Chicken.createAttributes().build());
		event.put(WOOLY_COW, Cow.createAttributes().build());
		event.put(MELON_GOLEM, MelonGolem.createAttributes().build());
		event.put(BONE_SPIDER, BoneSpider.createAttributes().build());
		event.put(STRAY_BONE_SPIDER, BoneSpider.createAttributes().build());
		event.put(VILER_WITCH, VilerWitch.createAttributes().build());
	}
}