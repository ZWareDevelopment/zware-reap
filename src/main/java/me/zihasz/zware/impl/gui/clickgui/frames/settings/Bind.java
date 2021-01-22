package me.zihasz.zware.impl.gui.clickgui.frames.settings;

import me.zihasz.zware.impl.gui.clickgui.frames.FrameButton;
import me.zihasz.zware.impl.gui.clickgui.frames.FrameSetting;
import net.minecraft.client.gui.Gui;
import org.lwjgl.input.Keyboard;

import java.awt.*;

public class Bind extends FrameSetting {
    boolean isBinding = false;
    String keyName = null;

    public Bind(FrameButton parent, int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 100;
        this.height = 20;
        this.parent = parent;
    }

    @Override
    public void render(int MouseX, int MouseY) {
        keyName = Keyboard.getKeyName(parent.getModule().getBind()) != null ? Keyboard.getKeyName(parent.getModule().getBind()) : "NONE";
        Gui.drawRect(x, y, x + width, y + height, new Color(63,63,63,96).getRGB());
        mc.fontRenderer.drawStringWithShadow("Bind", x, y + 5, Color.WHITE.getRGB());
        mc.fontRenderer.drawStringWithShadow(keyName, x + width - mc.fontRenderer.getStringWidth(keyName), y + 5, Color.GRAY.getRGB());
    }

    @Override
    public void click(int mX, int mY, int mB) {

    }
}
