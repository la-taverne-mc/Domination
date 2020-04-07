package me.lataverne.domination;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import com.google.common.collect.Lists;

import org.apache.commons.lang.RandomStringUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import me.lataverne.domination.commands.DominationCommand;
import me.lataverne.domination.commands.ItemsCommand;
import me.lataverne.domination.game.Games;
import me.lataverne.domination.game.SerializedFlag;
import me.lataverne.domination.game.SerializedGame;
import me.lataverne.domination.listeners.FireballWandListener;
import me.lataverne.domination.listeners.HealingWandListener;
import me.lataverne.domination.listeners.KillAssistListener;
import me.lataverne.domination.listeners.PreventBlockPlacing;
import me.lataverne.domination.listeners.PreventItemDrop;
import me.lataverne.domination.listeners.PreventTeamModification;
import me.lataverne.domination.utils.FileManager;

public class Main extends JavaPlugin {
    private FileManager saveFile;
    private FileConfiguration saveContent;

    public static Games games;

    private String blueTeamName;
    private String redTeamName;

    public static Team blueTeam;
    public static Team redTeam;

    public static Scoreboard mainScoreboard;

    public static HashMap<Player, Player> killAssists = new HashMap<Player, Player>(); // HashMap<Damaged, Damager>

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

        saveGames();

        getLogger().log(Level.INFO, "Domination has been successfully disabled");
    }

    private void loadGames() {
        saveFile = new FileManager(this, "save.yml");
        saveContent = saveFile.getContent();

        List<SerializedGame> serializedGames = Lists.newArrayList();
        for (String gameName : saveContent.getKeys(false)) {
            String blueSpawn = saveContent.getString(gameName + ".blueSpawn");
            String redSpawn = saveContent.getString(gameName + ".redSpawn");

            List<SerializedFlag> flags = Lists.newArrayList();
            if (saveContent.isSet(gameName + ".flags")) {
                for (String flagName : saveContent.getConfigurationSection(gameName + ".flags").getKeys(false)) {
                    String centerLocation = saveContent.getString(gameName + ".flags." + flagName + ".centerLocation");
                    String radius = saveContent.getString(gameName + ".flags." + flagName + ".radius");
                    String bossBarRadius = saveContent.getString(gameName + ".flags." + flagName + ".bossBarRadius");
                    flags.add(new SerializedFlag(flagName, centerLocation, radius, bossBarRadius));
                }
            }

            serializedGames.add(new SerializedGame(gameName, blueSpawn, redSpawn, flags));
        }

        games = new Games(serializedGames);
    }

    private void saveGames() {
        saveFile.clear();

        List<SerializedGame> serializedGames = games.serialize();

        if (serializedGames != null) {
            for (SerializedGame serializedGame : serializedGames) {
                if (serializedGame.blueSpawn != null) saveContent.set(serializedGame.name + ".blueSpawn", serializedGame.blueSpawn);
                if (serializedGame.redSpawn != null) saveContent.set(serializedGame.name + ".redSpawn", serializedGame.redSpawn);

                if (serializedGame.flags != null) {
                    for (SerializedFlag serializedFlag : serializedGame.flags) {
                        saveContent.set(serializedGame.name + ".flags." + serializedFlag.name + ".centerLocation", serializedFlag.centerLocation);
                        saveContent.set(serializedGame.name + ".flags." + serializedFlag.name + ".radius", serializedFlag.radius);
                        saveContent.set(serializedGame.name + ".flags." + serializedFlag.name + ".bossBarRadius", serializedFlag.bossBarRadius);
                    }
                }
            }
        }

        saveFile.save();
    }

    private void registerCommands() {
        getCommand("domination").setExecutor(new DominationCommand(games));
        getCommand("items").setExecutor(new ItemsCommand());
    }
    
    private void registerListeners() {
        PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new PreventTeamModification(Lists.newArrayList(blueTeamName, redTeamName)), this);
        pluginManager.registerEvents(new PreventBlockPlacing(), this);
        pluginManager.registerEvents(new PreventItemDrop(), this);

        pluginManager.registerEvents(new KillAssistListener(), this);

        pluginManager.registerEvents(new HealingWandListener(), this);
        pluginManager.registerEvents(new FireballWandListener(), this);
    }

    private void registerTeams() {
        ScoreboardManager scoreboardManager = getServer().getScoreboardManager();
        mainScoreboard = scoreboardManager.getMainScoreboard();

        blueTeamName = RandomStringUtils.randomAlphanumeric(16);
        redTeamName = RandomStringUtils.randomAlphanumeric(16);

        mainScoreboard.registerNewTeam(blueTeamName);
        mainScoreboard.registerNewTeam(redTeamName);

        blueTeam = mainScoreboard.getTeam(blueTeamName);
        redTeam = mainScoreboard.getTeam(redTeamName);

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
