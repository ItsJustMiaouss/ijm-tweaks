package com.itsjustmiaouss.ijmtweaks.render;

import com.itsjustmiaouss.ijmtweaks.IJMTweaks;
import net.minecraft.client.Minecraft;

public class RenderHelper {

    public static void updateFullbright() {
        Minecraft.getInstance().levelRenderer.allChanged();
        IJMTweaks.LOGGER.info("Rerendered level lightning!");
    }

}
