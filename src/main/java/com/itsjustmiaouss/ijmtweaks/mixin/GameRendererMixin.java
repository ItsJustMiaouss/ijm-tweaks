package com.itsjustmiaouss.ijmtweaks.mixin;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import com.itsjustmiaouss.ijmtweaks.keybind.IJMTweaksBindings;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {

    @Unique boolean zoomEnabled = false;

    @Inject(method = "getFov", at = @At("RETURN"), cancellable = true)
    private void setZoomFov(Camera camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<Double> cir) {
        GameOptions options = MinecraftClient.getInstance().options;

        if (IJMTweaksBindings.zoomKeyBinding.isPressed()) {
            zoomEnabled = true;
            options.smoothCameraEnabled = true;

            double zoomLevel =  IJMTweaksConfig.get().zoomLevel;

            double zoomFactor;
            if (zoomLevel == 0) {
                zoomFactor = 1.0; // 0% is the default FOV
            } else if (zoomLevel == 100) {
                zoomFactor = 0.1; // 100% the max zoom level
            } else {
                zoomFactor = 1.0 - (zoomLevel / 100.0 * 0.55); // Approximate the zoom level
            }

            cir.setReturnValue(cir.getReturnValue() * zoomFactor);
        }

        if(!IJMTweaksBindings.zoomKeyBinding.isPressed() && zoomEnabled) {
            zoomEnabled = false;
            options.smoothCameraEnabled = false;
        }
    }

}
