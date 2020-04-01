package me.lataverne.domination.listeners;

import me.lataverne.domination.game.Game;
import me.lataverne.domination.enums.Items;
import me.lataverne.domination.enums.Archetypes;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;


public class RespawnListener implements Listener {
    /*@EventHandler
    public void onPlayerRespawn (PlayerRespawnEvent event) {
        //récupérer la game du joueur
        Game game= player().getGame();
        //récupéré la team du joueur
        // Team team= player().getTeam();
        //récupéré l'emplacement du spawn de la team du joueur dans cette game
        //Location spawn_team = game.getSpawn(team);
        //todo SetSpawn : changer les deux lignes ci-dessous utiliser pour les tests:
        Location spawn_team = event.getPlayer().getLocation().clone();
        spawn_team.setY(spawn_team.getY()+5);

        event.setRespawnLocation(spawn_team);

        //Récupérer la class du joueur
         Specialities speciality = game.players_specialities.get();
         switch (speciality.toString()){
             case "ARCHER":
                 event.getPlayer().getInventory().addItem(Items.ARC_ARCHER.getItem());
                 event.getPlayer().getInventory().addItem(Items.ARMOR_ARCHER.getItem());
                 event.getPlayer().getInventory().addItem(Items.FEET_ARCHER.getItem());
                 event.getPlayer().updateInventory();
             case "MAGICIAN":
                 event.getPlayer().getInventory().addItem(Items.FIREBALL_WAND.getItem());
                 event.getPlayer().getInventory().addItem(Items.HEAL_WAND.getItem());
                 event.getPlayer().updateInventory();
             case "OUTFIELDER":
                 //kit voltigeur
             default:
                //kit voltigeur
         }

    }
*/
}