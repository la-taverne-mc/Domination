package me.lataverne.domination.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.lataverne.domination.Main;

public class DeathListener implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (Main.games.getPlayerGame(event.getEntity()) != null) event.getDrops().clear();
    }
}