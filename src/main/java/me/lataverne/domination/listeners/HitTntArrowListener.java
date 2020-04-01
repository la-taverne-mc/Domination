package me.lataverne.domination.listeners;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class HitTntArrowListener implements Listener {
    @EventHandler(priority= EventPriority.HIGH)
    public void onProjectileHit(ProjectileHitEvent event) {
        Entity entity = event.getEntity();
        if ((entity instanceof Arrow)) {
            Arrow arrow = (Arrow)entity;
            Entity shooter = (Entity) arrow.getShooter();
            if(event.getHitEntity().getEntityId() )
            item = event.getPlayer().getInventory().getItemInMainHand();

            if ((shooter instanceof Player)) {
                Player player = (Player)shooter;
                player.getWorld().createExplosion(arrow.getLocation(), 5.0F);
            }
        }
    }
}
