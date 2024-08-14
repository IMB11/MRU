package dev.imb11.mru.mixin;

import dev.imb11.mru.packing.Unpacker;
import net.minecraft.resource.LifecycledResourceManagerImpl;
import net.minecraft.resource.ResourcePack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.ArrayList;
import java.util.List;

@Mixin(value = LifecycledResourceManagerImpl.class, priority = 5000)
public class ReloadableResourceManagerImplMixin {
	@ModifyVariable(method = "<init>", at = @At("HEAD"), argsOnly = true)
	private static @NotNull List<ResourcePack> mru$modifyResourcePackList(List<ResourcePack> packs) {
		return new ArrayList<>(packs) {{
			// Add all registered pack to the end of the list.
			addAll(Unpacker.getPacks());
		}};
	}
}