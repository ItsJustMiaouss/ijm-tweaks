package com.itsjustmiaouss.ijmtweaks.mixin;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ModelBlockRenderer.class)
public abstract class ModelBlockRendererMixin {

    @Redirect(
            method = "tesselateBlock",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/renderer/block/ModelBlockRenderer;ambientOcclusion:Z",
                    opcode = Opcodes.GETFIELD))
    private boolean enableAmbientOcclusion(ModelBlockRenderer instance) {
        if (IJMTweaksConfig.get().fullbright && !IJMTweaksConfig.get().fullbrightAmbientOcclusion) {
            return false;
        }

        return ijmtweaks$instanceAmbientOcclusion(instance);
    }

    @Unique
    private static boolean ijmtweaks$instanceAmbientOcclusion(ModelBlockRenderer instance) {
        return ((ModelBlockRendererAccessor) instance).ijmtweaks$getAmbientOcclusion();
    }
}
