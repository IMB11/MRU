package cc.cassian.mru.mixin;

import cc.cassian.mru.util.Identifiable;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(ResourceKey.class)
public abstract class ResourceKeyMixin implements Identifiable {

	@Shadow
	@Final
	//~ if >26 'location'->'identifier'
	private Identifier identifier;

	@Override
	public Identifier mru$identifier() {
		//~ if >26 'location'->'identifier'
		return identifier;
	}
}
