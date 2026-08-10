package cc.cassian.mru.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.minecraft.client.KeyMapping;
//? if >26
import net.minecraft.world.clock.WorldClocks;

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
	
	public static float getOverworldTime() {
		var level = Minecraft.getInstance().level;
		//? if >26.2 {
		/*return level.clockManager().getInstance(level.registryAccess().getOrThrow(WorldClocks.OVERWORLD)).totalTicks();
		 *///?} else if >26 {
		return level.clockManager().getTotalTicks(level.registryAccess().getOrThrow(WorldClocks.OVERWORLD));
		//?} else
		//return level.getDayTime();
	}
}
