package me.zihasz.zware.api.event;

import me.zihasz.zware.ZWare;
import me.zihasz.zware.api.module.Module;
import me.zihasz.zware.api.module.ModuleManager;
import net.minecraftforge.client.event.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

public class ForgeEvents {
    public ForgeEvents() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    // Key Input Event
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

    // On Tick Event
    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        ZWare.moduleManager.onUpdate();
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post event) {
        // ZWare.EVENT_BUS.dispatch(event);
        if(event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
            ZWare.moduleManager.onRender();
        }
    }
}
