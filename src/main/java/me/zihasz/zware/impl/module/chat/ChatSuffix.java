package me.zihasz.zware.impl.module.chat;

import me.zihasz.zware.ZWare;
import me.zihasz.zware.api.event.ForgeEvents;
import me.zihasz.zware.api.event.events.PacketEvent;
import me.zihasz.zware.api.module.Module;
import me.zihasz.zware.api.setting.Setting;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatSuffix extends Module {
    Setting<ChatSuffixMode> mode = register(new Setting<ChatSuffixMode>("Mode", "Type of suffix", ChatSuffixMode.UNICODE));

    public ChatSuffix() {
        super("ChatSuffix", "Appends a suffix to your chat messages", Category.CHAT);
    }

    @SubscribeEvent
    public void onPacketSend(ClientChatEvent event) {
        String msg = event.getOriginalMessage();

        switch (mode.getValue()) {
            case NORMAL:
                msg += " > ZWare";
                break;

            case UNICODE:
                msg += " > " + toUnicode("ZWare");
                break;
        }

        event.setMessage(msg);


    }

    public void onEnable()  {
        MinecraftForge.EVENT_BUS.register(this);
    }
    public void onDisable(){
        MinecraftForge.EVENT_BUS.unregister(this);
    }

    public enum ChatSuffixMode {
        NORMAL,
        UNICODE,
    }

    public static String toUnicode(String s) {
        return s.toLowerCase()
                .replace("a", "\u1d00")
                .replace("b", "\u0299")
                .replace("c", "\u1d04")
                .replace("d", "\u1d05")
                .replace("e", "\u1d07")
                .replace("f", "\ua730")
                .replace("g", "\u0262")
                .replace("h", "\u029c")
                .replace("i", "\u026a")
                .replace("j", "\u1d0a")
                .replace("k", "\u1d0b")
                .replace("l", "\u029f")
                .replace("m", "\u1d0d")
                .replace("n", "\u0274")
                .replace("o", "\u1d0f")
                .replace("p", "\u1d18")
                .replace("q", "\u01eb")
                .replace("r", "\u0280")
                .replace("s", "\ua731")
                .replace("t", "\u1d1b")
                .replace("u", "\u1d1c")
                .replace("v", "\u1d20")
                .replace("w", "\u1d21")
                .replace("x", "\u02e3")
                .replace("y", "\u028f")
                .replace("z", "\u1d22");
    }
}
