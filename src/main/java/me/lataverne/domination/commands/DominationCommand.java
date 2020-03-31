package me.lataverne.domination.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import me.lataverne.domination.Main;

public class DominationCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length >= 1) {
            switch (args[0]) {
                //dom new {nom_game} // /dom create {nom_game}
                case "create":
                    if (args.length >= 2) {
                        sender.sendMessage(args[1]);
                        Main.games.add(args[1]);
                    } else {
                        sender.sendMessage("§cIl te manque un argument: /Domination create <name_game>");
                    }
                    return true;

                case "list":
                    if (!Main.games.isEmpty()) {
                        for (String game : Main.games) {
                            sender.sendMessage(game);
                        }
                    } else {
                        sender.sendMessage("§eIl n'y a pas de games crées pour l'instant");
                    }
                    return true;
                //dom flag {nom_game} : donne une balise, qui créer les drapeau au clic et l'ajout
//                case "flag":
//                    if (args.length >= 2) {
//                        sender.sendMessage(args[1]);
//                    } else {
//                        sender.sendMessage("§cIl te manque un argument: /Domination create <name_game>");
//                    }
//                    return true;
//                //dom radius {nom_game} {radius} : défini la taille de la zone des flags
//                case "radius":
//
//                //dom spawnpoint {team} {nom_game} set le spawn point de l'équipe bleu
//                case "spawnpoint":
//                ///dom win {nom_game} {score} : défini le score a atteindre pour gagner
//                case "scorewin":

                default:
                    sender.sendMessage("§cCet argument n'existe pas");
                    return false;
            }
        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return null;
    }
}
