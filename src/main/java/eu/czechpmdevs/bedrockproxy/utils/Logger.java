package eu.czechpmdevs.bedrockproxy.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    public void info(String text) {
        System.out.println(TextFormat.colorize("§e[" + this.getTime() + "] §3[" + Thread.currentThread().getName() + "/Info] §f" + text + "§r"));
    }

    public void error(String text) {
        System.out.println(TextFormat.colorize("§e[" + this.getTime() +"] §4[" + Thread.currentThread().getName() + "/Error] §c" + text + "§r"));
    }

    public void debug(String text) {
        System.out.println(TextFormat.colorize("§e[" + this.getTime() + "] §e[" + Thread.currentThread().getName() + "/Debug] §f" + text + "§r"));
    }

    public void logException(Exception exception) {
        this.error(exception.getClass().getName() + " appeared in " + Thread.currentThread().getName() + "!");
        this.error(" Message: " + exception.getMessage() + "; Loc Message: " + exception.getLocalizedMessage());

        for(StackTraceElement element : exception.getStackTrace()) {
            this.debug(element.toString());
        }

        if(exception.getCause() != null) {
            this.error("Caused by: " + exception.getMessage() + "; Loc Message: "+ exception.getLocalizedMessage());

            for(StackTraceElement element : exception.getStackTrace()) {
                this.debug(element.toString());
            }
        }
    }

    private String getTime() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }
}
