package me.lataverne.domination.game;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import me.lataverne.domination.enums.Squads;
import me.lataverne.domination.utils.LocationUtils;

public class SerializedGame {
    public final String name;
    public final String blueSpawn;
    public final String redSpawn;
    public final List<SerializedFlag> flags;

    public SerializedGame(@NotNull Game game, List<SerializedFlag> flags) {
        this.name = game.getName();
        this.blueSpawn = LocationUtils.LocationToString(game.getTeamSpawn(Squads.BLUE));
        this.redSpawn = LocationUtils.LocationToString(game.getTeamSpawn(Squads.RED));
        this.flags = flags;
    }

    public SerializedGame(@NotNull String name, String blueSpawn, String redSpawn, List<SerializedFlag> flags) {
        this.name = name;
        this.blueSpawn = blueSpawn;
        this.redSpawn = redSpawn;
        this.flags = flags;
    }
}
