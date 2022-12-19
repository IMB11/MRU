package mine.block.mru.datagen.providers;

import com.google.common.hash.HashCode;
import com.mojang.authlib.minecraft.client.MinecraftClient;
import com.sksamuel.scrimage.ImmutableImage;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.DataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Extend this class and implement the relevant methods.
 *
 * <p>Register an instance of the class with a {@link net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint}
 */
public abstract class FabricTextureProvider implements DataProvider {
    private final FabricDataOutput output;
    private final DataOutput.PathResolver pathResolver;

    protected FabricTextureProvider(FabricDataOutput output) {
        this.output = output;
        this.pathResolver = output.getResolver(DataOutput.OutputType.RESOURCE_PACK, "textures");
    }

    /**
     * Implement this method to add textures anywhere in the "assets" folder.
     */
    public abstract void generateMiscTextures(TextureConsumer textureConsumer);

    /**
     * Get a texture from the "assets" folder of the Minecraft jar.
     *
     * @param texturePath The path of the texture.
     * @return An ImmutableImage containing the texture.
     * @throws IOException If the texture doesn't exist, or is of an invalid file type, this is thrown.
     */
    public ImmutableImage getMinecraftTexture(Identifier texturePath) throws IOException {
        return ImmutableImage.loader().fromStream(MinecraftClient.class.getResourceAsStream("/assets/" + texturePath.getNamespace() + "/" + texturePath.getPath()));
    }

    @Override
    public CompletableFuture<Void> run(DataWriter writer) {
        final List<CompletableFuture<?>> futures = new ArrayList<>();

        HashMap<Identifier, byte[]> textures = new HashMap<>();

        generateMiscTextures(textures::put);

        for (Map.Entry<Identifier, byte[]> identifierEntry : textures.entrySet()) {
            futures.add(CompletableFuture.runAsync(() -> {
                try {
                    writer.write(getAssetsPath(identifierEntry.getKey()), identifierEntry.getValue(), HashCode.fromBytes(identifierEntry.getValue()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }));
        }

        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    private Path getAssetsPath(Identifier texturePath) {
        return pathResolver.resolve(texturePath, "png");
    }

    @Override
    public String getName() {
        return "Textures";
    }
}