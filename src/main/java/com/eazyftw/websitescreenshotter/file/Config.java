package com.eazyftw.websitescreenshotter.file;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class Config {

    private static Config instance;
    private JsonObject root;

    public static Config getInstance() {
        if(instance == null)
            instance = new Config();

        return instance;
    }

    private Config() {
        File file = new File("config.json");

        if(!file.exists()) {
            try {
                InputStream src = Config.class.getResourceAsStream("/config.json");
                Files.copy(src, Paths.get(file.toURI()), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            String json = FileUtils.readFileToString(file, StandardCharsets.UTF_8);

            JsonParser jsonParser = new JsonParser();
            root = (JsonObject) jsonParser.parse(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean containsWebsite() {
        return root.has("websites") && root.getAsJsonArray("websites").size() > 0;
    }

    public int getInterval() {
        return root.has("interval") ? root.get("interval").getAsInt() : 2;
    }

    public List<Website> getWebsites() {
        List<Website> websites = new ArrayList<>();
        JsonArray wArray = root.getAsJsonArray("websites");

        wArray.forEach(wElement -> {
            if(wElement instanceof JsonObject) {
                JsonObject wObject = wElement.getAsJsonObject();

                if(wObject.has("url") && wObject.has("fileName")) {
                    int height = wObject.has("height") ? wObject.get("height").getAsInt() : 0;
                    int width = wObject.has("width") ? wObject.get("width").getAsInt() : 0;

                    websites.add(new Website(wObject.get("url").getAsString(), wObject.get("fileName").getAsString(), height, width));
                }
            }
        });

        return websites;
    }
}
