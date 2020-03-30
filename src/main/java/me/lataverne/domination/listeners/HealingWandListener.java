package me.lataverne.domination.listeners;

import org.bukkit.entity.SmallFireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import me.lataverne.domination.Items;

public class HealingWandListener implements Listener {
    @EventHandler
    public void onUse(PlayerInteractEvent event) {
        ItemStack item;
        if (event.getHand() == EquipmentSlot.HAND) {
            item = event.getPlayer().getInventory().getItemInMainHand();
        } else {
            item = event.getPlayer().getInventory().getItemInOffHand();
        }

        if (Items.FIREBALL_WAND.compareTo(item)) {
            event.getPlayer().launchProjectile(SmallFireball.class);
        }
    }
}
