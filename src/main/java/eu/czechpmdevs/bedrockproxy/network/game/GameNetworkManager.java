package eu.czechpmdevs.bedrockproxy.network.game;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.nukkitx.protocol.bedrock.BedrockServer;

import eu.czechpmdevs.bedrockproxy.network.Network;
import eu.czechpmdevs.bedrockproxy.network.NetworkManager;
import lombok.Getter;

public class GameNetworkManager implements NetworkManager{
	
	@Getter
	private Network network;
	public static ExecutorService pool = Executors.newCachedThreadPool();
	
	public GameNetworkManager(Network network) {
		this.network = network;
	}
	
	public void start() {
		pool.execute(() -> {
			BedrockServer beServer = new BedrockServer(new InetSocketAddress(this.network.getProxy().getIp(), this.network.getProxy().getPort()));
			
			beServer.bind().whenComplete((hm, throwable) -> {
				if(throwable == null) {
					network.getProxy().getLogger().info("Started BedrockProxy on port " + String.valueOf(network.getProxy().getPort()));
				} else {
					network.getProxy().getLogger().info("Error while starting BedrockProxy: " + throwable.getStackTrace());
				}
			});
		});
	}
	
	public void stop() {
		pool.shutdown();
	}
	
}