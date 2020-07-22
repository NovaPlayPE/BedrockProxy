package eu.czechpmdevs.bedrockproxy.console.commands;

import eu.czechpmdevs.bedrockproxy.console.Command;
import eu.czechpmdevs.bedrockproxy.console.CommandMap;
import eu.czechpmdevs.bedrockproxy.console.CommandSender;
import lombok.Getter;

public class HelpCommand implements Command {

    @Getter
    private CommandMap commandMap;

    public HelpCommand(CommandMap commandMap) {
        this.commandMap = commandMap;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage("§f--- Showing help page 1/1 ---");
        for(Command command : this.getCommandMap().getCommands().values()) {
            sender.sendMessage("§2/" + command.getName() + " §f" + command.getDescription());
        }
        sender.sendMessage("§f--- Displayed " + this.getCommandMap().getCommands().size() + " commands. ---");
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Show all available commands";
    }
}
