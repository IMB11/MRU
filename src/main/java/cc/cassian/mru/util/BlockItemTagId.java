//? if <26.2 {
/*package cc.cassian.mru.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public record BlockItemTagId(TagKey<Block> block, TagKey<Item> item) {
	public static BlockItemTagId create(final Identifier blockId, final Identifier itemId) {
		return new BlockItemTagId(TagKey.create(Registries.BLOCK, blockId), TagKey.create(Registries.ITEM, itemId));
	}

	public static BlockItemTagId create(final Identifier id) {
		return create(id, id);
	}
}
*///?}