package eu.czechpmdevs.bedrockproxy.network.server;

import eu.czechpmdevs.bedrockproxy.utils.Logger;
import lombok.Getter;

public class FakeServer extends Thread {

    @Getter
    private final String ip;
    @Getter
    private final int port;

    public FakeServer(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("FakeServer thread");

        Logger logger = new Logger();
        UDPServerSocket socket = new UDPServerSocket(logger, ip, port);
    }
}
