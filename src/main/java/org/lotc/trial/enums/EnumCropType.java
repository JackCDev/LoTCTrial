package org.lotc.trial.enums;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Jaxon on 7/19/2017.
 */
public enum EnumCropType {
    WHEAT((byte)7,new ItemStack(Material.WHEAT), new ItemStack(Material.SEEDS)),
    MELON((byte)0,new ItemStack(Material.MELON), new ItemStack(Material.MELON_SEEDS)),
    PUMPKIN((byte)0, new ItemStack(Material.PUMPKIN), new ItemStack(Material.PUMPKIN_SEEDS)),
    POTATO((byte)7,new ItemStack(Material.POTATO_ITEM), null),
    CARROT((byte)7,new ItemStack(Material.CARROT_ITEM), null),
    COCOA((byte)2, new ItemStack(Material.COCOA), null),
    BEATS((byte)3,new ItemStack(Material.BEETROOT), new ItemStack(Material.BEETROOT_SEEDS)),
    NETHER_WART((byte)3,new ItemStack(Material.NETHER_WARTS), null);

    ItemStack cropType;
    ItemStack seedType;
    byte fullyGrown;

    EnumCropType(byte fullyGrown, ItemStack cropType, ItemStack seedType) {
        this.cropType = cropType;
        this.seedType = seedType;
        this.fullyGrown = fullyGrown;

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
    /*returns the data of the fully grown plant*/
    public byte getByte(){
        return this.fullyGrown;
    }

}
