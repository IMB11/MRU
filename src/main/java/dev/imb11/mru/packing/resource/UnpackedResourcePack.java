package dev.imb11.mru.packing.resource;

import net.minecraft.resource.*;
import net.minecraft.resource.metadata.ResourceMetadataReader;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Attribution: <a href="https://github.com/gbl/CrowdinTranslate">CrowdinTranslate</a> and <a href="https://github.com/IMB11/Loqui">Loqui</a>.
 * <p>
 * An unpacked resource pack is one which is loaded from outside the game's `resourcepacks` folder and is not within a mod JAR file.
 * It's useful if you want to make a resource pack that is easily editable by the user.
 */
public class UnpackedResourcePack implements ResourcePack {
    private final String modID;
    private final Path packPath;
    private final Logger logger;
    private final String packName;
    private final Set<String> namespaces = new HashSet<>();
    private final @Nullable String readme;
    /**
     * Creates a new unpacked resource pack.
     *
     * @param modID    The mod ID of the mod that owns this resource pack.
     * @param packPath The location of the unpacked resource pack. MRU will handle the unpacking process as long as you
     * @param packName The name of the resource pack, this should be lowercase and contain no spaces.
     * @param readme   Optional: The contents of the README file for the resource pack.
     */
    public UnpackedResourcePack(String modID, Path packPath, String packName, @Nullable String readme) {
        this.modID = modID;
        this.packPath = packPath;
        this.packName = packName;
        this.readme = readme;

        this.logger = LoggerFactory.getLogger("MRU/UnpackedResourcePack/" + packName);
    }

    public String getModID() {
        return modID;
    }

    public @Nullable String getReadme() {
        return readme;
    }

    @ApiStatus.Internal
    public void walkNamespaces() {
        this.logger.info("Walking resource pack namespaces...");
        try {
            namespaces.clear();
            @NotNull Path assetsFolder = packPath.resolve("assets");
            if (!assetsFolder.toFile().exists() || !assetsFolder.toFile().isDirectory()) {
                return;
            }

            try (@NotNull var assets = Files.list(assetsFolder)) {
                assets.forEach(namespace -> {
                    if (namespace.toFile().isDirectory()) {
                        namespaces.add(namespace.getFileName().toString());
                        this.logger.info("Found namespace: {}", namespace.getFileName().toString());
                    }
                });
            }
        } catch (IOException e) {
            this.logger.error("Error occurred while walking resource pack namespaces: {}", ExceptionUtils.getStackTrace(e));
        }
    }

    @Override
    public @Nullable InputSupplier<InputStream> openRoot(String... strings) {
        String fileName = String.join("/", strings);
        Path filePath = packPath.resolve(fileName);

        if (filePath.toFile().exists()) {
            return () -> Files.newInputStream(filePath);
        }

        return null;
    }

    @Override
    public @Nullable InputSupplier<InputStream> open(ResourceType type, Identifier id) {
        return this.openRoot(type.getDirectory() + "/" + id.getNamespace() + "/" + id.getPath());
    }

    @Override
    public void findResources(ResourceType type, String namespace, String prefix, ResultConsumer consumer) {
        String start = packPath + "/" + type.getDirectory() + "/" + namespace + "/" + prefix;
        String[] files = new File(start).list();

        if (files == null || files.length == 0) {
            return;
        }

        List<Identifier> resultList = Arrays.stream(files)
                .map(file -> Identifier.of(namespace, prefix + "/" + file))
                .toList();

        for (Identifier result : resultList) {
            consumer.accept(result, open(type, result));
        }
    }

    @Override
    public @NotNull Set<String> getNamespaces(ResourceType packType) {
        return this.namespaces;
    }

    @Override
    public @Nullable <T> T parseMetadata(ResourceMetadataReader<T> metaReader) throws IOException {
        InputSupplier<InputStream> inputSupplier = this.openRoot("pack.mcmeta");

        if (inputSupplier != null) {
            try (InputStream input = inputSupplier.get()) {
                return AbstractFileResourcePack.parseMetadata(metaReader, input);
            }
        } else {
            return null;
        }
    }

    /*? if >=1.20.6 {*/
    @Override
    public ResourcePackInfo getInfo() {
        return new ResourcePackInfo(packName, Text.literal(packName), ResourcePackSource.BUILTIN, Optional.empty());
    }
    /*?}*/

    /*? if <=1.20.4 {*/
	/*@SuppressWarnings("CommentedOutCode")
	@Override
	public String getName() {
		return packName;
	}
	*//*?} else {*/
    @SuppressWarnings("CommentedOutCode")
    @Override
    public String getId() {
        return packName;
    }
    /*?}*/

    @Override
    public void close() {
    }

    public Path getPackPath() {
        return packPath;
    }

    public String getPackName() {
        return packName;
    }
}