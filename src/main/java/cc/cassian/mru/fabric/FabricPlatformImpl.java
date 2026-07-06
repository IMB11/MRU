package cc.cassian.mru.fabric;

//? fabric {

import cc.cassian.mru.client.util.Overlay;
import cc.cassian.mru.Platform;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.Identifier;

import java.nio.file.Path;
import java.util.ArrayList;

public class FabricPlatformImpl implements Platform {

    @Override
    public boolean isLoaded(String modid) {
        return FabricLoader.getInstance().isModLoaded(modid);
    }

    @Override
    public String loader() {
        return "fabric";
    }

    public boolean isLoadingLoaded(String mod) {
        return isLoaded(mod);
    }

    @Override
    public Path configPath() {
        return FabricLoader.getInstance().getConfigDir();
    }

    @Override
    public ArrayList<String> getMods() {
        ArrayList<String> modContainers = new ArrayList<>();
        FabricLoader.getInstance().getAllMods().forEach((mod)-> modContainers.add(mod.getMetadata().getId()));
        return modContainers;
    }

    @Override
	public boolean isDeveloperEnvironment() {
		return FabricLoader.getInstance().isDevelopmentEnvironment();
	}
}
//?}