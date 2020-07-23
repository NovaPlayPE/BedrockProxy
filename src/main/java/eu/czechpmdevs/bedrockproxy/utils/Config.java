package eu.czechpmdevs.bedrockproxy.utils;

import eu.czechpmdevs.bedrockproxy.ProxyServer;
import lombok.Getter;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Config {

    public static final int PROPERTIES = 0;
    public static final int YAML = 1;

    @Getter
    private File file;
    @Getter
    private int type;

    private Map<Object, Object> data;

    public Config(File file, int type) {
        this.file = file;
        this.type = type;

        this.load();
    }

    public void load()  {
        this.setAll(new HashMap<Object, Object>());

        StringBuilder content = new StringBuilder();
        try {
            Scanner reader = new Scanner(this.getFile());
            while (reader.hasNextLine()) {
                content.append(reader.nextLine());

                if(reader.hasNextLine()) {
                    content.append("\n");
                }
            }

            reader.close();
        } catch (FileNotFoundException e) {
            ProxyServer.getInstance().getLogger().logException(e);
        }

        switch (this.getType()) {
            case Config.PROPERTIES:
                this.parseProperties(content.toString());
                break;
            case Config.YAML:
                this.parseYaml(content.toString());
                break;
        }
    }

    public void save() {
        try {
            if(!this.getFile().exists()) {
                this.getFile().createNewFile();
            }

            String content;
            switch (this.getType()) {
                case Config.PROPERTIES:
                    content = this.writeProperties();
                    break;
                case Config.YAML:
                    content = this.writeYaml();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + this.getType());
            }

            FileWriter writer = new FileWriter(this.getFile());
            writer.write(content);
            writer.close();
        }
        catch (IOException exception) {
            ProxyServer.getInstance().getLogger().logException(exception);
        }
    }

    private String writeProperties() {
        StringBuilder content = new StringBuilder("#Properties Config file\n");
        for(Object key : this.getAll().keySet()) {
            content.append(key).append("=").append(this.getAll().get(key)).append("\n");
        }

        return content.toString();
    }

    private String writeYaml() {
        Yaml yaml = new Yaml();
        return yaml.dump(this.data);
    }

    private void parseProperties(String content) {
        for(String line : content.split("\n")) {
            int splitIndex = line.indexOf('=');
            if(splitIndex == -1) {
                continue;
            }

            String key = line.substring(0, splitIndex);
            String value = line.substring(splitIndex + 1);

            switch (value.toLowerCase()) {
                case "true":
                case "on":
                case "yes":
                    this.getAll().put(key, true);
                    break;
                case "false":
                case "off":
                case "no":
                    this.getAll().put(key, false);
                    break;
                default:
                    this.getAll().put(key, value);
                    break;
            }
        }
    }

    private void parseYaml(String content) {
        Yaml yaml = new Yaml();
        this.setAll((Map<Object, Object>) yaml.load(content));
    }

    public Map<Object, Object> getAll() {
        return this.data;
    }

    public void setAll(Map<Object, Object> data) {
        this.data = data;
    }
}
