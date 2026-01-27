package com.itsjustmiaouss.ijmtweaks.mixin;

import net.minecraft.client.renderer.block.ModelBlockRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ModelBlockRenderer.class)
public interface ModelBlockRendererAccessor {
    @Accessor("ambientOcclusion")
    boolean ijmtweaks$getAmbientOcclusion();
}
