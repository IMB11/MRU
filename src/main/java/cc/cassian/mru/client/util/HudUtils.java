package cc.cassian.mru.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
//? if >1.21.6 {
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.util.ARGB;
//?}
import cc.cassian.mru.util.CommonUtils;

import java.util.function.Function;

public class HudUtils {

	/**
	 * Draws text to the screen. Will always be opaque, regardless of the alpha.
	 */
	public static void drawString(GuiGraphicsExtractor guiGraphics, Font font, Component text, int x, int y, Integer color) {
		//? if >1.21.6
		color = ARGB.opaque(color);
		guiGraphics.text(font, text, x, y, color);
	}

	/**
	 * Draws text to the screen. Will always be opaque, regardless of the alpha.
	 */
	public static void drawString(GuiGraphicsExtractor guiGraphics, Font font, String text, int x, int y, Integer color) {
		//? if >1.21.6
		color = ARGB.opaque(color);
		guiGraphics.text(font, text, x, y, color);
	}

	/**
	 * Draws a sprite to the screen. Will always be opaque, regardless of the alpha.
	 */
	public static void blit(GuiGraphicsExtractor guiGraphics, Identifier texture, int x, int y, int uOffset, int vOffset, int uWidth, int vHeight, int textureWidth, int textureHeight) {
		//? if >1.21.5 {
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, texture,
		 //?} else {
		/*guiGraphics.blit(texture,
				*///?}
				x, y,
				//? if <1.21.2
				//0, //z
				uOffset,
				vOffset, uWidth, vHeight,
				textureWidth, textureHeight);
	}

	/**
	 * Draws a tinted sprite to the screen. Will always be opaque, regardless of the alpha.
	 */
	public static void blit(GuiGraphicsExtractor guiGraphics, Identifier id, int x, int y, float u, float v, int width, int height, int uWidth, int vHeight, int textureWidth, int textureHeight, int color) {
		guiGraphics.blit(
				//? if >1.21.2 {
				RenderPipelines.GUI_TEXTURED,id, x, y, u, v, width, height, uWidth, vHeight, textureWidth, textureHeight, color
				//?} else {
				/*id, x, y, 0, u, v, uWidth, vHeight, textureWidth, textureHeight
				 *///?}
		);
	}

	/**
	 * Draws a sprite to the screen. Will always be opaque, regardless of the alpha.
	 */
	public static void blitSprite(GuiGraphicsExtractor guiGraphics, Identifier background, int x, int y, int width, int height) {
		//? if >1.20.2 {
		guiGraphics.blitSprite(
				//? if >1.21.2
				RenderPipelines.GUI_TEXTURED,
				background, x, y, width, height);
		//?} else
		//blitSprite(guiGraphics, CommonUtils.id(background.getNamespace(), "textures/gui/sprites/%s.png".formatted(background.getPath())), x, y, width);
	}

	/**
	 * Draws a sprite to the screen. Will always be opaque, regardless of the alpha.
	 */
	public static void blitSprite(GuiGraphicsExtractor guiGraphics, Identifier texture, int x, int y) {
		blitSprite(guiGraphics, texture, x, y, 16);
	}

	/**
	 * Draws a sprite to the screen. Will always be opaque, regardless of the alpha.
	 */
	public static void blitSprite(GuiGraphicsExtractor guiGraphics, Identifier texture, int x, int y, int size) {
		blit(guiGraphics, texture, x, y, 0, 0, size, size, size, size);
	}

	/**
	 * Whether to hide a given HUD overlay.
	 * @param mc - The Minecraft singleton.
	 * @param enabled - Whether the overlay is enabled.
	 * @param hideFromDebug - Whether the overlay should be hidden if the F3 scren is open.
	 */
	public static boolean shouldCancelRender(Minecraft mc, boolean enabled, boolean hideFromDebug) {
		//? if >26.1 {
		if (mc.gui.hud.isHidden())
		 //?} else {
		/*if (mc.options.hideGui)
		*///?}
			return true;
		if (!enabled) return true;
		if (hideFromDebug) {
			return
			//? if >1.21.10 {
			mc.debugEntries.isOverlayVisible();
			 //?} else if >1.21 {
			/*mc.getDebugOverlay().showDebugScreen();
			*///?} else {
			/*mc.options.renderDebug;
			 *///?}
		}
		return false;
	}


	/**
	 * Creates a new screen with the current parent.
	 */
	public static void setScreen(Function<Screen, Screen> screenConstructor) {
		Minecraft mc = Minecraft.getInstance();
		//? if >26.1 {
		Screen parent = mc.gui.screen();
		 //?} else {
		/*Screen parent = mc.screen;
		*///?}
		HudUtils.setScreen(screenConstructor.apply(parent));
	}

	/**
	 * Sets the current screen to a new screen.
	 */
	public static void setScreen(Screen screen) {
		Minecraft mc = Minecraft.getInstance();
		//? if >26.1 {
		mc.gui.setScreen(screen);
		 //?} else {
		/*mc.setScreen(screen);
		*///?}
	}

	/**
	 * Displays a clientside message in the chat.
	 */
	public static void sendSystemMessage(Player player, MutableComponent mutableComponent) {
		//? if >26 {
		player.sendSystemMessage(mutableComponent);
		 //?} else {
		/*player.displayClientMessage(mutableComponent, false);
		*///?}
	}

	/**
	 * Displays a clientside message in the action bar.
	 */
	public static void sendOverlayMessage(Player player, MutableComponent mutableComponent) {
		//? if >26 {
		player.sendOverlayMessage(mutableComponent);
		 //?} else {
		/*player.displayClientMessage(mutableComponent, true);
		*///?}
	}

	public static void blit(GuiGraphicsExtractor graphics, Identifier headerSeparator, int x, int y, float v, float v1, int width, int vHeight, int textureWidth, int textureHeight) {
		//? if >1.21.2
		graphics.blit(RenderPipelines.GUI_TEXTURED, headerSeparator, x, y, v, v1, width, vHeight, textureWidth, textureHeight);
		//? if <1.21.2
		//graphics.blit(headerSeparator, x, y, v, v1, width, vHeight, textureWidth, textureHeight);
	}

	public static void blitSprite(GuiGraphicsExtractor guiGraphics, Identifier craftingShapeless, int x, int y, int u, int v, int width, int height, int textureWidth, int textureHeight) {
		//? if >1.21.2 {
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, craftingShapeless, x, y, u, v, width, height, textureWidth, textureHeight);
		 //?} else {
		/*guiGraphics.blit(craftingShapeless, x, y, u, v, width, height, textureWidth, textureHeight);
		*///?}
	}
}
