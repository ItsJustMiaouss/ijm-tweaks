package com.itsjustmiaouss.ijmtweaks.mixin;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Half;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
@Environment(EnvType.CLIENT)
public abstract class StairsBlockMixin {

    @Inject(method = "stepOn", at = @At("HEAD"))
    private void toggleAutoJump(Level level, BlockPos pos, BlockState state, Entity entity, CallbackInfo ci) {
        if (!level.isClientSide()) return;

        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer player = minecraft.player;
        if (player == null || entity != player) return;

        IJMTweaksConfig config = IJMTweaksConfig.get();
        if (!config.autoJumpOnStairs) return;

        OptionInstance<Boolean> autoJump = minecraft.options.autoJump();
        boolean shouldEnable = false;

        if (state.getBlock() instanceof StairBlock) {
            if (state.getValue(BlockStateProperties.WATERLOGGED)) return;

            // Is the block upside down
            if (state.getValue(BlockStateProperties.HALF) == Half.TOP) return;

            Direction facing = state.getValue(HorizontalDirectionalBlock.FACING);
            BlockPos upperPos = pos.relative(facing).above();
            BlockState upperState = level.getBlockState(upperPos);

            if (!(upperState.getBlock() instanceof StairBlock)) return;

            shouldEnable = true;
        }
        autoJump.set(shouldEnable);
    }
}
