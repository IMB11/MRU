//? fabric {
//~ if <26 'FabricTagsProvider'->'FabricTagProvider' {
//~ if <26 '.BlockTagsProvider'->'.BlockTagProvider' {
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
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public abstract class MultiversionedBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {

	public MultiversionedBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
		super(output, registryLookupFuture);
	}

	public class MultiversionedBlockTagBuilder {
		//? if >1.21.2 && <26.2 {
		/*private TagAppender<Block, Block> valueLookupBuilder;
		 *///?}
		//? if <1.21.2 {
		/*private FabricTagsProvider<Block>.FabricTagBuilder valueLookupBuilder;
		*///?}

		private TagBuilder rawBuilder;

		public MultiversionedBlockTagBuilder(TagKey<Block> tag) {
			//? if >1.21.2 && <26.2 {
			/*this.valueLookupBuilder = valueLookupBuilder(tag);
			 *///?}
			//? if <1.21.2 {
			/*this.valueLookupBuilder = getOrCreateTagBuilder(tag);
			*///?}

			this.rawBuilder = getOrCreateRawBuilder(tag);
		}

		public MultiversionedBlockTagBuilder add(Block block) {
			//? if >=26.2 {
			rawBuilder = rawBuilder.addElement(BuiltInRegistries.BLOCK.getKey(block));
			 //?} else {
			/*valueLookupBuilder = valueLookupBuilder.add(block);
			*///?}
			return this;
		}

		public MultiversionedBlockTagBuilder add(Supplier<Block> block) {
			add(block.get());
			return this;
		}

		public MultiversionedBlockTagBuilder add(Holder<Block> block) {
			add(block.value());
			return this;
		}

		public MultiversionedBlockTagBuilder add(ItemLikeEntry<?> block) {
			rawBuilder = rawBuilder.addElement(block.id());
			return this;
		}

		public MultiversionedBlockTagBuilder addOptionalTag(TagKey<Block> blockTagKey) {
			rawBuilder = rawBuilder.addOptionalTag(blockTagKey.location());
			return this;
		}

		public MultiversionedBlockTagBuilder addOptionalTag(BlockItemTagId blockItemTagId) {
			return addOptionalTag(blockItemTagId.block());
		}

		public MultiversionedBlockTagBuilder add(Block... blocks) {
			//? if >=26.2 {
			for (Block block : blocks) {
				rawBuilder = rawBuilder.addElement(BuiltInRegistries.BLOCK.getKey(block));
			}
			//?} else {
			/*valueLookupBuilder = valueLookupBuilder.add(blocks);
			*///?}
			return this;
		}

		public MultiversionedBlockTagBuilder addOptional(Identifier item) {
			rawBuilder = rawBuilder.addOptionalElement(item);
			return this;
		}

		public MultiversionedBlockTagBuilder add(Identifier item) {
			rawBuilder = rawBuilder.addElement(item);
			return this;
		}
	}

	protected MultiversionedBlockTagBuilder tagBuilder(TagKey<Block> tag) {
		return new MultiversionedBlockTagBuilder(tag);
	}

	public static TagKey<Block> conventionTag(String id) {
		return CommonUtils.blockTag("c", id);
	}
}
//~}
//~}
//~}
//?}