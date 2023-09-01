package com.itsjustmiaouss.ijmtweaks.api;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> IJMTweaksConfig.getScreen().generateScreen(parent);
    }
}
