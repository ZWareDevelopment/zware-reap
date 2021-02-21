package me.zihasz.zware;

import club.minnced.discord.rpc.DiscordRPC;;
import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRichPresence;

public class ZWareRPC {
    public static final String APP_ID = "748610552569397309";
    public static final String STEAM_ID = "";
    public static DiscordRichPresence presence;

    private static final DiscordRPC rpc;
    private static Thread thread;    
    private static boolean started = false;

    static {
        rpc = DiscordRPC.INSTANCE;
        presence = new DiscordRichPresence();
    }

    public static void start() {
        if (started) return;
        ZWare.LOG.info("Starting RPC!");

        DiscordEventHandlers handlers = new DiscordEventHandlers();
        rpc.Discord_Initialize(APP_ID, handlers, true, STEAM_ID);

        ZWareRPC.presence.startTimestamp = System.currentTimeMillis() / 1000L;
        ZWareRPC.presence.state = "ZWare owns all!";
        ZWareRPC.presence.details = "discord.gg/jctEczt";
        ZWareRPC.presence.smallImageKey = "small";
        ZWareRPC.presence.largeImageKey = "large";
        ZWareRPC.presence.smallImageText = "idk";
        ZWareRPC.presence.largeImageText = ZWare.MOD_NAME + " " + ZWare.VERSION;

        rpc.Discord_UpdatePresence(presence);

        thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                rpc.Discord_RunCallbacks();
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException ignored) {}
            }
        }, "RPC-Callback-Handler");
        thread.start();
    }

    public static void stop() {
        if (!started) return;
        ZWare.LOG.info("Stopping RPC!");

        if (thread != null && !thread.isInterrupted()) {
            thread.interrupt();
        }
        rpc.Discord_Shutdown();
    }
}
