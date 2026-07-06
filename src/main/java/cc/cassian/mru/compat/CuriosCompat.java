package cc.cassian.mru.compat;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;
import java.util.function.Function;
//? if neoforge || (fabric && >1.20.1)
import net.neoforged.neoforge.items.IItemHandlerModifiable;
//? if forge
//import net.minecraftforge.items.IItemHandlerModifiable;
//? if >1.20.1 || forge {
import top.theillusivec4.curios.api.CuriosApi;
//?}

public class CuriosCompat {
    public static void checkForImportantAccessories(Player player, Consumer<ItemStack> isImportantItemOrContainer) {
        //? if >1.20.1 || forge {
        var capability = CuriosApi.getCuriosInventory(player);
        if (capability.isPresent()) {
            IItemHandlerModifiable allEquipped = capability
                    //? if <1.21
                    //.resolve()
                    .get().getEquippedCurios();
            for (int i = 0; i < allEquipped.getSlots(); i++) {
                isImportantItemOrContainer.accept(allEquipped.getStackInSlot(i));
            }
        }
        //?}
    }
}
