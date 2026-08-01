//? fabric {
package cc.cassian.mru.client.fabric;

import cc.cassian.mru.client.events.ClientRegisterEvent;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class FabricClientEvents implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		FabricLoader.getInstance().invokeEntrypoints("mru_client", ClientRegisterEvent.class, ClientRegisterEvent::onInitializeClient);
	}
}
//?}