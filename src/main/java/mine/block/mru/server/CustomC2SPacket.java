package mine.block.mru.server;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * A custom client to server packet.
 * @author mineblock11
 */
public abstract class CustomC2SPacket extends AbstractCustomPacket implements ServerPlayNetworking.PlayChannelHandler {
    /**
     * Send an instance of this packet to the server.
     * @param data Any data to be sent with the packet, can be null.
     */
    public void send(@Nullable PacketByteBuf data) {
        ClientPlayNetworking.send(getID(), Objects.requireNonNullElseGet(data, PacketByteBufs::create));
    }

    @ApiStatus.Internal
    @Override
    public void register() {
        // No need for environment check as ServerPlayNetworking is present on both client and server.
        ServerPlayNetworking.registerGlobalReceiver(getID(), this);
    }
}
