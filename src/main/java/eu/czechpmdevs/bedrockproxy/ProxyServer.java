package eu.czechpmdevs.bedrockproxy;

import eu.czechpmdevs.bedrockproxy.console.Console;
import eu.czechpmdevs.bedrockproxy.utils.Logger;
import lombok.Getter;

import java.io.File;

public class ProxyServer {

    @Getter
    private boolean isRunning = true;

    @Getter
    private Logger logger;
    @Getter
    private Console console;

    public ProxyServer(Logger logger) {
        this.logger = logger;
        this.loadConfiguration();
        this.start();

        this.getLogger().info(this.getName() + " started in " + Math.round(System.currentTimeMillis() - BedrockProxy.START_TIME) + " seconds!");
        this.tickProcessor();
    }

    private void loadConfiguration() {
        File config = new File(this.getDataPath() + "config.json");
        if(!config.exists()) {

        }
    }

    private void start() {
        this.console = new Console(this);
    }

    private void tickProcessor() {
        while (this.isRunning()) {
            this.tick();
        }
    }

    private void tick() {
        this.getConsole().tick();
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
