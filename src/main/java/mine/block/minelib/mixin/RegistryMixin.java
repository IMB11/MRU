package mine.block.minelib.mixin;

import mine.block.minelib.registry.MinelibRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.SimpleRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SimpleRegistry.class)
public class RegistryMixin<T> {
    @Unique private boolean hasFrozenYet = false;

    @Inject(method = "freeze", at = @At("TAIL"), cancellable = false)
    public void freezeRegistryTail(CallbackInfoReturnable<Registry<T>> cir) {
        if(!hasFrozenYet) {
            MinelibRegistry.iterate();
            hasFrozenYet = true;
        }
    }
}
