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
                case "create":
                    if (args.length >= 2) {
                        sender.sendMessage(args[1]);
                        Main.games.add(args[1]);
                    } else {
                        sender.sendMessage("§cIl te manque un argument");
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
