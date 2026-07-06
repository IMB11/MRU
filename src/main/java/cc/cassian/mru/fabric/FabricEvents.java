//? fabric {
package cc.cassian.mru.fabric;

import cc.cassian.mru.events.CommonRegisterEvent;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class FabricEvents implements ModInitializer {

	@Override
	public void onInitialize() {
		FabricLoader.getInstance().invokeEntrypoints("mru", CommonRegisterEvent.class, CommonRegisterEvent::onInitialize);
	}
}
//?}