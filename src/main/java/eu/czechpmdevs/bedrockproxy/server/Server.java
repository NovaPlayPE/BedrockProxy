package eu.czechpmdevs.bedrockproxy.server;

import eu.czechpmdevs.bedrockproxy.Player;
import eu.czechpmdevs.bedrockproxy.utils.InternetAddress;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Server {

    @Getter
    private final ServerManager serverManager;

    @Getter
    private final String name;
    @Getter
    private final InternetAddress address;
    @Getter @Setter
    private boolean isOpened;
    @Getter @Setter
    private boolean isDefault;

    @Getter @Setter
    private boolean isOnline = false;
    @Getter @Setter
    private int maxPlayers = 0;

    private final List<Player> players = new ArrayList<>();

    public Server(ServerManager serverManager, String name, InternetAddress address, boolean isOpened, boolean isDefault) {
        this.serverManager = serverManager;

        this.name = name;
        this.address = address;
        this.isOpened = isOpened;
        this.isDefault = isDefault;
    }

    public void connectPlayer(Player player) {
        this.players.add(player);
        this.getServerManager().getProxy().getLogger().info("");
    }

    public void disconnectPlayer(Player player) {
        this.players.remove(player);
    }

    public List<Player> getOnlinePlayers() {
        return this.players;
    }
}
