package me.lataverne.domination.utils;

import java.util.regex.Pattern;

import org.jetbrains.annotations.NotNull;

public class UuidUtils {
    public static boolean isValidUuid(@NotNull String string) {
        final String uuidRegex = "^[0-9a-fA-F]{8}-?[0-9a-fA-F]{4}-?[0-9a-fA-F]{4}-?[0-9a-fA-F]{4}-?[0-9a-fA-F]{12}$";
        return Pattern.matches(uuidRegex, string);
    }
}
