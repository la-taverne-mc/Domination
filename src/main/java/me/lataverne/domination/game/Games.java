package me.lataverne.domination.game;

import java.util.List;

import com.google.common.collect.Lists;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Games {
    private List<Game> games;

    public Games() {
        this.games = Lists.newArrayList();
    }

    public Games(@NotNull List<SerializedGame> serializedGames) {
        this.games = Lists.newArrayList();

        for (SerializedGame serializedGame : serializedGames) {
            this.games.add(new Game(serializedGame));
        }
    }

    public boolean createGame(@NotNull String name) {
        if (getGameByName(name) != null) return false;

        games.add(new Game(name));
        return true;
    }

    public List<Game> getGames() { return games; }

    public List<String> getGamesNames() {
        List<String> list = Lists.newArrayList();

        for (Game game : games) {
            list.add(game.getName());
        }

        return list;
    }

    public List<String> getRunningGamesNames() {
        List<String> list = Lists.newArrayList();

        for (Game game : games) {
            if (game.isRunning()) list.add(game.getName());
        }

        return list;
    }

    public @Nullable Game getGameByName(@NotNull String name) {
        for (Game game : games) {
            if (game.getName().equalsIgnoreCase(name)) return game;
        }

        return null;
    }

    public void stopAll() {
        for (Game game : games) {
            game.stop();
        }
    }

    public @Nullable Game getPlayerGame(@NotNull Player player) {
        for (Game game : games) {
            if (game.hasPlayer(player)) return game;
        }

        return null;
    }

    public @Nullable List<SerializedGame> serialize() {
        if (games.size() <= 0) return null;

        List<SerializedGame> serializedGames = Lists.newArrayList();

        for (Game game : games) {
            serializedGames.add(game.serialize());
        }

        return serializedGames;
    }
}
