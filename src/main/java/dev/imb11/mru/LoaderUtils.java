package dev.imb11.mru;

import java.nio.file.Path;

public class LoaderUtils {
    public static Path getConfigPath(String modID, String configFileName, String configExtension) {
        /*? if fabric {*/
        return net.fabricmc.loader.api.FabricLoader.getInstance().getConfigDir().resolve(modID).resolve(configFileName + "." + configExtension);
        /*?} else {*/
        /*return net.neoforged.fml.loading.FMLLoader.getGamePath().resolve("config").resolve(modID).resolve(configFileName + "." + configExtension);
         *//*?}*/
    }

    public static boolean isModInstalled(String modid) {
        /*? if fabric {*/
        return net.fabricmc.loader.api.FabricLoader.getInstance().isModLoaded(modid);
        /*?} else {*/
        /*return net.neoforged.fml.loading.FMLLoader.getLoadingModList().getModFileById(modid) != null;
         *//*?}*/
    }

    public static Path getGameDir() {
        /*? if fabric {*/
        return net.fabricmc.loader.api.FabricLoader.getInstance().getGameDir();
        /*?} else {*/
        /*return net.neoforged.fml.loading.FMLLoader.getGamePath();
         *//*?}*/
    }

    public static Path getConfigFolder(String modID) {
        /*? if fabric {*/
        return net.fabricmc.loader.api.FabricLoader.getInstance().getGameDir().resolve("config").resolve(modID);
        /*?} else {*/
        /*return net.neoforged.fml.loading.FMLLoader.getGamePath().resolve("config").resolve(modID);
         *//*?}*/
    }

    public static boolean isDevelopmentEnvironment() {
        /*? if fabric {*/
        return net.fabricmc.loader.api.FabricLoader.getInstance().isDevelopmentEnvironment();
        /*?} else {*/
        /*return !net.neoforged.fml.loading.FMLLoader.isProduction();
        *///?}
    }
}
