package dev.imb11.mru.yacl;

import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.controller.*;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * A helper class for creating config options for YetAnotherConfigLib (YACL) v3.5+.
 */
public class ConfigHelper {
    private final String modID;
    private final String configTranslationKey;

    /**
     * Create a new ConfigHelper.
     *
     * @param modID                The mod ID.
     * @param configTranslationKey The translation key for the config.
     */
    public ConfigHelper(@NotNull String modID, @NotNull String configTranslationKey) {
        this.modID = modID;
        this.configTranslationKey = configTranslationKey;
    }

    /**
     * @param entryType        The type of the entry that requires a translation key,
     * @param configOptionName The name of the entry that requires a translation key,
     * @return The {@link Text} with substituted values in the translation key.
     */
    public @NotNull Text getText(@NotNull EntryType entryType, @NotNull String configOptionName) {
        @NotNull String entryText;
        switch (entryType) {
            case CATEGORY_NAME -> entryText = "category";
            case GROUP_NAME -> entryText = "group";
            case OPTION_NAME -> entryText = "option";
            case OPTION_DESCRIPTION -> entryText = "option.description";
            default -> throw new IllegalArgumentException("TextType is invalid.");
        }

        return Text.translatable(String.format("%s.%s.%s.%s", modID, configTranslationKey, entryText, configOptionName));
    }

    /**
     * Get the option description for a given option.
     *
     * @param name      The name of the option.
     * @param withImage Whether to include an image with the option description.
     * @return The option description.
     */
    public @NotNull OptionDescription get(@NotNull String name, boolean withImage) {
        var builder = OptionDescription.createBuilder()
                .text(getText(EntryType.OPTION_DESCRIPTION, name));

        if (withImage) {
            builder = builder.webpImage(Identifier.of(modID, "textures/gui/options/" + name.toLowerCase() + ".webp"));
        }

        return builder.build();
    }

    /**
     * Creates a boolean option.
     *
     * @param name         The name of the option.
     * @param defaultValue The default value of the option.
     * @param getter       The getter for the option.
     * @param setter       The setter for the option.
     * @param withImage    Whether to include an image with the option description.
     * @return The boolean option.
     */
    public @NotNull Option<Boolean> get(@NotNull String name, boolean defaultValue, Supplier<Boolean> getter, Consumer<Boolean> setter, boolean withImage) {
        return Option.<Boolean>createBuilder()
                .name(getText(EntryType.OPTION_NAME, name))
                .description(get(name, withImage))
                .binding(defaultValue, getter, setter)
                .controller(opt -> BooleanControllerBuilder.create(opt).coloured(true).trueFalseFormatter())
                .build();
    }

    /**
     * Creates a boolean option.
     *
     * @param name         The name of the option.
     * @param defaultValue The default value of the option.
     * @param getter       The getter for the option.
     * @param setter       The setter for the option.
     * @return The boolean option.
     */
    public @NotNull Option<Boolean> get(@NotNull String name, boolean defaultValue, Supplier<Boolean> getter, Consumer<Boolean> setter) {
        return get(name, defaultValue, getter, setter, false);
    }

    /**
     * Creates a string option.
     *
     * @param name         The name of the option.
     * @param defaultValue The default value of the option.
     * @param getter       The getter for the option.
     * @param setter       The setter for the option.
     * @param withImage    Whether to include an image with the option description.
     * @return The string option.
     */
    public @NotNull Option<String> get(@NotNull String name, String defaultValue, Supplier<String> getter, Consumer<String> setter, boolean withImage) {
        return Option.<String>createBuilder()
                .name(getText(EntryType.OPTION_NAME, name))
                .description(get(name, withImage))
                .binding(defaultValue, getter, setter)
                .controller(StringControllerBuilder::create)
                .build();
    }

    /**
     * Creates a string option.
     *
     * @param name         The name of the option.
     * @param defaultValue The default value of the option.
     * @param getter       The getter for the option.
     * @param setter       The setter for the option.
     * @return The string option.
     */
    public @NotNull Option<String> get(@NotNull String name, String defaultValue, Supplier<String> getter, Consumer<String> setter) {
        return get(name, defaultValue, getter, setter, false);
    }

    /**
     * Creates an integer option.
     *
     * @param name         The name of the option.
     * @param min          The minimum value of the option.
     * @param max          The maximum value of the option.
     * @param defaultValue The default value of the option.
     * @param getter       The getter for the option.
     * @param setter       The setter for the option.
     * @param withImage    Whether to include an image with the option description.
     * @return The integer option.
     */
    public @NotNull Option<Integer> getField(@NotNull String name, int min, int max, int defaultValue, Supplier<Integer> getter, Consumer<Integer> setter, boolean withImage) {
        return Option.<Integer>createBuilder()
                .name(getText(EntryType.OPTION_NAME, name))
                .description(get(name, withImage))
                .binding(defaultValue, getter, setter)
                .controller(opt -> IntegerFieldControllerBuilder.create(opt).min(min).max(max))
                .build();
    }

    /**
     * Creates an integer option.
     *
     * @param name         The name of the option.
     * @param min          The minimum value of the option.
     * @param max          The maximum value of the option.
     * @param defaultValue The default value of the option.
     * @param getter       The getter for the option.
     * @param setter       The setter for the option.
     * @return The integer option.
     */
    public @NotNull Option<Integer> getField(@NotNull String name, int min, int max, int defaultValue, Supplier<Integer> getter, Consumer<Integer> setter) {
        return getField(name, min, max, defaultValue, getter, setter, false);
    }

    /**
     * Creates an integer option.
     *
     * @param name         The name of the option.
     * @param defaultValue The default value of the option.
     * @param getter       The getter for the option.
     * @param setter       The setter for the option.
     * @param withImage    Whether to include an image with the option description.
     * @return The integer option.
     */
    public @NotNull Option<Integer> getField(@NotNull String name, int defaultValue, Supplier<Integer> getter, Consumer<Integer> setter, boolean withImage) {
        return getField(name, Integer.MIN_VALUE, Integer.MAX_VALUE, defaultValue, getter, setter, withImage);
    }

    /**
     * Creates an integer option.
     *
     * @param name         The name of the option.
     * @param defaultValue The default value of the option.
     * @param getter       The getter for the option.
     * @param setter       The setter for the option.
     * @return The integer option.
     */
    public @NotNull Option<Integer> getField(@NotNull String name, int defaultValue, Supplier<Integer> getter, Consumer<Integer> setter) {
        return getField(name, defaultValue, getter, setter, false);
    }

    /**
     * Creates a slider option.
     *
     * @param name         The name of the option.
     * @param min          The minimum value of the option.
     * @param max          The maximum value of the option.
     * @param defaultValue The default value of the option.
     * @param getter       The getter for the option.
     * @param setter       The setter for the option.
     * @param withImage    Whether to include an image with the option description.
     * @return The slider option.
     */
    public @NotNull Option<Integer> getSlider(@NotNull String name, int min, int max, int defaultValue, Supplier<Integer> getter, Consumer<Integer> setter, boolean withImage) {
        return Option.<Integer>createBuilder()
                .name(getText(EntryType.OPTION_NAME, name))
                .description(get(name, withImage))
                .binding(defaultValue, getter, setter)
                .controller(opt -> IntegerSliderControllerBuilder.create(opt).range(min, max).step(1))
                .build();
    }

    /**
     * Creates a slider option.
     *
     * @param name         The name of the option.
     * @param min          The minimum value of the option.
     * @param max          The maximum value of the option.
     * @param defaultValue The default value of the option.
     * @param getter       The getter for the option.
     * @param setter       The setter for the option.
     * @return The slider option.
     */
    public @NotNull Option<Integer> getSlider(@NotNull String name, int min, int max, int defaultValue, Supplier<Integer> getter, Consumer<Integer> setter) {
        return getSlider(name, min, max, defaultValue, getter, setter, false);
    }

    /**
     * Creates a float option.
     *
     * @param name         The name of the option.
     * @param min          The minimum value of the option.
     * @param max          The maximum value of the option.
     * @param defaultValue The default value of the option.
     * @param getter       The getter for the option.
     * @param setter       The setter for the option.
     * @param withImage    Whether to include an image with the option description.
     * @return The float option.
     */
    public @NotNull Option<Float> getField(@NotNull String name, float min, float max, float defaultValue, Supplier<Float> getter, Consumer<Float> setter, boolean withImage) {
        return Option.<Float>createBuilder()
                .name(getText(EntryType.OPTION_NAME, name))
                .description(get(name, withImage))
                .binding(defaultValue, getter, setter)
                .controller(opt -> FloatFieldControllerBuilder.create(opt).min(min).max(max))
                .build();
    }

    /**
     * Creates a float option.
     *
     * @param name         The name of the option.
     * @param min          The minimum value of the option.
     * @param max          The maximum value of the option.
     * @param defaultValue The default value of the option.
     * @param getter       The getter for the option.
     * @param setter       The setter for the option.
     * @return The float option.
     */
    public @NotNull Option<Float> getField(@NotNull String name, float min, float max, float defaultValue, Supplier<Float> getter, Consumer<Float> setter) {
        return getField(name, min, max, defaultValue, getter, setter, false);
    }

    /**
     * Creates a float option.
     *
     * @param name         The name of the option.
     * @param defaultValue The default value of the option.
     * @param getter       The getter for the option.
     * @param setter       The setter for the option.
     * @param withImage    Whether to include an image with the option description.
     * @return The float option.
     */
    public @NotNull Option<Float> getField(@NotNull String name, float defaultValue, Supplier<Float> getter, Consumer<Float> setter, boolean withImage) {
        return getField(name, Float.MIN_VALUE, Float.MAX_VALUE, defaultValue, getter, setter, withImage);
    }

    /**
     * Creates a float option.
     *
     * @param name         The name of the option.
     * @param defaultValue The default value of the option.
     * @param getter       The getter for the option.
     * @param setter       The setter for the option.
     * @return The float option.
     */
    public @NotNull Option<Float> getField(@NotNull String name, float defaultValue, Supplier<Float> getter, Consumer<Float> setter) {
        return getField(name, defaultValue, getter, setter, false);
    }

    /**
     * Creates a float slider option.
     *
     * @param name         The name of the option.
     * @param min          The minimum value of the option.
     * @param max          The maximum value of the option.
     * @param step         The step value of the option.
     * @param defaultValue The default value of the option.
     * @param getter       The getter for the option.
     * @param setter       The setter for the option.
     * @param withImage    Whether to include an image with the option description.
     * @return The float slider option.
     */
    public @NotNull Option<Float> getSlider(@NotNull String name, float min, float max, float step, float defaultValue, Supplier<Float> getter, Consumer<Float> setter, boolean withImage) {
        return Option.<Float>createBuilder()
                .name(getText(EntryType.OPTION_NAME, name))
                .description(get(name, withImage))
                .binding(defaultValue, getter, setter)
                .controller(opt -> FloatSliderControllerBuilder.create(opt).range(min, max).step(step))
                .build();
    }

    /**
     * Creates a float slider option.
     *
     * @param name         The name of the option.
     * @param min          The minimum value of the option.
     * @param max          The maximum value of the option.
     * @param step         The step value of the option.
     * @param defaultValue The default value of the option.
     * @param getter       The getter for the option.
     * @param setter       The setter for the option.
     * @return The float slider option.
     */
    public @NotNull Option<Float> getSlider(@NotNull String name, float min, float max, float step, float defaultValue, Supplier<Float> getter, Consumer<Float> setter) {
        return getSlider(name, min, max, step, defaultValue, getter, setter, false);
    }

    /**
     * Creates a color option.
     *
     * @param name         The name of the option.
     * @param allowAlpha   Whether to allow alpha in the color.
     * @param defaultValue The default value of the option.
     * @param getter       The getter for the option.
     * @param setter       The setter for the option.
     * @param withImage    Whether to include an image with the option description.
     * @return The color option.
     */
    public @NotNull Option<Color> get(@NotNull String name, boolean allowAlpha, Color defaultValue, Supplier<Color> getter, Consumer<Color> setter, boolean withImage) {
        return Option.<Color>createBuilder()
                .name(getText(EntryType.OPTION_NAME, name))
                .description(get(name, withImage))
                .binding(defaultValue, getter, setter)
                .controller(opt -> ColorControllerBuilder.create(opt).allowAlpha(allowAlpha))
                .build();
    }

    /**
     * Creates a color option.
     *
     * @param name         The name of the option.
     * @param allowAlpha   Whether to allow alpha in the color.
     * @param defaultValue The default value of the option.
     * @param getter       The getter for the option.
     * @param setter       The setter for the option.
     * @return The color option.
     */
    public @NotNull Option<Color> get(@NotNull String name, boolean allowAlpha, Color defaultValue, Supplier<Color> getter, Consumer<Color> setter) {
        return get(name, allowAlpha, defaultValue, getter, setter, false);
    }

    /**
     * Creates a color option.
     *
     * @param name         The name of the option.
     * @param defaultValue The default value of the option.
     * @param getter       The getter for the option.
     * @param setter       The setter for the option.
     * @param withImage    Whether to include an image with the option description.
     * @return The color option.
     */
    public @NotNull Option<Color> get(@NotNull String name, Color defaultValue, Supplier<Color> getter, Consumer<Color> setter, boolean withImage) {
        return get(name, false, defaultValue, getter, setter, withImage);
    }

    /**
     * Creates a color option.
     *
     * @param name         The name of the option.
     * @param defaultValue The default value of the option.
     * @param getter       The getter for the option.
     * @param setter       The setter for the option.
     * @return The color option.
     */
    public @NotNull Option<Color> get(@NotNull String name, Color defaultValue, Supplier<Color> getter, Consumer<Color> setter) {
        return get(name, false, defaultValue, getter, setter, false);
    }
}
