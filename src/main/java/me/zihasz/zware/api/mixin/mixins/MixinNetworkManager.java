package me.zihasz.zware.api.mixin.mixins;

import me.yagel15637.blitz.modifiers.EventEra;
import me.zihasz.zware.ZWare;
import me.zihasz.zware.api.event.events.PacketEvent;

import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

@Mixin(NetworkManager.class)
public class MixinNetworkManager {

    @Inject(method = "sendPacket(Lnet/minecraft/network/Packet;)V", at = @At("HEAD"), cancellable = true)
    private void preSendPacket(Packet<?> packet, CallbackInfo callbackInfo) {
        PacketEvent.Send event = new PacketEvent.Send(EventEra.PRE, packet);
        ZWare.EVENT_BUS.dispatch(event);

        if (event.isCancelled()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method = "channelRead0", at = @At("HEAD"), cancellable = true)
    private void preChannelRead(ChannelHandlerContext context, Packet<?> packet, CallbackInfo callbackInfo) {
        PacketEvent.Receive event = new PacketEvent.Receive(EventEra.PRE, packet);
        ZWare.EVENT_BUS.dispatch(event);

        if (event.isCancelled()) {
            callbackInfo.cancel();
        }
    }

}
