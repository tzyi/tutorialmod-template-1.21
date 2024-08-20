package com.besson.tutorialmod.world;

import com.besson.tutorialmod.TutorialMod;
import com.besson.tutorialmod.block.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> ICE_ETHER_TREE_PLACED_KEY = of("ice_ether_tree_placed");
    public static final RegistryKey<PlacedFeature> SIMPLE_FLOWER_PLACED_KEY = of("simple_flower_placed");
    public static final RegistryKey<PlacedFeature> ICE_ETHER_ORE_PLACED_KEY = of("ice_ether_ore_placed");
    public static final RegistryKey<PlacedFeature> NETHER_ICE_ETHER_ORE_PLACED_KEY = of("nether_ice_ether_ore_placed");
    public static final RegistryKey<PlacedFeature> END_ICE_ETHER_ORE_PLACED_KEY = of("end_ice_ether_ore_placed");
    public static void bootstrap(Registerable<PlacedFeature> featureRegisterable) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookup = featureRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        register(featureRegisterable, ICE_ETHER_TREE_PLACED_KEY, registryEntryLookup.getOrThrow(ModConfiguredFeatures.ICE_ETHER_TREE_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                        PlacedFeatures.createCountExtraModifier(2, 0.1f, 2),
                        ModBlocks.ICE_ETHER_SAPLING));

        register(featureRegisterable, SIMPLE_FLOWER_PLACED_KEY, registryEntryLookup.getOrThrow(ModConfiguredFeatures.SIMPLE_FLOWER_KEY),
                RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

        register(featureRegisterable, ICE_ETHER_ORE_PLACED_KEY, registryEntryLookup.getOrThrow(ModConfiguredFeatures.ICE_ETHER_ORE_KEY),
                ModOrePlacements.modifiersWithCount(12,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-80), YOffset.fixed(80))));
        register(featureRegisterable, NETHER_ICE_ETHER_ORE_PLACED_KEY, registryEntryLookup.getOrThrow(ModConfiguredFeatures.NETHER_ICE_ETHER_ORE_KEY),
                ModOrePlacements.modifiersWithCount(12,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-80), YOffset.fixed(80))));
        register(featureRegisterable, END_ICE_ETHER_ORE_PLACED_KEY, registryEntryLookup.getOrThrow(ModConfiguredFeatures.END_ICE_ETHER_ORE_KEY),
                ModOrePlacements.modifiersWithCount(12,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-80), YOffset.fixed(80))));
    }
    public static RegistryKey<PlacedFeature> of(String id) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(TutorialMod.MOD_ID, id));
    }
    public static void register(
            Registerable<PlacedFeature> featureRegisterable,
            RegistryKey<PlacedFeature> key,
            RegistryEntry<ConfiguredFeature<?, ?>> feature,
            List<PlacementModifier> modifiers
    ) {
        featureRegisterable.register(key, new PlacedFeature(feature, List.copyOf(modifiers)));
    }

    public static void register(
            Registerable<PlacedFeature> featureRegisterable,
            RegistryKey<PlacedFeature> key,
            RegistryEntry<ConfiguredFeature<?, ?>> feature,
            PlacementModifier... modifiers
    ) {
        register(featureRegisterable, key, feature, List.of(modifiers));
    }
}
