package me.zihasz.zware.impl.gui.clickgui;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;

import java.awt.*;

public class ClickGUI extends GuiScreen {
    public ClickGUI() {
        Color red = new Color(255, 63, 63);
        Gui.drawRect(100, 100, 200, 400, red.getRGB());
    }
}
