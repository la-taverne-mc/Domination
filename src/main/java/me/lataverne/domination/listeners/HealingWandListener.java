package me.lataverne.domination.listeners;

import me.lataverne.domination.utils.Particles;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import me.lataverne.domination.Items;

import javax.smartcardio.ATR;

public class HealingWandListener implements Listener {
    @EventHandler
    public void onUse(PlayerInteractEntityEvent event) {
        ItemStack item;
        if (event.getHand() == EquipmentSlot.HAND) {
            item = event.getPlayer().getInventory().getItemInMainHand();
        } else {
            item = event.getPlayer().getInventory().getItemInOffHand();
        }

        if(event.getPlayer().hasCooldown(item.getType()))
            return;
        if (Items.HEAL_WAND.compareTo(item)) {
            if(event.getRightClicked() instanceof Player){
                Player clickedPlayer = (Player) event.getRightClicked();
               //Todo Fonction obtention et check de l'equipe => eviter de heal un enemi
                //le joueur est un allier
                //le joueur n'est pas full vie
                double max_heal = clickedPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
                double friend_heal = clickedPlayer.getHealth();
                double bonus_heal = 2;
                if( (friend_heal+bonus_heal) <= max_heal){


                    Location location = clickedPlayer.getLocation().clone();
                    location.setY(clickedPlayer.getLocation().getY()+ 2);

                    clickedPlayer.getLocation().getWorld().spawnParticle(Particle.VILLAGER_HAPPY,location,15);

                    clickedPlayer.setHealth(friend_heal+bonus_heal);
                    event.getPlayer().setCooldown(item.getType(),20);
                }
            }
        }
    }
}
