package com.itsjustmiaouss.ijmtweaks.mixin.sodium;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import net.caffeinemc.mods.sodium.client.model.light.LightMode;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.pipeline.BlockRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(BlockRenderer.class)
public abstract class SodiumBlockRendererMixin {

    @ModifyVariable(
            method = "processQuad",
            at = @At("STORE"),
            name = "lightMode")
    private LightMode forceFlatLighting(LightMode original) {
        return IJMTweaksConfig.get().fullbright ? LightMode.FLAT : original;
    }
}
