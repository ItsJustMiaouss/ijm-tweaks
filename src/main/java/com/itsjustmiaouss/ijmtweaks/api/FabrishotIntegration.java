package com.itsjustmiaouss.ijmtweaks.api;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import me.ramidzkh.fabrishot.event.ScreenshotSaveCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class FabrishotIntegration {

    public static void load() {
        ScreenshotSaveCallback.EVENT.register(path -> {
            IJMTweaksConfig config = IJMTweaksConfig.get();
            if(!config.screenshotsFolder) return;

            MutableText text = Text.translatable("screenshot.folder")
                    .formatted(Formatting.UNDERLINE)
                    .styled(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, path.getParent().toString())));

            MinecraftClient instance = MinecraftClient.getInstance();
            instance.execute(() -> instance.inGameHud.getChatHud().addMessage(text));
        });
    }

}