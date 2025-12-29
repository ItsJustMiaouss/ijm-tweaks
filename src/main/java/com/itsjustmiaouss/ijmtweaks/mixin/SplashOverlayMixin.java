package com.itsjustmiaouss.ijmtweaks.mixin;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import net.minecraft.client.gui.screens.LoadingOverlay;
import net.minecraft.client.renderer.texture.TextureManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LoadingOverlay.class)
public abstract class SplashOverlayMixin {

    @Mutable @Shadow @Final private static int LOGO_BACKGROUND_COLOR;
    @Shadow @Final private static int LOGO_BACKGROUND_COLOR_DARK;

    @Inject(method = "registerTextures", at = @At("TAIL"))
    private static void forceMonochromeScreen(TextureManager textureManager, CallbackInfo ci) {
        IJMTweaksConfig config = IJMTweaksConfig.get();
        if(config.darkLoadingOverlay) {
            LOGO_BACKGROUND_COLOR = LOGO_BACKGROUND_COLOR_DARK;
        }
    }

}
