package com.itsjustmiaouss.ijmtweaks.config;

import com.google.gson.GsonBuilder;
import com.itsjustmiaouss.ijmtweaks.IJMTweaks;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.EnumControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

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
    @SerialEntry public int pumpkinOverlayOpacity = 40;
    @SerialEntry public int blockBreakParticle = 0;
    @SerialEntry public boolean experienceBarInCreative = false;
    @SerialEntry public boolean autoJumpOnStairs = true;
    @SerialEntry public int zoomLevel = 70;
    @SerialEntry public boolean singleItemInventorySwap = false;
    @SerialEntry public boolean screenshotsFolder = true;
    @SerialEntry public boolean debugInvisibleEntities = false;
    @SerialEntry public boolean fullbright = false;

    public enum FireOverlayType implements NameableEnum {
        DEFAULT,
        REDUCED,
        HIDDEN;

        @Override
        public Text getDisplayName() {
            return Text.translatable(String.format("enum.%s.fireOverlayType.%s", IJMTweaks.MOD_ID, this.name().toLowerCase()));
        }
    }

    @SerialEntry public FireOverlayType fireOverlay = FireOverlayType.REDUCED;

    public static YetAnotherConfigLib getScreen() {
        return YetAnotherConfigLib.create(HANDLER, ((defaults, config, builder) -> {
            // Config Options
            Option<Boolean> darkLoadingScreenOpt = IJMTweaksConfig.<Boolean>getGenericOption("darkLoadingOverlay", "dark_overlay")
                    .binding(defaults.darkLoadingOverlay,
                            () -> config.darkLoadingOverlay,
                            newVal -> config.darkLoadingOverlay = newVal)
                    .flag(OptionFlag.ASSET_RELOAD)
                    .controller(opt -> BooleanControllerBuilder.create(opt).trueFalseFormatter())
                    .build();

            Option<Integer> pumpkinOverlayOpacityOpt = IJMTweaksConfig.<Integer>getGenericOption("pumpkinOverlayOpacity", "pumpkin_overlay")
                    .binding(defaults.pumpkinOverlayOpacity,
                            () -> config.pumpkinOverlayOpacity,
                            newVal -> config.pumpkinOverlayOpacity = newVal)
                    .controller(opt -> IntegerSliderControllerBuilder.create(opt)
                            .range(0, 100).step(10).formatValue(value -> Text.literal(value + "%")))
                    .build();

            Option<Integer> blockBreakParticleScaleOpt = IJMTweaksConfig.<Integer>getGenericOption("blockBreakParticle", "break_particles")
                    .binding(defaults.blockBreakParticle,
                            () -> config.blockBreakParticle,
                            newVal -> config.blockBreakParticle = newVal)
                    .controller(opt -> IntegerSliderControllerBuilder.create(opt).range(0, 4).step(1))
                    .build();

            Option<Boolean> experienceBarInCreativeOpt = IJMTweaksConfig.<Boolean>getGenericOption("experienceBarInCreative", "experience_bar")
                    .binding(defaults.experienceBarInCreative,
                            () -> config.experienceBarInCreative,
                            newVal -> config.experienceBarInCreative = newVal)
                    .controller(opt -> BooleanControllerBuilder.create(opt).trueFalseFormatter())
                    .build();

            Option<Boolean> autoJumpOnStairsOpt = IJMTweaksConfig.<Boolean>getGenericOption("autoJumpOnStairs", "auto_jump")
                    .binding(defaults.autoJumpOnStairs,
                            () -> config.autoJumpOnStairs,
                            newVal -> config.autoJumpOnStairs = newVal)
                    .controller(opt -> BooleanControllerBuilder.create(opt).trueFalseFormatter())
                    .build();

            Option<Integer> zoomLevelOpt = IJMTweaksConfig.<Integer>getGenericOption("zoomLevel", "zoom_level")
                    .binding(defaults.zoomLevel,
                            () -> config.zoomLevel,
                            newVal -> config.zoomLevel = newVal)
                    .controller(opt -> IntegerSliderControllerBuilder.create(opt)
                            .range(0, 100).step(10).formatValue(value -> Text.literal(value + "%")))
                    .build();

            Option<FireOverlayType> fireOverlayOpt = IJMTweaksConfig.<FireOverlayType>getGenericOption("fireOverlay", "fire_overlay")
                    .binding(defaults.fireOverlay,
                            () -> config.fireOverlay,
                            newVal -> config.fireOverlay = newVal)
                    .controller(opt -> EnumControllerBuilder.create(opt).enumClass(IJMTweaksConfig.FireOverlayType.class))
                    .build();

            Option<Boolean> singleItemInventorySwapOpt = IJMTweaksConfig.<Boolean>getGenericOption("singleItemInventorySwap", "inventory_swap")
                    .binding(defaults.singleItemInventorySwap,
                            () -> config.singleItemInventorySwap,
                            newVal -> config.singleItemInventorySwap = newVal)
                    .controller(opt -> BooleanControllerBuilder.create(opt).trueFalseFormatter())
                    .build();

            Option<Boolean> screenshotsFolderOpt = IJMTweaksConfig.<Boolean>getGenericOption("screenshotsFolder", "screenshots_folder")
                    .binding(defaults.screenshotsFolder,
                            () -> config.screenshotsFolder,
                            newVal -> config.screenshotsFolder = newVal)
                    .controller(opt -> BooleanControllerBuilder.create(opt).trueFalseFormatter())
                    .build();

            Option<Boolean> debugInvisibleEntitiesOpt = IJMTweaksConfig.<Boolean>getGenericOption(
                    "debugInvisibleEntities", "debug_invisible_entities", OptionRequirement.NON_SURVIVAL_OP
                    )
                    .binding(defaults.debugInvisibleEntities,
                            () -> config.debugInvisibleEntities,
                            newVal -> config.debugInvisibleEntities = newVal)
                    .controller(opt -> BooleanControllerBuilder.create(opt).trueFalseFormatter())
                    .build();

            Option<Boolean> fullbrightOpt = IJMTweaksConfig.<Boolean>getGenericOption(
                    "fullbright", "fullbright", OptionRequirement.NON_SURVIVAL
                    )
                    .binding(defaults.fullbright,
                            () -> config.fullbright,
                            newVal -> config.fullbright = newVal)
                    .controller(opt -> BooleanControllerBuilder.create(opt).trueFalseFormatter())
                    .build();

            return builder.title(Text.of(IJMTweaks.MOD_DISPLAY_NAME))
                    // Screen
                    .category(ConfigCategory.createBuilder()
                            .name(IJMTweaksConfig.getCategoryName("screen"))
                            .group(OptionGroup.createBuilder()
                                    .name(IJMTweaksConfig.getGroupName("overlay"))
                                    .options(List.of(
                                            darkLoadingScreenOpt,
                                            pumpkinOverlayOpacityOpt,
                                            fireOverlayOpt
                                    ))
                                    .build())
                            .build())

                    // Utility
                    .category(ConfigCategory.createBuilder()
                            .name(IJMTweaksConfig.getCategoryName("utility"))
                            .group(OptionGroup.createBuilder()
                                    .name(IJMTweaksConfig.getGroupName("general"))
                                    .options(List.of(
                                            zoomLevelOpt,
                                            screenshotsFolderOpt
                                    ))
                                    .build())
                            .group(OptionGroup.createBuilder()
                                    .name(IJMTweaksConfig.getGroupName("movement"))
                                    .options(List.of(
                                            autoJumpOnStairsOpt
                                    ))
                                    .build())
                            .group(OptionGroup.createBuilder()
                                    .name(IJMTweaksConfig.getGroupName("creative"))
                                    .options(List.of(
                                            experienceBarInCreativeOpt,
                                            singleItemInventorySwapOpt
                                    ))
                                    .build())
                            .build())

                    // Visual
                    .category(ConfigCategory.createBuilder()
                            .name(IJMTweaksConfig.getCategoryName("visual"))
                            .group(OptionGroup.createBuilder()
                                    .name(IJMTweaksConfig.getGroupName("rendering"))
                                    .options(List.of(
                                            blockBreakParticleScaleOpt,
                                            fullbrightOpt
                                    ))
                                    .build())
                            .group(OptionGroup.createBuilder()
                                    .name(IJMTweaksConfig.getGroupName("debug"))
                                    .options(List.of(
                                            debugInvisibleEntitiesOpt
                                    ))
                                    .build())
                            .build())
                    .save(IJMTweaksConfig::save);
        }));
    }

    private static <T> Option.Builder<T> getGenericOption(String name, String image) {
        return getGenericOption(name, image, null);
    }

    private static <T> Option.Builder<T> getGenericOption(String name, String image, @Nullable OptionRequirement requirement) {
        return Option.<T>createBuilder()
                .name(IJMTweaksConfig.getOptionName(name))
                .description(OptionDescription.createBuilder()
                        .text(IJMTweaksConfig.getDesc(name, requirement))
                        .image(IJMTweaksConfig.getImage(image), IMG_WIDTH, IMG_HEIGHT)
                        .build());
    }

    private static Text getCategoryName(String category) {
        return Text.translatable(String.format("category.%s.%s", IJMTweaks.MOD_ID, category));
    }

    private static Text getGroupName(String group) {
        return Text.translatable(String.format("group.%s.%s.name", IJMTweaks.MOD_ID, group));
    }

    private static Text getOptionName(String option) {
        return Text.translatable(String.format("option.%s.%s.name", IJMTweaks.MOD_ID, option));
    }

    private static Text getDesc(String option, @Nullable OptionRequirement requirement) {
        MutableText text = Text.translatable(String.format("option.%s.%s.desc", IJMTweaks.MOD_ID, option));
        if (requirement != null) text.append(Text.literal("\n").append(requirement.getText()));
        return text;
    }

    private static Identifier getImage(String name) {
        return new Identifier(IJMTweaks.MOD_ID, String.format("config/%s.png", name));
    }
}
