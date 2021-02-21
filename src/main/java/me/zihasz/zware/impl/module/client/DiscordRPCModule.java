package me.zihasz.zware.impl.module.client;

import me.zihasz.zware.ZWareRPC;
import me.zihasz.zware.api.module.Module;

public class DiscordRPCModule extends Module {
    public DiscordRPCModule() {
        super("DiscordRPC", "Enble/Disable RPC", Category.CLIENT);
    }

    @Override
    public void onEnable() {
        ZWareRPC.start();
    }

    @Override
    public void onDisable() {
        ZWareRPC.stop();
    }
}
