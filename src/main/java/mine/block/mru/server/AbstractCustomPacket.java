package mine.block.mru.server;

import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;

public abstract class AbstractCustomPacket {
    /**
     * Get the ID of the packet for registration.
     * @return The ID of the packet.
     */
    public abstract Identifier getID();
    @ApiStatus.Internal
    public abstract void register();
}
