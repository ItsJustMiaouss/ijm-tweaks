package com.itsjustmiaouss.ijmtweaks.mixin;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ModelBlockRenderer.class)
public abstract class ModelBlockRendererMixin {

    @Redirect(
            method = "tesselateBlock",
            at = @At(
            value = "FIELD",
            target = "Lnet/minecraft/client/renderer/block/ModelBlockRenderer;ambientOcclusion:Z"
    ))
    private boolean disableAmbientOcclusion(ModelBlockRenderer instance) {
        return !IJMTweaksConfig.get().fullbright && instanceAmbientOcclusion(instance);
    }

    private static boolean instanceAmbientOcclusion(ModelBlockRenderer instance) {
        return ((ModelBlockRendererAccessor) instance).ijmtweaks$getAmbientOcclusion();
    }
}
