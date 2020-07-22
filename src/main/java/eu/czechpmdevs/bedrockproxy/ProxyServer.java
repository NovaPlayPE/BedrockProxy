package eu.czechpmdevs.bedrockproxy;

import eu.czechpmdevs.bedrockproxy.utils.Logger;
import lombok.Getter;

import java.io.File;

public class ProxyServer {

    @Getter
    private boolean isRunning = true;

    @Getter
    private final Logger logger;

    public ProxyServer(Logger logger) {
        this.logger = logger;
        this.loadConfiguration();

        this.getLogger().info(this.getName() + " started in " + Math.round(System.currentTimeMillis() - BedrockProxy.START_TIME) + " seconds!");
        this.tickProcessor();
    }

    private void loadConfiguration() {
        File config = new File(this.getDataPath() + "config.json");
        if(!config.exists()) {

        }
    }

    private void tickProcessor() {
        while (this.isRunning()) {
            this.tick();
        }
    }

    private void tick() {

    }

    public void shutdown() {
        this.getLogger().info("Shutting down proxy server.");
        this.isRunning = false;
    }

    public String getName() {
        return BedrockProxy.NAME;
    }

    public String getDataPath() {
        return BedrockProxy.DATA_PATH;
    }
}
