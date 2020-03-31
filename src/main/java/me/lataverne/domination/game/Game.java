package me.lataverne.domination.game;

import java.util.List;

import com.google.common.collect.Lists;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import me.lataverne.domination.tasks.GameTask;

public class Game {
    private Plugin plugin;
    private String name;
    private List<Flag> flags;
    private BukkitTask task;
    private boolean isRunning;

    public Game(@NotNull String name) {
        this.plugin = Bukkit.getPluginManager().getPlugin("Domination");
        this.name = name;
        this.flags = Lists.newArrayList();
    }

    public String getName() { return name; }
    public boolean isRunning() { return isRunning; }

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
}
