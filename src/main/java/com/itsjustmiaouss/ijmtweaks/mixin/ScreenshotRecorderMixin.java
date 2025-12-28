package com.itsjustmiaouss.ijmtweaks.mixin;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.ScreenshotRecorder;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;

@Mixin(ScreenshotRecorder.class)
public class ScreenshotRecorderMixin {

    @Inject(method = "saveScreenshot", at = @At("TAIL"))
    private static void ijmtweaks$onScreenshotSaved(File gameDirectory, String fileName, CallbackInfo ci) {
        IJMTweaksConfig config = IJMTweaksConfig.get();
        if (!config.screenshotsFolder) return;

        File screenshots = new File(gameDirectory, "screenshots");
        File file = new File(screenshots, fileName);

        MutableText text = Text.translatable("chat.ijmtweaks.screenshot.message")
                .formatted(Formatting.UNDERLINE)
                .styled(style -> style.withClickEvent(
                        new ClickEvent.OpenFile(file.getParent())
                ));

        MinecraftClient.getInstance().execute(() ->
                MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(text)
        );
    }
}
