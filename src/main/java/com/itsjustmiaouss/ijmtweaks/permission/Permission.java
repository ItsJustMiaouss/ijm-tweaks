package com.itsjustmiaouss.ijmtweaks.permission;

import com.itsjustmiaouss.ijmtweaks.config.OptionRequirement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;

public class Permission {

    private static final MinecraftClient instance = MinecraftClient.getInstance();

    private static boolean isSurvivalLike() {
        ClientPlayerInteractionManager interactionManager = instance.interactionManager;
        return interactionManager == null || interactionManager.getCurrentGameMode().isSurvivalLike();
    }

    /**
     * Check if the player has the required permissions.
     * @see OptionRequirement
     */
    public static boolean hasOptionRequirement(OptionRequirement requirement) {
        if (instance.isInSingleplayer()) return true;

        ClientPlayerEntity player = instance.player;
        if (player == null) return false;

        return switch (requirement) {
            case NON_SURVIVAL -> !isSurvivalLike();
            case NON_SURVIVAL_OP -> !isSurvivalLike() && player.getAbilities().creativeMode;
        };
    }

}
