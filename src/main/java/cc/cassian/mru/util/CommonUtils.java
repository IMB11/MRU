package cc.cassian.mru.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.locale.Language;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

@SuppressWarnings("unused")
public class CommonUtils {
	/**
	 * Creates an ID from a namespace and path.
	 */
	public static Identifier id(String namespace, String path) {
		//? if >1.21 {
		return Identifier.fromNamespaceAndPath(namespace, path);
		//?} else {
		/*return new Identifier(namespace, path);
		 *///?}
	}

	public static TagKey<Block> blockTag(String namespace, String path) {
		return TagKey.create(Registries.BLOCK, CommonUtils.id(namespace, path));
	}

	public static TagKey<Item> itemTag(String namespace, String path) {
		return TagKey.create(Registries.ITEM, CommonUtils.id(namespace, path));
	}

	public static ResourceKey<Block> blockKey(String namespace, String path) {
		return ResourceKey.create(Registries.BLOCK, CommonUtils.id(namespace, path));
	}

	public static ResourceKey<Item> itemKey(String namespace, String path) {
		return ResourceKey.create(Registries.ITEM, CommonUtils.id(namespace, path));
	}


	/**
	 * Creates an ID from a string, defaulting to the Minecraft namespace if no namespace is included.
	 */
	public static Identifier parseId(String string) {
		//? if >1.21 {
		return Identifier.parse(string);
		//?} else {
		/*return new Identifier(string);
		 *///?}
	}

	/**
	 * Checks if a translation key exits in the current language.
	 */
	public static boolean exists(String formatted) {
		return Language.getInstance().has(formatted);
	}
}
