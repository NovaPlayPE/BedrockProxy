package eu.czechpmdevs.bedrockproxy.network;

import eu.czechpmdevs.bedrockproxy.ProxyServer;
import eu.czechpmdevs.bedrockproxy.network.connection.DownstreamConnection;
import lombok.Getter;

public class Network {

    @Getter
    private final ProxyServer proxy;

    private final DownstreamConnection downstreamConnection;

    public Network(ProxyServer proxy) {
        this.proxy = proxy;

        this.downstreamConnection = new DownstreamConnection(this.getProxy().getIp(), this.getProxy().getPort());
        this.downstreamConnection.start();
    }

    public void tick() {
    }

}
