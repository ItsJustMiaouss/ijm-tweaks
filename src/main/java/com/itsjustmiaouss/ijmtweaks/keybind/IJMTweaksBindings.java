package com.itsjustmiaouss.ijmtweaks.keybind;

import com.itsjustmiaouss.ijmtweaks.IJMTweaks;
import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class IJMTweaksBindings {

    public static KeyMapping zoomKeyBinding;
    public static KeyMapping openConfigBinding;
    public static KeyMapping fullbrightKeyBinding;

    private static final KeyMapping.Category IJMTWEAKS_CATEGORY = KeyMapping.Category.register(Identifier.fromNamespaceAndPath(IJMTweaks.MOD_ID, "bindings"));

    public static void registerBindings() {
        zoomKeyBinding = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                "key.ijmtweaks.zoom",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_C,
                IJMTWEAKS_CATEGORY
        ));

        openConfigBinding = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                "key.ijmtweaks.config",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                IJMTWEAKS_CATEGORY
        ));

        fullbrightKeyBinding = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                "key.ijmtweaks.fullbright",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                IJMTWEAKS_CATEGORY
        ));
    }

}
