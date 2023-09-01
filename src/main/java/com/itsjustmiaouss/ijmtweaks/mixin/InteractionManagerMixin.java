package com.itsjustmiaouss.ijmtweaks.mixin;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.world.GameMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerInteractionManager.class)
public abstract class InteractionManagerMixin {

    @Shadow private GameMode gameMode;

    @Inject(method = "hasExperienceBar", at = @At("RETURN"), cancellable = true)
    private void hasExperienceBar(CallbackInfoReturnable<Boolean> cir) {
        IJMTweaksConfig config = IJMTweaksConfig.get();
        if(config.experienceBarInCreative) {
            cir.setReturnValue(gameMode.isSurvivalLike() || gameMode.isCreative());
        }
    }

}
