package dev.imb11.mru;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Class used to interface with the https://api.imb11.dev/v2/ API.
 */
public class API {
    private static final String URL = "https://api.imb11.dev/v2";
    private final Gson GSON;
    public API() {
        GSON = new Gson();
    }

    /**
     * Get a JSON object from the API.
     * @param path The path to get from.
     * @return The JSON object.
     * @throws IOException
     */
    private JsonElement get(String path) throws IOException {
        String response = IOUtils.toString(new URL(URL + path), StandardCharsets.UTF_8);
        return GSON.fromJson(response, JsonElement.class);
    }

    /**
     * Get a list of ko-fi supporters.
     * @return An array of strings where each string is a ko-fi supporter's name/username.
     */
    public String[] getKofiSupporters() throws IOException {
        JsonElement json = get("/kofi");
        return GSON.fromJson(json, String[].class);
    }
}
