package com.itsjustmiaouss.ijmtweaks.mixin;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import net.minecraft.client.texture.NativeImage;
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
public abstract class ScreenshotRecorderMixin {

    @Inject(
            method = "method_1661",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/function/Consumer;accept(Ljava/lang/Object;)V",
                    ordinal = 0,
                    shift = At.Shift.AFTER
            )
    )
    private static void addScreenshotsFolderMessage(NativeImage nativeImage, File file, Consumer<Text> consumer, CallbackInfo ci) {
        IJMTweaksConfig config = IJMTweaksConfig.get();
        if(!config.screenshotsFolder) return;

        MutableText text = Text.translatable("chat.ijmtweaks.screenshot.message")
                .formatted(Formatting.UNDERLINE)
                .styled(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, file.getParent())));

        consumer.accept(text);
    }

}
