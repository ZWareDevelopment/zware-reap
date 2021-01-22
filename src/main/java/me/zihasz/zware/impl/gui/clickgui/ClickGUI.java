package me.zihasz.zware.impl.gui.clickgui;

import me.zihasz.zware.api.module.Module;
import me.zihasz.zware.api.util.MathUtil;
import me.zihasz.zware.impl.gui.clickgui.frames.Frame;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;
import java.util.ArrayList;

public class ClickGUI extends GuiScreen {
    ArrayList<Frame> frames = new ArrayList<>();

    public static ClickGUI INSTANCE = new ClickGUI();

    public ClickGUI() {
        int fOffset = 0;
        for (Module.Category category : Module.Category.values()) {
            frames.add(new Frame(category, 15 + fOffset, 15, 100, 20));
            fOffset += 100 + 15;
        }
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        for (Frame frame : frames) {
            frame.render(mouseX, mouseY);
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        for (Frame frame : frames) {
            frame.click(mouseX, mouseY, mouseButton);
        }
    }
}
