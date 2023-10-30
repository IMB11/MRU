package com.mineblock11.mru.config;

import com.google.gson.GsonBuilder;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.OptionGroup;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import org.apache.commons.lang3.text.WordUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;


/**
 * Various helper methods for YACL.
 */
public class YACLHelper {
    /**
     * Namespaced helper for YACL.
     */
    public static class NamespacedHelper {
        /**
         * The namespace of the mod.
         */
        public final String namespace;

        /**
         * Creates a new namespaced helper.
         * @param namespace The namespace of the mod.
         */

        public NamespacedHelper(String namespace) {
            this.namespace = namespace;
        }


        /**
         * Gets the name of the config option.
         * @param id The ID of the config option.
         * @return The translated name of the config option.
         */
        public Text getName(String id) {
            return YACLHelper.getName(this.namespace, id);
        }

        /**
         * Gets the description of the config option.
         * @param id The ID of the config option.
         * @return The translated description of the config option.
         */
        public Text getDesc(String id) {
            return YACLHelper.getDesc(this.namespace, id);
        }

        /**
         * Gets the image of the config option.
         * @param id The ID of the config option.
         * @return The location of the image of the config option.
         */
        public Identifier getImg(String id) {
            return YACLHelper.getImg(this.namespace, id);
        }

        /**
         * Gets the description of the config option.
         * @param id The ID of the config option.
         * @param image Whether or not the config option has an image.
         * @return The description of the config option.
         */
        public OptionDescription description(String id, boolean image) {
            return YACLHelper.description(this.namespace, id, image);
        }

        /**
         * Gets the description of the config option.
         * @param id The ID of the config option.
         * @return The description of the config option.
         */
        public OptionDescription description(String id) {
            return YACLHelper.description(this.namespace, id);
        }

        /**
         * Creates a new config handler.
         * @param klass The class of the config.
         * @param unaryOperators The unary operators to apply to the GsonBuilder.
         * @return The config handler.
         * @param <T> The type of the config class.
         */
        public <T> ConfigClassHandler<T> createHandler(Class<T> klass, ArrayList<UnaryOperator<GsonBuilder>> unaryOperators) {
            return YACLHelper.createHandler(this.namespace, klass, unaryOperators);
        }

        /**
         * Creates a new config handler.
         * @param klass The class of the config.
         * @return The config handler.
         * @param <T> The type of the config class.
         */
        public <T> ConfigClassHandler<T> createHandler(Class<T> klass) {
            return YACLHelper.createHandler(this.namespace, klass);
        }

        /**
         * Creates a togglable option.
         * @param id The ID of the config option.
         * @param optionToToggle The option to toggle.
         * @param defaultValue The default value of the option.
         * @param getter The getter of the option.
         * @param setter The setter of the option.
         * @return The togglable option.
         */
        public Option<Boolean> createTogglableOption(String id, Option<?> optionToToggle, boolean defaultValue, Supplier<Boolean> getter, Consumer<Boolean> setter) {
            return YACLHelper.createTogglableOption(this.namespace, id, optionToToggle, defaultValue, getter, setter);
        }

        /**
         * Creates a togglable option group.
         * @param id The ID of the group.
         * @param optionsToToggle The options to toggle.
         * @param defaultValue The default value of the option.
         * @param getter The getter of the option.
         * @param setter The setter of the option.
         * @return The togglable option group.
         */
        public OptionGroup createToggleableGroup(String id, ArrayList<Option<?>> optionsToToggle, boolean defaultValue, Supplier<Boolean> getter, Consumer<Boolean> setter) {
            return YACLHelper.createToggleableGroup(this.namespace, id, optionsToToggle, defaultValue, getter, setter);
        }

        /**
         * Creates a togglable option group.
         * @param id The ID of the group.
         * @param hasImage Whether or not the group has an image.
         * @param optionsToToggle The options to toggle.
         * @param defaultValue The default value of the option.
         * @param getter The getter of the option.
         * @param setter The setter of the option.
         * @return The togglable option group.
         */
        public OptionGroup createToggleableGroup(String id, boolean hasImage, ArrayList<Option<?>> optionsToToggle, boolean defaultValue, Supplier<Boolean> getter, Consumer<Boolean> setter) {
            return YACLHelper.createToggleableGroup(this.namespace, id, hasImage, optionsToToggle, defaultValue, getter, setter);
        }
    }

    /**
     * Get the name of the config option.
     * @param namespace The namespace of the mod.
     * @param id The ID of the config option.
     * @return
     */
    public static Text getName(String namespace, String id) {
        return Text.translatable(namespace + ".config." + id);
    }

    /**
     * Get the description of the config option.
     * @param namespace The namespace of the mod.
     * @param id The ID of the config option.
     * @return The translated description of the config option.
     */
    public static Text getDesc(String namespace, String id) {
        return Text.translatable(namespace + ".config." + id + ".desc");
    }

    /**
     * Get the image of the config option.
     * @param namespace The namespace of the mod.
     * @param id The ID of the config option.
     * @return The location of the image of the config option.
     */
    public static Identifier getImg(String namespace, String id) {
        return new Identifier(namespace, "textures/config/" + id.toLowerCase() + ".webp");
    }

    /**
     * Get the description of the config option.
     * @param namespace The namespace of the mod.
     * @param id The ID of the config option.
     * @param image Whether or not the config option has an image.
     * @return The description of the config option.
     */
    public static OptionDescription description(String namespace, String id, boolean image) {
        var builder = OptionDescription.createBuilder()
                .text(getDesc(namespace, id));

        if(image) {
            builder.webpImage(getImg(namespace, id.replace("group.", "")));
        }

        return builder.build();
    }

    /**
     * Get the description of the config option.
     * @param namespace The namespace of the mod.
     * @param id The ID of the config option.
     * @return The description of the config option.
     */
    public static OptionDescription description(String namespace, String id) {
        return description(namespace, id, false);
    }

    /**
     * Creates a new config handler.
     * @param id The ID of the config.
     * @param klass The class of the config.
     * @param unaryOperators The unary operators to apply to the GsonBuilder.
     * @return The config handler.
     * @param <T> The type of the config class.
     */
    public static <T> ConfigClassHandler<T> createHandler(String id, Class<T> klass, ArrayList<UnaryOperator<GsonBuilder>> unaryOperators) {
        var configPath = FabricLoader.getInstance().getConfigDir().resolve(id + ".config.json");
        return ConfigClassHandler
                .createBuilder(klass)
                .id(new Identifier(id, "config"))
                .serializer(config -> {
                    var builder = GsonConfigSerializerBuilder
                            .create(config)
                            .setPath(configPath)
                            .appendGsonBuilder(GsonBuilder::setPrettyPrinting);

                    for (UnaryOperator<GsonBuilder> unaryOperator : unaryOperators) {
                        builder.appendGsonBuilder(unaryOperator);
                    }

                    return builder.build();
                })
                .build();
    }

    /**
     * Creates a new config handler.
     * @param id The ID of the config.
     * @param klass The class of the config.
     * @return The config handler.
     * @param <T> The type of the config class.
     */
    public static <T> ConfigClassHandler<T> createHandler(String id, Class<T> klass) {
        return createHandler(id, klass, new ArrayList<>());
    }

    private static Option.Builder<Boolean> createToggleableOptionBuilder(String namespace, String id, boolean defaultValue, Supplier<Boolean> getter, Consumer<Boolean> setter) {
        return Option.<Boolean>createBuilder()
                .name(getName(namespace, "disable" + WordUtils.capitalize(id)))
                .description(OptionDescription.createBuilder()
                        .text(getDesc(namespace, "disable" + WordUtils.capitalize(id)))
                        .webpImage(getImg(namespace, "disable" + WordUtils.capitalize(id)))
                        .build())
                .controller(opt -> BooleanControllerBuilder.create(opt).yesNoFormatter())
                .binding(defaultValue, getter, setter);
    }

    /**
     * Creates a togglable option.
     * @param namespace The namespace of the mod.
     * @param id The ID of the config option.
     * @param optionToToggle The option to toggle.
     * @param defaultValue The default value of the option.
     * @param getter The getter of the option.
     * @param setter The setter of the option.
     * @return The togglable option.
     */
    public static Option<Boolean> createTogglableOption(String namespace, String id, Option<?> optionToToggle, boolean defaultValue, Supplier<Boolean> getter, Consumer<Boolean> setter) {
        return createToggleableOptionBuilder(namespace, id, defaultValue, getter, setter)
                .listener((opt, val) -> {
                    optionToToggle.setAvailable(!val);
                }).build();
    }

    /**
     * Creates a togglable option group.
     * @param namespace The namespace of the mod.
     * @param id The ID of the group.
     * @param optionsToToggle The options to toggle.
     * @param defaultValue The default value of the option.
     * @param getter The getter of the option.
     * @param setter The setter of the option.
     * @return
     */
    public static OptionGroup createToggleableGroup(String namespace, String id, ArrayList<Option<?>> optionsToToggle, boolean defaultValue, Supplier<Boolean> getter, Consumer<Boolean> setter) {
        return createToggleableGroup(namespace, id, false, optionsToToggle, defaultValue, getter, setter);
    }

    /**
     * Creates a togglable option group.
     * @param namespace The namespace of the mod.
     * @param id The ID of the group.
     * @param hasImage Whether or not the group has an image.
     * @param optionsToToggle The options to toggle.
     * @param defaultValue The default value of the option.
     * @param getter The getter of the option.
     * @param setter The setter of the option.
     * @return The togglable option group.
     */
    public static OptionGroup createToggleableGroup(String namespace, String id, boolean hasImage, ArrayList<Option<?>> optionsToToggle, boolean defaultValue, Supplier<Boolean> getter, Consumer<Boolean> setter) {
        var toggleOption = createToggleableOptionBuilder(namespace, id, defaultValue, getter, setter)
                .listener((opt, val) -> {
                    optionsToToggle.forEach(option -> option.setAvailable(!val));
                }).build();

        return OptionGroup.createBuilder()
                .name(getName(namespace, "group." + id))
                .collapsed(false)
                .description(description(namespace, "group." + id, hasImage))
                .option(toggleOption)
                .options(optionsToToggle)
                .build();
    }
}
