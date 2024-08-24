package com.itsjustmiaouss.ijmtweaks;

import com.itsjustmiaouss.ijmtweaks.api.FabrishotIntegration;
import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import com.itsjustmiaouss.ijmtweaks.event.HandleKeybindingsEvent;
import com.itsjustmiaouss.ijmtweaks.keybind.IJMTweaksBindings;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IJMTweaks implements ClientModInitializer {

    public static final String MOD_ID = "ijmtweaks";
    public static final String MOD_DISPLAY_NAME = "IJM's Tweaks";
    public static final Logger LOGGER = LoggerFactory.getLogger(IJMTweaks.MOD_ID);

    @Override
    public void onInitializeClient() {
        // Config
        IJMTweaksConfig.load();

        // Keybindings
        IJMTweaksBindings.registerBindings();

        // Events
        ClientTickEvents.END_CLIENT_TICK.register(new HandleKeybindingsEvent());
        ClientLifecycleEvents.CLIENT_STOPPING.register(client -> IJMTweaksConfig.save());

        // Fabrishot
        if (FabricLoader.getInstance().isModLoaded("fabrishot")) {
            FabrishotIntegration.load();
        }
    }
}
