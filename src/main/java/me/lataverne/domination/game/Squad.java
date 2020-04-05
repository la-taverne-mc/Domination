package me.lataverne.domination.game;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Squad {
    private String name;
    private Team team;
    private Set<UUID> playersUuids;
    private Location spawn;
    private int score;

    public Squad(@NotNull String name, @NotNull Team team) {
        this.name = name;
        this.team = team;
        this.playersUuids = new HashSet<UUID>();
        this.score = 0;
    }

    public Squad(@NotNull String name, @NotNull Team team, @NotNull Set<Player> players) {
        this.name = name;
        this.team = team;
        this.score = 0;

        for (Player player : players) {
            this.playersUuids.add(player.getUniqueId());
            this.team.addEntry(player.getName());
        }
    }

    public String getName() { return name; }

    public Set<UUID> getPlayersUuids() { return playersUuids; }
    public boolean hasPlayer(@NotNull Player player) { return playersUuids.contains(player.getUniqueId()); }

    public void addPlayer(@NotNull Player player) {
        playersUuids.add(player.getUniqueId());
        team.addEntry(player.getName());
    }

    public void removePlayer(@NotNull Player player) {
        playersUuids.remove(player.getUniqueId());
        team.removeEntry(player.getName());
    }
    
    public @Nullable Location getSpawn() { return spawn; }
    public void setSpawn(@NotNull Location spawn) { this.spawn = spawn; }

    public int getScore() { return this.score; }
    public void setScore(@NotNull int score) { this.score = score; }
    public void addScore(@NotNull int points) { this.score += points; }
    public void subtractScore(@NotNull int points) { this.score -= points; }

    public void reset() {
        for (UUID playerUuid : playersUuids) {
            team.removeEntry(Bukkit.getOfflinePlayer(playerUuid).getName());
        }

        playersUuids.clear();

        score = 0;
    }
}
