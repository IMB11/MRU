package cc.cassian.mru.compat;

import cc.cassian.mru.Platform;

public class ModCompat {
    /**
     * Accessories - used for detecting overlays in accessory slots.
     * Multiplatform
     */
    public static final boolean ACCESSORIES = Platform.INSTANCE.isLoaded("accessories");
    /**
     * Cloth Config - used for mod configuration.
     * Multiplatform
     */
    public static final boolean CLOTH_CONFIG = Platform.INSTANCE.isLoaded("cloth-config") || Platform.INSTANCE.isLoaded("cloth_config");
    /**
     * Curios - used for detecting overlays in accessory slots.
     * Forge/NeoForge
     */
    public static final boolean CURIOS = Platform.INSTANCE.isLoaded("curios");
    /**
     * Fabric API
     * Multiplatform (Forgified Fabric API)
     */
    public static final boolean FABRIC_API = Platform.INSTANCE.isLoaded("fabric-api") || Platform.INSTANCE.isLoaded("fabric_api");
    /**
     * Ohmega - used for detecting overlays in accessory slots.
     * Multiplatform
     */
    public static boolean OHMEGA = Platform.INSTANCE.isLoaded("ohmega");
    /**
     * Sophisticated Backpacks - used for detecting overlays in backpack slots.
     */
    public static final boolean SOPHISTICATED_BACKPACKS = Platform.INSTANCE.isLoaded("sophisticatedbackpacks");
    /**
     * Traveler's Backpacks - used for detecting overlays in backpack slots.
     */
    public static final boolean TRAVELERS_BACKPACK = Platform.INSTANCE.isLoaded("travelersbackpack");
    /**
     * Trinkets - used for detecting overlays in accessory slots.
     * Fabric
     */
    public static final boolean TRINKETS = Platform.INSTANCE.isLoaded("trinkets");
    /**
     * Trinkets (Updated) - used for detecting overlays in accessory slots.
     * Multiplatform
     */
    public static final boolean TRINKETS_UPDATED = Platform.INSTANCE.isLoaded("trinkets_updated");

    /**
     * Yet Another Config Lib - ingame configuration.
     * Multiplatform
     */
    public static final boolean YET_ANOTHER_CONFIG_LIB = Platform.INSTANCE.isLoaded("yet_another_config_lib_v3");

}
