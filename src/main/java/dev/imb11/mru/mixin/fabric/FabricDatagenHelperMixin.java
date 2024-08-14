package dev.imb11.mru.mixin.fabric;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.nio.file.Path;

@Pseudo
@Mixin(targets = "net.fabricmc.fabric.impl.datagen.FabricDataGenHelper", remap = false)
public abstract class FabricDatagenHelperMixin {
	/*? if fabric && >=1.20.6 {*/
	@Inject(method = "runInternal()V", at = @At("TAIL"), remap = false)
	private static void runInternal(CallbackInfo ci) {
		dev.imb11.mru.event.fabric.DatagenFinishedCallback.EVENT.invoker().onDatagenFinished(Path.of(System.getProperty("fabric-api.datagen.output-dir")));
	}
	/*?}*/
}