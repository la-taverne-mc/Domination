package me.lataverne.domination.listeners;

import me.lataverne.domination.Items;
import org.bukkit.Material;
import org.bukkit.entity.SmallFireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class FireWandListener implements Listener {

    private Map<String, Long> cooldowns = new HashMap<>();

    @EventHandler
    public void onUse(PlayerInteractEvent event) {


        ItemStack item;
        if (event.getHand() == EquipmentSlot.HAND) {
            item = event.getPlayer().getInventory().getItemInMainHand();
        } else {
            item = event.getPlayer().getInventory().getItemInOffHand();

        }
    //    System.out.println(item);
    //    System.out.println(Items.FIREBALL_WAND.compareTo(item));
        if (Items.FIREBALL_WAND.compareTo(item)) {

            //verified if player not have cooldowns ago
            if(cooldowns.containsKey(event.getPlayer().getName()))
            {
                int seconds = 3;
                long timeToWait = (cooldowns.get(event.getPlayer().getName()) / 1000 ) + seconds ;
                long timeleft = timeToWait - System.currentTimeMillis() /1000;
                if(timeleft > 0){
                 //   event.getPlayer().sendMessage("Rechargement en cours, besoin de "+timeleft+" seconde avant de lancer un sort");
                    event.setCancelled(true);
                    return;
                }
            }
            cooldowns.put(event.getPlayer().getName(), System.currentTimeMillis());
            event.getPlayer().setCooldown(Material.REDSTONE_TORCH,20*3);

            event.getPlayer().launchProjectile(SmallFireball.class);
        }
    }
}
