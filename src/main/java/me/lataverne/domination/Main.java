package me.lataverne.domination;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

import me.lataverne.domination.commands.DominationCommand;

public class Main extends JavaPlugin {
    public static List<String> games = new ArrayList<String>();

    @Override
    public void onEnable() {
        registerCommands();
        
    	getLogger().log(Level.INFO, "Domination has been successfully enabled");
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "Domination has been successfully disabled");
    }

    private void registerCommands() {
        getCommand("domination").setExecutor(new DominationCommand());
    }

}
