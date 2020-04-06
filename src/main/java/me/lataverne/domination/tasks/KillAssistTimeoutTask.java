package me.lataverne.domination.tasks;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import me.lataverne.domination.Main;

public class KillAssistTimeoutTask extends BukkitRunnable {
    private final Player damaged;
    private final Player damager;

    public KillAssistTimeoutTask(@NotNull Player damaged, @NotNull Player damager) {
        this.damaged = damaged;
        this.damager = damager;
    }

    @Override
    public void run() {
        Main.killAssists.remove(damaged, damager);
    }
}