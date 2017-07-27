package org.lotc.trial;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.lotc.trial.commands.ToggleTrample;
import org.lotc.trial.configs.TrampleConfig;
import org.lotc.trial.listeners.MainListener;
import sun.applet.Main;

/**
 * Created by Jaxon on 7/19/2017.
 */
public class LoTCTrial extends JavaPlugin {

    private MainListener mainListener;

    private static LoTCTrial plugin;

    public static LoTCTrial getInstance() {
        return plugin;
    }

    public void onEnable() {
        plugin = this;
        mainListener = new MainListener();
        /*Loads Commands*/
        this.getCommand("toggletrample").setExecutor(new ToggleTrample());

        /*Loads the Single Listener (Would have made a manager, but since there is only one Listener I didn't see much of a point)*/
        Bukkit.getPluginManager().registerEvents(mainListener, getInstance());

        /*Setups the Config*/
        TrampleConfig.setupConfig();
    }

    public void onDisable() {

    }
}
