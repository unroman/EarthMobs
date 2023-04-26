package baguchan.earthmobsmod.event;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.registry.ModItems;
import com.google.common.collect.Sets;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

@Mod.EventBusSubscriber(modid = EarthMobsMod.MODID)
public class LootEvents {
    private static final Set<ResourceLocation> TEMPLE_LOOT = Sets.newHashSet(new ResourceLocation("chests/jungle_temple"));
    private static final Set<ResourceLocation> ARCHEOLOGY = Sets.newHashSet(BuiltInLootTables.ANCIENT_CITY);
    private static final Set<ResourceLocation> DESERT_LOOT = Sets.newHashSet(BuiltInLootTables.DESERT_PYRAMID);

    @SubscribeEvent
    public static void onInjectLoot(LootTableLoadEvent event) {
        if (TEMPLE_LOOT.contains(event.getName())) {
            LootPool pool = LootPool.lootPool().apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))).add(LootItem.lootTableItem(ModItems.RUBY.get()).setWeight(2)).add(LootItem.lootTableItem(Items.AIR).setWeight(3)).build();
            event.getTable().addPool(pool);
        }

        if (DESERT_LOOT.contains(event.getName())) {
            LootPool pool = LootPool.lootPool().apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))).add(LootItem.lootTableItem(ModItems.RUBY.get()).setWeight(2)).add(LootItem.lootTableItem(Items.AIR).setWeight(3)).build();
            event.getTable().addPool(pool);
        }
    }
}