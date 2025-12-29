package com.itsjustmiaouss.ijmtweaks.mixin;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import com.mojang.blaze3d.pipeline.RenderTarget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;
import java.util.function.Consumer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Screenshot;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

@Mixin(Screenshot.class)
public class ScreenshotRecorderMixin {

    @Inject(method = "grab", at = @At("TAIL"))
    private static void ijmtweaks$onScreenshotSaved(
            File file,
            RenderTarget framebuffer,
            Consumer<Component> messageReceiver,
            CallbackInfo ci
    ) {
        IJMTweaksConfig config = IJMTweaksConfig.get();
        if (!config.screenshotsFolder) return;

        File gameDirectory = Minecraft.getInstance().gameDirectory;
        File screenshotsDir = new File(gameDirectory, "screenshots");

        MutableComponent text = Component.translatable("chat.ijmtweaks.screenshot.message")
                .withStyle(ChatFormatting.UNDERLINE)
                .withStyle(style -> style.withClickEvent(
                        new ClickEvent.OpenFile(screenshotsDir.getAbsolutePath())
                ));

        Minecraft.getInstance().execute(() ->
                Minecraft.getInstance().gui.getChat().addMessage(text)
        );
    }
}
