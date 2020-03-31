package me.lataverne.domination.utils;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class Particles {
    public static void particleLine(@NotNull Location from, @NotNull Location to, @NotNull double interval, @NotNull Particle particle) {
        double distance = from.distance(to);
        Vector direction = from.subtract(to).toVector().normalize();

        World world = from.getWorld();

        for (double i = 0; i < distance; i += interval) {
            Location particleLocation = from.add(direction.multiply(i));
            world.spawnParticle(particle, particleLocation, 1);
        }
    }
}
