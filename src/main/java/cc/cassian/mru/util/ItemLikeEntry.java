package cc.cassian.mru.util;

import net.minecraft.core.registries.Registries;
//? if >=26.2 {
import net.minecraft.references.BlockItemId;
//?}
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

/**
 * A pair of a block or item and its ID. Primary benefit over a direct value is for 26.2+ data generation, which requires the ID to be stored separately from the block.
 */
public record ItemLikeEntry<T extends ItemLike>(Identifier id, T value) implements ItemLike {

	@Override
	public Item asItem() {
		return value.asItem();
	}

	public T get() {
		return this.value;
	}

	@Override
	public T value() {
		return get();
	}

	public boolean is(ItemStack heldItem) {
		return heldItem.is(this.value.asItem());
	}

	public String getPath() {
		return id().getPath();
	}

	public boolean isVanilla() {
		return id().getNamespace().equals("minecraft");
	}

	public ResourceKey<Block> blockKey() {
		return ResourceKey.create(Registries.BLOCK, this.id());
	}

	public ResourceKey<Item> itemKey() {
		return ResourceKey.create(Registries.ITEM, this.id());
	}

	//? if >=26.2 {
	public BlockItemId blockItemId() {
		return new BlockItemId(blockKey(), itemKey());
	}
	//?}

	public BlockState defaultBlockState() {
		if (value() instanceof Block block) {
			return block.defaultBlockState();
		} else {
			throw new IllegalStateException("Cannot call defaultBlockState on %s, as it is not a Block.".formatted(id));
		}
	}

	public ItemStack getDefaultInstance() {
		return value().asItem().getDefaultInstance();
	}

	@Override
	public String toString() {
		return id.toString();
	}
}