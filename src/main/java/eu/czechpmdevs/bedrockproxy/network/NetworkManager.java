package eu.czechpmdevs.bedrockproxy.network;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import eu.czechpmdevs.bedrockproxy.ProxyServer;
import lombok.Getter;

public interface NetworkManager {
	
	void start();
	void stop();
	
}