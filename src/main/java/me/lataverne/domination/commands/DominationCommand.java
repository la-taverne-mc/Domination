package me.lataverne.domination.commands;

import java.util.List;

import com.google.common.collect.Lists;

import me.lataverne.domination.listeners.PreventBlockPlacing;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import me.lataverne.domination.game.Game;
import me.lataverne.domination.game.Games;
import me.lataverne.domination.utils.NumericUtils;

public class DominationCommand implements TabExecutor {
    private Games games;

    public DominationCommand(@NotNull Games games) {
        this.games = games;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length >= 1) {
            Game game;
            
            switch (args[0].toLowerCase()) {
                case "creategame":
                    if (args.length != 2) {
                        sender.sendMessage("§cUsage : /domination createGame <name>");
                        return false;
                    }

                    if (!games.createGame(args[1])) {
                        sender.sendMessage("§cUne game nommée '" + args[1] + "' existe déjà");
                        return false;
                    }

                    sender.sendMessage("§eLa game '" + args[1] + "' a bien été créée");
                    return true;
                
                case "listgames":
                    if (args.length != 1) {
                        sender.sendMessage("§cUsage : /domination listGames");
                        return false;
                    }

                    sender.sendMessage("§eVoici une liste de toutes les games ayant été créée :");

                    for (String gameName : games.getGamesName()) {
                        sender.sendMessage("§e   " + gameName);
                    }

                    return true;
                
                case "startgame":
                    if (args.length < 2 || args.length > 3) {
                        sender.sendMessage("§cUsage : /domination startGame <game_name> [duration_in_seconds]");
                        return false;
                    }

                    game = games.getGameByName(args[1]);

                    if (game == null) {
                        sender.sendMessage("§cIl n'y a pas de game nommée '" + args[1] + "'");
                        return false;
                    }

                    if (args.length == 3 && !NumericUtils.isInteger(args[2])) {
                        sender.sendMessage("§cL'argument 'duration_in_seconds' doit être un nombre entier");
                        return false;
                    }

                    int durationInSeconds;

                    if (args.length < 3) durationInSeconds = 600;
                    else durationInSeconds = Integer.parseInt(args[2]);

                    if (!game.start(durationInSeconds)) {
                        sender.sendMessage("§cLa game '" + game.getName() + "' est déjà en cours");
                        return false;
                    }

                    sender.sendMessage("§eLa game '" + game.getName() + "' vient de démarrer");
                    return true;
                
                case "stopgame":
                    if (args.length != 2) {
                        sender.sendMessage("§cUsage : /domination stopGame <game_name>");
                        return false;
                    }

                    game = games.getGameByName(args[1]);

                    if (game == null) {
                        sender.sendMessage("§cIl n'y a pas de game nommée '" + args[1] + "'");
                        return false;
                    }

                    game.stop();

                    sender.sendMessage("§eLa game '" + game.getName() + "' a bien été arrêtée");
                    return true;

                case "createflag":
                    if (!(sender instanceof Player)) {
                        sender.sendMessage("§cSeul les joueurs peuvent utiliser cette commande");
                        return false;
                    }

                    if (args.length < 3 || args.length > 5) {
                        sender.sendMessage("§cUsage : /domination createFlag <flag_name> <game_name> [flag_radius] [bossbar_radius]");
                        return false;
                    }

                    game = games.getGameByName(args[2]);

                    if (game == null) {
                        sender.sendMessage("§cIl n'y a pas de game nommée '" + args[2] + "'");
                        return false;
                    }

                    if (args.length >= 4 && !NumericUtils.isDouble(args[3])) {
                        sender.sendMessage("§cL'argument 'flag_radius' doit être un nombre décimal");
                        return false;
                    }

                    if (args.length >= 5 && !NumericUtils.isDouble(args[4])) {
                        sender.sendMessage("§cL'argument 'bossbar_radius' doit être un nombre décimal");
                        return false;
                    }

                    double flagRadius;
                    double bossBarRadius;

                    if (args.length < 4) flagRadius = 3.5;
                    else flagRadius = Double.parseDouble(args[3]);

                    if (args.length < 5) bossBarRadius = 15;
                    else bossBarRadius = Double.parseDouble(args[4]);

                    game.createFlag(args[1], ((Player) sender).getLocation(), flagRadius, bossBarRadius);
                    
                    sender.sendMessage("§eLe flag '" + args[1] + "' a bien été ajouté à la game '" + game.getName() + "'");
                    return true;

                default:
                    sender.sendMessage("§cCette sous-commande n'existe pas");
                    return false;
            }
        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (!command.getName().equalsIgnoreCase("domination") || args.length != 1) return null;

        List<String> argsList = Lists.newArrayList("createGame", "createFlag", "listGames", "startGame");

        List<String> list = Lists.newArrayList();

        for (String arg : argsList) {
            if (arg.toLowerCase().startsWith(args[0].toLowerCase())) list.add(arg);
        }

        return list;
    }
}
