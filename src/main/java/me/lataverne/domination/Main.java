package me.lataverne.domination;

import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
    	getLogger().log(Level.INFO, "Domination has been successfully enabled");
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "Domination has been successfully disabled");
    }
}