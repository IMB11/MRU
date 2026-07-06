package cc.cassian.mru;

//? fabric {
import cc.cassian.mru.fabric.FabricPlatformImpl;
//?}
import java.nio.file.Path;
import java.util.ArrayList;
//? neoforge {
/*import cc.cassian.mru.neoforge.NeoforgePlatformImpl;
*///?}
//? forge
//import cc.cassian.mru.forge.ForgePlatformImpl;

public interface Platform {

    //? fabric {
    Platform INSTANCE = new FabricPlatformImpl();
    //?}
    //? neoforge {
    /*Platform INSTANCE = new NeoforgePlatformImpl();
    *///?}
    //? forge {
    /*Platform INSTANCE = new ForgePlatformImpl();
     *///?}


    boolean isLoaded(String modid);
    boolean isLoadingLoaded(String mod);
    String loader();
    Path configPath();
    ArrayList<String> getMods();

	boolean isDeveloperEnvironment();
}
