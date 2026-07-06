//? neoforge {
/*package cc.cassian.mru.neoforge;

import cc.cassian.mru.events.CommonRegisterEvent;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.loading.LoadingModList;
import net.neoforged.neoforge.registries.RegisterEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@EventBusSubscriber(modid = "mru")
public class NeoForgeEvents {
	public static final Logger LOGGER = LoggerFactory.getLogger("MRU");

	@SubscribeEvent
	public static void register(RegisterEvent event) {
		if (event.getRegistryKey().equals(Registries.BLOCK)) {
			LoadingModList.get().getMods().forEach(modInfo -> {
				Optional<String> optional = modInfo.getConfig().getConfigElement("mru");
				if (optional.isPresent()) {
					LOGGER.info("MRU: Registering content for : {}", modInfo.getModId());
					try {
						Class<?> clazz = Class.forName(optional.get());
						CommonRegisterEvent integration = ((CommonRegisterEvent) clazz.getConstructor().newInstance());
						integration.onInitialize();
						LOGGER.info("MRU: Registered content for mod: {}", modInfo.getModId());
						return;
					} catch (Exception ignored) {
					}

					LOGGER.error("MRU: Failed to load content: {}", optional.get());
				}
			});
		}

	}
}
*///?}