package com.itsjustmiaouss.ijmtweaks.api;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import me.ramidzkh.fabrishot.event.ScreenshotSaveCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.ChatFormatting;
import java.nio.file.Path;

public class FabrishotIntegration {

    public static void load() {
        ScreenshotSaveCallback.EVENT.register(path -> {
            IJMTweaksConfig config = IJMTweaksConfig.get();
            if(!config.screenshotsFolder) return;

            Path folder = path.getParent() != null ? path.getParent() : path;

            MutableComponent text = Component.translatable("chat.ijmtweaks.screenshot.message").withStyle(ChatFormatting.UNDERLINE)
                    .withStyle(style -> style.withClickEvent(new ClickEvent.OpenFile(folder)));

            Minecraft mc = Minecraft.getInstance();
            mc.execute(() -> mc.gui.getChat().addMessage(text));
        });
    }

}
