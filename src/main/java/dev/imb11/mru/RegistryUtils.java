package dev.imb11.mru;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class RegistryUtils {
    public static Function<ResourceLocation, SoundEvent> getSoundEventRegistry(@Nullable ClientLevel level) {
        if (level == null) {
            //? if >=1.21.2 {
            return BuiltInRegistries.SOUND_EVENT::getValue;
            //?} else {
            /*return BuiltInRegistries.SOUND_EVENT::get;
             *///?}
        } else {
            //? if >=1.21.2 {
            return location -> level.registryAccess().lookupOrThrow(Registries.SOUND_EVENT).getValue(location);
            //?} else {
            /*return location -> level.registryAccess().registryOrThrow(Registries.SOUND_EVENT).get(location);
             *///?}
        }
    }

    public static ResourceLocation getId(SoundEvent event) {
        //? if >=1.21.2 {
        return event.location();
        //?} else {
        /*return event.getLocation();
        *///?}
    }
}
