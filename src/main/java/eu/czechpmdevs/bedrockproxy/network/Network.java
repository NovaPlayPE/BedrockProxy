package eu.czechpmdevs.bedrockproxy.network;

import eu.czechpmdevs.bedrockproxy.ProxyServer;
import eu.czechpmdevs.bedrockproxy.network.server.FakeServer;
import lombok.Getter;

public class Network {

    @Getter
    private final ProxyServer proxy;

    private final FakeServer fakeServer;

    public Network(ProxyServer proxy) {
        this.proxy = proxy;

        this.fakeServer = new FakeServer(this.getProxy().getIp(), this.getProxy().getPort());
        this.fakeServer.start();
    }

    public void tick() {
    }

}
