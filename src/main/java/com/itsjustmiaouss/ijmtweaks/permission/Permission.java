package com.itsjustmiaouss.ijmtweaks.permission;

import com.itsjustmiaouss.ijmtweaks.config.OptionRequirement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.permissions.Permissions;

public class Permission {

    private static final Minecraft instance = Minecraft.getInstance();

    private static boolean isNonSurvival() {
        MultiPlayerGameMode gm = instance.gameMode;
        return gm != null && !gm.getPlayerMode().isSurvival();
    }

    /**
     * Check if the player has the required permissions.
     * @see OptionRequirement
     */

    public static boolean hasOptionRequirement(OptionRequirement requirement) {
        if (instance.isLocalServer()) return true;

        LocalPlayer player = instance.player;
        if (player == null) return false;

        return switch (requirement) {
            case NON_SURVIVAL -> isNonSurvival();
            case NON_SURVIVAL_OP -> isNonSurvival() && player.permissions().hasPermission(Permissions.COMMANDS_ADMIN);
        };
    }

}
