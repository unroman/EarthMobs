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
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = EarthMobsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, EarthMobsMod.MODID);

	public static final RegistryObject<EntityType<CluckShroom>> CLUCK_SHROOM = ENTITIES.register("cluck_shroom", () -> EntityType.Builder.of(CluckShroom::new, MobCategory.CREATURE).sized(0.4F, 0.7F).clientTrackingRange(10).build(prefix("cluck_shroom")));
	public static final RegistryObject<EntityType<WoolyCow>> WOOLY_COW = ENTITIES.register("wooly_cow", () -> EntityType.Builder.of(WoolyCow::new, MobCategory.CREATURE).sized(0.9F, 1.4F).clientTrackingRange(10).build(prefix("wooly_cow")));
	public static final RegistryObject<EntityType<HornedSheep>> HORNED_SHEEP = ENTITIES.register("horned_sheep", () -> EntityType.Builder.of(HornedSheep::new, MobCategory.CREATURE).sized(0.9F, 1.3F).clientTrackingRange(10).build(prefix("horned_sheep")));
	public static final RegistryObject<EntityType<HyperRabbit>> HYPER_RABBIT = ENTITIES.register("hyper_rabbit", () -> EntityType.Builder.of(HyperRabbit::new, MobCategory.CREATURE).sized(0.4F, 0.6F).clientTrackingRange(8).build(prefix("hyper_rabbit")));


	public static final RegistryObject<EntityType<MelonGolem>> MELON_GOLEM = ENTITIES.register("melon_golem", () -> EntityType.Builder.of(MelonGolem::new, MobCategory.MISC).sized(0.7F, 1.9F).immuneTo(Blocks.POWDER_SNOW).clientTrackingRange(8).build(prefix("melon_golem")));


	public static final RegistryObject<EntityType<BoneSpider>> BONE_SPIDER = ENTITIES.register("bone_spider", () -> EntityType.Builder.of(BoneSpider::new, MobCategory.MONSTER).sized(1.4F, 0.9F).build(prefix("bone_spider")));
	public static final RegistryObject<EntityType<StrayBoneSpider>> STRAY_BONE_SPIDER = ENTITIES.register("stray_bone_spider", () -> EntityType.Builder.of(StrayBoneSpider::new, MobCategory.MONSTER).sized(1.4F, 0.9F).build(prefix("stray_bone_spider")));

	public static final RegistryObject<EntityType<VilerWitch>> VILER_WITCH = ENTITIES.register("viler_witch", () -> EntityType.Builder.of(VilerWitch::new, MobCategory.MONSTER).sized(0.6F, 1.95F).build(prefix("viler_witch")));

	public static final RegistryObject<EntityType<BoulderingZombie>> BOULDERING_ZOMBIE = ENTITIES.register("bouldering_zombie", () -> EntityType.Builder.of(BoulderingZombie::new, MobCategory.MONSTER).sized(0.6F, 1.95F).build(prefix("bouldering_zombie")));


	public static final RegistryObject<EntityType<SmellyEgg>> SMELLY_EGG = ENTITIES.register("smelly_egg", () -> EntityType.Builder.<SmellyEgg>of(SmellyEgg::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build(prefix("smelly_egg")));
	public static final RegistryObject<EntityType<BoneShard>> BONE_SHARD = ENTITIES.register("bone_shard", () -> EntityType.Builder.<BoneShard>of(BoneShard::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build(prefix("bone_shard")));
	public static final RegistryObject<EntityType<MelonSeed>> MELON_SEED = ENTITIES.register("melon_seeds", () -> EntityType.Builder.<MelonSeed>of(MelonSeed::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build(prefix("melon_seeds")));

	private static String prefix(String path) {
		return EarthMobsMod.MODID + "." + path;
	}

	@SubscribeEvent
	public static void registerEntityAttribute(EntityAttributeCreationEvent event) {


		Raid.RaiderType.create("viler_witch", VILER_WITCH.get(), new int[]{0, 0, 1, 0, 1, 1, 2, 1});

		SpawnPlacements.register(CLUCK_SHROOM.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CluckShroom::checkCluckShroomSpawnRules);
		SpawnPlacements.register(WOOLY_COW.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
		SpawnPlacements.register(HORNED_SHEEP.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
		SpawnPlacements.register(HYPER_RABBIT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
		SpawnPlacements.register(MELON_GOLEM.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
		SpawnPlacements.register(BONE_SPIDER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
		SpawnPlacements.register(STRAY_BONE_SPIDER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
		SpawnPlacements.register(VILER_WITCH.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
		SpawnPlacements.register(BOULDERING_ZOMBIE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);


		event.put(CLUCK_SHROOM.get(), Chicken.createAttributes().build());
		event.put(WOOLY_COW.get(), Cow.createAttributes().build());
		event.put(HORNED_SHEEP.get(), HornedSheep.createAttributes().build());
		event.put(HYPER_RABBIT.get(), HyperRabbit.createAttributes().build());
		event.put(MELON_GOLEM.get(), MelonGolem.createAttributes().build());
		event.put(BONE_SPIDER.get(), BoneSpider.createAttributes().build());
		event.put(STRAY_BONE_SPIDER.get(), BoneSpider.createAttributes().build());
		event.put(VILER_WITCH.get(), VilerWitch.createAttributes().build());
		event.put(BOULDERING_ZOMBIE.get(), BoulderingZombie.createAttributes().build());
	}
}