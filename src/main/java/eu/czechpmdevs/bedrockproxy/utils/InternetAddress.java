package eu.czechpmdevs.bedrockproxy.utils;

import lombok.Getter;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InternetAddress {

    @Getter
    private final String ip;
    @Getter
    private final int port;

    public InternetAddress(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public InetAddress toInetAddress() throws UnknownHostException {
        return InetAddress.getByName(ip);
    }

    public String toString() {
        return this.getIp() + ":" + this.getPort();
    }
}
