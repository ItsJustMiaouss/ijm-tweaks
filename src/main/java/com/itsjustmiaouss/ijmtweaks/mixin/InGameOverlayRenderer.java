package com.itsjustmiaouss.ijmtweaks.mixin;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(net.minecraft.client.gui.hud.InGameOverlayRenderer.class)
public abstract class InGameOverlayRenderer {

    @ModifyArg(
            method = "renderFireOverlay",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/util/math/MatrixStack;translate(FFF)V"
            ),
            index = 1
    )
    private static float fireOverlayHeight(float x) {
        IJMTweaksConfig config = IJMTweaksConfig.get();

        switch (config.fireOverlay) {
            case REDUCE -> {
                return -0.5f;
            }
            case HIDE -> {
                return -1;
            }
            default -> {
                return -0.3f;
            }
        }
    }

}
