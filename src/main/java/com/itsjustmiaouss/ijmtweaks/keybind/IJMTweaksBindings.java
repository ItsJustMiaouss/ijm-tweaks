package com.itsjustmiaouss.ijmtweaks.keybind;


import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class IJMTweaksBindings {

    public static KeyBinding zoomKeyBinding;

    public static void registerBindings() {
        zoomKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.ijmtweaks.zoom",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                "category.ijmtweaks.bindings"
        ));
    }

}
