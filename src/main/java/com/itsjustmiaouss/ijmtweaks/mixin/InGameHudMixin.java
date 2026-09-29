package com.itsjustmiaouss.ijmtweaks.mixin;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import net.minecraft.client.gui.Gui;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(Gui.class)
public abstract class InGameHudMixin {

    @ModifyArgs(method = "extractCameraOverlays", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/Gui;extractTextureOverlay(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Lnet/minecraft/resources/Identifier;F)V"
    ))
    private void modifyPumpkinOverlayOpacity(Args args) {
        Identifier texture = args.get(1);
        args.get(2);
        float alpha;

        if (texture != null && ijmtweaks$isPumpkinOverlay(texture)) {
            alpha = (float) IJMTweaksConfig.get().pumpkinOverlayOpacity / 100.0F;
            args.set(2, alpha);
        }
    }

    @Unique
    private static boolean ijmtweaks$isPumpkinOverlay(Identifier texture) {
        String path = texture.getPath();
        return path.equals("textures/misc/pumpkinblur.png")
                || path.endsWith("/pumpkinblur.png")
                || path.contains("pumpkinblur");
    }
}
