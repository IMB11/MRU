//? neoforge {
/*package cc.cassian.mru.client.neoforge;

import cc.cassian.mru.client.events.ClientRegisterEvent;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.loading.LoadingModList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@EventBusSubscriber(modid = "mru", value = Dist.CLIENT)
public class NeoForgeClientEvents {
	public static final Logger LOGGER = LoggerFactory.getLogger("MRU");

	@SubscribeEvent
	public static void register(FMLClientSetupEvent event) {
		LoadingModList.get().getMods().forEach(modInfo -> {
			Optional<String> optional = modInfo.getConfig().getConfigElement("mru_client");
			if (optional.isPresent()) {
				LOGGER.debug("MRU: Registering client content for : {}", modInfo.getModId());
				try {
					Class<?> clazz = Class.forName(optional.get());
					ClientRegisterEvent integration = ((ClientRegisterEvent) clazz.getConstructor().newInstance());
					integration.onInitializeClient();
					LOGGER.debug("MRU: Registered client content for mod: {}", modInfo.getModId());
				} catch (Exception ignored) {
					LOGGER.error("MRU: Failed to load client content: {}", optional.get());
				}
			}
		});
	}
}
*///?}