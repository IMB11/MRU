package com.mineblock11.mru;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Utilities {
    public static float lerp(float delta, float start, float end) {
        return (1 - delta) * start + delta * end;
    }

    public static String[] getKofiSupporters() throws IOException {
        // GET https://api.imb11.dev/v2/kofi returns JSON array of strings.
        String json = IOUtils.toString(new URL("https://api.imb11.dev/v2/kofi"), StandardCharsets.UTF_8);
        Gson gson = new Gson();
        return gson.fromJson(json, String[].class);
    }
}
