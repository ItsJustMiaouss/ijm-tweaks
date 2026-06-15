package com.itsjustmiaouss.ijmtweaks.render;

import com.itsjustmiaouss.ijmtweaks.IJMTweaks;
import net.minecraft.client.Minecraft;

public class RenderHelper {

    public static void updateFullbright() {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.level == null) return;

        minecraft.levelRenderer.invalidateCompiledGeometry(
                minecraft.level,
                minecraft.options,
                minecraft.gameRenderer.mainCamera(),
                minecraft.getBlockColors()
        );
        IJMTweaks.LOGGER.info("Rerendered level lightning!");
    }

}
