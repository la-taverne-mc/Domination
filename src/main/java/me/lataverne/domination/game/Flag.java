package me.lataverne.domination.game;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Game game;
    private Location centerLocation;
    private double radius;
    private BukkitTask task;
    private int capturePoints; // pts < 0 = red; pts > 0 = blue
    private FlagOwnership ownership;
    private BossBar bossBar;
    private double bossBarRadius;
    private Set<Player> lastBossBarPlayers;

    public Flag(@NotNull String name, @NotNull Game game, @NotNull Location centerLocation, @NotNull double radius, @NotNull double bossBarRadius) {
        this.plugin = Bukkit.getPluginManager().getPlugin("Domination");
        this.name = name;
        this.game = game;
        this.centerLocation = centerLocation;
        this.radius = radius;
        this.ownership = FlagOwnership.NONE;
        this.bossBar = Bukkit.createBossBar(ChatColor.WHITE + this.name, BarColor.WHITE, BarStyle.SOLID);
        this.bossBarRadius = bossBarRadius;
        this.lastBossBarPlayers = new HashSet<Player>();
    }

    public Game getGame() { return game; }
    public Location getCenterLocation() { return centerLocation; }
    public double getRadius() { return radius; }
    public double getBossBarRadius() { return bossBarRadius; }

    public void activate() {
        BukkitRunnable runnable = new FlagTask(this);
        task = runnable.runTaskTimer(plugin, 0, 20);
    }

    public void deactivate() {
        if (task != null && !task.isCancelled()) task.cancel();
        bossBar.removeAll();
    }

    public void updatePoints(@NotNull int bluePlayers, @NotNull int redPlayers) {
        if (bluePlayers > 0 && redPlayers > 0) {
            ownership = FlagOwnership.DISPUTED;
        } else if (bluePlayers > 0 && redPlayers <= 0) {
            capturePoints = Math.min(capturePoints + bluePlayers, 100);

            if (capturePoints > 0) {
                ownership = FlagOwnership.BLUE;
            } else {
                ownership = FlagOwnership.RED;
            }
        } else if (bluePlayers <= 0 && redPlayers > 0) {
            capturePoints = Math.max(capturePoints - redPlayers, -100);

            if (capturePoints > 0) {
                ownership = FlagOwnership.BLUE;
            } else {
                ownership = FlagOwnership.RED;
            }
        }

        updateBossBar();
    }

    private void updateBossBar() {
        double progress = Math.abs(capturePoints) / 100d;
        bossBar.setProgress(progress);

        ownership = capturePoints == 0 ? FlagOwnership.NONE : ownership;

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
        for (Player player : bossBarPlayers) {
            if (!lastBossBarPlayers.contains(player)) bossBar.addPlayer(player);
            bossBarPlayers.add(player);
        }

        lastBossBarPlayers.removeAll(new HashSet<Player>(bossBarPlayers));

        for (Player player : bossBarPlayers) {
            if (player != null) bossBar.removePlayer(player);
        }

        lastBossBarPlayers = new HashSet<Player>(bossBarPlayers);
    }
}
