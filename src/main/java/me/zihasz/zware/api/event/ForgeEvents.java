package me.zihasz.zware.api.event;

import me.zihasz.zware.api.module.Module;
import me.zihasz.zware.api.module.ModuleManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class ForgeEvents {
    public ForgeEvents() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    // Bind
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (Keyboard.getEventKeyState()) {
            if (Keyboard.getEventKey() == Keyboard.KEY_NONE || Keyboard.getEventKey() == Keyboard.CHAR_NONE) return;
            for (Module modules : ModuleManager.getModules()) {
                if (modules.getBind() == Keyboard.getEventKey()) {
                    modules.toggle();
                }
            }

        }
    }
}
