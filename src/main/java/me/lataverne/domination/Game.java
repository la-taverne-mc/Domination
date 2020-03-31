package me.lataverne.domination;

import me.lataverne.domination.enums.Specialities;
import me.lataverne.domination.game.Flag;
import org.bukkit.scoreboard.Team;

import java.util.Map;

/**
 * Gestion d'un partie
 */
public class Game {
    public String name;
    public Integer score_win;
    public Flag[] flags;

    public Integer score;
    public Team red;
    public Team blue;

    // <uuid, Specialities>
    public Map<String, Specialities> players_specialities;
}
