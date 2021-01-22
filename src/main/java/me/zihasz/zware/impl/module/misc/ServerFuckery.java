package me.zihasz.zware.impl.module.misc;

import me.zihasz.zware.api.module.Module;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.login.client.CPacketLoginStart;

public class ServerFuckery extends Module {
    public ServerFuckery() {
        super("ServerFuckery","Fucks with packets to confuse the server", Category.MISC);
    }

    @Override
    public void onUpdate() {
        mc.networkManager.sendPacket(new C00Handshake(
                mc.serverName,
                mc.serverPort,
                EnumConnectionState.LOGIN
        ));
    }
}
