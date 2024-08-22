package com.besson.tutorialmod.particle;

import com.besson.tutorialmod.TutorialMod;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {
    public static final SimpleParticleType GREEN_FLAME = register("green_flame", FabricParticleTypes.simple());
    private static SimpleParticleType register(String name, SimpleParticleType type) {
        return Registry.register(Registries.PARTICLE_TYPE, Identifier.of(TutorialMod.MOD_ID, name), type);
    }
    public static void registerModParticles() {
    }
}
