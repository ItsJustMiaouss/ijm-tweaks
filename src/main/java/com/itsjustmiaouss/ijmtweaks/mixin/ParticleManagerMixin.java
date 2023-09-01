package com.itsjustmiaouss.ijmtweaks.mixin;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import net.minecraft.block.BlockState;
import net.minecraft.client.particle.BlockDustParticle;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ParticleManager.class)
public abstract class ParticleManagerMixin {

    @Shadow protected ClientWorld world;

    @Inject(method = "addBlockBreakParticles", at = @At("HEAD"), cancellable = true)
    private void inject(BlockPos pos, BlockState state, CallbackInfo ci) {
       if (state.isAir() || !state.hasBlockBreakParticles()) {
           return;
       }
       VoxelShape voxelShape = state.getOutlineShape(world, pos);
        IJMTweaksConfig config = IJMTweaksConfig.get();

        voxelShape.forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> {
            double d = Math.min(1.0, maxX - minX);
            double e = Math.min(1.0, maxY - minY);
            double f = Math.min(1.0, maxZ - minZ);
            int i = Math.max(2, MathHelper.ceil(d / 0.25));
            int j = Math.max(2, MathHelper.ceil(e / 0.25));
            int k = Math.max(2, MathHelper.ceil(f / 0.25));

            i -= config.blockBreakParticle;
            j -= config.blockBreakParticle;
            k -= config.blockBreakParticle;

            for (int l = 0; l < i; ++l) {
                for (int m = 0; m < j; ++m) {
                    for (int n = 0; n < k; ++n) {
                        double g = ((double)l + 0.5) / (double)i;
                        double h = ((double)m + 0.5) / (double)j;
                        double o = ((double)n + 0.5) / (double)k;
                        double p = g * d + minX;
                        double q = h * e + minY;
                        double r = o * f + minZ;
                        ((ParticleManager)(Object)this).addParticle(new BlockDustParticle(this.world, (double)pos.getX() + p, (double)pos.getY() + q, (double)pos.getZ() + r, g - 0.5, h - 0.5, o - 0.5, state, pos));
                    }
                }
            }
        });

        ci.cancel();
   }

}
