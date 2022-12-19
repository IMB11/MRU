package mine.block.mru.mixin;

import mine.block.mru.registry.MRURegistry;
import net.minecraft.registry.Registry;
import net.minecraft.registry.SimpleRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SimpleRegistry.class)
public class RegistryMixin<T> {
    @Unique private boolean hasFrozenYet = false;

    @Inject(method = "freeze", at = @At("TAIL"))
    public void freezeRegistryTail(CallbackInfoReturnable<Registry<T>> cir) {
        if(!hasFrozenYet) {
            MRURegistry.iterate();
            hasFrozenYet = true;
        }
    }
}
