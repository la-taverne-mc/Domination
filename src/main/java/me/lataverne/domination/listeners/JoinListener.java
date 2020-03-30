package me.lataverne.domination.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.lataverne.domination.Items;

public class JoinListener implements Listener {
    @EventHandler
    public void onLogin(PlayerJoinEvent event) {
        event.getPlayer().getInventory().addItem(Items.FIREBALL_WAND.getItem());
        event.getPlayer().updateInventory();
    }
}