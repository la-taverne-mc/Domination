package me.lataverne.domination.game;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.common.collect.Lists;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import me.lataverne.domination.enums.Archetypes;
import me.lataverne.domination.tasks.GameTask;

public class Game {
    private Plugin plugin;
    private String name;
    private List<Flag> flags;
    private BukkitTask task;
    private boolean isRunning;
    private Map<UUID, Archetypes> playersArchetypes;
    public TeamDom red;
    public TeamDom blue;

    public Game(@NotNull String name) {
        this.plugin = Bukkit.getPluginManager().getPlugin("Domination");
        this.name = name;
        this.flags = Lists.newArrayList();
        this.red = new TeamDom("red");
        this.blue = new TeamDom("blue");
    }

    public String getName() { return name; }
    public boolean isRunning() { return isRunning; }

    public @Nullable Archetypes getPlayerSpecialty(@NotNull UUID playerUuid) { return playersArchetypes.get(playerUuid); }
    
    public void setPlayerSpecialty(@NotNull UUID playerUuid, @NotNull Archetypes archetype) {
        if (playersArchetypes.containsKey(playerUuid)) playersArchetypes.replace(playerUuid, archetype);
        else playersArchetypes.put(playerUuid, archetype);
    }

    public void createFlag(@NotNull String flagName, @NotNull Location flagLocation, @NotNull double flagRadius, @NotNull double bossBarRadius) {
        flags.add(new Flag(flagName, flagLocation, flagRadius, bossBarRadius));
    }

    public boolean start(@NotNull int gameDuration) {
        if (isRunning) return false;

        for (Flag flag : flags) {
            flag.activate();
        }

        task = new GameTask(this, gameDuration).runTaskTimer(plugin, 0, 20);
        
        isRunning = true;
        return true;
    }

    public boolean stop() {
        if (!isRunning) return false;

        for (Flag flag : flags) {
            flag.deactivate();
        }

        if (task != null && !task.isCancelled()) task.cancel();

        isRunning = false;
        return true;
    }

    //todo voir si on met un void ou on laisse un retour erreur
    public int addPlayer(Player player, String teamDom){
        switch (teamDom){
            case "red":
                this.red.addPlayer(player);
                return 1;
            case "blue":
                this.blue.addPlayer(player);
                return 1;
            default:
                return -1;
        }
    }

    //todo voir si on met un void ou on laisse un retour erreur
    public int setSpawn(Location location, String teamDom){
        switch (teamDom){
            case "red":
                this.red.setSpawn(location);
                return 1;
            case "blue":
                this.blue.setSpawn(location);
                return 1;
            default:
                return -1;
        }
    }

    public String foundTeam(Player player){
        if(this.red.hasPlayer(player))
            return "red";
        if(this.blue.hasPlayer(player))
            return "blue";
        return "Player not have team";
    }
}
