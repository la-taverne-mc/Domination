package me.lataverne.domination.game;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import me.lataverne.domination.Main;

public class Squad {
    private String name;
    private Team team;
    private Set<Player> players;
    private Location spawn;
    private int score;
    private HashMap<Player, Team> oldTeams;

    public Squad(@NotNull String name, @NotNull Team team) {
        this.name = name;
        this.team = team;
        this.players = new HashSet<Player>();
        this.score = 0;
        this.oldTeams = new HashMap<Player, Team>();
    }

    public Squad(@NotNull String name, @NotNull Team team, @NotNull Set<Player> players) {
        this.name = name;
        this.team = team;
        this.players = players;
        this.score = 0;
        this.oldTeams = new HashMap<Player, Team>();
    }

    public String getName() { return name; }

    public Set<Player> getPlayers() { return players; }
    public boolean hasPlayer(@NotNull Player player) { return players.contains(player); }
    public void addPlayer(@NotNull Player player) { players.add(player); }
    public void removePlayer(@NotNull Player player) { players.remove(player); }
    
    public @Nullable Location getSpawn() { return spawn; }
    public void setSpawn(@NotNull Location spawn) { this.spawn = spawn; }

    public int getScore() { return this.score; }
    public void setScore(@NotNull int score) { this.score = score; }
    public void addScore(@NotNull int points) { this.score += points; }
    public void subtractScore(@NotNull int points) { this.score -= points; }

    public void reset() {
        revertPlayersTeam();

        players.clear();

        score = 0;
    }

    public void setPlayersTeam() {
        for (Player player : players) {
            Team currentTeam = Main.mainScoreboard.getEntryTeam(player.getName());

            if (currentTeam != null) {
                if (oldTeams.containsKey(player)) oldTeams.replace(player, currentTeam);
                else oldTeams.put(player, currentTeam);
            }

            team.addEntry(player.getName());
        }
    }

    public void revertPlayersTeam() {
        for (Player player : players) {
            if (oldTeams.containsKey(player)) oldTeams.get(player).addEntry(player.getName());
        }

        oldTeams.clear();
    }
}
