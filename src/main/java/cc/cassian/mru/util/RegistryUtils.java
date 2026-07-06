package cc.cassian.mru.util;

//? fabric
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jspecify.annotations.Nullable;

import java.util.Optional;
import java.util.Set;

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
	//?} else if >1.20.4 {
	/*public static <T> Optional<Holder.Reference<T>> getHolder(Registry<T> registry, Identifier name) {
		return registry.getHolder(name);
	}
	public static <T> @Nullable T getValue(Registry<T> registry, Identifier name) {
		return registry.get(name);
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
