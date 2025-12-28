package com.itsjustmiaouss.ijmtweaks.mixin;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import com.itsjustmiaouss.ijmtweaks.config.OptionRequirement;
import com.itsjustmiaouss.ijmtweaks.permission.Permission;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.state.EntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import javax.swing.text.html.parser.Entity;

@Mixin(EntityRenderer.class)
public abstract class EntityRenderDispatcherMixin<T extends Entity, S extends EntityRenderState> {

    @ModifyExpressionValue(
            method = "updateRenderState",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/Entity;isInvisible()Z"
            )
    )
    private boolean ijmtweaks$shouldHideInvisibleEntitiesInDebug(boolean original) {
        if (IJMTweaksConfig.get().debugInvisibleEntities) {
            return original && !Permission.hasOptionRequirement(OptionRequirement.NON_SURVIVAL_OP);
        }
        return original;
    }
}
