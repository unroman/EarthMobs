package baguchan.earthmobsmod.entity;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.block.Block;
import org.apache.commons.lang3.tuple.Pair;

public interface IFlowerCow {

	Pair<MobEffect, Integer> getEffectForCow();

	Block getFlower();
}