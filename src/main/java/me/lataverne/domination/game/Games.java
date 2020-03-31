package me.lataverne.domination.game;

import java.util.List;

import com.google.common.collect.Lists;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Games {
    private List<Game> games;

    public Games() {
        this.games = Lists.newArrayList();
    }

    public boolean createGame(@NotNull String name) {
        if (getGameByName(name) != null) return false;

        games.add(new Game(name));
        return true;
    }

    public List<Game> getGames() { return games; }

    public List<String> getGamesName() {
        List<String> list = Lists.newArrayList();

        for (Game game : games) {
            list.add(game.getName());
        }

        return list;
    }

    public @Nullable Game getGameByName(@NotNull String name) {
        for (Game game : games) {
            if (game.getName().equalsIgnoreCase(name)) return game;
        }

        return null;
    }
}
