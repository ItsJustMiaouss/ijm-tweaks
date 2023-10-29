package com.itsjustmiaouss.ijmtweaks;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import com.itsjustmiaouss.ijmtweaks.keybind.IJMTweaksBindings;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IJMTweaks implements ClientModInitializer {

    public static final String MOD_ID = "ijmtweaks";
    public static final String MOD_DISPLAY_NAME = "IJM's Tweaks";
    public static final Logger LOGGER = LoggerFactory.getLogger(IJMTweaks.MOD_ID);

    @Override
    public void onInitializeClient() {
        IJMTweaksConfig.load();
        IJMTweaksBindings.registerBindings();
    }
}
