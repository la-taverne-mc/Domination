package me.lataverne.domination;

import java.util.logging.Level;

import me.lataverne.domination.listeners.*;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.lataverne.domination.commands.DominationCommand;
import me.lataverne.domination.commands.ItemsCommand;
import me.lataverne.domination.game.Games;

public class Main extends JavaPlugin {
    private Games games;

    @Override
    public void onEnable() {
        loadGames();

        registerCommands();
        registerListeners();
        
    	getLogger().log(Level.INFO, "Domination has been successfully enabled");
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "Domination has been successfully disabled");
    }

    private void loadGames() {
        games = new Games();
    }

    private void registerCommands() {
        getCommand("domination").setExecutor(new DominationCommand(games));
        getCommand("items").setExecutor(new ItemsCommand());
    }
    
    private void registerListeners() {
        PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new PreventBlockPlacing(), this);
        pluginManager.registerEvents(new DeathListener(), this);
        pluginManager.registerEvents(new OnEntityShootCrossbowEvent(), this);
        pluginManager.registerEvents(new OnEntityLunchArrowEvent(), this);
        pluginManager.registerEvents(new DragonEffectListener(), this);
        pluginManager.registerEvents(new HealingWandListener(), this);
        pluginManager.registerEvents(new FireballWandListener(), this);
    }
}
