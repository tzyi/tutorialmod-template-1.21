package com.besson.tutorialmod.item.custom;

import com.besson.tutorialmod.item.AbstractDurabilityItem;
import net.minecraft.item.Item;

public class FireEther extends Item implements AbstractDurabilityItem {
    public FireEther(Item.Settings settings) {
        super(settings.maxDamage(128));
    }
}
