package com.itsjustmiaouss.ijmtweaks.mixin;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.SplashOverlay;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SplashOverlay.class)
public abstract class SplashOverlayMixin {

    @Mutable @Shadow @Final private static int MOJANG_RED;
    @Shadow @Final private static int MONOCHROME_BLACK;

    @Inject(method = "init", at = @At("TAIL"))
    private static void forceMonochromeScreen(MinecraftClient client, CallbackInfo ci) {
        IJMTweaksConfig config = IJMTweaksConfig.get();
        if(config.darkLoadingOverlay) {
            MOJANG_RED = MONOCHROME_BLACK;
        }
    }

}
