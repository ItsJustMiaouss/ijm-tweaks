package com.itsjustmiaouss.ijmtweaks.mixin;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import net.minecraft.client.multiplayer.ClientLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ClientLevel.class)
public abstract class ClientWorldMixin {

    @ModifyVariable(
            method = "lambda$addDestroyBlockEffect$0(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;DDDDDD)V",
            at = @At("STORE"),
            name = "countX"
    )
    private int reduceDestroyParticlesX(int value) {
        return ijmtweaks$getReducedValue(value);
    }

    @ModifyVariable(
            method = "lambda$addDestroyBlockEffect$0(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;DDDDDD)V",
            at = @At("STORE"),
            name = "countY"
    )
    private int reduceDestroyParticlesY(int value) {
        return ijmtweaks$getReducedValue(value);
    }

    @ModifyVariable(
            method = "lambda$addDestroyBlockEffect$0(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;DDDDDD)V",
            at = @At("STORE"),
            name = "countZ"
    )
    private int reduceDestroyParticlesZ(int value) {
        return ijmtweaks$getReducedValue(value);
    }

    @Unique private int ijmtweaks$getReducedValue(int value) {
        float inverted = 100 - IJMTweaksConfig.get().blockBreakParticle;
        return Math.round(value * inverted / 100f);
    }
}
