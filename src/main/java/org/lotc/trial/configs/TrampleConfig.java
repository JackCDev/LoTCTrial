package org.lotc.trial.configs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.lotc.trial.LoTCTrial;

import java.io.File;
import java.io.IOException;

/**
 * Created by Jaxon on 7/20/2017.
 */
    public class TrampleConfig {
        static FileConfiguration mainFile;
        static File togglesFile;


        /*Setups the Trample Config to make sure it actually loads*/
        public static void setupConfig() {
            togglesFile = new File(LoTCTrial.getInstance().getDataFolder(), "Toggles.yml");

            if (!togglesFile.exists()) {
                try {
                    togglesFile.createNewFile();
                } catch (IOException e) {
                }
            }
            /*Sets Mainfil as the YAML config*/
            mainFile = YamlConfiguration.loadConfiguration(togglesFile);
        }
        /*Returns the entire config*/
        public static FileConfiguration getConfig() {
            return mainFile;
        }

        /*Saves the config ;O*/
        public static void saveConfig() {
            try {
                mainFile.save(togglesFile);
            } catch (IOException e) {
                Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save Toggles.yml!");
            }
        }
        /*reloadsConfig*/
        public static void reloadConfig() {
            mainFile = YamlConfiguration.loadConfiguration(togglesFile);
        }

    }
