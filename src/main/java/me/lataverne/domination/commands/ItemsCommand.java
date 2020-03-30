package me.lataverne.domination.commands;

import com.google.common.collect.Lists;
import me.lataverne.domination.Items;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemsCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!command.getName().equalsIgnoreCase("items")) return false;

        if (args.length == 2) {
            Player player = Bukkit.getPlayer(args[1]);
            ItemStack item = Items.getItemNamed(args[0]);

            if (item == null) {
                sender.sendMessage("§cIl n'y a pas d'item nommé '" + args[0] + "'");
                return false;
            }
            if (player == null || !player.isOnline()) {
                sender.sendMessage("§cLe joueur '" + args[1] + "' n'existe pas ou n'est pas connecté");
            }

            sender.sendMessage("§eLe joueur '" + args[1] + "' a reçu '" + args[0] + "'");
            player.getInventory().addItem(item);
            return true;
        }
        else if (args.length == 1 && sender instanceof Player) {
            Player player = (Player) sender;
            ItemStack item = Items.getItemNamed(args[0]);

            if (item == null) {
                sender.sendMessage("§cIl n'y a pas d'item nommé '" + args[0] + "'");
                return false;
            }

            sender.sendMessage("§eTu as reçu '" + args[0] + "'");
            player.getInventory().addItem(item);
            return true;
        }
        else {
            sender.sendMessage("§cUsage : /items <item> [player]");
            return false;
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!command.getName().equalsIgnoreCase("items") || args.length != 1) return null;

        List<String> list = Lists.newArrayList();

        for (Items item : Items.values()) {
            list.add(item.getName());
        }

        return list;
    }
}
