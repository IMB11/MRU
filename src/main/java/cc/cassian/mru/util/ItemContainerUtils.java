package cc.cassian.mru.util;

import cc.cassian.mru.compat.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
//? if >1.21 {
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.item.component.ItemContainerContents;
//?} else {
/*import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.CompassItem;
*///?}
//? if >1.21.6 {

import net.minecraft.world.entity.EquipmentSlot;
//?}
import net.minecraft.world.entity.player.Player;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class ItemContainerUtils {

	/**
	 * Checks the player for important items. This checks their inventory, their Curios, Trinkets, Accessories, and Ohmega slots, and checks the contents of their bundles, shulker boxes, generic backpacks that use either of the vanilla components, Traveler's Backpack, or Sophisticated Backpack.
	 * @param player - The player to check
	 * @param isImportantItem - The function to apply on the ItemStack.
	 * @param checkContainers - Whether to check containers
	 * @param checkContainersForContainers - Whether to check only containers, or also containers that contain containers.
	 */
	public static void checkPlayerForImportantItems(Player player, Consumer<ItemStack> isImportantItem, boolean checkContainers, boolean checkContainersForContainers) {
		Consumer<ItemStack> isImportantItemOrContainer;
		if (checkContainers) {
			isImportantItemOrContainer = isImportantItem.andThen(p->isImportantItemOrContainer(p, isImportantItem, checkContainersForContainers));
		} else {
			isImportantItemOrContainer = isImportantItem;
		}

		//? if <1.21.5 {
		/*player.getArmorSlots().forEach((isImportantItemOrContainer));
		*///?} else {
                for (EquipmentSlot value : EquipmentSlot.values()) {
                    isImportantItemOrContainer.accept(player.getItemBySlot(value));
                }
                //?}
		if (ModCompat.ACCESSORIES) {
			AccessoriesCompat.checkForImportantAccessories(player, isImportantItemOrContainer);
		}
		if (ModCompat.CURIOS) {
			CuriosCompat.checkForImportantAccessories(player, isImportantItemOrContainer);
		}
		//? if fabric && <26 {
		/*if (ModCompat.TRINKETS) {
			TrinketsCompat.checkForImportantAccessories(player, isImportantItemOrContainer);
		}
		*///?}
		//? if >26 {
		if (ModCompat.TRINKETS_UPDATED) {
			TrinketsCompat.checkForImportantAccessories(player, isImportantItemOrContainer);
		}
		//?}
		if (ModCompat.OHMEGA) {
			OhmegaCompat.checkForImportantAccessories(player, isImportantItemOrContainer);
		}
		if (ModCompat.TRAVELERS_BACKPACK)
			TravelersBackpackCompat.checkForImportantAccessories(player, isImportantItemOrContainer);
		checkInventoryForStack(player.getInventory(), isImportantItemOrContainer, checkContainersForContainers);
	}


	/**
	 * Checks a container for important items. This checks their inventory and checks the contents of their bundles, shulker boxes, generic backpacks that use either of the vanilla components, Traveler's Backpack, or Sophisticated Backpack.
	 * @param container - The container to check
	 * @param isImportantItemOrContainer - The function to apply on the ItemStack.
	 * @param searchContainersForContainers - Whether to check only containers, or also containers that contain containers.
	 */
	public static void findImportantContainerContents(ItemStack container, Consumer<ItemStack> isImportantItemOrContainer, boolean searchContainersForContainers) {
		List<ItemStack> list = ItemContainerUtils.getContainerContents(container).toList();
		for (ItemStack itemStack : list) {
			if (searchContainersForContainers) {
				isImportantItemOrContainer(itemStack, isImportantItemOrContainer, true);
			} else {
				isImportantItem(itemStack, isImportantItemOrContainer);
			}
		}
	}

	/**
	 * @param inventory - The inventory to check.
	 * @param item - The item to check for.
	 * @param value - Whether the check has already succeeded.
	 */
	public static boolean checkInventoryForItem(Inventory inventory, Item item, boolean value) {
		if (value) return true;
		else return checkInventoryForStack(inventory, item) != ItemStack.EMPTY;
	}

	/**
	 * Checks an inventory for important items. This checks their inventory and checks the contents of their bundles, shulker boxes, generic backpacks that use either of the vanilla components, Traveler's Backpack, or Sophisticated Backpack.
	 * @param inventory - The inventory to check
	 * @param isImportantItemOrContainer - The function to apply on the ItemStack.
	 * @param searchContainersForContainers - Whether to check only containers, or also containers that contain containers.
	 */
	public static void checkInventoryForStack(Inventory inventory, Consumer<ItemStack> isImportantItemOrContainer, boolean searchContainersForContainers) {
		for (ItemStack stack : items(inventory)) {
			isImportantItem(stack, isImportantItemOrContainer);
			if (isContainer(stack)) {
				findImportantContainerContents(stack, isImportantItemOrContainer, searchContainersForContainers);
			}
		}
	}

	private static void isImportantItem(ItemStack stack, Consumer<ItemStack> isImportantItemOrContainer) {
		isImportantItemOrContainer.accept(stack);
	}

	public static void isImportantItemOrContainer(ItemStack stack, Consumer<ItemStack> isImportantItemOrContainer, boolean searchContainersForContainers) {
		isImportantItem(stack, isImportantItemOrContainer);
		if (isContainer(stack)) {
			findImportantContainerContents(stack, isImportantItemOrContainer, searchContainersForContainers);
		}
		if (ModCompat.SOPHISTICATED_BACKPACKS) {
			SophisticatedBackpacksCompat.checkBackpackContents(stack, isImportantItemOrContainer);
		}
	}

	public static ItemStack checkInventoryForStack(Inventory inventory, Item item) {
		for (ItemStack stack : items(inventory)) {
			if (stack.is(item)) return stack;
			else if (item != null && stack.is(item))
				return stack;
			else if (isContainer(stack)) {
				List<ItemStack> contents = getContainerContents(stack).toList();
				for (ItemStack content : contents) {
					if (item != null && content.is(item))
						return content;
				}
			}
		}
		return ItemStack.EMPTY;
	}




	/**
	 * Checks if an item is a bundle, shulker box, generic backpack that uses a vanilla component, Traveler's Backpack, or Sophisticated Backpack.
	 */
	public static boolean isContainer(ItemStack stack) {
		if (stack.isEmpty()) return false;
		//? if >1.20.5 {
		var components = stack.getComponents();
		if (components.has(DataComponents.BUNDLE_CONTENTS)) {
			return true;
		}
		else if (components.has(DataComponents.CONTAINER)) {
			return true;
		}
		//?} else {
        /*CompoundTag compoundtag = stack.getTag();
        if (compoundtag == null) {
            return false;
        } else {
            if (compoundtag.contains("Items")) {
                return true;
            }
            else if (compoundtag.contains("BlockEntityTag")) {
                if (compoundtag.getCompound("BlockEntityTag").contains("Items")) {
                    return true;
                }
            }
        }
        *///?}
		return true;
	}


	/**
	 * Checks the contents of a bundle, shulker box, generic backpack that uses a vanilla component, Traveler's Backpack, or Sophisticated Backpack.
	 */
	public static Stream<ItemStack> getContainerContents(ItemStack stack) {
		if (!isContainer(stack)) return Stream.empty();
		//? if >1.20.5 {
		var components = stack.getComponents();
		if (components.has(DataComponents.BUNDLE_CONTENTS)) {
			BundleContents bundleContents = components.get(DataComponents.BUNDLE_CONTENTS);
			if (bundleContents != null) {
				//~ if >26.2 'itemCopyStream'->'itemCopies'
				return bundleContents.itemCopyStream();
			}
		}
		else if (components.has(DataComponents.CONTAINER)) {
			ItemContainerContents containerContents = components.get(DataComponents.CONTAINER);
			if (containerContents != null) {
				//? >26.2 {
				/*return containerContents.itemCopies();
				*///?} else if >26 {
				return containerContents.allItemsCopyStream();
				 //?} else {
				/*return containerContents.stream();
				*///?}
			}
		}
		//?} else {
        /*CompoundTag compoundtag = stack.getTag();
        if (compoundtag == null) {
            return Stream.empty();
        } else {
            if (compoundtag.contains("Items")) {
                ListTag listtag = compoundtag.getList("Items", 10);
                return listtag.stream().map(CompoundTag.class::cast).map(ItemStack::of);
            }
            else if (compoundtag.contains("BlockEntityTag")) {
                var compound = compoundtag.getCompound("BlockEntityTag");
                ListTag listtag = compound.getList("Items", 10);
                return listtag.stream().map(CompoundTag.class::cast).map(ItemStack::of);
            }
        }
        *///?}
		return Stream.empty();
	}


	/**
	 * Multi-version safe version of {@code Inventory#getNonEquipmentItems}.
	 */
	public static List<ItemStack> items(Inventory inventory) {
		return
		//? if <1.21.5 {
		/*inventory.items;
		*///?} else {
		inventory.getNonEquipmentItems();
		 //?}
	}
}