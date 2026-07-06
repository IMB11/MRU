package cc.cassian.mru.client.util;

import net.minecraft.resources.Identifier;
import net.minecraft.client.KeyMapping;

@SuppressWarnings("unused")
public class ClientUtil {
	public static
	//? if >1.21.8 {
	KeyMapping.Category
	 //?} else {
	/*String
	*///?}
	registerKeyMappingCategory(Identifier location) {
		//? if >1.21.8 {
		return KeyMapping.Category.register(location); // The category translation key used to categorize in the Controls screen
		 //?} else {
		/*return location.toLanguageKey("key.category");
		*///?}
	}
}
