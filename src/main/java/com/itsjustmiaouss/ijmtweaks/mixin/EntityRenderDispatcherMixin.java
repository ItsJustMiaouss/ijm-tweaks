package com.itsjustmiaouss.ijmtweaks.mixin;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import com.itsjustmiaouss.ijmtweaks.config.OptionRequirement;
import com.itsjustmiaouss.ijmtweaks.permission.Permission;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.entity.Entity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityRenderer.class)
public abstract class EntityRenderDispatcherMixin<T extends Entity, S extends EntityRenderState> {

    @Redirect(
            method = "updateRenderState",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/entity/state/EntityRenderState;invisible:Z", opcode = Opcodes.GETFIELD)
    )
    private boolean shouldHideInvisibleEntitiesInDebug(EntityRenderState instance) {
        if (IJMTweaksConfig.get().debugInvisibleEntities) {
            return instance.invisible && !Permission.hasOptionRequirement(OptionRequirement.NON_SURVIVAL_OP);
        }

        return instance.invisible;
    }

}
