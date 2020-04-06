package me.lataverne.domination.listeners;


import org.bukkit.entity.Explosive;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;


public class DragonEffectListener implements Listener {
    @EventHandler
    public void onUse(ProjectileHitEvent event) {
       System.out.println( event.getEntity().toString()+"mon test");
        if(event.getEntity().toString() == "CraftDragonFireball"){
            Explosive explosive = (Explosive) event.getEntity();
        }
    }
}
