package cc.cassian.mru.mixin;

import cc.cassian.mru.util.Identifiable;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TagKey.class)
public class TagKeyMixin implements Identifiable {

	@Shadow
	@Final
	private Identifier location;

	@Override
	public Identifier mru$identifier() {
		return location;
	}
}
