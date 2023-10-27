package com.mineblock11.mru.updates;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.Version;
import net.fabricmc.loader.api.VersionParsingException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ModUpdateChecker {
    private final String modrinthID;
    private final ModContainer modContainer;

    public ModUpdateChecker(String modrinthID, ModContainer modContainer) {
        this.modrinthID = modrinthID;
        this.modContainer = modContainer;
    }

    public String getModrinthID() {
        return modrinthID;
    }

    public ModContainer getModContainer() {
        return modContainer;
    }

    public boolean doesNeedUpdating() {
        try {
            System.out.println("Checking for updates for " + modContainer.getMetadata().getName() + "...");
            var request = HttpRequest.newBuilder(new URI("https://api.mineblock11.dev/v2/update/" + modrinthID)).GET().build();
            var client = HttpClient.newBuilder().build();
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parse using GSON
            var json = response.body();
            System.out.println("JSON: " + json);
            var gson = new Gson();
            var modReleases = gson.fromJson(json, JsonObject.class);

            // Get latest release.
            var latest = modReleases.get("latest").getAsJsonObject();
            var versionString = latest.get("versionString").getAsString();

            // Compare versionString in modContainer with versionString here.
            // If the versionString in modContainer is less than the versionString here, return true.
            // Otherwise, return false.
            var result = modContainer.getMetadata().getVersion().compareTo(Version.parse(versionString));
            return result < 0;
        } catch (URISyntaxException | IOException | InterruptedException | VersionParsingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
