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
            cir.setReturnValue(cir.getReturnValue() * IJMTweaksConfig.get().zoomLevel);
        }

        if(!IJMTweaksBindings.zoomKeyBinding.isPressed() && zoomEnabled) {
            zoomEnabled = false;
            options.smoothCameraEnabled = false;
        }
    }

}
