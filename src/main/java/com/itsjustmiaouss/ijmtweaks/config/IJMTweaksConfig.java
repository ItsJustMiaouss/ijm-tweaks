package com.itsjustmiaouss.ijmtweaks.config;

import com.google.gson.GsonBuilder;
import com.itsjustmiaouss.ijmtweaks.IJMTweaks;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.*;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
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
    private static final ConfigClassHandler<IJMTweaksConfig> HANDLER = ConfigClassHandler.createBuilder(IJMTweaksConfig.class)
            .id(new Identifier(IJMTweaks.MOD_ID, "ijmtweaks"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve(IJMTweaks.MOD_ID + ".json"))
                    .appendGsonBuilder(GsonBuilder::setPrettyPrinting)
                    .setJson5(false)
                    .build())
            .build();

    public static void load() {
        HANDLER.load();
    }

    public static void save() {
        HANDLER.save();
    }

    public static IJMTweaksConfig get() {
        return HANDLER.instance();
    }

    @SerialEntry public boolean darkLoadingOverlay = true;
    @SerialEntry public float pumpkinOverlayOpacity = 0.4f;
    @SerialEntry public int blockBreakParticle = 0;
    @SerialEntry public boolean experienceBarInCreative = true;
    @SerialEntry public boolean autoJumpOnStairs = true;
    @SerialEntry public double zoomLevel = 0.3;

    public enum FireOverlayType implements NameableEnum {
        DEFAULT,
        REDUCE,
        HIDE;

        @Override
        public Text getDisplayName() {
            return Text.translatable(String.format("enum.%s.fireOverlayType.%s", IJMTweaks.MOD_ID, this.name().toLowerCase()));
        }
    }

    @SerialEntry public FireOverlayType fireOverlay = FireOverlayType.REDUCE;

    public static YetAnotherConfigLib getScreen() {
        return YetAnotherConfigLib.create(HANDLER, ((defaults, config, builder) -> {
            Option<Boolean> darkLoadingScreenOpt = Option.<Boolean>createBuilder()
                    .name(IJMTweaksConfig.getOptionName("darkLoadingOverlay"))
                    .description(OptionDescription.createBuilder().
                            text(IJMTweaksConfig.getDesc("darkLoadingOverlay"))
                            .image(IJMTweaksConfig.getImage("dark_overlay"), IMG_WIDTH, IMG_HEIGHT).build())
                    .binding(defaults.darkLoadingOverlay,
                            () -> config.darkLoadingOverlay,
                            newVal -> config.darkLoadingOverlay = newVal)
                    .flag(OptionFlag.ASSET_RELOAD)
                    .controller(opt -> BooleanControllerBuilder.create(opt).trueFalseFormatter())
                    .build();

            Option<Float> pumpkinOverlayOpacityOpt = Option.<Float>createBuilder()
                    .name(IJMTweaksConfig.getOptionName("pumpkinOverlayOpacity"))
                    .description(OptionDescription.createBuilder().
                            text(IJMTweaksConfig.getDesc("pumpkinOverlayOpacity"))
                            .image(IJMTweaksConfig.getImage("pumpkin_overlay"), IMG_WIDTH, IMG_HEIGHT).build())
                    .binding(defaults.pumpkinOverlayOpacity,
                            () -> config.pumpkinOverlayOpacity,
                            newVal -> config.pumpkinOverlayOpacity = newVal)
                    .controller(opt -> FloatSliderControllerBuilder.create(opt).range(0f, 1f).step(0.1f))
                    .build();

            Option<Integer> blockBreakParticleScaleOpt = Option.<Integer>createBuilder()
                    .name(IJMTweaksConfig.getOptionName("blockBreakParticle"))
                    .description(OptionDescription.createBuilder().
                            text(IJMTweaksConfig.getDesc("blockBreakParticle"))
                            .image(IJMTweaksConfig.getImage("break_particles"), IMG_WIDTH, IMG_HEIGHT).build())
                    .binding(defaults.blockBreakParticle,
                            () -> config.blockBreakParticle,
                            newVal -> config.blockBreakParticle = newVal)
                    .controller(opt -> IntegerSliderControllerBuilder.create(opt).range(0, 4).step(1))
                    .build();

            Option<Boolean> experienceBarInCreativeOpt = Option.<Boolean>createBuilder()
                    .name(IJMTweaksConfig.getOptionName("experienceBarInCreative"))
                    .description(OptionDescription.createBuilder()
                            .text(IJMTweaksConfig.getDesc("experienceBarInCreative"))
                            .image(IJMTweaksConfig.getImage("experience_bar"), IMG_WIDTH, IMG_HEIGHT).build())
                    .binding(defaults.experienceBarInCreative,
                            () -> config.experienceBarInCreative,
                            newVal -> config.experienceBarInCreative = newVal)
                    .controller(opt -> BooleanControllerBuilder.create(opt).trueFalseFormatter())
                    .build();

            Option<Boolean> autoJumpOnStairsOpt = Option.<Boolean>createBuilder()
                    .name(IJMTweaksConfig.getOptionName("autoJumpOnStairs"))
                    .description(OptionDescription.createBuilder()
                            .text(IJMTweaksConfig.getDesc("autoJumpOnStairs")).build())
                    .binding(defaults.autoJumpOnStairs,
                            () -> config.autoJumpOnStairs,
                            newVal -> config.autoJumpOnStairs = newVal)
                    .controller(opt -> BooleanControllerBuilder.create(opt).trueFalseFormatter())
                    .build();

            Option<Double> zoomLevelOpt = Option.<Double>createBuilder()
                    .name(IJMTweaksConfig.getOptionName("zoomLevel"))
                    .description(OptionDescription.createBuilder().
                            text(IJMTweaksConfig.getDesc("zoomLevel"))
                            .image(IJMTweaksConfig.getImage("zoom_level"), IMG_WIDTH, IMG_HEIGHT).build())
                    .binding(defaults.zoomLevel,
                            () -> config.zoomLevel,
                            newVal -> config.zoomLevel = newVal)
                    .controller(opt -> DoubleSliderControllerBuilder.create(opt).range(0.1, 0.9).step(0.1))
                    .build();

            Option<FireOverlayType> fireOverlayOpt = Option.<FireOverlayType>createBuilder()
                    .name(IJMTweaksConfig.getOptionName("fireOverlay"))
                    .description(OptionDescription.createBuilder().
                            text(IJMTweaksConfig.getDesc("fireOverlay"))
                            .image(IJMTweaksConfig.getImage("fire_overlay"), IMG_WIDTH, IMG_HEIGHT)
                            .build())
                    .binding(defaults.fireOverlay,
                            () -> config.fireOverlay,
                            newVal -> config.fireOverlay = newVal)
                    .controller(opt -> EnumControllerBuilder.create(opt).enumClass(IJMTweaksConfig.FireOverlayType.class))
                    .build();

            return builder.title(Text.of(IJMTweaks.MOD_DISPLAY_NAME))
                    .category(ConfigCategory.createBuilder()
                            .name(IJMTweaksConfig.getCategoryName("overlay"))
                            .options(List.of(
                                    darkLoadingScreenOpt,
                                    pumpkinOverlayOpacityOpt,
                                    fireOverlayOpt
                            ))
                            .build())
                    .category(ConfigCategory.createBuilder()
                            .name(IJMTweaksConfig.getCategoryName("utility"))
                            .options(List.of(
                                    experienceBarInCreativeOpt,
                                    autoJumpOnStairsOpt,
                                    zoomLevelOpt
                            ))
                            .build())
                    .category(ConfigCategory.createBuilder()
                            .name(IJMTweaksConfig.getCategoryName("rendering"))
                            .options(List.of(
                                    blockBreakParticleScaleOpt
                            ))
                            .build())
                    .save(IJMTweaksConfig::save);
        }));
    }

    private static Text getCategoryName(String category) {
        return Text.translatable(String.format("category.%s.%s", IJMTweaks.MOD_ID, category));
    }

    private static Text getOptionName(String option) {
        return Text.translatable(String.format("option.%s.%s.name", IJMTweaks.MOD_ID, option));
    }

    private static Text getDesc(String option) {
        return Text.translatable(String.format("option.%s.%s.desc", IJMTweaks.MOD_ID, option));
    }

    private static Identifier getImage(String name) {
        return new Identifier(IJMTweaks.MOD_ID, String.format("config/%s.png", name));
    }
}
