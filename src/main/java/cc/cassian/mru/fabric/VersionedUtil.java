package cc.cassian.mru.fabric;

import net.minecraft.world.level.ItemLike;

public class VersionedUtil {
	public static void registerCompostable(ItemLike itemLike, float f) {
		//? if >26 {
		net.fabricmc.fabric.api.registry.CompostableRegistry
		 //?} else {
		/*net.fabricmc.fabric.api.registry.CompostingChanceRegistry
				*///?}
				.INSTANCE.add(itemLike, f);
	}
}