package eu.czechpmdevs.bedrockproxy;

import eu.czechpmdevs.bedrockproxy.utils.Logger;

public class BedrockProxy {

    public static final String NAME = "BedrockProxy";
    public static final String API_VERSION = "1.0.0-beta1";

    public static final String DATA_PATH = System.getProperty("user.dir") + "/";

    public static final long START_TIME = System.currentTimeMillis();

    public static void main(String[] args) {
        Logger logger = new Logger();

        try {
            new ProxyServer(logger);
        }
        catch (Exception e) {
            logger.error("Error while running ProxyServer class, shutting down server");
            e.printStackTrace();
        }
    }
}
