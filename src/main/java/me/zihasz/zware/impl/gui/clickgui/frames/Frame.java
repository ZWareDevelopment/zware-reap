package me.zihasz.zware.impl.gui.clickgui.frames;

import me.zihasz.zware.api.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

import java.awt.*;

public class Frame {
    int x, y, width, height;
    Module.Category category;

    Minecraft mc = Minecraft.getMinecraft();

    public Frame(Module.Category category, int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 120;
        this.height = 240;
        this.category = category;
    }

    public void render(int MouseX, int MouseY) {
        mc.fontRenderer.drawString(category.toString(), x+2, y+2, new Color(255, 255, 255).getRGB());
        Gui.drawRect(x, y, x+width, y+height, new Color(69,255,69, 127).getRGB());
    }
}
