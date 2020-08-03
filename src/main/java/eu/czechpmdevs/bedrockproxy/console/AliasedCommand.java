package eu.czechpmdevs.bedrockproxy.console;

public interface AliasedCommand extends Command {

    public String[] getAliases();
}
