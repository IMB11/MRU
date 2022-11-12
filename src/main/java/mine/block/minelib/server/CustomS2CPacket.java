package mine.block.minelib.server;

import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * A custom server to client packet.
 * @author mineblock11
 */
public abstract class CustomS2CPacket extends AbstractCustomPacket implements ClientPlayNetworking.PlayChannelHandler {
    /**
     * Send an instance of this packet to a client.
     * @param player The player to send the packet to.
     * @param data Any data to be sent with the packet, can be null.
     */
    public void send(ServerPlayerEntity player, @Nullable PacketByteBuf data) {
        ServerPlayNetworking.send(player, getID(), Objects.requireNonNullElseGet(data, PacketByteBufs::create));
    }

    @ApiStatus.Internal
    @Override
    public void register() {
        EnvType type = FabricLoader.getInstance().getEnvironmentType();
        if (type == EnvType.CLIENT) {
            ClientPlayNetworking.registerGlobalReceiver(getID(), this);
        }
    }
}
