package me.zihasz.zware;

import club.cafedevelopment.blitz.dispatcher.EventDispatcher;
import me.zihasz.zware.api.module.ModuleManager;
import me.yagel15637.venture.manager.CommandManager;
import me.zihasz.zware.api.event.ForgeEvents;
import me.zihasz.zware.api.mixin.MixinLoader;
import me.zihasz.zware.impl.command.*;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
//import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.opengl.Display;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = ZWare.MOD_ID, name = ZWare.MOD_NAME, version = ZWare.VERSION)
public class ZWare {
    public static final String MOD_ID = "zware";
    public static final String MOD_NAME = "ZWare";
    public static final String VERSION = "1.0-SNAPSHOT";
    public static String commandsPrefix = ".";

    public static final Logger LOG = LogManager.getLogger("ZWARE");
    public static ModuleManager moduleManager;
    public static MixinLoader mixinLoader;
    public static ForgeEvents forgeEvents;

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
        mixinLoader = new MixinLoader();
        CommandManager.addCommands(
                // new ExampleCommand()
                new ToggleCommand(),
                new BindCommand(),
                new PrefixCommand(),
                new SearchCommand(),
                new PornCommand()
        );
    }

    /**
     * This is the second initialization event. Load modules and config here.
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Display.setTitle(MOD_NAME + " " + VERSION);
        moduleManager = new ModuleManager();
        forgeEvents = new ForgeEvents();
    }


    /**
     * This is the final initialization event. Setup RPC and other artificial things here.
     */
   /* @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {
        Display.setTitle(MOD_NAME + " " + VERSION);
    }*/
}
