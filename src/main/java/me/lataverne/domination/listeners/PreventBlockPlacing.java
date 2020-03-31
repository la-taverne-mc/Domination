package me.lataverne.domination.listeners;

import me.lataverne.domination.enums.Items;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class PreventBlockPlacing implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (Items.contains(event.getItemInHand())) event.setCancelled(true);
    }
}
