package eu.czechpmdevs.bedrockproxy.utils;

import lombok.Getter;

public enum TextFormat {

    BLACK('0', "\u001B[30m"),
    DARK_BLUE('1', "\u001B[34m"),
    DARK_GREEN('2', "\u001B[32m"),
    DARK_AQUA('3', "\u001B[36m"),
    DARK_RED('4', "\u001B[31m"),
    DARK_PURPLE('5', "\u001B[35m"),
    GOLD('6', "\u001B[33m"),
    GRAY('7', "\u001B[37m"),
    DARK_GRAY('8', "\u001B[30m"),
    BLUE('9', "\u001B[34m"),
    GREEN('a', "\u001B[32m"),
    AQUA('b', "\u001B[36m"),
    RED('c', "\u001B[31m"),
    LIGHT_PURPLE('d', "\u001B[35m"),
    YELLOW('e', "\u001B[33m"),
    WHITE('f', "\u001B[37m"),
    RESET('r', "\u001b[0m");

    @Getter
    private final char minecraftChar;
    @Getter
    private final String ansi;

    TextFormat(char minecraftChar, String ansi) {
        this.minecraftChar = minecraftChar;
        this.ansi = ansi;
    }

    public static String colorize(String string) {
        for(TextFormat format : TextFormat.class.getEnumConstants()) {
            string = string.replace("§" + format.getMinecraftChar(), format.getAnsi());
        }

        return string;
    }

    public String toString() {
        return "§" + this.getMinecraftChar();
    }
}