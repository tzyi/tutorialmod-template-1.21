package com.besson.tutorialmod.entity;

import com.besson.tutorialmod.TutorialMod;
import com.besson.tutorialmod.block.ModBlocks;
import com.besson.tutorialmod.item.ModItems;
import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModBoats {
    public static final Identifier ICE_ETHER_BOAT = Identifier.of(TutorialMod.MOD_ID, "ice_ether_boat");
    public static final Identifier ICE_ETHER_CHEST_BOAT = Identifier.of(TutorialMod.MOD_ID, "ice_ether_chest_boat");
    public static final RegistryKey<TerraformBoatType> ICE_ETHER_BOAT_KEY = TerraformBoatTypeRegistry.createKey(ICE_ETHER_BOAT);
    public static void registerModBoats() {
        TerraformBoatType iceEtherBoat = new TerraformBoatType.Builder()
                .item(ModItems.ICE_ETHER_BOAT)
                .chestItem(ModItems.ICE_ETHER_CHEST_BOAT)
                .planks(ModBlocks.ICE_ETHER_PLANKS.asItem())
                .build();

        Registry.register(TerraformBoatTypeRegistry.INSTANCE, ICE_ETHER_BOAT_KEY, iceEtherBoat);
    }
}
