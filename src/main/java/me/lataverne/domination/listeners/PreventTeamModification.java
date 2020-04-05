package me.lataverne.domination.listeners;

import java.util.List;
import java.util.regex.Pattern;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.RemoteServerCommandEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.jetbrains.annotations.NotNull;

public class PreventTeamModification implements Listener {
    private String regex;

    public PreventTeamModification(@NotNull List<String> blockedTeams) {
        this.regex = "^/?(?i)team(?-i) \\w+ (" + String.join("|", blockedTeams) +")";
    }
    
    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        if (Pattern.matches(regex, event.getMessage())) {
            event.getPlayer().sendMessage("§cTu ne peux pas interagir avec cette team");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onServerCommand(ServerCommandEvent event) {
        if (Pattern.matches(regex, event.getCommand())) {
            event.getSender().sendMessage("§cTu ne peux pas interagir avec cette team");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onRemoteServerCommand(RemoteServerCommandEvent event) {
        if (Pattern.matches(regex, event.getCommand())) {
            event.getSender().sendMessage("§cTu ne peux pas interagir avec cette team");
            event.setCancelled(true);
        }
    }
}