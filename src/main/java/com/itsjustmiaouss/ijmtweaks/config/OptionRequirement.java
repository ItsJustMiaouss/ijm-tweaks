package com.itsjustmiaouss.ijmtweaks.config;

import com.itsjustmiaouss.ijmtweaks.IJMTweaks;
import com.itsjustmiaouss.ijmtweaks.permission.Permission;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

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
    NON_SURVIVAL("nonSurvival", Formatting.RED),

    /**
     * Only OPs players in a non survival-like gamemode.
     */
    NON_SURVIVAL_OP("nonSurvivalOp", Formatting.RED);

    private final String translationKey;
    private final Formatting[] formattings;

    OptionRequirement(String translationKey, Formatting... formattings) {
        this.translationKey = translationKey;
        this.formattings = formattings;
    }

    public Text getText() {
        return Text.translatable(
                String.format("option.%s.description.%s", IJMTweaks.MOD_ID, this.translationKey)
        ).formatted(this.formattings);
    }
}
