//? forge {
/*package cc.cassian.mru.forge;

import cc.cassian.mru.events.CommonRegisterEvent;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.LoadingModList;
import net.minecraftforge.registries.RegisterEvent;

import java.util.Optional;

import static com.mojang.text2speech.Narrator.LOGGER;

@Mod("mru")
@Mod.EventBusSubscriber(modid = "mru", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ForgeEvents {
	public ForgeEvents() {

	}

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