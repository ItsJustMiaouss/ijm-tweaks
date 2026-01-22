package com.itsjustmiaouss.ijmtweaks.mixin;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Screenshot;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;
import java.util.function.Consumer;

@Mixin(Screenshot.class)
public abstract class ScreenshotRecorderMixin {

    @Inject(
            method = "lambda$grab$1(Lcom/mojang/blaze3d/platform/NativeImage;Ljava/io/File;Ljava/util/function/Consumer;)V",
            at = @At(value = "INVOKE", target = "Ljava/util/function/Consumer;accept(Ljava/lang/Object;)V", ordinal = 0, shift = At.Shift.AFTER),
            require = 0)

    private static void afterSuccess(NativeImage image, File file, Consumer<Component> callback, CallbackInfo ci) {
        addScreenshotsFolderMessage(file, callback);
    }

    @Unique
    private static void addScreenshotsFolderMessage(File file, Consumer<Component> callback) {
        if (!IJMTweaksConfig.get().screenshotsFolder) return;

        File folder = file.getParentFile();

        MutableComponent text = Component.translatable("chat.ijmtweaks.screenshot.message")
                .withStyle(ChatFormatting.UNDERLINE)
                .withStyle(style -> style.withClickEvent(new ClickEvent.OpenFile(folder)));

        callback.accept(text);
    }

}
