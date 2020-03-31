package me.lataverne.domination.game;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import me.lataverne.domination.enums.FlagOwnership;
import me.lataverne.domination.tasks.FlagTask;

public class Flag {
    private Plugin plugin;
    private String name;
    private Location centerLocation;
    private double radius;
    private BukkitTask task;
    private int capturePoints; // pts < 0 = red; pts > 0 = blue
    private FlagOwnership ownership;
    private BossBar bossBar;
    private double bossBarRadius;

    public Flag(@NotNull String name, @NotNull Location centerLocation, @NotNull double radius, @NotNull double bossBarRadius) {
        this.plugin = Bukkit.getPluginManager().getPlugin("Domination");
        this.name = name;
        this.centerLocation = centerLocation;
        this.radius = radius;
        this.ownership = FlagOwnership.NONE;
        this.bossBar = Bukkit.createBossBar(ChatColor.WHITE + this.name, BarColor.WHITE, BarStyle.SOLID);
        this.bossBarRadius = bossBarRadius;
    }

    public Location getCenterLocation() { return centerLocation; }
    public double getRadius() { return radius; }
    public double getBossBarRadius() { return bossBarRadius; }

    public void activate() {
        BukkitRunnable runnable = new FlagTask(this);
        task = runnable.runTaskTimer(plugin, 0, 20);
    }

    public void deactivate() {
        task.cancel();
        bossBar.removeAll();
    }

    public void addPoints(@NotNull int points) {
        capturePoints = Math.max(-100, Math.min(capturePoints + points, 100));

        switch (capturePoints) {
            case 100:
                ownership = FlagOwnership.BLUE;
                break;
            
            case -100:
                ownership = FlagOwnership.RED;
                break;
            
            case 0:
                ownership = FlagOwnership.NONE;
                break;
            
            default:
                ownership = FlagOwnership.DISPUTED;
                break;
        }

        updateBossBar();
    }

    private void updateBossBar() {
        switch (ownership) {
            case BLUE:
                bossBar.setTitle(ChatColor.BLUE + name);
                bossBar.setColor(BarColor.BLUE);
                break;
            
            case DISPUTED:
                bossBar.setTitle(ChatColor.LIGHT_PURPLE + name);
                bossBar.setColor(BarColor.PURPLE);
                break;

            case RED:
                bossBar.setTitle(ChatColor.RED + name);
                bossBar.setColor(BarColor.RED);
                break;
            
            case NONE:
            default:
                bossBar.setTitle(ChatColor.WHITE + name);
                bossBar.setColor(BarColor.WHITE);
                break;
        }
    }

    public void setBossBarPlayers(List<Player> bossBarPlayers) {
        bossBar.removeAll();
        for (Player player : bossBarPlayers) {
            bossBar.addPlayer(player);
        }
    }
}
