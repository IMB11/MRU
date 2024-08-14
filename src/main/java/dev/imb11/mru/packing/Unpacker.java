package dev.imb11.mru.packing;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dev.imb11.mru.packing.resource.UnpackedResourcePack;
import org.apache.commons.lang3.StringEscapeUtils;
import org.jetbrains.annotations.ApiStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Stream;

public class Unpacker {
    private static final Logger LOGGER = LoggerFactory.getLogger("MRU/ResourceUnpacker");
    private static final HashMap<Class<?>, UnpackedResourcePack> PACKS = new HashMap<>();
    private static boolean unpacked = false;

    /**
     * Register a resource pack to be unpacked.
     * @param location Any class that exists within your mod's JAR file. This can be used to extract resources from your JAR.
     * @param pack The instance of the unpacked resource pack.
     */
    public static void register(Class<?> location, UnpackedResourcePack pack) {
        PACKS.put(location, pack);
        unpack(location, pack);
    }

    @ApiStatus.Internal
    public static ArrayList<UnpackedResourcePack> getPacks() {
        return new ArrayList<>(PACKS.values());
    }

    private static URI jarUrlResolver(String modID, Class<?> location) throws URISyntaxException {
        //? if fabric {
        return location.getProtectionDomain().getCodeSource().getLocation().toURI();
        //?}

        //? if forge {
        /*return net.minecraftforge.fml.ModList.get().getModFileById(modID).getFile().getFilePath().toUri();
        *///?}

        //? if neoforge {
/*        return net.neoforged.fml.ModList.get().getModFileById(modID).getFile().getFilePath().toUri();
        *///?}
    }

    private static void unpack(Class<?> location, UnpackedResourcePack pack) {
        LOGGER.info("Starting to unpack {}", location.getName());
        Path unpackedPath = pack.getPackPath();
        Path metaPath = unpackedPath.resolve("pack.mcmeta");
        Path readmePath = unpackedPath.resolve("README.txt");

        try {
            Files.createDirectories(unpackedPath);
        } catch (IOException e) {
            LOGGER.error(
                    "Exception thrown while creating folders for the unpacked config resource pack (path: {}): {}", unpackedPath, e);
        }

        if (!readmePath.toFile().exists()) {
            if(pack.getReadme() != null) {
                LOGGER.info("Creating README.txt in unpacked resource pack folder.");
                try {
                    Files.writeString(readmePath, pack.getReadme());
                } catch (IOException e) {
                    LOGGER.error("Failed to write README.txt");
                    throw new RuntimeException(e);
                }
            }
            
            
        }

        /*? if =1.20.1 {*/
        /*int packFormat = 15;
         *//*?} elif =1.20.4 {*/
        /*int packFormat = 26;
         *//*?} elif =1.20.6 {*/
        /*int packFormat = 41;
         *//*?} else {*/
        int packFormat = 45;
        /*?}*/

        if (!metaPath.toFile().exists()) {
            LOGGER.info("Creating pack.mcmeta in unpacked resource pack folder.");
            String desc = pack.getReadme();

            if(desc == null) {
                desc = "An unpacked resource pack provided by MRU.";
            }
            try {
                Files.writeString(metaPath, String.format("""
                        {
                          "pack": {
                            "description": "%s",
                            "pack_format": %d
                          }
                        }
                        """, StringEscapeUtils.escapeJson(desc), packFormat)
                );
            } catch (IOException e) {
                LOGGER.error("Failed to write pack.mcmeta.");
                throw new RuntimeException(e);
            }
        } else {
            // Ensure pack format is set to packFormat
            try {
                String metaContent = Files.readString(metaPath);
                Gson GSON = new GsonBuilder().setPrettyPrinting().create();
                JsonObject object = GSON.fromJson(metaContent, JsonObject.class);
                object.getAsJsonObject("pack").addProperty("pack_format", packFormat);
                Files.writeString(metaPath, GSON.toJson(object));
                LOGGER.info("Updated pack.mcmeta to pack format {}", packFormat);
            } catch (IOException e) {
                LOGGER.error("Failed to update pack.mcmeta.");
                throw new RuntimeException(e);
            }
        }

        try {
            URI jarUrl = jarUrlResolver(pack.getModID(), location);
            URL resourceUrl = location.getClassLoader().getResource("packed");
            if (resourceUrl == null) {
                throw new IllegalArgumentException("Resource not found: packed - There are no resources to unpack!");
            }

            URI uri = resourceUrl.toURI();

            LOGGER.info("Unpacking resources from: {}", uri);

            // Check if running from a JAR file
            try {
                String jarPath = Paths.get(jarUrl).toString();
                LOGGER.info("Running from JAR file: {}", jarPath);

                try (JarFile jarFile = new JarFile(jarPath)) {
                    Stream<JarEntry> entries = jarFile.stream();
                    entries.filter(e -> e.getName().startsWith("packed/"))
                            .forEach(entry -> {
                                Path targetPath = unpackedPath.resolve(entry.getName().substring("packed/".length()));
                                LOGGER.info("Unpacking: {}", targetPath);
                                if (entry.isDirectory()) {
                                    try {
                                        if (!Files.exists(targetPath)) {
                                            Files.createDirectories(targetPath);
                                        }
                                    } catch (IOException e) {
                                        LOGGER.error("Failed to create directory: {}", targetPath, e);
                                    }
                                } else {
                                    if (!Files.exists(targetPath)) {
                                        try (InputStream inputStream = jarFile.getInputStream(entry)) {
                                            Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
                                        } catch (IOException e) {
                                            LOGGER.error("Failed to copy file: {}", targetPath, e);
                                        }
                                    }
                                }
                            });
                }
            } catch (Exception ignored) {
                // Running from file system
                Path path = Paths.get(jarUrl.resolve("packed"));
                Files.walk(path).forEach(sourcePath -> {
                    try {
                        Path relativePath = path.relativize(sourcePath);
                        Path targetPath = unpackedPath.resolve(relativePath);

                        if (Files.isDirectory(sourcePath)) {
                            if (!Files.exists(targetPath)) {
                                Files.createDirectories(targetPath);
                            }
                        } else {
                            if (!Files.exists(targetPath)) {
                                Files.copy(sourcePath, targetPath);
                            }
                        }
                    } catch (IOException e) {
                        LOGGER.error("Failed to copy file.", e);
                    }
                });
            }

            pack.walkNamespaces();
        } catch (Exception e) {
            LOGGER.error("Failed to unpack resources", e);
        }
    }
}
