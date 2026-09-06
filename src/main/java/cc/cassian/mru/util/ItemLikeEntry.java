package cc.cassian.mru.util;

import net.minecraft.core.registries.Registries;
//~ if >=26.2 'cc.cassian.mru.util'->'net.minecraft.references'
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

/**
 * A pair of a block or item and its ID. Primary benefit over a direct value is for 26.2+ data generation, which requires the ID to be stored separately from the block.
 */
public record ItemLikeEntry<T extends ItemLike>(Identifier mru$id, T value) implements ItemLike, Identifiable, Supplier<T> {

	@Override
	public Item asItem() {
		return value.asItem();
	}

	public T get() {
		return value();
	}

	public Identifier identifier() {
		return mru$id;
	}

	public Identifier mru$identifier() {
		return mru$id;
	}

	public Identifier id() {
		return mru$identifier();
	}

	public String getNamespace() {
		return mru$identifier().getNamespace();
	}

	public String getPath() {
		return mru$identifier().getPath();
	}

	public boolean isVanilla() {
		return mru$id().getNamespace().equals("minecraft");
	}

	public boolean is(ItemStack heldItem) {
		return heldItem.is(this.value.asItem());
	}

	public ResourceKey<Block> blockKey() {
		return ResourceKey.create(Registries.BLOCK, this.mru$id());
	}

	public ResourceKey<Item> itemKey() {
		return ResourceKey.create(Registries.ITEM, this.mru$id());
	}

	public BlockItemId blockItemId() {
		return new BlockItemId(blockKey(), itemKey());
	}

	public BlockState defaultBlockState() {
		if (value() instanceof Block block) {
			return block.defaultBlockState();
		} else {
			throw new IllegalStateException("Cannot call defaultBlockState on %s, as it is not a Block.".formatted(mru$id));
		}
	}

	public ItemStack getDefaultInstance() {
		return value().asItem().getDefaultInstance();
	}

	@Override
	public String toString() {
		return mru$identifier().toString();
	}
}