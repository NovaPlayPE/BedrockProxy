package eu.czechpmdevs.bedrockproxy.network;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.nukkitx.protocol.bedrock.BedrockServer;

import eu.czechpmdevs.bedrockproxy.ProxyServer;
import lombok.Getter;

public class NetworkManager {
	
	@Getter
	private ProxyServer server;
	public static ExecutorService pool = Executors.newCachedThreadPool();
	
	public NetworkManager(ProxyServer server) {
		this.server = server;
	}
	
	public void start() {
		pool.execute(() -> {
			BedrockServer beServer = new BedrockServer(new InetSocketAddress(this.server.getIp(), this.server.getPort()));
			
			beServer.bind().whenComplete((hm, throwable) -> {
				if(throwable == null) {
					server.getLogger().info("Started BedrockProxy on port " + String.valueOf(server.getPort()));
				} else {
					server.getLogger().info("Error while starting BedrockProxy: " + throwable.getStackTrace());
				}
			});
		});
	}
	
	public void stop() {
		pool.shutdown();
	}
	
}