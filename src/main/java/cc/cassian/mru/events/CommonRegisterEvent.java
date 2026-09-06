package cc.cassian.mru.events;

/**
 * Fired in Fabric's <code>ModInitializer</code> and NeoForge's <code>RegisterEvent</code> to allow Fabric-style static initialization in common code. <strong>Not safe to register statically initialize blocks with on Forge, that loader makes some strange decisions.</strong>
 */
public interface CommonRegisterEvent {
	void onInitialize();
}
