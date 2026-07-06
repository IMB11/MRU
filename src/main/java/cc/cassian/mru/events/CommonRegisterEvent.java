package cc.cassian.mru.events;

/**
 * Fired in Fabric's <code>ModInitializer</code> and (Neo)Forge's <code>RegisterEvent</code> to allow Fabric-style static initialization in common code. Not safe to register statically initialize blocks with on Forge, that loader makes some strange decisions.
 */
public interface CommonRegisterEvent {
	void onInitialize();
}
