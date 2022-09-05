package mine.block.minelib.gui.w;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.WWidget;
import io.github.cottonmc.cotton.gui.widget.data.InputResult;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.Vec3f;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

public class WEntityWidget extends WWidget {
    private final MinecraftClient client;
    private float scale;
    private EntityType<?> entityType;
    private final EntityRenderDispatcher dispatcher;
    private final VertexConsumerProvider.Immediate entityBuffers;
    private Entity entity;
    protected float mouseRotation = 0;

    public WEntityWidget(EntityType<?> entityType) {
        super();

        this.scale = 0.25f;
        this.client = MinecraftClient.getInstance();
        this.dispatcher = client.getEntityRenderDispatcher();
        this.entityBuffers = client.getBufferBuilders().getEntityVertexConsumers();

        setEntityType(entityType);
    }

    public WEntityWidget(EntityType<?> entityType, float scale) {
        this(entityType);
        this.scale = scale;
    }

    public void setEntityType(@Nullable EntityType<?> entityType) {
        if(this.entity != null) {
            this.entity.kill();
            this.entity = null;
        }

        this.entityType = entityType;

        if(this.entityType == null) return;

        this.entity = this.entityType.create(client.world);
        assert client.player != null;
        assert this.entity != null;
        this.entity.updatePosition(client.player.getX(), client.player.getY(), client.player.getZ());
    }

    @Override
    public void paint(MatrixStack matrices, int x, int y, int mouseX, int mouseY) {
        ScreenDrawing.drawBeveledPanel(matrices, x - 1, y - 1, getWidth() + 2, getHeight() + 2, 0xB8000000, 0xB8374F4A, 0xB8FFFFFF);

        matrices.push();

        if(this.entityType == EntityType.GHAST) {
            scale = this.entity.getWidth() / 35f;
        }

        matrices.translate(x + (this.width / 2f), y + (this.height / 2f), 100);
        matrices.scale(75 * scale * this.width / 64f, -75 * scale * this.height / 64f, 75);

        matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(35));
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-45 + this.mouseRotation));

        matrices.translate(0, entity.getHeight() / -2f, 0);

        RenderSystem.setShaderLights(new Vec3f(.15f, 1, 0), new Vec3f(.15f, -1, 0));
        this.dispatcher.setRenderShadows(false);
        this.dispatcher.render(this.entity, 0, 0, 0, 0, 0, matrices, this.entityBuffers, LightmapTextureManager.MAX_SKY_LIGHT_COORDINATE);
        this.dispatcher.setRenderShadows(true);
        this.entityBuffers.draw();
        DiffuseLighting.enableGuiDepthLighting();

        matrices.pop();
    }

    @Override
    public InputResult onMouseDrag(int x, int y, int button, double deltaX, double deltaY) {
        if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
            this.mouseRotation += deltaX;

            super.onMouseDrag(x, y, button, deltaX, deltaY);
            return InputResult.PROCESSED;
        } else {
            return super.onMouseDrag(x, y, button, deltaX, deltaY);
        }
    }
}
