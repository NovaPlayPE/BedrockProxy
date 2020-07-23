package eu.czechpmdevs.bedrockproxy.utils;

import eu.czechpmdevs.bedrockproxy.BedrockProxy;
import eu.czechpmdevs.bedrockproxy.ProxyServer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Utils {

    public static void saveResource(String filename) {
        saveResource(filename, false);
    }

    public static void saveResource(String filename, boolean replace) {
        InputStream inputStream = Utils.class.getClassLoader().getResourceAsStream(filename);
        if(inputStream == null) {
            return;
        }

        File target = new File(BedrockProxy.DATA_PATH + filename);
        if(target.exists()) {
            if(!replace) {
                return;
            }
        }
        else {
            try {
                target.createNewFile();
            } catch (IOException exception) {
                ProxyServer.getInstance().getLogger().logException(exception);
            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(target);

            byte[] buffer = new byte[1024];
            int length;

            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }

            inputStream.close();
            outputStream.close();
        } catch (IOException exception) {
            ProxyServer.getInstance().getLogger().logException(exception);
        }
    }
}
