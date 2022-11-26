package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, EarthMobsMod.MODID);

	public static final RegistryObject<SoundEvent> BONE_SPIDER_SAY = createEvent("entity.bone_spider.say");
	public static final RegistryObject<SoundEvent> BONE_SPIDER_HURT = createEvent("entity.bone_spider.hurt");
	public static final RegistryObject<SoundEvent> BONE_SPIDER_SPIT = createEvent("entity.bone_spider.spit");
	public static final RegistryObject<SoundEvent> BONE_SPIDER_WALK = createEvent("entity.bone_spider.walk");
	public static final RegistryObject<SoundEvent> BONE_SPIDER_DEATH = createEvent("entity.bone_spider.death");

	public static final RegistryObject<SoundEvent> DUCK_IDLE = createEvent("entity.duck.idle");
	public static final RegistryObject<SoundEvent> DUCK_STEP = createEvent("entity.duck.step");

	public static final RegistryObject<SoundEvent> SKELETON_WOLF_BARK = createEvent("entity.skeleton_wolf.bark");
	public static final RegistryObject<SoundEvent> SKELETON_WOLF_GROWL = createEvent("entity.skeleton_wolf.growl");
	public static final RegistryObject<SoundEvent> SKELETON_WOLF_HURT = createEvent("entity.skeleton_wolf.hurt");
	public static final RegistryObject<SoundEvent> SKELETON_WOLF_DEATH = createEvent("entity.skeleton_wolf.death");
	public static final RegistryObject<SoundEvent> SKELETON_WOLF_STEP = createEvent("entity.skeleton_wolf.step");
	public static final RegistryObject<SoundEvent> SKELETON_WOLF_SHAKE = createEvent("entity.skeleton_wolf.shake");
	public static final RegistryObject<SoundEvent> SKELETON_WOLF_PANTING = createEvent("entity.skeleton_wolf.panting");
	public static final RegistryObject<SoundEvent> SKELETON_WOLF_WHINE = createEvent("entity.skeleton_wolf.whine");


	private static RegistryObject<SoundEvent> createEvent(String sound) {
		ResourceLocation name = new ResourceLocation(EarthMobsMod.MODID, sound);
		return SOUND_EVENTS.register(sound, () -> new SoundEvent(name));
	}

}