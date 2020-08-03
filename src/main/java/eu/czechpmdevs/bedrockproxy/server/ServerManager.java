package eu.czechpmdevs.bedrockproxy.server;

import eu.czechpmdevs.bedrockproxy.Player;
import eu.czechpmdevs.bedrockproxy.ProxyServer;
import eu.czechpmdevs.bedrockproxy.utils.InternetAddress;
import lombok.Getter;

import java.util.*;

public class ServerManager {

    @Getter
    private final ProxyServer proxy;

    private final Map<String, Server> servers = new HashMap<>();

    public ServerManager(ProxyServer proxy) {
        this.proxy = proxy;
    }

    public void loadConfiguration(HashMap<String, Object> configuration) {
        for(String serverName : configuration.keySet()) {
            Map<String, Object> serverSettings = (Map<String, Object>) configuration.get(serverName);

            InternetAddress address = null;
            boolean isOpened = true;
            boolean isDefault = false;

            for(String key : serverSettings.keySet()) {
                switch (key) {
                    case "address":
                        String[] splitAddress = String.valueOf(serverSettings.get(key)).split(":");

                        address = new InternetAddress(splitAddress[0], Integer.parseInt(splitAddress[1]));
                        break;
                    case "open":
                        isOpened = (Boolean) serverSettings.get(key);
                        break;
                    case "default":
                        isDefault = (Boolean) serverSettings.get(key);
                        break;
                }
            }

            if(address == null) {
                this.getProxy().getLogger().error("Error whilst loading server configuration: " + serverName + " has not valid address!");
                continue;
            }

            this.registerServer(new Server(this, serverName, address, isOpened, isDefault));
        }

        this.getProxy().getLogger().info("Loaded " + this.servers.size() + " servers.");
    }

    public void registerServer(Server server) {
        this.registerServer(server, false);
    }

    public void registerServer(Server server, boolean replace) {
        if(this.servers.containsKey(server.getName()) && !replace) {
            return;
        }

        this.servers.put(server.getName(), server);
    }

    public List<Server> getDefaultServers() {
        return this.getDefaultServers(false);
    }

    public List<Server> getDefaultServers(boolean onlyJoinable) {
        List<Server> defaultServers = new ArrayList<>();
        for(Server server : this.servers.values()) {
            if(server.isDefault() && (!onlyJoinable || (server.isOnline() && server.getOnlinePlayers().size() < server.getMaxPlayers()))) {
                defaultServers.add(server);
            }
        }

        return defaultServers;
    }

    public Server getDefaultServerForPlayer(Player player) {
        List<Server> availableServers = new ArrayList<>();
        for(Server server : this.getDefaultServers(true)) {
            if(server.isOpened() || player.isOp()) {
                availableServers.add(server);
            }
        }

        if(availableServers.size() == 0) {
            return null;
        }

        if(availableServers.size() == 1) {
            return availableServers.get(0);
        }

        availableServers.sort((a, b) -> Integer.compare(b.getOnlinePlayers().size(), a.getOnlinePlayers().size()));

        return availableServers.get(0);
    }
}
