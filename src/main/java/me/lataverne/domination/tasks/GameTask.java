package me.lataverne.domination.tasks;

import java.time.Instant;

import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import me.lataverne.domination.game.Game;

public class GameTask extends BukkitRunnable {
    private Game game;
    private long gameEndTime;

    public GameTask(@NotNull Game game, @NotNull int durationInSeconds) {
        this.game = game;
        this.gameEndTime = Instant.now().plusSeconds(durationInSeconds).getEpochSecond();
    }

    @Override
    public void run() {
        if (gameEndTime <= Instant.now().getEpochSecond()) {
            game.stop();
            return;
        }
    }
}