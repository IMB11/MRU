package cc.cassian.mru.neoforge;

//? neoforge {
/*import cc.cassian.mru.client.util.Overlay;
import cc.cassian.mru.Platform;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.fml.loading.LoadingModList;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;

import java.nio.file.Path;
import java.util.ArrayList;

public class NeoforgePlatformImpl implements Platform {
    public static RegisterGuiLayersEvent guiLayersEvent;

    public boolean isLoaded(String mod) {
        return ModList.get().isLoaded(mod);
    }

    public boolean isLoadingLoaded(String mod) {
        return LoadingModList.get().getModFileById(mod) != null;
    }

    @Override
    public String loader() {
        return "neoforge";
    }

    @Override
    public Path configPath() {
        return FMLPaths.CONFIGDIR.get();
    }

    @Override
    public ArrayList<String> getMods() {
        ArrayList<String> ids = new ArrayList<>();
        ModList.get().getMods().forEach(mod -> ids.add(mod.getModId()));
        return ids;
    }

    @Override
    public boolean isDeveloperEnvironment() {
        //? if >1.21.9 {
        return !FMLEnvironment.isProduction();
        //?} else {
        /^return !FMLEnvironment.production;
        ^///?}

    }
}
*///?}