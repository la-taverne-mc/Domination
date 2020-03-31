package me.lataverne.domination.utils;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class ParticleUtils {
    public static void particleLine(@NotNull Location from, @NotNull Location to, @NotNull double interval, @NotNull Particle particle) {
        double distance = from.distance(to);
        Vector direction = to.subtract(from).toVector().normalize();

        World world = from.getWorld();

        for (double i = 0; i <= distance; i += interval) {
            world.spawnParticle(particle, from.clone().add(direction.clone().multiply(i)), 1, 0, 0, 0, 0);
        }
    }

    public static void particleLine(@NotNull Location from, @NotNull Location to, @NotNull double interval, @NotNull Particle particle, @NotNull double yOffset) {
        particleLine(from.add(0, yOffset, 0), to.add(0, yOffset, 0), interval, particle);
    }
}
