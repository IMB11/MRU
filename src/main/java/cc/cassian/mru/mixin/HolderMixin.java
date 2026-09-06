package cc.cassian.mru.mixin;

import cc.cassian.mru.util.Identifiable;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin({Holder.Reference.class, Holder.Direct.class})
public abstract class HolderMixin implements Identifiable {

	@Shadow
	public abstract Optional<ResourceKey<?>> unwrapKey();

	@Override
	public Identifier mru$identifier() {
		//~ if >26 'location'->'identifier'
		return unwrapKey().orElseThrow().identifier();
	}
}
