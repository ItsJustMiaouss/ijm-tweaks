package com.itsjustmiaouss.ijmtweaks.mixin;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import net.minecraft.client.renderer.LightmapRenderStateExtractor;
import net.minecraft.client.renderer.state.LightmapRenderState;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightmapRenderStateExtractor.class)
public abstract class LightmapRenderStateExtractorMixin {

    @Unique private static final Vector3f IJM_TWEAKS_WHITE = new Vector3f(1.0F, 1.0F, 1.0F);

    @Inject(
            method = "extract(Lnet/minecraft/client/renderer/state/LightmapRenderState;F)V",
            at = @At("TAIL")
    )
    private void applyFullbright(LightmapRenderState renderState, float partialTicks, CallbackInfo ci) {
        if (!IJMTweaksConfig.get().fullbright) return;

        renderState.needsUpdate = true;
        renderState.nightVisionEffectIntensity = 1.0F;
        renderState.nightVisionColor = IJM_TWEAKS_WHITE;
    }
}
