package mine.block.mru.datagen.filters;

import com.sksamuel.scrimage.ImmutableImage;
import com.sksamuel.scrimage.color.RGBColor;
import com.sksamuel.scrimage.pixels.Pixel;
import net.minecraft.util.Pair;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ColorSwapFilter {
    /**
     * Applies a color swap to an image.
     * @param image The image to apply the color swap to.
     * @param mainColor The color to swap.
     * @return The processed image.
     */
    public static ImmutableImage apply(ImmutableImage image, RGBColor mainColor) throws IOException {
        HashMap<Pair<Integer, Integer>, Integer> nonInvisAlphaPixelPositions = new HashMap<>();
        HashSet<Pair<Integer, Integer>> pixelPositions = new HashSet<>();

        for (Pixel pixel : image.pixels()) {
            if (pixel.toColor().alpha == 0) {
                continue;
            } else if (pixel.toColor().alpha > 0 && pixel.toColor().alpha != 1) {
                nonInvisAlphaPixelPositions.put(new Pair<>(pixel.x, pixel.y), pixel.toColor().alpha);
            }
            pixelPositions.add(new Pair<>(pixel.x, pixel.y));
        }

        ImmutableImage overlay = ImmutableImage.create(image.dimensions());

        for (Pair<Integer, Integer> pixelPosition : pixelPositions) {
            overlay.setColor(pixelPosition.getLeft(), pixelPosition.getRight(), mainColor);
        }

        for (Map.Entry<Pair<Integer, Integer>, Integer> pairIntegerEntry : nonInvisAlphaPixelPositions.entrySet()) {
            RGBColor color = new RGBColor(mainColor.red, mainColor.green, mainColor.blue, pairIntegerEntry.getValue());
            overlay.setColor(pairIntegerEntry.getKey().getLeft(), pairIntegerEntry.getKey().getRight(), color);
        }

        return overlay;
    }
}
