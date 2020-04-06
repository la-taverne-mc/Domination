package me.lataverne.domination.listeners;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.Objects;

public class OnEntityShootCrossbowEvent implements Listener {
    @EventHandler
    public void onEntityShootBowEvent(ProjectileHitEvent event) {
        System.out.println(event.getEntity().toString()+ "->entity : ShootCrossbowEvent");
        Player player = (Player)   event.getEntity().getShooter();

        if (    player.getInventory().getItemInMainHand().getType().name() == "CROSSBOW" ||
                (player.getInventory().getItemInOffHand().getType().name()  == "CROSSBOW" && player.getInventory().getItemInMainHand().getType().name() != "BOW"))
        {
            Entity entity = event.getEntity();
            if ((entity instanceof Arrow)) {
                Arrow arrow = (Arrow) entity;
                Entity shooter = (Entity) arrow.getShooter();
                if ((shooter instanceof Player  && Objects.requireNonNull(arrow.getCustomName()).equals("Boum"))) {
                    shooter.getWorld().createExplosion(arrow.getLocation(), 5.0F);
                    arrow.remove();
                }
            }
        }
    }
}