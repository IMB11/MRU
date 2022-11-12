package mine.block.minelib.server;

import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
@Deprecated(since = "0.1.4", forRemoval = true)
public abstract class MinelibPacketManager {
    public final Identifier ID;
    private final EnvType env;
    private final ServerPlayNetworking.PlayChannelHandler serverAction;
    private final ClientPlayNetworking.PlayChannelHandler clientAction;

    public MinelibPacketManager(String id, @Nullable EnvType envType, @Nullable ServerPlayNetworking.PlayChannelHandler serverAction, @Nullable ClientPlayNetworking.PlayChannelHandler clientAction) {
        this.ID = new Identifier(id);
        this.env = envType;
        this.serverAction = serverAction;
        this.clientAction = clientAction;
    }

    public void register() {
        if(this.env == null) {
            // Both SERVER + CLIENT

            if(serverAction != null) {
                ServerPlayNetworking.registerGlobalReceiver(ID, serverAction);
            }

            if(FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT && clientAction != null) {
                ClientPlayNetworking.registerGlobalReceiver(ID, clientAction);
            }
        } else if (this.env == EnvType.CLIENT && FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT && clientAction != null) {
            ClientPlayNetworking.registerGlobalReceiver(ID, clientAction);
        } else if (this.env == EnvType.SERVER && FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER && serverAction != null) {
            ServerPlayNetworking.registerGlobalReceiver(ID, serverAction);
        }

        System.out.println("Registered Packet: " + this.ID);
    }
}
