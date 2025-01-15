package com.besson.tutorialmod.enchantment;

import com.besson.tutorialmod.TutorialMod;
import com.besson.tutorialmod.enchantment.custom.TestEnchantmentEffect;
import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantmentEffects {

    public static final MapCodec<? extends EnchantmentEntityEffect> TEST = register("test", TestEnchantmentEffect.CODEC);

    private static MapCodec<? extends EnchantmentEntityEffect> register(String name, MapCodec<? extends EnchantmentEntityEffect> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.of(TutorialMod.MOD_ID, name), codec);
    }

    public static void registerModEnchantmentEffects() {

    }
}
