package me.lataverne.domination.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.jetbrains.annotations.NotNull;

public enum Squads {
    BLUE("blue"),
    RED("red");

    private final String name;

    Squads(@NotNull String name) {
        this.name = name;
    }

    private static final List<Squads> values = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int size = values.size();
    private static final Random random = new Random();

    public static Squads randomSquad() { return values.get(random.nextInt(size)); }

    public String getName() { return name; }
}
