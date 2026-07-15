package cc.cassian.mru.client.util;

import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
//? if >1.21.2 {
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
 //?} else {
/*import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
*///?}
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class ClientVersionedUtil {
	public static void registerKeyMapping(KeyMapping keyMapping) {
		//? if <26 {
		/*KeyBindingHelper.registerKeyBinding(keyMapping);
		*///?} else {
        KeyMappingHelper.registerKeyMapping(keyMapping);
        //?}
	}

	public static void registerOverlay(Identifier id, Overlay overlay) {
		//? if >1.21.5 {
		HudElementRegistry.addFirst(id, overlay::render);
		 //?} else {
		/*HudRenderCallback.EVENT.register(overlay::render);
		*///?}
	}
}