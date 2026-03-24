package com.itsjustmiaouss.ijmtweaks.mixin;

import com.itsjustmiaouss.ijmtweaks.config.IJMTweaksConfig;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(CreativeModeInventoryScreen.class)
public abstract class CreativeInventoryScreenMixin {

    @ModifyArg(method = "slotClicked", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/player/Inventory;setItem(ILnet/minecraft/world/item/ItemStack;)V"),
            index = 1)
    private ItemStack modifyStack(ItemStack stack) {
        IJMTweaksConfig config = IJMTweaksConfig.get();
        return stack.copyWithCount(config.singleItemInventorySwap ? 1 : stack.getMaxStackSize());
    }
}
