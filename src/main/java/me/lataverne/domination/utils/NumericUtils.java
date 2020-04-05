package me.lataverne.domination.utils;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NumericUtils {
    public static boolean isDouble(@NotNull String str) {
        final String digits     = "(\\p{Digit}+)";
        final String hexDigits  = "(\\p{XDigit}+)";
        // an exponent is 'e' or 'E' followed by an optionally 
        // signed decimal integer.
        final String exp        = "[eE][+-]?"+digits;
        final String fpRegex    =
            ("[\\x00-\\x20]*"+ // Optional leading "whitespace"
            "[+-]?(" +         // Optional sign character
            "NaN|" +           // "NaN" string
            "Infinity|" +      // "Infinity" string

            // A decimal floating-point string representing a finite positive
            // number without a leading sign has at most five basic pieces:
            // Digits . Digits ExponentPart FloatTypeSuffix
            // 
            // Since this method allows integer-only strings as input
            // in addition to strings of floating-point literals, the
            // two sub-patterns below are simplifications of the grammar
            // productions from the Java Language Specification, 2nd 
            // edition, section 3.10.2.

            // Digits ._opt Digits_opt ExponentPart_opt FloatTypeSuffix_opt
            "((("+digits+"(\\.)?("+digits+"?)("+exp+")?)|"+

            // . Digits ExponentPart_opt FloatTypeSuffix_opt
            "(\\.("+digits+")("+exp+")?)|"+

            // Hexadecimal strings
            "((" +
            // 0[xX] HexDigits ._opt BinaryExponent FloatTypeSuffix_opt
            "(0[xX]" + hexDigits + "(\\.)?)|" +

            // 0[xX] HexDigits_opt . HexDigits BinaryExponent FloatTypeSuffix_opt
            "(0[xX]" + hexDigits + "?(\\.)" + hexDigits + ")" +

            ")[pP][+-]?" + digits + "))" +
            "[fFdD]?))" +
            "[\\x00-\\x20]*");// Optional trailing "whitespace"

        return Pattern.matches(fpRegex, str);
    }

    public static boolean isInteger(@NotNull String str) {
        final String digits     = "(\\p{Digit}+)";
        final String iRegex     = 
            ("[\\x00-\\x20]*" +
            "[+-]?" + digits +
            "[\\x00-\\x20]*");

        return Pattern.matches(iRegex, str);
    }

    public static @Nullable Float closest(@NotNull ArrayList<Float> numbers, @NotNull float number) {
        if (numbers.isEmpty()) return null;

        float distance = Math.abs(numbers.get(0) - number);
        int idx = 0;

        for (int i = 0; i < numbers.size(); i++) {
            float idistance = Math.abs(numbers.get(i) - number);
            if (idistance < distance) {
                idx = i;
                distance = idistance;
            }
        }

        return numbers.get(idx);
    }

    public static @Nullable Double closest(@NotNull ArrayList<Double> numbers, @NotNull double number) {
        if (numbers.isEmpty()) return null;

        double distance = Math.abs(numbers.get(0) - number);
        int idx = 0;

        for (int i = 0; i < numbers.size(); i++) {
            double idistance = Math.abs(numbers.get(i) - number);
            if (idistance < distance) {
                idx = i;
                distance = idistance;
            }
        }

        return numbers.get(idx);
    }
}
