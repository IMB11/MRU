package dev.imb11.mru.mixin;

import dev.imb11.mru.packing.Unpacker;
import dev.imb11.mru.packing.resource.UnpackedResourcePack;
import net.minecraft.resource.LifecycledResourceManagerImpl;
import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourceType;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.ArrayList;
import java.util.List;

@Mixin(value = LifecycledResourceManagerImpl.class, priority = 5000)
public class ReloadableResourceManagerImplMixin {
	@ModifyVariable(method = "<init>", at = @At("HEAD"), argsOnly = true)
	private static @NotNull List<ResourcePack> mru$modifyResourcePackList(List<ResourcePack> packs, ResourceType type) {
		if (type != ResourceType.CLIENT_RESOURCES || packs.isEmpty()) {
			return packs;
		}

		var unpackerPacks = Unpacker.getPacks();
		unpackerPacks.forEach(UnpackedResourcePack::walkNamespaces);

		var list = new ArrayList<>(packs);
		list.addAll(0, unpackerPacks);

		return list;
	}
}