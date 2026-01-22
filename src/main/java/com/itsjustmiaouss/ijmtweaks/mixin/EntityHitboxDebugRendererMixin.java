package com.itsjustmiaouss.ijmtweaks.mixin;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import com.itsjustmiaouss.ijmtweaks.config.OptionRequirement;
import com.itsjustmiaouss.ijmtweaks.permission.Permission;
import net.minecraft.client.renderer.debug.EntityHitboxDebugRenderer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityHitboxDebugRenderer.class)
public abstract class EntityHitboxDebugRendererMixin {

    @Redirect(
            method = "emitGizmos",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Entity;isInvisible()Z"
            )
    )
    private boolean overrideInvisibility(Entity entity) {

        boolean invisible = entity.isInvisible();

        if (IJMTweaksConfig.get().debugInvisibleEntities) {
            return invisible && !Permission.hasOptionRequirement(OptionRequirement.NON_SURVIVAL_OP);
        }
        return invisible;
    }

}
