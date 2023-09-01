package com.itsjustmiaouss.ijmtweaks.config;

import com.google.gson.GsonBuilder;
import com.itsjustmiaouss.ijmtweaks.IJMTweaks;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.FloatSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import dev.isxander.yacl3.config.ConfigEntry;
import dev.isxander.yacl3.config.GsonConfigInstance;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

/**
 * The code structure was inspired by <a href="https://github.com/Minenash">Minenash</a> for the
 * <a href="https://github.com/Minenash/Seamless-Loading-Screen">Seamless Loading Screen</a> mod, and from
 * the YACL's documentation.
 */
public class IJMTweaksConfig {

    public static final int IMG_WIDTH = 1920;
    public static final int IMG_HEIGHT = 1080;

    private static final GsonConfigInstance<IJMTweaksConfig> INSTANCE = GsonConfigInstance.createBuilder(IJMTweaksConfig.class)
            .overrideGsonBuilder(new GsonBuilder()
                    .setPrettyPrinting()
                    .create())
            .setPath(FabricLoader.getInstance().getConfigDir().resolve(IJMTweaks.MOD_ID + ".json"))
            .build();

    public static void load() {
        INSTANCE.load();
    }

    public static void save() {
        INSTANCE.save();
    }

    public static IJMTweaksConfig get() {
        return INSTANCE.getConfig();
    }

    @ConfigEntry public boolean darkLoadingOverlay = true;
    @ConfigEntry public float pumpkinOverlayOpacity = 0.4f;
    @ConfigEntry public int blockBreakParticle = 0;
    @ConfigEntry public boolean experienceBarInCreative = true;
    @ConfigEntry public boolean autoJumpOnStairs = true;

    public static YetAnotherConfigLib getScreen() {
        return YetAnotherConfigLib.create(INSTANCE, ((defaults, config, builder) -> {
            Option<Boolean> darkLoadingScreenOpt = Option.<Boolean>createBuilder()
                    .name(getName("darkLoadingOverlay"))
                    .description(OptionDescription.createBuilder().
                            text(getDesc("darkLoadingOverlay"))
                            .image(getImage("dark_overlay"), IMG_WIDTH, IMG_HEIGHT).build())
                    .binding(defaults.darkLoadingOverlay,
                            () -> config.darkLoadingOverlay,
                            newVal -> config.darkLoadingOverlay = newVal)
                    .flag(OptionFlag.ASSET_RELOAD)
                    .controller(opt -> BooleanControllerBuilder.create(opt).trueFalseFormatter())
                    .build();

            Option<Float> pumpkinOverlayOpacityOpt = Option.<Float>createBuilder()
                    .name(getName("pumpkinOverlayOpacity"))
                    .description(OptionDescription.createBuilder().
                            text(getDesc("pumpkinOverlayOpacity"))
                            .image(getImage("pumpkin_overlay"), IMG_WIDTH, IMG_HEIGHT).build())
                    .binding(defaults.pumpkinOverlayOpacity,
                            () -> config.pumpkinOverlayOpacity,
                            newVal -> config.pumpkinOverlayOpacity = newVal)
                    .controller(opt -> FloatSliderControllerBuilder.create(opt).range(0f, 1f).step(0.1f))
                    .build();

            Option<Integer> blockBreakParticleScaleOpt = Option.<Integer>createBuilder()
                    .name(getName("blockBreakParticle"))
                    .description(OptionDescription.createBuilder().
                            text(getDesc("blockBreakParticle"))
                            .image(getImage("break_particles"), IMG_WIDTH, IMG_HEIGHT).build())
                    .binding(defaults.blockBreakParticle,
                            () -> config.blockBreakParticle,
                            newVal -> config.blockBreakParticle = newVal)
                    .controller(opt -> IntegerSliderControllerBuilder.create(opt).range(0, 4).step(1))
                    .build();

            Option<Boolean> experienceBarInCreativeOpt = Option.<Boolean>createBuilder()
                    .name(getName("experienceBarInCreative"))
                    .description(OptionDescription.createBuilder()
                            .text(getDesc("experienceBarInCreative"))
                            .image(getImage("experience_bar"), IMG_WIDTH, IMG_HEIGHT).build())
                    .binding(defaults.experienceBarInCreative,
                            () -> config.experienceBarInCreative,
                            newVal -> config.experienceBarInCreative = newVal)
                    .controller(opt -> BooleanControllerBuilder.create(opt).trueFalseFormatter())
                    .build();

            Option<Boolean> autoJumpOnStairsOpt = Option.<Boolean>createBuilder()
                    .name(getName("autoJumpOnStairs"))
                    .description(OptionDescription.createBuilder()
                            .text(getDesc("autoJumpOnStairs")).build())
                    .binding(defaults.autoJumpOnStairs,
                            () -> config.autoJumpOnStairs,
                            newVal -> config.autoJumpOnStairs = newVal)
                    .controller(opt -> BooleanControllerBuilder.create(opt).trueFalseFormatter())
                    .build();

            return builder.title(Text.of(IJMTweaks.MOD_DISPLAY_NAME))
                    .category(ConfigCategory.createBuilder()
                        .name(Text.of("Configuration"))
                        .options(List.of(
                                darkLoadingScreenOpt,
                                pumpkinOverlayOpacityOpt,
                                blockBreakParticleScaleOpt,
                                experienceBarInCreativeOpt,
                                autoJumpOnStairsOpt
                        ))
                        .build())
                    .save(IJMTweaksConfig::save);
        }));
    }

    private static Text getName(String option) {
        return Text.translatable(String.format("option.%s.%s.name", IJMTweaks.MOD_ID, option));
    }

    private static Text getDesc(String option) {
        return Text.translatable(String.format("option.%s.%s.desc", IJMTweaks.MOD_ID, option));
    }

    private static Identifier getImage(String name) {
        return new Identifier(IJMTweaks.MOD_ID, String.format("config/%s.png", name));
    }
}
