package cc.cassian.mru.util;

import net.minecraft.locale.Language;
import net.minecraft.resources.Identifier;

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

	/**
	 * Creates an ID from a string, defaulting to the Minecraft namespace if no namespace is included.
	 */
	private static Identifier parseId(String string) {
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
