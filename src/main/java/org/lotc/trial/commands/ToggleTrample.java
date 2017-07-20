package org.lotc.trial.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.lotc.trial.configs.TrampleConfig;

/**
 * Created by Jaxon on 7/19/2017.
 */
public class ToggleTrample implements CommandExecutor {

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            FileConfiguration configuration = TrampleConfig.getConfig();
            /*Makes sure there is a actual config section to read from.*/
            if (!configuration.contains(player.getUniqueId() + ".Toggles.Trample")) {
                configuration.set(player.getUniqueId() + ".Toggles.Trample", false);
            }
            Boolean trampleToggled = configuration.getBoolean(player.getUniqueId() + ".Toggles.Trample");
            /*If the trample toggle is already true it enables trampling crops*/
            if(trampleToggled) {
                player.sendMessage(ChatColor.RED + "** Trampling Enabled **");
                configuration.set(player.getUniqueId() + ".Toggles.Trample", false);
                /*Disables the trampling of crops if it returns false*/
            }else if(trampleToggled == false) {
                player.sendMessage(ChatColor.GREEN + "** Trampling Disabled **");
                configuration.set(player.getUniqueId() + ".Toggles.Trample", true);
            }
            TrampleConfig.saveConfig();
        }
        return false;
    }
}
