package dev.imb11.mru;

import cc.cassian.mru.util.CommonUtils;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class RegistryUtils {
    public static Function<Identifier, SoundEvent> getSoundEventRegistry(@Nullable ClientLevel level) {
        if (level == null) {
            return id -> cc.cassian.mru.util.RegistryUtils.getValue(BuiltInRegistries.SOUND_EVENT, id);
        } else {
            //? if >=1.21.2 {
            return location -> level.registryAccess().lookupOrThrow(Registries.SOUND_EVENT).getValue(location);
            //?} else {
            /*return location -> level.registryAccess().registryOrThrow(Registries.SOUND_EVENT).get(location);
             *///?}
        }
    }

    public static Identifier getId(SoundEvent event) {
        //? if >1.21.2 {
        return event.location();
        //?} else {
        /*return event.getLocation();
        *///?}
    }
}
