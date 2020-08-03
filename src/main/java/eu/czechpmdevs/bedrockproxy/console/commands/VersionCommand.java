package eu.czechpmdevs.bedrockproxy.console.commands;

import eu.czechpmdevs.bedrockproxy.BedrockProxy;
import eu.czechpmdevs.bedrockproxy.console.AliasedCommand;
import eu.czechpmdevs.bedrockproxy.console.CommandMap;
import eu.czechpmdevs.bedrockproxy.console.CommandSender;
import lombok.Getter;

public class VersionCommand implements AliasedCommand {

    @Getter
    private final CommandMap commandMap;

    public VersionCommand(CommandMap commandMap) {
        this.commandMap = commandMap;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender.isOp()) {
            sender.sendMessage("This proxy is running " + BedrockProxy.NAME + " with api version " + BedrockProxy.API_VERSION + ".");
        }
    }

    @Override
    public String getName() {
        return "version";
    }

    @Override
    public String getDescription() {
        return "Displays proxy server version";
    }

    @Override
    public String[] getAliases() {
        return new String[] {"ver", "about"};
    }
}
