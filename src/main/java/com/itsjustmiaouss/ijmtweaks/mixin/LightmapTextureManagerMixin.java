package com.itsjustmiaouss.ijmtweaks.mixin;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import com.itsjustmiaouss.ijmtweaks.config.OptionRequirement;
import com.itsjustmiaouss.ijmtweaks.permission.Permission;
import net.minecraft.client.render.LightmapTextureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LightmapTextureManager.class)
public abstract class LightmapTextureManagerMixin {

    @Redirect(method = "update", at = @At(value = "INVOKE", target = "Ljava/lang/Double;floatValue()F", ordinal = 1))
    private float modifyGamma(Double originalGamma) {
        if (!Permission.hasOptionRequirement(OptionRequirement.NON_SURVIVAL)) return originalGamma.floatValue();

        IJMTweaksConfig config = IJMTweaksConfig.get();

        return config.fullbright ? 100 : originalGamma.floatValue();
    }

}
