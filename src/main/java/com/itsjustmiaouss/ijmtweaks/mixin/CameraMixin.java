package com.itsjustmiaouss.ijmtweaks.mixin;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import com.itsjustmiaouss.ijmtweaks.keybind.IJMTweaksBindings;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public abstract class CameraMixin {

    @Shadow private float fov;
    @Shadow private float hudFov;

    @Unique private float ijmtweaks$zoomAnchorHudFov;
    @Unique private boolean ijmtweaks$zoomActive;
    @Unique private boolean ijmtweaks$previousSmoothCamera;

    @Unique private static float ijmtweaks$getZoomFactor() {
        float zoomLevel = IJMTweaksConfig.get().zoomLevel;
        float zoomFactor = 1.0f - (zoomLevel / 100.0f);
        return Math.max(zoomFactor, 0.05f);
    }

    @Inject(method = "update", at = @At(
            value = "FIELD",
            target = "Lnet/minecraft/client/Camera;fov:F",
            ordinal = 0,
            shift = At.Shift.AFTER
    ))
    private void applyZoomToWorldFov(DeltaTracker deltaTracker, CallbackInfo ci) {
        Options options = Minecraft.getInstance().options;
        boolean zoomHeld = IJMTweaksBindings.zoomKeyBinding.isDown();

        if (zoomHeld) {
            if (!this.ijmtweaks$zoomActive) {
                this.ijmtweaks$zoomActive = true;
                this.ijmtweaks$zoomAnchorHudFov = this.hudFov;
                this.ijmtweaks$previousSmoothCamera = options.smoothCamera;
                options.smoothCamera = true;
            }

            float vanillaFov = this.fov;
            this.fov = vanillaFov * ijmtweaks$getZoomFactor();
        } else if (this.ijmtweaks$zoomActive) {
            this.ijmtweaks$zoomActive = false;
            options.smoothCamera = this.ijmtweaks$previousSmoothCamera;
        }
    }

    @Inject(method = "update", at = @At(
            value = "FIELD",
            target = "Lnet/minecraft/client/Camera;hudFov:F",
            ordinal = 0,
            shift = At.Shift.AFTER
    ))
    private void applyAnchoredHudFov(DeltaTracker deltaTracker, CallbackInfo ci) {
        if (!this.ijmtweaks$zoomActive) return;

        float vanillaWorldHalfTan = (float) Math.tan(Math.toRadians((this.fov / ijmtweaks$getZoomFactor()) * 0.5f));
        float anchorHudHalfTan = (float) Math.tan(Math.toRadians(this.ijmtweaks$zoomAnchorHudFov * 0.5f));
        float zoomedWorldHalfTan = (float) Math.tan(Math.toRadians(this.fov * 0.5f));

        if (vanillaWorldHalfTan <= 0.0f) return;

        float compensatedHudHalfTan = anchorHudHalfTan * (zoomedWorldHalfTan / vanillaWorldHalfTan);
        this.hudFov = (float) Math.toDegrees(2.0 * Math.atan(compensatedHudHalfTan));
    }
}
