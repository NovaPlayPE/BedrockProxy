package eu.czechpmdevs.bedrockproxy;

import lombok.Getter;

public class Player {

    @Getter
    private final ProxyServer proxy;

    @Getter
    protected String name;

    public Player(ProxyServer proxy, String name) {
        this.proxy = proxy;

        this.name = name;
    }
}
