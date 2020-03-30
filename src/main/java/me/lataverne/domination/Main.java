package me.lataverne.domination;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import me.lataverne.domination.listeners.FireWandListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.lataverne.domination.commands.DominationCommand;
import me.lataverne.domination.listeners.HealingWandListener;
import me.lataverne.domination.listeners.JoinListener;

public class Main extends JavaPlugin {
    public static List<String> games = new ArrayList<String>();

    @Override
    public void onEnable() {
        registerCommands();
        registerListeners();
        
    	getLogger().log(Level.INFO, "Domination has been successfully enabled");
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "Domination has been successfully disabled");
    }

    private void registerCommands() {
        getCommand("domination").setExecutor(new DominationCommand());
    }
    
    private void registerListeners() {
        PluginManager pluginManager = getServer().getPluginManager();

       // pluginManager.registerEvents(new HealingWandListener(), this);
        pluginManager.registerEvents(new FireWandListener(), this);
        pluginManager.registerEvents(new JoinListener(), this);
    }
}
