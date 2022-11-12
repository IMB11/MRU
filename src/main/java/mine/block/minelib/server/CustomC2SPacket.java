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
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * A custom packet that is listened
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
