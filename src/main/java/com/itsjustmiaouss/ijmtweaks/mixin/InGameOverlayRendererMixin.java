package com.itsjustmiaouss.ijmtweaks.mixin;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ScreenEffectRenderer.class)
public abstract class InGameOverlayRendererMixin {

    @ModifyArg(method = "lambda$submitFire$0", at = @At(
            value = "INVOKE",
            target = "Lorg/joml/Matrix4f;translate(FFF)Lorg/joml/Matrix4f;"),
            index = 1)
    private static float fireOverlayHeight(float y) {
        IJMTweaksConfig config = IJMTweaksConfig.get();

        switch (config.fireOverlay) {
            case REDUCED -> {
                return -0.5f;
            }
            case HIDDEN -> {
                return -1;
            }
            default -> {
                return -0.3f;
            }
        }
    }
}
