package eu.czechpmdevs.bedrockproxy.console;

import eu.czechpmdevs.bedrockproxy.ProxyServer;
import lombok.Getter;

public class Console {

    @Getter
    private final ProxyServer proxy;

    @Getter
    private final CommandMap commandMap;
    @Getter
    private final ConsoleReader reader;

    private final ConsoleCommandSender sender;

    public Console(ProxyServer proxy) {
        this.proxy = proxy;

        this.commandMap = new CommandMap(this);
        this.reader = new ConsoleReader();
        this.reader.start();

        this.sender = new ConsoleCommandSender(this);
    }

    public void tick() {
        this.handleCommands();
    }

    private void handleCommands() {
        String commandLine = this.getReader().readConsole();
        if(commandLine == null || commandLine.equals("")) {
            return;
        }

        this.getCommandMap().executeCommand(this.sender, commandLine);
    }
}
