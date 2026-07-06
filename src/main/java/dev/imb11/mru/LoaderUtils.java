package dev.imb11.mru;

import cc.cassian.mru.Platform;

import java.nio.file.Path;

public class LoaderUtils {
    public static Path getConfigPath(String modID, String configFileName, String configExtension) {
        return Platform.INSTANCE.configPath().resolve(modID).resolve(configFileName + "." + configExtension);
    }

    public static boolean isModInstalled(String modid) {
        return Platform.INSTANCE.isLoaded(modid);
    }

    public static Path getGameDir() {
        //? if fabric {
        return net.fabricmc.loader.api.FabricLoader.getInstance().getGameDir();
        //?} else if neoforge && >1.21.6 {
        /*return net.neoforged.fml.loading.FMLLoader.getCurrent().getGameDir();
        *///?} else if neoforge {
        /*return net.neoforged.fml.loading.FMLLoader.getGamePath();
        *///?} else {
        /*return net.minecraftforge.fml.loading.FMLLoader.getGamePath();
        *///?}
    }

    public static Path getConfigFolder(String modID) {
        return Platform.INSTANCE.configPath();
    }

    public static boolean isDevelopmentEnvironment() {
        return Platform.INSTANCE.isDeveloperEnvironment();
    }
}
