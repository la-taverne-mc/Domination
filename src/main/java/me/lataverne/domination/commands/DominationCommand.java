package me.lataverne.domination.commands;

import java.util.List;

import com.google.common.collect.Lists;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import me.lataverne.domination.enums.Squads;
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

                    for (String gameName : games.getGamesNames()) {
                        sender.sendMessage("§e   " + gameName);
                    }

                    return true;
                
                case "startgame":
                    if (args.length < 3 || args.length > 4) {
                        sender.sendMessage("§cUsage : /domination startGame <game_name> <random|manual|playerChoice> [duration_in_seconds]");
                        return false;
                    }

                    game = games.getGameByName(args[1]);

                    if (game == null) {
                        sender.sendMessage("§cIl n'y a pas de game nommée '" + args[1] + "'");
                        return false;
                    }

                    if (!args[2].equalsIgnoreCase("random") && !args[2].equalsIgnoreCase("manual") && !args[2].equalsIgnoreCase("playerChoice")) {
                        sender.sendMessage("§cLe mode de sélection de team n'a pas été choisit");
                        return false;
                    }

                    if (args.length == 4 && !NumericUtils.isInteger(args[3])) {
                        sender.sendMessage("§cL'argument 'duration_in_seconds' doit être un nombre entier");
                        return false;
                    }

                    int durationInSeconds;

                    if (args.length < 4) durationInSeconds = 600;
                    else durationInSeconds = Integer.parseInt(args[3]);

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

                case "setteam":
                    if (args.length != 4) {
                        sender.sendMessage("§cUsage : /domination setTeam <player> <game> <team>");
                        return false;
                    }

                    game = games.getGameByName(args[2]);

                    if (game == null) {
                        sender.sendMessage("§cIl n'y a pas de game nommée '" + args[2] + "'");
                        return false;
                    }
            
                    Player player = Bukkit.getPlayer(args[1]);
                    
                    if (player == null || !player.isOnline()) {
                        sender.sendMessage("§cLe joueur '" + args[1] + "' n'existe pas ou n'est pas connecté");
                        return false;
                    }

                    switch (args[3].toLowerCase()) {
                        case "blue":
                            game.addPlayer(player, Squads.BLUE);
                            Bukkit.broadcastMessage("§e" + player.getName() + " a rejoint l'équipe des §9Bleus");
                            break;

                        case "red":
                            game.addPlayer(player, Squads.RED);
                            Bukkit.broadcastMessage("§e" + player.getName() + " a rejoint l'équipe des §cRouges");
                            break;

                        case "random":
                            Squads squad = Squads.randomSquad();
                            game.addPlayer(player, squad);
                            if (squad == Squads.BLUE) Bukkit.broadcastMessage("§e" + player.getName() + " a rejoint l'équipe des §9Bleus");
                            else Bukkit.broadcastMessage("§e" + player.getName() + " a rejoint l'équipe des §cRouges");
                            break;

                        default:
                            sender.sendMessage("§cLa team peut être soit §nblue§n, soit §nred§n, soit §nrandom§n");
                            return false;
                    }

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
        if (command.getName().equalsIgnoreCase("domination")) {
            switch (args.length) {
                case 1:
                    return getStringsStartingWith(args[0], Lists.newArrayList("createGame", "listGames", "startGame", "stopGame", "createFlag", "setTeam"));

                case 2:
                    switch (args[0].toLowerCase()) {
                        case "startgame":
                        case "stopgame":
                            return getStringsStartingWith(args[1], games.getGamesNames());
                        
                        case "creategame":
                        case "createflag":
                            return Lists.newArrayList();
                        
                        default:
                            break;
                    }

                    break;

                case 3:
                    switch (args[0].toLowerCase()) {
                        case "createflag":
                        case "setteam":
                            return getStringsStartingWith(args[2], games.getGamesNames());
                        
                        case "startgame":
                            return getStringsStartingWith(args[2], Lists.newArrayList("random", "manual", "playerChoice"));
                        
                        default:
                            break;
                    }

                    break;

                case 4:
                    switch (args[0].toLowerCase()) {
                        case "setteam":
                            return getStringsStartingWith(args[3], Lists.newArrayList("blue", "red", "random"));
                        
                        default:
                            break;
                    }
                    
                    break;

                default:
                    break;
            }
        }

        return null;
    }

    private List<String> getStringsStartingWith(@NotNull String testString, @NotNull List<String> stringsList) {
        List<String> list = Lists.newArrayList();

        for (String string : stringsList) {
            if (string.toLowerCase().startsWith(testString.toLowerCase())) list.add(string);
        }

        return list;
    }
}
