package me.lataverne.domination.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import me.lataverne.domination.Main;
import me.lataverne.domination.enums.Items;

public class PreventBlockPlacing implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (Items.contains(event.getItemInHand())) event.setCancelled(true);
        if (Main.games.getPlayerGame(event.getPlayer()) != null) event.setCancelled(true);
    }
}
