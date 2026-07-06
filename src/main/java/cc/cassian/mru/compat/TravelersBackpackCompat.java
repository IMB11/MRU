package cc.cassian.mru.compat;

//? if neoforge && <26 {
/*import com.tiviacz.travelersbackpack.capability.AttachmentUtils;
*///?} else if forge {
/*import com.tiviacz.travelersbackpack.capability.CapabilityUtils;
*///?} else if fabric && <26 {
/*import com.tiviacz.travelersbackpack.component.ComponentUtils;
*///?} else if fabric {
import com.tiviacz.travelersbackpack.attachment.AttachmentUtils;
//?} else if neoforge && >26 {
/*import com.tiviacz.travelersbackpack.attachment.AttachmentUtils;
*///?}
//? if forge {
/*import net.minecraftforge.items.ItemStackHandler;
*///?} else {
import com.tiviacz.travelersbackpack.inventory.BackpackWrapper;
//?}
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;
import java.util.function.Function;

public class TravelersBackpackCompat {
    public static void checkForImportantAccessories(Player player, Consumer<ItemStack> isImportantItemOrContainer) {
        //? if (neoforge) || (fabric && >26) {
        if (AttachmentUtils.isWearingBackpack(player)) {
            BackpackWrapper backpackWrapper = AttachmentUtils.getBackpackWrapper(player);
        //?} else if forge {
        /*if (CapabilityUtils.isWearingBackpack(player)) {
            var backpackWrapper = CapabilityUtils.getBackpackWrapper(player);
        *///?} else if fabric {
        /*if (ComponentUtils.isWearingBackpack(player)) {
            var backpackWrapper = ComponentUtils.getBackpackWrapper(player);
        *///?}
        if (backpackWrapper != null) {
                var inventory = backpackWrapper.getStorage();
                //? if neoforge && >26 {
                /*for (int i = 0; i < inventory.size(); i++) {
                    isImportantItemOrContainer.accept(inventory.getResource(i).toStack());
                }
                *///?} else {
                for (int i = 0; i < inventory.getSlots(); i++) {
                    isImportantItemOrContainer.accept(inventory.getStackInSlot(i));
                }
                //?}
            }
        }
    }
}
