package com.itsjustmiaouss.ijmtweaks.config;

import com.itsjustmiaouss.ijmtweaks.IJMTweaks;
import com.itsjustmiaouss.ijmtweaks.permission.Permission;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

/**
 * Used to add an informative text to the descriptions of some options.
 * Also used to check the player's permissions.
 *
 * <p>Only apply on a dedicated server.</p>
 *
 * @see Permission
 */
public enum OptionRequirement {
    /**
     * Only players in a non survival-like gamemode.
     */
    NON_SURVIVAL("nonSurvival", ChatFormatting.RED),

    /**
     * Only OPs players in a non survival-like gamemode.
     */
    NON_SURVIVAL_OP("nonSurvivalOp", ChatFormatting.RED);

    private final String translationKey;
    private final ChatFormatting[] formattings;

    OptionRequirement(String translationKey, ChatFormatting... formattings) {
        this.translationKey = translationKey;
        this.formattings = formattings;
    }

    public Component getText() {
        return Component.translatable(
                String.format("option.%s.description.%s", IJMTweaks.MOD_ID, this.translationKey)
        ).withStyle(this.formattings);
    }
}
