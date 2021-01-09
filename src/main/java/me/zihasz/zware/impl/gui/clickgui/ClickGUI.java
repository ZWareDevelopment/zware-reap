package me.zihasz.zware.impl.gui.clickgui;

import me.zihasz.zware.api.module.Module;
import me.zihasz.zware.impl.gui.clickgui.frames.Frame;

import net.minecraft.client.gui.GuiScreen;

import java.util.ArrayList;

public class ClickGUI extends GuiScreen {
    public static ClickGUI INSTANCE = new ClickGUI();

    ArrayList<Frame> frames;

    public ClickGUI() {
        frames = new ArrayList<>();

        int offset = 0;
        for (Module.Category category : Module.Category.values()) {
            frames.add(new Frame(category, 10 + offset, 20));
            offset += 130;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        for (Frame frame : frames) {
            frame.render(mouseX, mouseY);
        }
    }
}