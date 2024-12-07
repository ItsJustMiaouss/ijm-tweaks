package com.itsjustmiaouss.ijmtweaks.mixin;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import com.itsjustmiaouss.ijmtweaks.config.OptionRequirement;
import com.itsjustmiaouss.ijmtweaks.permission.Permission;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.state.EntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(EntityRenderDispatcher.class)
public abstract class EntityRenderDispatcherMixin {

    @Redirect(
            method = "render(Lnet/minecraft/entity/Entity;DDDFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/render/entity/EntityRenderer;)V",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/entity/state/EntityRenderState;invisible:Z")
    )
    private boolean shouldHideInvisibleEntitiesInDebug(EntityRenderState instance) {
        if (IJMTweaksConfig.get().debugInvisibleEntities) {
            return instance.invisible && !Permission.hasOptionRequirement(OptionRequirement.NON_SURVIVAL_OP);
        }

        return instance.invisible;
    }

}
