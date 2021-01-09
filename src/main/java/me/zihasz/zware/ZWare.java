package me.zihasz.zware;

import akka.io.Tcp;
import me.yagel15637.blitz.dispatcher.EventDispatcher;
import me.yagel15637.venture.manager.CommandManager;
import me.zihasz.zware.api.event.ForgeEvents;
import me.zihasz.zware.api.module.ModuleManager;
import me.zihasz.zware.impl.command.*;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(modid = ZWare.MOD_ID, name = ZWare.MOD_NAME, version = ZWare.VERSION)
public class ZWare {
    public static final String MOD_ID = "zware";
    public static final String MOD_NAME = "ZWare";
    public static final String VERSION = "1.0-SNAPSHOT";

    public Minecraft mc = Minecraft.getMinecraft();

    public static ForgeEvents forgeEvents;
    public static ModuleManager moduleManager;

    /**
     * This is the instance of your mod as created by Forge. It will never be null, and you can use it for non-static variables.
     */
    @Mod.Instance(MOD_ID)
    public static ZWare INSTANCE;

    public static final EventDispatcher EVENT_BUS = new EventDispatcher();

    /**
     * This is the first initialization event. Load mixins and libraries here.
     */
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        CommandManager.addCommands(
                new ExampleCommand()
        );
        //CommandManager.ignoresCases = false; use this if you hate your users :D
    }

    /**
     * This is the second initialization event. Load modules and config here.
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        forgeEvents = new ForgeEvents();
        moduleManager = new ModuleManager();
    }

    /**
     * This is the final initialization event. Setup RPC and other artificial things here.
     */
    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {

    }
}
