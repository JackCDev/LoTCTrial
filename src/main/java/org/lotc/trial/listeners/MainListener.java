package org.lotc.trial.listeners;


import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.lotc.trial.Utils.MiscUtils;
import org.lotc.trial.configs.TrampleConfig;
import org.lotc.trial.enums.EnumCropType;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.oliwali.HawkEye.DataType;
import uk.co.oliwali.HawkEye.entry.DataEntry;
import uk.co.oliwali.HawkEye.util.HawkEyeAPI;

import java.util.Random;

/**
 * Created by Jaxon on 7/19/2017.
 */
public class MainListener implements Listener {

    /*Trample Disabler.*/
    @EventHandler
    public void onEntityInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        if (event.isCancelled()) return;
        /*Checks if Action was physical, could have left this out but I'de rather have this check than not.*/
        if(event.getAction() == Action.PHYSICAL) {
            /*Checks if trampled block is soil.*/
            if(block.getType() == Material.SOIL ) {
                /*Does the final check to make sure the player has his toggles disabled, and cancels the event.*/
                if(TrampleConfig.getConfig().getBoolean(player.getUniqueId() + ".Toggles.Trample") == true){event.setCancelled(true);}
            }
        }

    }

    /*Disables Pistons from breaking crops.*/
    @EventHandler
    public void onBlockPistonExtend(BlockPistonExtendEvent event) {
        // Gets the block in the new Direction
        final Block block = event.getBlock().getRelative(event.getDirection());
        if(block.getType() == Material.CROPS) {
            event.setCancelled(true);
        }
    }

    /*Handles the dropping of the item, and checking what Item is used.*/
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Material blockType = event.getBlock().getType();
        ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();
        /*The Amounts that are going to drop*/
        Random random = new Random();
        int rarityAmount = random.nextInt(20) + 1;
        int cropAmount = 0;
        int seedAmount = random.nextInt(2) + 1;
        /*Makes sure the item broke is a crop.*/
        if (EnumCropType.enumFromMaterial(blockType) == null) {
            return;
        }
        /*Clears the drops to make sure nothing sticky happens ;)*/
        event.setCancelled(true);
        block.setType(Material.AIR);

        /*Checks to make sure that the crop is fully grown.*/
        if (EnumCropType.enumFromMaterial(blockType).getByte() == block.getData()) {
        /*Checks which item the player is using*/
            switch (itemInHand.getType()) {
                case WOOD_HOE:
                case STONE_HOE:
                    cropAmount = 1;
                    break;
                case IRON_HOE:
                case GOLD_HOE:
                    cropAmount = 2;
                    break;
                case DIAMOND_HOE:
                    cropAmount = 3;
                    break;
                default:
                    seedAmount = 0;
                    break;
            }
            /*Logs the block break with Hawke-Eye ;)*/
            HawkEyeAPI.addEntry(new DataEntry(player, DataType.BLOCK_BREAK, block.getLocation()));

            /*Removes the correct Durability from the Item (Unbreaking already considers the Dura)*/
            itemInHand.setDurability((short)(itemInHand.getDurability() + 1));

            /*Checks to make sure there is enough to drop before running this.*/
            if (cropAmount >= 1) {
            /*Checks if Item has Luck enchant and adds the # of luck onto the drop amount.*/
                if (itemInHand.getEnchantments().containsKey(Enchantment.LOOT_BONUS_BLOCKS)) {
                    final int luckAmount = itemInHand.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
                    seedAmount = luckAmount + seedAmount;
                    cropAmount = luckAmount + cropAmount;
                }
            /*Adds a little spice to the drops! :D*/
                if (rarityAmount <= 2) {
                    ItemStack itemStack = new ItemStack(Material.DIAMOND);
                    block.getWorld().dropItemNaturally(block.getLocation(), itemStack);
                    player.sendMessage(ChatColor.AQUA + "While farming you dug up diamonds!");
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                    MiscUtils.doFirework(player);
                }

            /*Drops the seed if the amount is above 1*/
                if (seedAmount >= 1) {
                    ItemStack seedToDrop = EnumCropType.enumFromMaterial(blockType).toSeedItemStack();
                    seedToDrop.setAmount(cropAmount);
                    block.getWorld().dropItemNaturally(block.getLocation(), seedToDrop);
                }
            /*Drops the current crop amount*/
                ItemStack cropToDrop = EnumCropType.enumFromMaterial(blockType).toCropItemStack();
                cropToDrop.setAmount(cropAmount);
                block.getWorld().dropItemNaturally(block.getLocation(), cropToDrop);
            }
        }
    }
}
