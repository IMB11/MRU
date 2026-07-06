package cc.cassian.mru.compat;

import com.swacky.ohmega.api.AccessoryHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;
import java.util.function.Function;

public class OhmegaCompat {
    public static void checkForImportantAccessories(Player player, Consumer<ItemStack> isImportantItemOrContainer) {
        var container = AccessoryHelper.getContainer(player);
        if (container != null) {
            for (ItemStack slotEntryReference : container.getStacks()) {
                if (slotEntryReference != null && AccessoryHelper.isActive(slotEntryReference)) {
                    isImportantItemOrContainer.accept(slotEntryReference);
                }
            }
        }
    }
}
