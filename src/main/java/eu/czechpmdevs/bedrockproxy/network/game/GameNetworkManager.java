package eu.czechpmdevs.bedrockproxy.network.game;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import eu.czechpmdevs.bedrockproxy.network.Network;
import eu.czechpmdevs.bedrockproxy.network.NetworkManager;
import eu.czechpmdevs.bedrockproxy.utils.TextFormat;
import lombok.Getter;
import net.novatech.jbprotocol.GameSession;
import net.novatech.jbprotocol.GameVersion;
import net.novatech.jbprotocol.ProtocolServer;
import net.novatech.jbprotocol.bedrock.BedrockSession;
import net.novatech.jbprotocol.listener.LoginListener;
import net.novatech.jbprotocol.listener.ServerListener;
import net.novatech.jbprotocol.util.SessionData;

public class GameNetworkManager implements NetworkManager{
	
	@Getter
	private Network network;
	public static ExecutorService pool = Executors.newCachedThreadPool();
	
	public GameNetworkManager(Network network) {
		this.network = network;
	}
	
	public void start() {
		pool.execute(() -> {
			ProtocolServer server = new ProtocolServer(new InetSocketAddress(this.network.getProxy().getIp(), this.network.getProxy().getPort()), GameVersion.BEDROCK);
			server.setServerListener(new ServerListener() {

				@Override
				public void sessionConnected(GameSession session) {
					BedrockSession ses = (BedrockSession) session;
					ses.requireAuthentication(network.getProxy().isXboxAuthEnabled());
					ses.setLoginListener(new LoginListener() {

						@Override
						public void loginCompleted(SessionData data) {
							String username = data.getUsername();
							network.getProxy().getLogger().info(TextFormat.AQUA + username + TextFormat.GREEN + " connected to proxy");
						}
						
					});
				}

				@Override
				public void sessionDisconnected(GameSession session, String cause) {
					
				}
				
			});
		});
	}
	
	public void stop() {
		pool.shutdown();
	}
	
}