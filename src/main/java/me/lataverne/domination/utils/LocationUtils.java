package me.lataverne.domination.utils;

import com.google.common.collect.Lists;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LocationUtils {
    public static Location getApproximativeLocation(@NotNull Location location) {
        double x = NumericUtils.closest(Lists.newArrayList(location.getBlockX() - 0.5d, location.getBlockX() + 0.5d), location.getX());
        double z = NumericUtils.closest(Lists.newArrayList(location.getBlockZ() - 0.5d, location.getBlockZ() + 0.5d), location.getZ());
        float yaw = NumericUtils.closest(Lists.newArrayList(-180.f, -90.f, 0.f, 90.f, 180.f), location.getYaw());
        float pitch = NumericUtils.closest(Lists.newArrayList(-90.f, -45.f, 0.f, 45.f, 90.f), location.getPitch());
        
        return new Location(location.getWorld(), x, location.getBlockY(), z, yaw, pitch);
    }

    public static String LocationToString(@NotNull Location location) {
        String str = String.join(";",
            location.getWorld().getUID().toString(),
            String.valueOf(location.getX()),
            String.valueOf(location.getY()),
            String.valueOf(location.getZ()),
            String.valueOf(location.getYaw()),
            String.valueOf(location.getPitch())
        );

        return str;
    }

    public static @Nullable Location StringToLocation(@NotNull String string) {
        String[] str = string.split(";");

        if (str.length != 6) return null;

        return new Location(Bukkit.getWorld(str[0]), Double.parseDouble(str[1]), Double.parseDouble(str[2]), Double.parseDouble(str[3]), Float.parseFloat(str[4]), Float.parseFloat(str[5]));
    }
}
