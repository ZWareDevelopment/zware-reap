package me.zihasz.zware.api.event;

import me.yagel15637.venture.manager.CommandManager;
import me.zihasz.zware.ZWare;
import me.zihasz.zware.api.module.Module;
import me.zihasz.zware.api.module.ModuleManager;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

public class ForgeEvents {
    public ForgeEvents() {
        MinecraftForge.EVENT_BUS.register(this);
        ZWare.EVENT_BUS.register(this);
    }

    // Bind
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (Keyboard.getEventKeyState()) {
            if (Keyboard.getEventKey() == Keyboard.KEY_NONE || Keyboard.getEventKey() == Keyboard.CHAR_NONE) return;
            for (Module modules : ZWare.moduleManager.getModules()) {
                if (modules.getBind() == Keyboard.getEventKey()) {
                    modules.toggle();
                }
            }

        }
    }

    // Commands
    @SubscribeEvent
    public void onChatMessage(ClientChatEvent event) {
        if (event.getMessage().startsWith(ZWare.commandsPrefix)) {
            CommandManager.parseCommand(event.getMessage().replaceFirst(ZWare.commandsPrefix, ""));
            event.setCanceled(true);
            // ZWare.mc.ingameGUI.getChatGUI().addToSentMessages(event.getMessage());
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        ZWare.moduleManager.onUpdate();
    }
}
