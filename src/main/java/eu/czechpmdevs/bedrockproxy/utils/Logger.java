package eu.czechpmdevs.bedrockproxy.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    public void info(String text) {
        System.out.println(TextFormat.colorize("§b[" + this.getTime() + "] §3[Info] §f" + text + "§r"));
    }

    public void error(String text) {
        System.out.println(TextFormat.colorize("§b[" + this.getTime() + "] §4[Error] §c" + text + "§r"));
    }

    public void debug(String text) {
        System.out.println(TextFormat.colorize("§b[" + this.getTime() + "] §e[Debug] §f" + text + "§r"));
    }

    public void logException(Exception exception) {
        this.error(exception.getMessage());

        for(StackTraceElement element : exception.getStackTrace()) {
            this.debug(element.toString());
        }
    }

    private String getTime() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }
}
