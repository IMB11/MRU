package cc.cassian.mru.mixin;

import cc.cassian.mru.util.Identifiable;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Block.class)
public class BlockMixin implements Identifiable {
	@Shadow
	@Final
	private Holder.Reference<Item> builtInRegistryHolder;

	@Override
	public Identifier mru$identifier() {
		//~ if >26 'location'->'identifier'
		return builtInRegistryHolder.key().identifier();
	}
}
