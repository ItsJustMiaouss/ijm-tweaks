package com.itsjustmiaouss.ijmtweaks.event;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import com.itsjustmiaouss.ijmtweaks.keybind.IJMTweaksBindings;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

public class HandleKeybindingsEvent implements ClientTickEvents.EndTick {

    @Override
    public void onEndTick(MinecraftClient client) {
        if (IJMTweaksBindings.openConfigBinding.wasPressed()) {
            MinecraftClient instance = MinecraftClient.getInstance();
            instance.setScreen(IJMTweaksConfig.getScreen().generateScreen(instance.currentScreen));
        }

        if (IJMTweaksBindings.fullbrightKeyBinding.wasPressed()) {
            IJMTweaksConfig config = IJMTweaksConfig.get();
            config.fullbright = !config.fullbright;
        }
    }
}
