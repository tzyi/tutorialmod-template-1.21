package com.besson.tutorialmod.entity;

import com.besson.tutorialmod.TutorialMod;
import com.besson.tutorialmod.entity.custom.TigerEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<TigerEntity> TIGER = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(TutorialMod.MOD_ID, "tiger"),
            EntityType.Builder.create(TigerEntity::new, SpawnGroup.CREATURE).dimensions(1f,1f).build());
}
