package cc.cassian.mru.compat;


//? if neoforge {
/*import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackWrapper;
import net.p3pp3rf1y.sophisticatedcore.inventory.InventoryHandler;
import net.p3pp3rf1y.sophisticatedcore.inventory.ItemStackKey;
*///?} else if fabric {
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackItem;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackWrapper;
//?} else if forge {
/*import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackItem;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackWrapper;
*///?}
import net.p3pp3rf1y.sophisticatedcore.inventory.InventoryHandler;

import net.minecraft.world.item.ItemStack;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

public class SophisticatedBackpacksCompat {

    public static void checkBackpackContents(ItemStack stack, Consumer<ItemStack> function) {
        //? if >1.21 {
        BackpackWrapper.fromExistingData(stack).ifPresent(backpackWrapper -> {
            var inventory = backpackWrapper.getInventoryHandler();
            //? fabric || (neoforge && >1.21.9) {
            for (var slot : getSlots(inventory)) {
                function.accept(getStack(slot));
            }
            //?} else {
            /*for (int i = 0; i < inventory.getSlots(); i++) {
                function.accept(getStack(inventory, i));
            }
            *///?}
        });
        //?} else if =1.20.1 {
        /*if (stack.getItem() instanceof BackpackItem) {
            var backpackWrapper = new BackpackWrapper(stack);
            var inventory = backpackWrapper.getInventoryHandler();
            for (int i = 0; i < getSlots(inventory); i++) {
                function.accept(inventory.getStackInSlot(i));
            }
        }
        *///?}
    }

    //? fabric && >1.21 && <26 {
    /*private static ItemStack getStack(SingleSlotStorage<ItemVariant> slot) {
        return slot.getResource().toStack();
    }
    private static List<SingleSlotStorage<ItemVariant>> getSlots(InventoryHandler inventory) {
        return inventory.getSlots();
    }
    *///?} else if neoforge && >1.21.9 {
    /*private static ItemStack getStack(ItemStackKey slot) {
        return slot.stack();
    }
    private static Set<ItemStackKey> getSlots(InventoryHandler inventory) {
        return inventory.getTrackedStacks();
    }
    *///?} else if neoforge {
    /*private static int getSlots(InventoryHandler inventory) {
        return inventory.getSlots();
    }
    private static ItemStack getStack(InventoryHandler inventory, int i) {
        return inventory.getStackInSlot(i);
    }
    *///?} else if fabric && =1.20.1 {
    /*private static int getSlots(InventoryHandler inventory) {
        return inventory.getBaseSlotLimit();
    }
    *///?} else if forge && =1.20.1 {
    /*private static int getSlots(InventoryHandler inventory) {
        return inventory.getSlots();
    }
    *///?} else {
    private static ItemStack getStack(Object slot) {
        return ItemStack.EMPTY;
    }
    private static List<Object> getSlots(InventoryHandler inventory) {
        return List.of();
    }
    //?}
}

