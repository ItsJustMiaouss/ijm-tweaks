package com.itsjustmiaouss.ijmtweaks.event;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import com.itsjustmiaouss.ijmtweaks.keybind.IJMTweaksBindings;
import com.itsjustmiaouss.ijmtweaks.render.RenderHelper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.NonNull;

public class HandleKeybindingsEvent implements ClientTickEvents.EndTick {

    private final Minecraft instance = Minecraft.getInstance();

    @Override
    public void onEndTick(@NonNull Minecraft client) {
        if (IJMTweaksBindings.openConfigBinding.consumeClick()) {
            instance.setScreenAndShow(IJMTweaksConfig.getScreen().generateScreen(instance.gui.screen()));
        }

        if (IJMTweaksBindings.fullbrightKeyBinding.consumeClick()) {
            IJMTweaksConfig config = IJMTweaksConfig.get();
            config.fullbright = !config.fullbright;
            RenderHelper.updateFullbright();

            LocalPlayer player = instance.player;
            if (player != null) {
                Component text = config.fullbright
                        ? Component.translatable("overlay.ijmtweaks.fullbright.enabled")
                        : Component.translatable("overlay.ijmtweaks.fullbright.disabled");
                player.sendOverlayMessage(text);
            }
        }
    }
}
