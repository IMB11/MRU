package cc.cassian.mru.util;

//? fabric {
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
//?}
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
//? if >1.21 {
import net.minecraft.core.component.DataComponentType;
//?}
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
//~ if >=26.2 'cc.cassian.mru.util'->'net.minecraft.references'
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public interface CommonRegistry {

	String namespace();

	default Identifier id(String name) {
		return CommonUtils.id(namespace(), name);
	}

	default BlockItemId blockItemId(String name) {
		return new BlockItemId(CommonUtils.blockKey(namespace(), name), CommonUtils.itemKey(namespace(), name));
	}

	//? fabric && >1.21.2 {
	default <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(String name, FabricBlockEntityTypeBuilder.Factory<T> value, Block... block) {
		return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id(name), FabricBlockEntityTypeBuilder.create(value, block).build());
	}
	//?} else if neoforge && >1.21.2 {
	/*default <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(String name, BlockEntityType.BlockEntitySupplier<T> value, Block... block) {
		return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id(name), new BlockEntityType<>(value, block));
	}
	*///?} else {
	/*default <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(String name, BlockEntityType.BlockEntitySupplier<T> value, Block... block) {
		return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, CommonUtils.id(namespace(), name), BlockEntityType.Builder.of(value, block).build(null));
	}
	*///?}
	
	default <R, T extends R> T register(String name, T object, Registry<R> reg) {
		Registry.register(reg, id(name), object);
		return object;
	}

	//? if >1.21 {
	default <T> DataComponentType<T> registerComponentType(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
		return register(name, builderOperator.apply(DataComponentType.builder()).build(), BuiltInRegistries.DATA_COMPONENT_TYPE);
	}
	//?}

	default <B extends Item> B registerItem(String name, B item) {
		return register(name, item, BuiltInRegistries.ITEM);
	}

	default <B extends Block> B registerBlock(String name, B block) {
		return register(name, block, BuiltInRegistries.BLOCK);
	}

	/**
	 * Registers a block and its block item.
	 */
	default ItemLikeEntry<Block> registerBlockItemEntry(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
		// Create a resource key for the block
		Identifier id = id(name);
		ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, id);
		// Create the block instance
		Block block = blockFactory.apply(properties
				//? if >1.21.2
				.setId(blockKey)
		);

		// Items need to be registered with a different type of resource key, but the ID can be the same.
		ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, id);

		BlockItem blockItem = new BlockItem(block, new Item.Properties()
				//? if >1.21.2
				.setId(itemKey).useBlockDescriptionPrefix()
		);
		Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);

		return new ItemLikeEntry<>(id, Registry.register(BuiltInRegistries.BLOCK, id, block));
	}

	default <B extends Block> ItemLikeEntry<B> registerBlockEntry(String name, B block) {
		return new ItemLikeEntry<>(id(name), register(name, block, BuiltInRegistries.BLOCK));
	}

	default <B extends Block> ItemLikeEntry<B> registerBlockEntry(String name, Function<BlockBehaviour.Properties, B> blockFactory, BlockBehaviour.Properties properties) {
		B block = blockFactory.apply(properties
				//? if >1.21.2
				.setId(ResourceKey.create(Registries.BLOCK, id(name)))
				);
		return new ItemLikeEntry<>(id(name), register(name, block, BuiltInRegistries.BLOCK));
	}

	default <B extends Item> ItemLikeEntry<B> registerItemEntry(String name, Function<Item.Properties, B> itemFactory, Item.Properties properties) {
		B item = itemFactory.apply(properties
				//? if >1.21.2
				.setId(ResourceKey.create(Registries.ITEM, id(name)))
				);
		return new ItemLikeEntry<>(id(name), register(name, item, BuiltInRegistries.ITEM));
	}

	default <B extends Item> ItemLikeEntry<B> registerItemEntry(String name, B item) {
		return new ItemLikeEntry<>(id(name), register(name, item, BuiltInRegistries.ITEM));
	}

	default <T extends EntityType<?>> T registerEntity(String name, T entity) {
		return register(name, entity, BuiltInRegistries.ENTITY_TYPE);
	}

	default SoundEvent registerSoundEvent(String name, SoundEvent soundEvent) {
		return register(name, soundEvent, BuiltInRegistries.SOUND_EVENT);
	}

	default SoundEvent registerSoundEvent(String name) {
		return registerSoundEvent(name, SoundEvent.createVariableRangeEvent(id(name)));
	}

	default Holder<MobEffect> registerMobEffect(String name, MobEffect effect) {
		return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, id(name), effect);
	}

	default <B extends RecipeSerializer<?>> B registerRecipeSerializer(String name, B supplier) {
		return register(name, supplier, BuiltInRegistries.RECIPE_SERIALIZER);
	}

	default <T extends Recipe<?>> RecipeType<T> registerRecipeType(final String name) {
		return Registry.register(BuiltInRegistries.RECIPE_TYPE, id(name), new RecipeType<T>() {
			public String toString() {
				return namespace() + ":" + name;
			}
		});
	}
}