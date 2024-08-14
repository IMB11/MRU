/*? if forge {*/
/*package dev.imb11.loader.forge;

import dev.imb11.mru.LoaderUtils;
import dev.imb11.mru.packing.Unpacker;
import dev.imb11.mru.packing.resource.UnpackedResourcePack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod("mru")
public class MRUForge {
	public MRUForge() {
		if(!FMLEnvironment.production || true) {
			Unpacker.register(MRUForge.class, new UnpackedResourcePack("mru", LoaderUtils.getGameDir().resolve("mru"), "mru", "Stop reading this and get back to work!"));
		}
	}
}
*//*?}*/