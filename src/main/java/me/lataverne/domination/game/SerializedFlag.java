package me.lataverne.domination.game;

import org.jetbrains.annotations.NotNull;

import me.lataverne.domination.utils.LocationUtils;

public class SerializedFlag {
    public final String name;
    public final String centerLocation;
    public final String radius;
    public final String bossBarRadius;

    public SerializedFlag(@NotNull Flag flag) {
        this.name = flag.getName();
        this.centerLocation = LocationUtils.LocationToString(flag.getCenterLocation());
        this.radius = String.valueOf(flag.getRadius());
        this.bossBarRadius = String.valueOf(flag.getBossBarRadius());
    }

    public SerializedFlag(@NotNull String name, @NotNull String centerLocation, @NotNull String radius, @NotNull String bossBarRadius) {
        this.name = name;
        this.centerLocation = centerLocation;
        this.radius = radius;
        this.bossBarRadius = bossBarRadius;
    }
}
