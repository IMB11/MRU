package cc.cassian.mru.compat;

//? if fabric && <26 {
/*import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketsApi;
*///?}
import net.minecraft.world.item.ItemStack;
//? if >26 {
import eu.pb4.trinkets.api.TrinketSlotAccess;
import eu.pb4.trinkets.api.TrinketsApi;
//?}
//? if <26.2 {
/*import net.minecraft.util.Tuple;
*///?}
import net.minecraft.world.entity.player.Player;

import java.util.function.Consumer;
import java.util.function.Function;

public class TrinketsCompat {
    public static void checkForImportantAccessories(Player player, Consumer<ItemStack> isImportantItemOrContainer) {
        //? if >26 {
        TrinketsApi.getAttachment(player).allEquipped(false).stream().map(TrinketSlotAccess::get).forEach(isImportantItemOrContainer);
        //?} else if fabric {
        /*var capability = TrinketsApi.getTrinketComponent(player);
        if (capability.isPresent()) {
            for (Tuple<SlotReference, ItemStack> tuple : capability.get().getAllEquipped()) {
                isImportantItemOrContainer.accept(tuple.getB());
            }
        }
        *///?}
    }
}
