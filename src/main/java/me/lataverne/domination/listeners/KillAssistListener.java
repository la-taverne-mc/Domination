package me.lataverne.domination.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;

import me.lataverne.domination.Main;
import me.lataverne.domination.tasks.KillAssistTimeoutTask;

public class KillAssistListener implements Listener {
    private Plugin plugin;

    public KillAssistListener() {
        this.plugin = Bukkit.getPluginManager().getPlugin("Domination");
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player && event.getDamager() instanceof Player) && 
            ((Player) event.getEntity()).getHealth() - event.getFinalDamage() <= 0) return;

        Player damaged = ((Player) event.getEntity());
        Player damager = ((Player) event.getDamager());

        if (Main.killAssists.containsKey(damaged))
            Main.killAssists.replace(damaged, damager);
        else
            Main.killAssists.put(damaged, damager);

        new KillAssistTimeoutTask(damaged, damager).runTaskLater(plugin, 200);
    }
}