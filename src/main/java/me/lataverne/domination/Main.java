package me.lataverne.domination;

import java.util.logging.Level;

import com.google.common.collect.Lists;

import org.apache.commons.lang.RandomStringUtils;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import me.lataverne.domination.commands.DominationCommand;
import me.lataverne.domination.commands.ItemsCommand;
import me.lataverne.domination.game.Games;
import me.lataverne.domination.listeners.DeathListener;
import me.lataverne.domination.listeners.FireballWandListener;
import me.lataverne.domination.listeners.HealingWandListener;
import me.lataverne.domination.listeners.PreventBlockPlacing;
import me.lataverne.domination.listeners.PreventTeamModification;

public class Main extends JavaPlugin {
    private Games games;

    private String blueTeamName;
    private String redTeamName;

    public static Team blueTeam;
    public static Team redTeam;

    @Override
    public void onEnable() {
        loadGames();

        registerTeams();

        registerCommands();
        registerListeners();
        
    	getLogger().log(Level.INFO, "Domination has been successfully enabled");
    }

    @Override
    public void onDisable() {
        games.stopAll();

        blueTeam.unregister();
        redTeam.unregister();

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

        pluginManager.registerEvents(new PreventTeamModification(Lists.newArrayList(blueTeamName, redTeamName)), this);
        pluginManager.registerEvents(new PreventBlockPlacing(), this);
        pluginManager.registerEvents(new DeathListener(), this);

        pluginManager.registerEvents(new HealingWandListener(), this);
        pluginManager.registerEvents(new FireballWandListener(), this);
    }

    private void registerTeams() {
        ScoreboardManager scoreboardManager = getServer().getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getMainScoreboard();

        blueTeamName = RandomStringUtils.randomAlphanumeric(16);
        redTeamName = RandomStringUtils.randomAlphanumeric(16);

        scoreboard.registerNewTeam(blueTeamName);
        scoreboard.registerNewTeam(redTeamName);

        blueTeam = scoreboard.getTeam(blueTeamName);
        redTeam = scoreboard.getTeam(redTeamName);

        blueTeam.setDisplayName("Bleus");
        redTeam.setDisplayName("Rouges");

        blueTeam.setColor(ChatColor.BLUE);
        blueTeam.setPrefix(ChatColor.BLUE.toString());
        redTeam.setColor(ChatColor.RED);
        redTeam.setPrefix(ChatColor.RED.toString());

        blueTeam.setCanSeeFriendlyInvisibles(true);
        redTeam.setCanSeeFriendlyInvisibles(true);

        blueTeam.setAllowFriendlyFire(false);
        redTeam.setAllowFriendlyFire(false);
    }
}
