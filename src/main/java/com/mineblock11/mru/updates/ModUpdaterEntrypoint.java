package com.mineblock11.mru.updates;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

import java.util.ArrayList;
import java.util.HashMap;

public class ModUpdaterEntrypoint implements ModInitializer {
    public static ArrayList<String> outdatedMods = new ArrayList<>();
    @Override
    public void onInitialize() {
        var mods = FabricLoader.getInstance().getAllMods();

        HashMap<ModContainer, String> containerModrinthIdMap = new HashMap<>();

        mods.forEach(mod -> {
            var customValue = mod.getMetadata().getCustomValue("mru:modrinth-id");
            if(customValue == null) return;

            var value = customValue.getAsString();

            if(value != null)
                containerModrinthIdMap.put(mod, value);
        });

        containerModrinthIdMap.entrySet().stream().map(entry -> new ModUpdateChecker(entry.getValue(), entry.getKey())).forEach(modUpdateChecker -> {
            if(modUpdateChecker.doesNeedUpdating()) {
                outdatedMods.add(modUpdateChecker.getModContainer().getMetadata().getId());
            }
        });
    }
}
