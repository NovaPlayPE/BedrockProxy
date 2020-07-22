package eu.czechpmdevs.bedrockproxy;

import eu.czechpmdevs.bedrockproxy.console.CommandSender;
import lombok.Getter;

public class Player implements CommandSender {

    @Getter
    private final ProxyServer proxy;

    @Getter
    protected String name;

    public Player(ProxyServer proxy, String name) {
        this.proxy = proxy;

        this.name = name;
    }

    public void sendMessage(String message) {
        // TODO - Implement sendMessage menthod
    }
}
