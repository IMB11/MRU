//? fabric {
//~ if <26 'FabricTagsProvider'->'FabricTagProvider' {
//~ if <26 '.ItemTagsProvider'->'.ItemTagProvider' {
//~ if <26 'BlockTagsProvider'->'BlockTagProvider' {
//~ if <26 'FabricPackOutput'->'FabricDataOutput' {
package cc.cassian.mru.fabric.datagen;

//~ if >=26.2 'cc.cassian.mru.util'->'net.minecraft.tags'
import net.minecraft.tags.BlockItemTagId;
import cc.cassian.mru.util.CommonUtils;
import cc.cassian.mru.util.ItemLikeEntry;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
//? if >=26.2 {
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
//?} else if >26 {
/*import net.minecraft.data.tags.TagAppender;
*///?}
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public abstract class MultiversionedItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {
	public MultiversionedItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture, BlockTagsProvider blockTagsProvider) {
		super(output, registryLookupFuture, blockTagsProvider);
	}

	public MultiversionedItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
		super(output, registryLookupFuture);
	}

	public class MultiversionedItemTagBuilder {
		//? if >1.21.2 && <26.2 {
		/*private TagAppender<Item, Item> valueLookupBuilder;
		*///?}
		//? if <1.21.2 {
		/*private FabricTagsProvider<Item>.FabricTagBuilder valueLookupBuilder;
		 *///?}

		private TagBuilder rawBuilder;

		public MultiversionedItemTagBuilder(TagKey<Item> tag) {
			//? if >1.21.2 && <26.2 {
			/*this.valueLookupBuilder = valueLookupBuilder(tag);
			*///?}
			//? if <1.21.2 {
			/*this.valueLookupBuilder = getOrCreateTagBuilder(tag);
			 *///?}

			this.rawBuilder = getOrCreateRawBuilder(tag);
		}

		public MultiversionedItemTagBuilder add(Item item) {
			//? if >=26.2 {
			rawBuilder = rawBuilder.addElement(BuiltInRegistries.ITEM.getKey(item));
			//?} else {
			/*valueLookupBuilder = valueLookupBuilder.add(item);
			*///?}
			return this;
		}

		public MultiversionedItemTagBuilder add(Supplier<Item> item) {
			return add(item.get());
		}

		public MultiversionedItemTagBuilder add(Holder<Item> item) {
			return add(item.value());
		}

		public MultiversionedItemTagBuilder add(ItemLikeEntry<?> item) {
			rawBuilder = rawBuilder.addElement(item.id());
			return this;
		}

		public MultiversionedItemTagBuilder addOptionalTag(BlockItemTagId itemTagKey) {
			return addOptionalTag(itemTagKey.item());
		}

		public MultiversionedItemTagBuilder addOptionalTag(TagKey<Item> itemTagKey) {
			rawBuilder = rawBuilder.addOptionalTag(itemTagKey.location());
			return this;
		}

		public MultiversionedItemTagBuilder add(Item... items) {
			//? if >=26.2 {
			for (Item item : items) {
				rawBuilder = rawBuilder.addElement(BuiltInRegistries.ITEM.getKey(item));
			}
			//?} else {
			/*valueLookupBuilder = valueLookupBuilder.add(items);
			 *///?}
			return this;
		}

		public MultiversionedItemTagBuilder addOptional(Identifier item) {
			rawBuilder = rawBuilder.addOptionalElement(item);
			return this;
		}

		public MultiversionedItemTagBuilder add(Identifier item) {
			rawBuilder = rawBuilder.addElement(item);
			return this;
		}
	}

	private MultiversionedItemTagBuilder tagBuilder(TagKey<Item> tag) {
		return new MultiversionedItemTagBuilder(tag);
	}

	public static TagKey<Item> conventionTag(String id) {
		return CommonUtils.itemTag("c", id);
	}
}
//~}
//~}
//~}
//~}
//?}