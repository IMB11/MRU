package cc.cassian.mru.client.events;

/**
 * Fired in Fabric's <code>ClientModInitializer</code> and (Neo)Forge's <code>FMLClientSetupEvent</code> to allow Fabric-style static initialization in client code.
 */
public interface ClientRegisterEvent {
	void onInitializeClient();
}
