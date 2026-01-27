package com.itsjustmiaouss.ijmtweaks.mixin;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.world.level.GameType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MultiPlayerGameMode.class)
public abstract class InteractionManagerMixin {

    @Shadow private GameType localPlayerMode;

    @Inject(method = "hasExperience", at = @At("RETURN"), cancellable = true)
    private void hasExperienceBar(CallbackInfoReturnable<Boolean> cir) {
        IJMTweaksConfig config = IJMTweaksConfig.get();
        if(config.experienceBarInCreative) {
            cir.setReturnValue(localPlayerMode.isSurvival() || localPlayerMode.isCreative());
        }
    }
}
