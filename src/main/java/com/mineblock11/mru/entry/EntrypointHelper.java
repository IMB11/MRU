package com.mineblock11.mru.entry;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

public class EntrypointHelper implements ModInitializer {
    @Override
    public void onInitialize() {
        FabricLoader loader = FabricLoader.getInstance();
        loader.getAllMods().forEach(this::iterateModContainer);
    }

    public void callCompatEntrypoints(String modID) {
        for (CompatabilityEntrypoint entrypoint : FabricLoader.getInstance().getEntrypoints("mru-compat-" + modID, CompatabilityEntrypoint.class)) {
            entrypoint.initialize();
        }
    }

    public void iterateModContainer(ModContainer container) {
        container.getContainedMods().forEach(this::iterateModContainer);
        callCompatEntrypoints(container.getMetadata().getId());
    }
}
