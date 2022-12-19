package mine.block.mru.datagen.providers;

import com.sksamuel.scrimage.ImmutableImage;
import com.sksamuel.scrimage.nio.PngWriter;
import net.minecraft.util.Identifier;

import java.io.IOException;

/**
 * A consumer used by various methods in {@link FabricTextureProvider}.
 */
public interface TextureConsumer {
    /**
     * Add a texture.
     *
     * @param texturePath The location of the texture.
     * @param image       The image data in PNG format.
     */
    void addTexture(Identifier texturePath, byte[] image);

    /**
     * Add a texture from a {@link ImmutableImage}.
     *
     * @param texturePath The location of the texture.
     * @param image       The image.
     * @throws IOException If the buffered image can't be converted to a PNG file, this is thrown.
     */
    default void addTexture(Identifier texturePath, ImmutableImage image) throws IOException {
        addTexture(texturePath, image.bytes(PngWriter.NoCompression));
    }
}