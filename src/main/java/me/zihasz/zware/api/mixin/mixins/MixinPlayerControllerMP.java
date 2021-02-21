package me.zihasz.zware.api.mixin.mixins;

import club.cafedevelopment.blitz.modifiers.EventEra;
import me.zihasz.zware.ZWare;
import me.zihasz.zware.api.event.events.BlockClickEvent;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.util.EnumFacing;
import org.spongepowered.asm.mixin.injection.At;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ PlayerControllerMP.class })
public class MixinPlayerControllerMP {

    @Inject(method = { "clickBlock" }, at = { @At("HEAD") }, cancellable = true)
    public void onClickBlock(final BlockPos posBlock, final EnumFacing directionFacing, final CallbackInfoReturnable info) {
        final BlockClickEvent eventClickBlock = new BlockClickEvent(EventEra.POST,posBlock, directionFacing);
        ZWare.EVENT_BUS.dispatch(eventClickBlock);
        if (eventClickBlock.isCancelled()) {
            info.cancel();
        }
    }

}
