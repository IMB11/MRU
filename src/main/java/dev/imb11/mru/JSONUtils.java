package dev.imb11.mru;

import com.google.gson.JsonObject;

import java.util.Optional;

public class JSONUtils {
    public static int getOrDefault(Optional<JsonObject> object, String fieldName, int defaultValue) {
        return object.filter(jsonObject -> jsonObject.has(fieldName))
                .map(jsonObject -> jsonObject.get(fieldName).getAsInt())
                .orElse(defaultValue);
    }

    public static boolean getOrDefault(Optional<JsonObject> object, String fieldName, boolean defaultValue) {
        return object.filter(jsonObject -> jsonObject.has(fieldName))
                .map(jsonObject -> jsonObject.get(fieldName).getAsBoolean())
                .orElse(defaultValue);
    }

    public static String getOrDefault(Optional<JsonObject> object, String fieldName, String defaultValue) {
        return object.filter(jsonObject -> jsonObject.has(fieldName))
                .map(jsonObject -> jsonObject.get(fieldName).getAsString())
                .orElse(defaultValue);
    }
}
