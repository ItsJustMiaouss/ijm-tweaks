package com.itsjustmiaouss.ijmtweaks.mixin;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
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
import java.util.function.Consumer;

@Mixin(ScreenshotRecorder.class)
public class ScreenshotRecorderMixin {

    @Inject(method = "saveScreenshot", at = @At("TAIL"))
    private static void ijmtweaks$onScreenshotSaved(
            File file,
            Framebuffer framebuffer,
            Consumer<Text> messageReceiver,
            CallbackInfo ci
    ) {
        IJMTweaksConfig config = IJMTweaksConfig.get();
        if (!config.screenshotsFolder) return;

        File gameDirectory = MinecraftClient.getInstance().runDirectory;
        File screenshotsDir = new File(gameDirectory, "screenshots");

        MutableText text = Text.translatable("chat.ijmtweaks.screenshot.message")
                .formatted(Formatting.UNDERLINE)
                .styled(style -> style.withClickEvent(
                        new ClickEvent.OpenFile(screenshotsDir.getAbsolutePath())
                ));

        MinecraftClient.getInstance().execute(() ->
                MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(text)
        );
    }
}
