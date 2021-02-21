package me.zihasz.zware.api.event.events;

import club.cafedevelopment.blitz.event.Event;

import club.cafedevelopment.blitz.modifiers.EventEra;
import net.minecraft.network.Packet;

public class PacketEvent extends Event {
    private final Packet packet;

    public PacketEvent(EventEra era, Packet packet) {
        super(era);
        this.packet = packet;
    }

    public Packet getPacket() {
        return packet;
    }
    public static class Send extends PacketEvent{
        public Send(EventEra era, Packet packet) {
            super(era, packet);
        }
    }

    public static class Receive extends PacketEvent {
        public Receive(EventEra era, Packet packet) {
            super(era, packet);
        }
    }
}
