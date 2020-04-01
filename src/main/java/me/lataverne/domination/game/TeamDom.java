package me.lataverne.domination.game;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


import java.util.ArrayList;
import java.util.UUID;


public class TeamDom {
    public String color;
    private ArrayList<@NotNull UUID> players;
    private Location spawn;
    private int score;

    public TeamDom(String color){
        this.color = color;
    }
    public void setSpawn(Location location){
        this.spawn = location;
    }

    public Location getSpawn(){
        return this.spawn;
    }

    public void addPlayer(Player player){
        this.players.add(player.getUniqueId());
    }

    public void removePlayer(Player player){
        if(this.hasPlayer(player))
            this.players.remove(player.getUniqueId());
    }

    public Boolean hasPlayer(Player player){
        return  this.players.contains(player.getUniqueId());
    }

    public void setScore(int newScore){
        this.score = newScore;
    }

    public void addScore(int bonus){
        this.score = this.score + bonus;
    }

    public int getScore(){
        return this.score;
    }

    public void remScore(int malus){
        this.score = this.score - malus;
    }

}
