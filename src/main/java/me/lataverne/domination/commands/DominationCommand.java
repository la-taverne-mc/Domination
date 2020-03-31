package me.lataverne.domination.commands;

import java.util.List;

import com.google.common.collect.Lists;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import me.lataverne.domination.game.Flag;
import me.lataverne.domination.utils.NumericUtils;

public class DominationCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length >= 1) {
            switch (args[0].toLowerCase()) {
                case "createflag":
                    if (!(sender instanceof Player)) {
                        sender.sendMessage("§cSeul les joueurs peuvent utiliser cette commande");
                        return false;
                    }

                    if (args.length < 2 || args.length > 4) {
                        sender.sendMessage("§cUsage : /domination createFlag <name> [flag_radius] [bossbar_radius]");
                        return false;
                    }

                    if (args.length >= 3 && !NumericUtils.isDouble(args[2])) {
                        sender.sendMessage("§cL'argument 'flag_radius' doit être un nombre valide");
                        return false;
                    }

                    if (args.length >= 4 && !NumericUtils.isDouble(args[3])) {
                        sender.sendMessage("§cL'argument 'bossbar_radius' doit être un nombre valide");
                        return false;
                    }

                    double flagRadius;
                    double bossbarRadius;

                    if (args.length < 3) flagRadius = 3.5;
                    else flagRadius = Double.parseDouble(args[2]);

                    if (args.length < 4) bossbarRadius = 15;
                    else bossbarRadius = Double.parseDouble(args[3]);

                    Flag flag = new Flag(args[1], ((Player) sender).getLocation(), flagRadius, bossbarRadius);
                    flag.activate();
                    
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
        if (!command.getName().equalsIgnoreCase("domination") || args.length != 1) return null;

        List<String> argsList = Lists.newArrayList("createFlag");

        List<String> list = Lists.newArrayList();

        for (String arg : argsList) {
            if (arg.toLowerCase().startsWith(args[0].toLowerCase())) list.add(arg);
        }

        return list;
    }
}
