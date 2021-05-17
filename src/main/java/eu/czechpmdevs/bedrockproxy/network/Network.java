package eu.czechpmdevs.bedrockproxy.network;

import eu.czechpmdevs.bedrockproxy.ProxyServer;
import eu.czechpmdevs.bedrockproxy.network.connection.DownstreamConnection;
import eu.czechpmdevs.bedrockproxy.network.game.GameNetworkManager;
import lombok.Getter;

public class Network {

    @Getter
    private final ProxyServer proxy;
    
    @Getter
    public NetworkManager gameNetworkManager;
    
    @Getter
    public SessionManager sessionManager;

    public Network(ProxyServer proxy) {
        this.proxy = proxy;
        
        this.gameNetworkManager = new GameNetworkManager(this);
        this.gameNetworkManager.start();
        
        this.sessionManager = new SessionManager(this);
    }
    
    public void close() {
    	this.gameNetworkManager.stop();
    }
}