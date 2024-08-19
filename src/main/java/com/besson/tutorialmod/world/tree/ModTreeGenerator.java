package com.besson.tutorialmod.world.tree;

import com.besson.tutorialmod.TutorialMod;
import com.besson.tutorialmod.world.ModConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

public class ModTreeGenerator {
    public static final SaplingGenerator ICE_ETHER_TREE = new SaplingGenerator(
            TutorialMod.MOD_ID + ":ice_ether_tree",
            Optional.empty(),
            Optional.of(ModConfiguredFeatures.ICE_ETHER_TREE_KEY),
            Optional.empty()
    );
}
