package com.itsjustmiaouss.ijmtweaks.mixin;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import net.minecraft.client.particle.BlockDustParticle;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BlockDustParticle.class)
public abstract class BlockDustParticleMixin {

//    @Redirect(
//            method = "<init>(Lnet/minecraft/client/world/ClientWorld;DDDDDDLnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;)V",
//            at = @At(
//                    value = "FIELD",
//                    target = "Lnet/minecraft/client/particle/BlockDustParticle;scale:F",
//                    opcode = Opcodes.PUTFIELD
//            )
//    )
//    private void blockBreakParticleScale(BlockDustParticle instance, float value) {
//        IJMTweaksConfig config = IJMTweaksConfig.get();
//        instance.scale(config.blockBreakParticleScale);
//    }

}
