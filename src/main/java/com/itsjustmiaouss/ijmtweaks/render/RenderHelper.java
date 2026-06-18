package com.itsjustmiaouss.ijmtweaks.render;

import com.itsjustmiaouss.ijmtweaks.IJMTweaks;
import net.minecraft.client.Minecraft;

import java.util.concurrent.atomic.AtomicBoolean;

public class RenderHelper {
    private static final AtomicBoolean LIGHTMAP_DIRTY = new AtomicBoolean();

    public static void updateFullbright() {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.level == null) return;

        LIGHTMAP_DIRTY.set(true);

        minecraft.levelRenderer.invalidateCompiledGeometry(
                minecraft.level,
                minecraft.options,
                minecraft.gameRenderer.mainCamera(),
                minecraft.getBlockColors()
        );
        IJMTweaks.LOGGER.info("Rerendered level lightning!");
    }

    public static boolean consumeLightmapDirty() {
        return LIGHTMAP_DIRTY.getAndSet(false);
    }
}
