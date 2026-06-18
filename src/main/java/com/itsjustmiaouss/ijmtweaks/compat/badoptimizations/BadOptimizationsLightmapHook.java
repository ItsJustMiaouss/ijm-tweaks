package com.itsjustmiaouss.ijmtweaks.compat.badoptimizations;

import com.itsjustmiaouss.ijmtweaks.render.RenderHelper;

import java.util.function.BooleanSupplier;

public final class BadOptimizationsLightmapHook implements BooleanSupplier {
    public BadOptimizationsLightmapHook() {}

    @Override public boolean getAsBoolean() {
        return RenderHelper.consumeLightmapDirty();
    }
}