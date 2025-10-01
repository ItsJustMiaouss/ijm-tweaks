package com.itsjustmiaouss.ijmtweaks.keybind;


import com.itsjustmiaouss.ijmtweaks.IJMTweaks;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class IJMTweaksBindings {

    public static KeyBinding zoomKeyBinding;
    public static KeyBinding openConfigBinding;
    public static KeyBinding fullbrightKeyBinding;

    private static final KeyBinding.Category IJMTWEAKS_CATEGORY = KeyBinding.Category.create(Identifier.of(IJMTweaks.MOD_ID, "ijmteaks"));

    public static void registerBindings() {
        zoomKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.ijmtweaks.zoom",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                IJMTWEAKS_CATEGORY
        ));

        openConfigBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.ijmtweaks.config",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                IJMTWEAKS_CATEGORY
        ));

        fullbrightKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.ijmtweaks.fullbright",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                IJMTWEAKS_CATEGORY
        ));
    }

}
