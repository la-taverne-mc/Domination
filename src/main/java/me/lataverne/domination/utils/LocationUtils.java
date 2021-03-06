package me.lataverne.domination.utils;

import java.util.UUID;

import com.google.common.collect.Lists;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LocationUtils {
    public static Location getApproximativeLocation(@NotNull Location location) {
        double x = NumericUtils.closest(Lists.newArrayList(location.getBlockX() - 0.5d, location.getBlockX() + 0.5d), location.getX());
        double z = NumericUtils.closest(Lists.newArrayList(location.getBlockZ() - 0.5d, location.getBlockZ() + 0.5d), location.getZ());
        float yaw = NumericUtils.closest(Lists.newArrayList(0.f, 45.f, 90.f, 135.f, 180.f, 225.f, 270.f, 315.f, 360.f), location.getYaw());
        yaw = yaw == 360.f ? 0.f : yaw;
        float pitch = NumericUtils.closest(Lists.newArrayList(-90.f, -45.f, 0.f, 45.f, 90.f), location.getPitch());
        
        return new Location(location.getWorld(), x, location.getBlockY(), z, yaw, pitch);
    }

    public static @Nullable String LocationToString(Location location) {
        if (location == null) return null;

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

    public static @Nullable Location StringToLocation(String string) {
        if (string == null) return null;

        String[] str = string.split(";");

        if (str.length != 6 || !(UuidUtils.isValidUuid(str[0]) && NumericUtils.isDouble(str[1]) && NumericUtils.isDouble(str[2]) && NumericUtils.isDouble(str[3]) && NumericUtils.isDouble(str[4]) && NumericUtils.isDouble(str[5]))) return null;

        return new Location(Bukkit.getWorld(UUID.fromString(str[0])), Double.parseDouble(str[1]), Double.parseDouble(str[2]), Double.parseDouble(str[3]), Float.parseFloat(str[4]), Float.parseFloat(str[5]));
    }
}
