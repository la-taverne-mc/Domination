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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import me.lataverne.domination.Main;
import me.lataverne.domination.enums.Archetypes;
import me.lataverne.domination.enums.Squads;
import me.lataverne.domination.tasks.GameTask;

public class Game {
    private Plugin plugin;
    private String name;
    private List<Flag> flags;
    private BukkitTask task;
    private boolean isRunning;
    private Map<UUID, Archetypes> playersArchetypes;
    private Squad red;
    private Squad blue;

    public Game(@NotNull String name) {
        this.plugin = Bukkit.getPluginManager().getPlugin("Domination");
        this.name = name;
        this.flags = Lists.newArrayList();
        this.blue = new Squad("Bleus", Main.blueTeam);
        this.red = new Squad("Rouges", Main.redTeam);
    }

    public String getName() { return name; }
    public boolean isRunning() { return isRunning; }

    public @Nullable Archetypes getPlayerArchetype(@NotNull UUID playerUuid) { return playersArchetypes.get(playerUuid); }
    
    public void setPlayerSpecialty(@NotNull UUID playerUuid, @NotNull Archetypes archetype) {
        if (playersArchetypes.containsKey(playerUuid)) playersArchetypes.replace(playerUuid, archetype);
        else playersArchetypes.put(playerUuid, archetype);
    }

    public void createFlag(@NotNull String flagName, @NotNull Location flagLocation, @NotNull double flagRadius, @NotNull double bossBarRadius) {
        flags.add(new Flag(flagName, this, flagLocation, flagRadius, bossBarRadius));
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

        blue.reset();
        red.reset();

        isRunning = false;
        return true;
    }

    public void addPlayer(@NotNull Player player, @NotNull Squads squad) {
        removePlayer(player);

        if (squad == Squads.BLUE) {
            blue.addPlayer(player);
        } else {
            red.addPlayer(player);
        }
    }

    public void removePlayer(@NotNull Player player) {
        if (hasPlayer(player)) {
            if (getPlayerTeam(player) == Squads.BLUE) {
                blue.removePlayer(player);
            } else {
                red.removePlayer(player);
            }
        }
    }

    public boolean hasPlayer(@NotNull Player player) { return blue.hasPlayer(player) || red.hasPlayer(player); }

    public @Nullable Squads getPlayerTeam(@NotNull Player player) {
        if (blue.hasPlayer(player)) {
            return Squads.BLUE;
        } else if (red.hasPlayer(player)) {
            return Squads.RED;
        } else {
            return null;
        }
    }

    public void setTeamSpawn(@NotNull Location spawn, @NotNull Squads squad) {
        if (squad == Squads.BLUE) {
            blue.setSpawn(spawn);
        } else {
            red.setSpawn(spawn);
        }
    }

    public Location getTeamSpawn(@NotNull Squads squad) {
        if (squad == Squads.BLUE) return blue.getSpawn();
        else return red.getSpawn();
    }
}