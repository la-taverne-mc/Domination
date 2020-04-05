package me.lataverne.domination.game;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Squad {
    private String name;
    private Team team;
    private Set<Player> players;
    private Location spawn;
    private int score;

    public Squad(@NotNull String name, @NotNull Team team) {
        this.name = name;
        this.team = team;
        this.players = new HashSet<Player>();
        this.score = 0;
    }

    public Squad(@NotNull String name, @NotNull Team team, @NotNull Set<Player> players) {
        this.name = name;
        this.team = team;
        this.score = 0;

        for (Player player : players) {
            this.players.add(player);
            this.team.addEntry(player.getName());
        }
    }

    public String getName() { return name; }

    public Set<Player> getPlayers() { return players; }
    public boolean hasPlayer(@NotNull Player player) { return players.contains(player); }

    public void addPlayer(@NotNull Player player) {
        players.add(player);
        team.addEntry(player.getName());
    }

    public void removePlayer(@NotNull Player player) {
        players.remove(player);
        team.removeEntry(player.getName());
    }
    
    public @Nullable Location getSpawn() { return spawn; }
    public void setSpawn(@NotNull Location spawn) { this.spawn = spawn; }

    public int getScore() { return this.score; }
    public void setScore(@NotNull int score) { this.score = score; }
    public void addScore(@NotNull int points) { this.score += points; }
    public void subtractScore(@NotNull int points) { this.score -= points; }

    public void reset() {
        for (Player player : players) {
            team.removeEntry(player.getName());
        }

        players.clear();

        score = 0;
    }
}
