package me.zihasz.zware.impl.module.client;

import me.zihasz.zware.api.module.Module;
import me.zihasz.zware.impl.gui.clickgui.ClickGUI;
import org.lwjgl.input.Keyboard;

public class ClickGUIModule extends Module {
    public ClickGUIModule() {
        super("ClickGUI", "The ClickGUI for the client.", Category.CLIENT);
        setBind(Keyboard.KEY_O);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        mc.displayGuiScreen(ClickGUI.INSTANCE);
        disable();
    }
}