package eu.czechpmdevs.bedrockproxy.utils;

import lombok.Getter;

public enum TextFormat {

    BLACK('0', "\u001b[30m"),
    DARK_BLUE('1', "\u001b[34m"),
    DARK_GREEN('2', "\u001b[32m"),
    DARK_AQUA('3', "\u001b[36m"),
    DARK_RED('4', "\u001b[31m"),
    DARK_PURPLE('5', "\u001b[35m"),
    GOLD('6', "\u001b[33m"),
    GRAY('7', "\u001B[37m"),
    DARK_GRAY('8', "\u001b[30;1m"),
    BLUE('9', "\u001B[34m"),
    GREEN('a', "\u001b[32;1m"),
    AQUA('b', "\u001b[36;1m"),
    RED('c', "\u001b[31;1m"),
    LIGHT_PURPLE('d', "\u001b[35;1m"),
    YELLOW('e', "\u001b[33;1m"),
    WHITE('f', "\u001b[37;1m"),
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
            string = string.replace("§" + format.getMinecraftChar(), TextFormat.RESET.getAnsi() + format.getAnsi());
        }

        return string;
    }

    public String toString() {
        return "§" + this.getMinecraftChar();
    }
}