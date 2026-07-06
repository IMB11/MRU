package cc.cassian.mru.client.util;

//? if >1.21
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public interface Overlay {
	void render(GuiGraphicsExtractor guiGraphics
				//? if >1.21 {
				, DeltaTracker deltaTracker
				//?} else {
				/*, float deltaTracker
				 *///?}
	);
}
