package com.besson.tutorialmod.world;

import com.besson.tutorialmod.TutorialMod;
import com.besson.tutorialmod.block.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> ICE_ETHER_TREE_KEY = of("ice_ether_tree");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SIMPLE_FLOWER_KEY = of("simple_flower");
    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> featureRegisterable) {
        register(featureRegisterable, ICE_ETHER_TREE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.ICE_ETHER_LOG),
                new StraightTrunkPlacer(4, 3, 2),
                BlockStateProvider.of(ModBlocks.ICE_ETHER_LEAVES),
                new BlobFoliagePlacer(ConstantIntProvider.create(4), ConstantIntProvider.create(2), 2),
                new TwoLayersFeatureSize(1, 0, 2)
        ).build());

        register(featureRegisterable, SIMPLE_FLOWER_KEY, Feature.FLOWER, new RandomPatchFeatureConfig(32,6,2,
                PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.SIMPLE_FLOWER)))));
    }
    public static RegistryKey<ConfiguredFeature<?, ?>> of(String id) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(TutorialMod.MOD_ID, id));
    }
    public static <FC extends FeatureConfig, F extends Feature<FC>> void register(
            Registerable<ConfiguredFeature<?, ?>> registerable, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC config
    ) {
        registerable.register(key, new ConfiguredFeature<FC, F>(feature, config));
    }
}
