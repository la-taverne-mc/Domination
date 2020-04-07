package me.lataverne.domination.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import me.lataverne.domination.Main;

public class PreventItemDrop implements Listener {
    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        if (Main.games.getPlayerGame(event.getPlayer()) != null) event.setCancelled(true);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (Main.games.getPlayerGame(event.getEntity()) != null) event.getDrops().clear();
    }
}