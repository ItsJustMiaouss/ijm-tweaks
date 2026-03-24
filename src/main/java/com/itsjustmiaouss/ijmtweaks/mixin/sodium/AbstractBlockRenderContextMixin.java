package com.itsjustmiaouss.ijmtweaks.mixin.sodium;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import net.caffeinemc.mods.sodium.client.model.light.LightMode;
import net.caffeinemc.mods.sodium.client.render.model.AbstractBlockRenderContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractBlockRenderContext.class)
public abstract class AbstractBlockRenderContextMixin {

    @Shadow protected boolean useAmbientOcclusion;
    @Shadow protected LightMode defaultLightMode;

    @Inject(method = "prepareAoInfo", at = @At("TAIL"))
    private void disableAmbientOcclusion(boolean modelAo, CallbackInfo ci) {
        if (!IJMTweaksConfig.get().fullbright) return;

        this.useAmbientOcclusion = false;
        this.defaultLightMode = LightMode.FLAT;
    }
}
