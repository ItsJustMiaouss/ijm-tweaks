package com.itsjustmiaouss.ijmtweaks.mixin;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.entity.Entity;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
@Environment(EnvType.CLIENT)
public abstract class StairsBlockMixin {

    @Inject(method = "onSteppedOn", at = @At("HEAD"))
    private void toggleAutoJump(World world, BlockPos pos, BlockState state, Entity entity, CallbackInfo ci) {
        if(!world.isClient()) return;

        IJMTweaksConfig config = IJMTweaksConfig.get();
        if(!config.autoJumpOnStairs) return;

        Block block = world.getBlockState(pos).getBlock();
        SimpleOption<Boolean> autoJump = MinecraftClient.getInstance().options.getAutoJump();

        if(block instanceof StairsBlock) {
            if(state.get(Properties.WATERLOGGED)) return;
            if(state.get(Properties.BLOCK_HALF).equals(BlockHalf.TOP)) return; // Is the block upside down

            Direction facing = state.get(HorizontalFacingBlock.FACING);
            BlockPos offsetPos = pos.offset(facing).up();
            Block upperBlock = world.getBlockState(offsetPos).getBlock();

            // If the next upper block isn't a stair
            if(!(upperBlock instanceof StairsBlock)) return;
        }

        autoJump.setValue(block instanceof StairsBlock);
    }

}
