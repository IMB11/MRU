package dev.imb11.mru.packing;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

@Deprecated
public class Packer {
    private static final Logger LOGGER = LoggerFactory.getLogger("MRU/Packer");

    /**
     * Packs files that match the globs into the output directory. You should run this after data generation, either via a mixin or using the {@link dev.imb11.mru.event.fabric.DatagenFinishedCallback} event.
     * @param outputDirectory The output directory of the data generation process.
     * @param globs The globs to match files against - anything that matches will be packed.
     */
    public static void pack(@NotNull Path outputDirectory, String... globs) {
        LOGGER.info("Beginning to pack files within '{}' that match the globs: {}", outputDirectory, Arrays.toString(globs));

        // Create pattern matcher for glob.
        ArrayList<PathMatcher> globsMatchers = new ArrayList<>();

        for (String glob : globs) {
            globsMatchers.add(FileSystems.getDefault().getPathMatcher("glob:" + glob));
        }

        try (Stream<Path> paths = Files.walk(outputDirectory.resolve("assets"))) {
            paths.filter(path -> path.toString().endsWith(".json") && globsMatchers.stream().anyMatch(matcher -> matcher.matches(path)))
                    .forEach(path -> {
                        try {
                            Path relativePath = outputDirectory.relativize(path);
                            Path targetPath = outputDirectory.resolve("packed").resolve(relativePath);
                            Files.createDirectories(targetPath.getParent());
                            Files.copy(path, targetPath, StandardCopyOption.REPLACE_EXISTING);
                            Files.delete(path);
                            LOGGER.info("Successfully packed file: {}", relativePath);
                        } catch (IOException e) {
                            throw new RuntimeException("Failed to copy file: " + path, e);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException("Failed to traverse assets directory", e);
        }
    }
}
