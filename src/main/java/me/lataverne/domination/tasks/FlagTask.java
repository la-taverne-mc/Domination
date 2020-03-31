package me.lataverne.domination.tasks;

import java.util.List;

import com.google.common.collect.Lists;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import me.lataverne.domination.game.Flag;
import me.lataverne.domination.utils.PredicateUtils;

public class FlagTask extends BukkitRunnable {
    private Flag flag;
    private Location centerLocation;
    private World world;
    private double radius;
    private double bossBarRadius;

    public FlagTask(@NotNull Flag flag) {
        this.flag = flag;
        this.centerLocation = this.flag.getCenterLocation();
        this.world = this.centerLocation.getWorld();
        this.radius = this.flag.getRadius();
        this.bossBarRadius = this.flag.getBossBarRadius();
    }

    @Override
    public void run() {
        List<Player> bossBarPlayers = Lists.newArrayList();
        for (Entity entity : world.getNearbyEntities(centerLocation, bossBarRadius*2, bossBarRadius*2, bossBarRadius*2, PredicateUtils.isPlayer())) {
            bossBarPlayers.add((Player) entity);
        }
        flag.setBossBarPlayers(bossBarPlayers);

        for (Entity entity : world.getNearbyEntities(centerLocation, radius*2, radius*2, radius*2, PredicateUtils.isPlayer())) {
            // TODO check players team and count points accordingly
        }
    }
}
