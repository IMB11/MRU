package mine.block.mru.registry;

import mine.block.mru.server.AbstractCustomPacket;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.SimpleRegistry;
import org.jetbrains.annotations.ApiStatus;

public class MRURegistry {
    public static final SimpleRegistry<AbstractCustomPacket> CUSTOM_PACKETS = FabricRegistryBuilder.createSimple(AbstractCustomPacket.class, new Identifier("minelib", "custom_packets")).buildAndRegister();

    @ApiStatus.Internal
    public static void iterate() {
        for (AbstractCustomPacket customPacket : CUSTOM_PACKETS) {
            customPacket.register();
        }
    }
}