package org.lotc.trial.enums;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Jaxon on 7/19/2017.
 */
public enum EnumCropType {
    WHEAT(new ItemStack(Material.WHEAT), new ItemStack(Material.SEEDS)),
    MELON(new ItemStack(Material.MELON), new ItemStack(Material.MELON_SEEDS)),
    PUMPKIN(new ItemStack(Material.PUMPKIN), new ItemStack(Material.PUMPKIN_SEEDS)),
    POTATO(new ItemStack(Material.POTATO), null),
    CARROT(new ItemStack(Material.CARROT), null),
    COCOA(new ItemStack(Material.COCOA), null),
    BEATS(new ItemStack(Material.BEETROOT), new ItemStack(Material.BEETROOT_SEEDS)),
    NETHER_WART(new ItemStack(Material.NETHER_WARTS), null);

    ItemStack cropType;
    ItemStack seedType;

    EnumCropType(ItemStack cropType, ItemStack seedType) {
        this.cropType = cropType;
        this.seedType = seedType;
    }


    /*Returns a Enum from the block that is broken*/
    public static EnumCropType enumFromMaterial(Material material) {
        switch(material) {
            case CROPS:
                return WHEAT;
            case MELON_BLOCK:
                return MELON;
            case PUMPKIN:
                return PUMPKIN;
            case POTATO:
                return POTATO;
            case CARROT:
                return CARROT;
            case COCOA:
                return COCOA;
            case BEETROOT_BLOCK:
                return BEATS;
            case NETHER_WART_BLOCK:
                return NETHER_WART;
        }
        return null;
    }
    /*Returns the Crop to drop*/
    public ItemStack toCropItemStack(){
        return this.cropType;
    }
    /*Returns the Seed to drop*/
    public ItemStack toSeedItemStack(){
        return this.seedType;
    }

}
