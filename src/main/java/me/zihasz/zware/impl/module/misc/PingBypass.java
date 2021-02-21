package me.zihasz.zware.impl.module.misc;

import club.cafedevelopment.blitz.dispatcher.DispatcherEntry;
import me.zihasz.zware.api.event.events.PacketEvent;
import me.zihasz.zware.api.module.Module;
import me.zihasz.zware.api.setting.Setting;

public class PingBypass extends Module {
    Setting<ServerLocation> server = register(new Setting<ServerLocation>("ServerLocation", "Location of bypass server", ServerLocation.NA));
    Setting<Boolean> autoDetect = register(new Setting<Boolean>("AutoDetect", "Detects the best server to use", true));

    public PingBypass() {
        super("PingBypass", "Ping go 10ms", Category.MISC);
    }

    @Override
    public void onUpdate() {

        if (autoDetect.getValue()) {

        }

        switch (server.getValue()) {
            case EU:

                break;

            case UK:

                break;

            case NA:

                break;

            case SA:

                break;
        }
    }

    @DispatcherEntry
    public void onPacketReceive(PacketEvent.Receive event) {

    }

    @DispatcherEntry
    public void onPacketSend(PacketEvent.Send event) {

    }

    public ServerLocation detectServer(String ip, int port) {
        return ServerLocation.EU;
    }

    public enum ServerLocation {
        EU,
        UK,
        NA,
        SA,
    }

}