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

    public SerializedGame(@NotNull Game game, List<SerializedFlag> serializedFlags) {
        this.name = game.getName();
        this.blueSpawn = game.getTeamSpawn(Squads.BLUE) == null ? null : LocationUtils.LocationToString(game.getTeamSpawn(Squads.BLUE));
        this.redSpawn = game.getTeamSpawn(Squads.RED) == null ? null : LocationUtils.LocationToString(game.getTeamSpawn(Squads.RED));
        this.flags = serializedFlags;
    }
}
