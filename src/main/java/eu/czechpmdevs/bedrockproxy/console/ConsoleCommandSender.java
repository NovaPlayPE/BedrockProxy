package eu.czechpmdevs.bedrockproxy.console;

import lombok.Getter;

public class ConsoleCommandSender implements CommandSender {

    @Getter
    private Console console;

    public ConsoleCommandSender(Console console) {
        this.console = console;
    }

    public void sendMessage(String message) {
        this.getConsole().getProxy().getLogger().info(message);
    }
}
