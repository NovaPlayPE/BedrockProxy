package eu.czechpmdevs.bedrockproxy.console;

public interface Command {

    public void execute(CommandSender sender, String[] args);

    public String getName();
    public String getDescription();
}
