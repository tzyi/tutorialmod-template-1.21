package com.besson.tutorialmod.world;

import com.besson.tutorialmod.TutorialMod;
import com.besson.tutorialmod.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> ICE_ETHER_TREE_KEY = of("ice_ether_tree");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SIMPLE_FLOWER_KEY = of("simple_flower");

    public static final RegistryKey<ConfiguredFeature<?, ?>> ICE_ETHER_ORE_KEY = of("ice_ether_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> NETHER_ICE_ETHER_ORE_KEY = of("nether_ice_ether_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> END_ICE_ETHER_ORE_KEY = of("end_ice_ether_ore");
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

        RuleTest stoneReplace = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepSlateReplace = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherReplace = new TagMatchRuleTest(BlockTags.BASE_STONE_NETHER);
        RuleTest endReplace = new BlockMatchRuleTest(Blocks.END_STONE);

        List<OreFeatureConfig.Target> overWorldTargets = List.of(
                OreFeatureConfig.createTarget(stoneReplace, ModBlocks.ICE_ETHER_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(deepSlateReplace, ModBlocks.ICE_ETHER_ORE.getDefaultState()));
        List<OreFeatureConfig.Target> netherTargets = List.of(
                OreFeatureConfig.createTarget(netherReplace, ModBlocks.ICE_ETHER_ORE.getDefaultState()));
        List<OreFeatureConfig.Target> endTargets = List.of(
                OreFeatureConfig.createTarget(endReplace, ModBlocks.ICE_ETHER_ORE.getDefaultState()));

        register(featureRegisterable, ICE_ETHER_ORE_KEY, Feature.ORE, new OreFeatureConfig(overWorldTargets, 8));
        register(featureRegisterable, NETHER_ICE_ETHER_ORE_KEY, Feature.ORE, new OreFeatureConfig(netherTargets, 8));
        register(featureRegisterable, END_ICE_ETHER_ORE_KEY, Feature.ORE, new OreFeatureConfig(endTargets, 8));
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
