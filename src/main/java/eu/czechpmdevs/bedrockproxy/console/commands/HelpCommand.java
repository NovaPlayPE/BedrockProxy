package eu.czechpmdevs.bedrockproxy.console.commands;

import eu.czechpmdevs.bedrockproxy.console.*;
import lombok.Getter;

public class HelpCommand implements AliasedCommand {

    @Getter
    private final CommandMap commandMap;

    public HelpCommand(CommandMap commandMap) {
        this.commandMap = commandMap;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof ConsoleCommandSender) {
            sender.sendMessage("§f--- Showing help page 1/1 ---");
            for(Command command : this.getCommandMap().getCommands().values()) {
                sender.sendMessage("§2/" + command.getName() + " §f" + command.getDescription());
            }
        }
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Displays all available commands";
    }

    @Override
    public String[] getAliases() {
        return new String[] {"?"};
    }
}
