package com.itsjustmiaouss.ijmtweaks.event;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import com.itsjustmiaouss.ijmtweaks.keybind.IJMTweaksBindings;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

public class HandleKeybindingsEvent implements ClientTickEvents.EndTick {

    private final MinecraftClient instance = MinecraftClient.getInstance();

    @Override
    public void onEndTick(MinecraftClient client) {
        if (IJMTweaksBindings.openConfigBinding.wasPressed()) {
            instance.setScreen(IJMTweaksConfig.getScreen().generateScreen(instance.currentScreen));
        }

        if (IJMTweaksBindings.fullbrightKeyBinding.wasPressed()) {
            IJMTweaksConfig config = IJMTweaksConfig.get();
            config.fullbright = !config.fullbright;

            ClientPlayerEntity player = instance.player;
            if (player != null) {
                Text text = config.fullbright
                        ? Text.translatable("overlay.ijmtweaks.fullbright.enabled")
                        : Text.translatable("overlay.ijmtweaks.fullbright.disabled");
                player.sendMessage(text, true);
            }
        }
    }
}
