package me.lataverne.domination.utils;

import java.util.function.Predicate;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class PredicateUtils {
    public static Predicate<Entity> isPlayer() { return p -> p.getType() == EntityType.PLAYER; }
}