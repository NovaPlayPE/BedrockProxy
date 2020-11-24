package eu.czechpmdevs.bedrockproxy.network;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.nukkitx.protocol.bedrock.BedrockServer;

import eu.czechpmdevs.bedrockproxy.ProxyServer;
import lombok.Getter;

public interface NetworkManager {
	
	void start();
	void stop();
	
}