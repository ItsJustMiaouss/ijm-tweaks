package com.itsjustmiaouss.ijmtweaks.mixin;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Options.class)
public abstract class OptionsMixin {

    @Inject(method = "save", at = @At("HEAD"))
    private void syncDarkLoadingOverlay(CallbackInfo ci) {
        IJMTweaksConfig.syncDarkLoadingOverlayFromOptions((Options) (Object) this);
    }

    @Unique
    private static final OptionInstance<Boolean> IJMTWEAKS$FORCED_OFF_AO = OptionInstance.createBoolean("options.ao", false);

    @ModifyReturnValue(method = "ambientOcclusion", at = @At("RETURN"))
    private OptionInstance<Boolean> overrideAoInstance(OptionInstance<Boolean> original) {
        if (IJMTweaksConfig.get().fullbright && !IJMTweaksConfig.get().fullbrightAmbientOcclusion) {
            return IJMTWEAKS$FORCED_OFF_AO;
        }
        return original;
    }
}
