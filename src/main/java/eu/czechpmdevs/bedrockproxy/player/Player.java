package eu.czechpmdevs.bedrockproxy.player;

import eu.czechpmdevs.bedrockproxy.ProxyServer;
import eu.czechpmdevs.bedrockproxy.console.CommandSender;
import lombok.Getter;

public class Player implements CommandSender {

    @Getter
    private final ProxyServer proxy;

    @Getter
    protected PlayerInfo playerInfo;

    public Player(ProxyServer proxy, PlayerInfo info) {
        this.proxy = proxy;

        this.playerInfo = info;
    }

    @Override
    public void sendMessage(String message) {
        // TODO - Implement sendMessage menthod
    }

    @Override
    public boolean isOp() {
        return false;
    }
}
