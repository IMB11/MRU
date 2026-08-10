package cc.cassian.mru.fabric;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
//? if >26.2 {
/*import net.minecraft.world.level.storage.loot.providers.number.ResolvableNumber;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.Compostable;
import java.util.Collections;
*///?}

public class VersionedUtil {
	public static void registerCompostable(ItemLike itemLike, float f) {
		//? if >26.2 {
		/*DefaultItemComponentEvents.MODIFY.register(modifyContext -> {
			modifyContext.modify(Collections.singleton(itemLike.asItem()), (builder, item) -> {
				builder.set(DataComponents.COMPOSTABLE, new Compostable(new ResolvableNumber.Constant(f)));
			});
		});
		*///?} else {
		//~ if >26 'CompostingChanceRegistry'->'CompostableRegistry'
		net.fabricmc.fabric.api.registry.CompostableRegistry.INSTANCE.add(itemLike, f);
		//?}
	}

	public static void registerStrippable(Block input, Block output) {
		//~ if >26.2 'StrippableBlockRegistry.register'->'BlockTransformerRegistry.registerStripping'
		net.fabricmc.fabric.api.registry.StrippableBlockRegistry.register(input, output);
	}

	public static void registerWaxable(Block input, Block output) {
		//~ if >26 'registerWaxableBlockPair('->'registerWaxable('
		net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry.registerWaxable(input, output);
	}

	public static void registerOxidizable(Block input, Block output) {
		//~ if >26 'registerOxidizableBlockPair('->'registerNextStage('
		net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry.registerNextStage(input, output);
	}
}