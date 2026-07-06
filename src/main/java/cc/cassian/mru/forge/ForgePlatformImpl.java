package cc.cassian.mru.forge;

//? forge {
/*import cc.cassian.mru.Platform;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.loading.LoadingModList;

import java.nio.file.Path;
import java.util.ArrayList;

public class ForgePlatformImpl implements Platform {

    public static CustomizeGuiOverlayEvent.DebugText guiLayersEvent;

	public boolean isLoaded(String mod) {
        return ModList.get().isLoaded(mod);
    }

    public boolean isLoadingLoaded(String mod) {
        return LoadingModList.get().getModFileById(mod) != null;
    }

    @Override
    public String loader() {
        return "forge";
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
        return !FMLEnvironment.production;
    }


}
*///?}