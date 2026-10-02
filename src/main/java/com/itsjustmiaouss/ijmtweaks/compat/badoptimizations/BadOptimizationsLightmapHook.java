package com.itsjustmiaouss.ijmtweaks.compat.badoptimizations;

import com.itsjustmiaouss.ijmtweaks.render.RenderHelper;

import java.util.function.BooleanSupplier;

public final class BadOptimizationsLightmapHook implements BooleanSupplier {
    public BadOptimizationsLightmapHook() {}

    // Force BadOptimizations to re-render the lightmap when toggling fullbright
    @Override public boolean getAsBoolean() {
        return RenderHelper.consumeLightmapDirty();
    }
}