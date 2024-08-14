//? if fabric {
package dev.imb11.loader.fabric;

import dev.imb11.mru.LoaderUtils;
import dev.imb11.mru.packing.Unpacker;
import dev.imb11.mru.packing.resource.UnpackedResourcePack;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class MRUFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        if(FabricLoader.getInstance().isDevelopmentEnvironment() || true) {
            Unpacker.register(MRUFabric.class, new UnpackedResourcePack("mru", LoaderUtils.getGameDir().resolve("mru"), "mru", "Stop reading this and get back to work!"));
        }
    }
}
//?}