package me.lataverne.domination.listeners;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class OnEntityLunchArrowEvent implements Listener {
    @EventHandler
    public void OnEntityLunchArrowEvent(ProjectileLaunchEvent event) {
        System.out.println(event.getEntity().toString()+ "->entity");
        Player player = (Player)   event.getEntity().getShooter();

        if (    player.getInventory().getItemInMainHand().getType().name() == "CROSSBOW" ||
                (player.getInventory().getItemInOffHand().getType().name()  == "CROSSBOW" && player.getInventory().getItemInMainHand().getType().name() != "BOW"))
        {
            Entity entity = event.getEntity();
            Arrow arrow = (Arrow) entity;
            arrow.setCustomName("Boum");
            System.out.println( arrow.getCustomName()+" name: LunchCrossbowEvent----------------------");

        }
    }
}