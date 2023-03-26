package mine.block.mru.datagen.providers;

import com.google.common.hash.HashCode;
import com.mojang.authlib.minecraft.client.MinecraftClient;
import com.sksamuel.scrimage.ImmutableImage;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Extend this class and implement the relevant methods.
 *
 * <p>Register an instance of the class with {@link FabricDataGenerator#addProvider} in a {@link net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint}
 */
public abstract class FabricTextureProvider implements DataProvider {
    protected final FabricDataGenerator generator;

    protected FabricTextureProvider(FabricDataGenerator generator) {
        this.generator = generator;
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
    public void run(DataWriter writer) throws IOException {
        HashMap<Identifier, byte[]> textures = new HashMap<>();

        generateMiscTextures(textures::put);

        for (Map.Entry<Identifier, byte[]> identifierEntry : textures.entrySet()) {
            writer.write(getAssetsPath(identifierEntry.getKey()), identifierEntry.getValue(), HashCode.fromBytes(identifierEntry.getValue()));
        }
    }

    private Path getAssetsPath(Identifier texturePath) {
        return generator.getOutput().resolve(Path.of("assets", texturePath.getNamespace(), texturePath.getPath()));
    }

    @Override
    public String getName() {
        return "Textures";
    }
}