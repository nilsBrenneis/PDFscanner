package de.bre.model;

import java.util.Arrays;

public enum WordsToLookFor {
    B3S("B3S"),
    STANDARD("Standard"),
    ISO("ISO"),
    GESETZ("Gesetz"),
    INFRASTRUKTUR("Infrastruktur"),
    MITARBEITER("Mitarbeiter"),
    ANGESTELLTE("Angestellte"),
    RISIKO("Risiko"),
    EIGHTA("8a"),
    GELTUNGSBEREICH("Geltungsbereich"),
    BETREIBER("Betreiber");

    private final String enumValue;

    WordsToLookFor(String word) {
        enumValue = word;
    }

    @Override
    public String toString() {
        return enumValue;
    }

    public static String[] getValuesAsArray(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::toString).toArray(String[]::new);
    }
}

