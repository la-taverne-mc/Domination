package me.lataverne.domination.utils;

import com.google.common.collect.Lists;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public class LocationUtils {
    public static Location getApproximativeLocation(@NotNull Location location) {
        double x = NumericUtils.closest(Lists.newArrayList(location.getBlockX() - 0.5d, location.getBlockX() + 0.5d), location.getX());
        double z = NumericUtils.closest(Lists.newArrayList(location.getBlockZ() - 0.5d, location.getBlockZ() + 0.5d), location.getZ());
        float yaw = NumericUtils.closest(Lists.newArrayList(-180.f, -90.f, 0.f, 90.f, 180.f), location.getYaw());
        float pitch = NumericUtils.closest(Lists.newArrayList(-90.f, -45.f, 0.f, 45.f, 90.f), location.getPitch());
        
        return new Location(location.getWorld(), x, location.getBlockY(), z, yaw, pitch);
    }
}
