package com.besson.tutorialmod.world.biome.surface;

import com.besson.tutorialmod.block.ModBlocks;
import com.besson.tutorialmod.world.biome.ModBiomes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import terrablender.api.TerraBlenderApi;

public class ModMaterialRules {
    private static final MaterialRules.MaterialRule DIRT = makeRule(Blocks.DIRT);
    private static final MaterialRules.MaterialRule GRASS_BLOCK = makeRule(Blocks.GRASS_BLOCK);
    private static final MaterialRules.MaterialRule ICE_ETHER = makeRule(ModBlocks.ICE_ETHER_BLOCK);
    private static final MaterialRules.MaterialRule DIAMOND = makeRule(Blocks.DIAMOND_BLOCK);

    public static MaterialRules.MaterialRule makeRules() {
        MaterialRules.MaterialCondition isAtOrAboveWaterLevel = MaterialRules.water(-1,0);
        MaterialRules.MaterialRule grassSurface = MaterialRules.sequence(MaterialRules.condition(isAtOrAboveWaterLevel,GRASS_BLOCK),DIRT);

        return MaterialRules.sequence(
                MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(ModBiomes.DIAMOND_BIOME),
                        MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, DIAMOND)),
                        MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, ICE_ETHER)),

                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, grassSurface)
        );
    }
    private static MaterialRules.MaterialRule makeRule(Block block) {
        return MaterialRules.block(block.getDefaultState());
    }
}
