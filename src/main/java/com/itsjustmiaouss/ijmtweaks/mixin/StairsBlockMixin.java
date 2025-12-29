package com.itsjustmiaouss.ijmtweaks.mixin;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
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
    private void toggleAutoJump(Level world, BlockPos pos, BlockState state, Entity entity, CallbackInfo ci) {
        if(!world.isClientSide()) return;

        IJMTweaksConfig config = IJMTweaksConfig.get();
        if(!config.autoJumpOnStairs) return;

        Block block = world.getBlockState(pos).getBlock();
        OptionInstance<Boolean> autoJump = Minecraft.getInstance().options.autoJump();

        if(block instanceof StairBlock) {
            if(state.getValue(BlockStateProperties.WATERLOGGED)) return;
            if(state.getValue(BlockStateProperties.HALF).equals(Half.TOP)) return; // Is the block upside down

            Direction facing = state.getValue(HorizontalDirectionalBlock.FACING);
            BlockPos offsetPos = pos.relative(facing).above();
            Block upperBlock = world.getBlockState(offsetPos).getBlock();

            // If the next upper block isn't a stair
            if(!(upperBlock instanceof StairBlock)) return;
        }

        autoJump.set(block instanceof StairBlock);
    }

}
