package me.lataverne.domination.listeners;

import org.bukkit.entity.SmallFireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import me.lataverne.domination.enums.Items;

public class FireballWandListener implements Listener {
    @EventHandler
    public void onUse(PlayerInteractEvent event) {
        ItemStack item;

        if (event.getHand() == EquipmentSlot.HAND) {
            item = event.getPlayer().getInventory().getItemInMainHand();
        } else {
            item = event.getPlayer().getInventory().getItemInOffHand();
        }

        if(event.getPlayer().hasCooldown(item.getType())) return;

        if (Items.FIREBALL_WAND.compareTo(item)) {
            event.getPlayer().setCooldown(item.getType(),20*3);
            event.getPlayer().launchProjectile(SmallFireball.class);
        }
    }
}
