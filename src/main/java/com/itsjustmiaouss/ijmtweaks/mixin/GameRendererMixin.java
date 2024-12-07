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

    @Inject(method = "getFov", at = @At(value = "RETURN", ordinal = 1), cancellable = true)
    private void setZoomFov(Camera camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<Float> cir) {
        GameOptions options = MinecraftClient.getInstance().options;

        if (IJMTweaksBindings.zoomKeyBinding.isPressed()) {
            zoomEnabled = true;
            options.smoothCameraEnabled = true;

            float zoomLevel = IJMTweaksConfig.get().zoomLevel;
            float zoomFactor =  (1.0f - (zoomLevel / 100f));
            if(zoomFactor == 0) zoomFactor = 0.05f;

            cir.setReturnValue(cir.getReturnValue() * zoomFactor);
        }

        if(!IJMTweaksBindings.zoomKeyBinding.isPressed() && zoomEnabled) {
            zoomEnabled = false;
            options.smoothCameraEnabled = false;
        }
    }

}
