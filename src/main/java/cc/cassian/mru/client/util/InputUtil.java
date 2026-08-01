package cc.cassian.mru.client.util;

import com.mojang.blaze3d.platform.InputConstants;
//? if >26 {
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.Minecraft;
//?} else {
/*import net.minecraft.client.gui.screens.Screen;
*///?}

@SuppressWarnings("unused")
public class InputUtil {
	//? if >26 {
	public static boolean isLeftClick(MouseButtonEvent mouseButtonEvent) {
		return mouseButtonEvent.button() == InputConstants.MOUSE_BUTTON_LEFT;
	}

	public static boolean isMiddleClick(MouseButtonEvent mouseButtonEvent) {
		return mouseButtonEvent.button() == InputConstants.MOUSE_BUTTON_MIDDLE;
	}

	public static boolean isRightClick(MouseButtonEvent mouseButtonEvent) {
		return mouseButtonEvent.button() == InputConstants.MOUSE_BUTTON_RIGHT;
	}

	public static boolean hasShiftDown() {
		return Minecraft.getInstance().hasShiftDown();
	}

	public static boolean hasControlDown() {
		return Minecraft.getInstance().hasControlDown();
	}

	public static boolean hasAltDown() {
		return Minecraft.getInstance().hasAltDown();
	}

	//?} else {
	/*public static boolean hasShiftDown() {
		return Screen.hasShiftDown();
	}

	public static boolean hasControlDown() {
		return Screen.hasControlDown();
	}

	public static boolean hasAltDown() {
		return Screen.hasAltDown();
	}
	*///?}
}
