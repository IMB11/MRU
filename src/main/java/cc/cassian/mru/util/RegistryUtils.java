package cc.cassian.mru.util;

//? fabric
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import org.jspecify.annotations.Nullable;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;

import java.util.Optional;
import java.util.stream.Stream;

public class RegistryUtils {

	/**
	 * Creates a {@link CommonRegistry} for your mod's namespace. This can be used alongside {@link cc.cassian.mru.events.CommonRegisterEvent} for Fabric-style registration in common code.
	 */
	public static CommonRegistry registrar(String namespace) {
		return () -> namespace;
	}

	//? if >1.21.2 {
	public static <T> Optional<Holder.Reference<T>> getHolder(Registry<T> registry, Identifier name) {
		return registry.get(name);
	}
	public static <T> @Nullable T getValue(Registry<T> registry, Identifier name) {
		return registry.getValue(name);
	}

	public static <T> Iterable<Holder<T>> getTagOrEmpty(Registry<T> registry, TagKey<T> tagKey) {
		return registry.getTagOrEmpty(tagKey);
	}

	public static <T> Stream<Holder.Reference<T>> listElements(Registry<T> registry) {
		return registry.listElements();
	}
	//?} else if >1.20.4 {
	/*public static <T> Optional<Holder.Reference<T>> getHolder(Registry<T> registry, Identifier name) {
		return registry.getHolder(name);
	}
	public static <T> @Nullable T getValue(Registry<T> registry, Identifier name) {
		return registry.get(name);
	}
	public static <T> Iterable<Holder<T>> getTagOrEmpty(Registry<T> registry, TagKey<T> tagKey) {
		return registry.getTagOrEmpty(tagKey);
	}

	public static <T> Stream<Holder.Reference<T>> listElements(Registry<T> registry) {
		return registry.holders();
	}
	*///?} else {
	/*public static <T> Optional<Holder.Reference<T>> getHolder(Registry<T> registry, Identifier name) {
		return registry.getHolder(ResourceKey.create(registry.key(), name));
	}
	public static <T> @Nullable T getValue(Registry<T> registry, Identifier name) {
		return registry.get(name);
	}

	*///?}
}
