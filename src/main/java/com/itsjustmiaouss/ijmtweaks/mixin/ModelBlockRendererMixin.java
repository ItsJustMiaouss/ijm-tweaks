package com.itsjustmiaouss.ijmtweaks.mixin;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ModelBlockRenderer.class)
public abstract class ModelBlockRendererMixin {
    @ModifyExpressionValue(
            method = "tesselateBlock",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/renderer/block/ModelBlockRenderer;ambientOcclusion:Z",
                    opcode = Opcodes.GETFIELD))
    private boolean enableAmbientOcclusion(boolean original) {
        if (IJMTweaksConfig.get().fullbright && !IJMTweaksConfig.get().fullbrightAmbientOcclusion) {
            return false;
        }

        return original;
    }
}
