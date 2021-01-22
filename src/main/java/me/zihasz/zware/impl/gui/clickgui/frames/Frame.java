package me.zihasz.zware.impl.gui.clickgui.frames;

import me.zihasz.zware.ZWare;
import me.zihasz.zware.api.module.Module;
import me.zihasz.zware.api.util.MathUtil;
import me.zihasz.zware.impl.gui.clickgui.ClickGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import scala.collection.parallel.ParIterableLike;

import java.awt.*;
import java.util.ArrayList;

public class Frame {

    Module.Category category;
    int x, y;
    int width, height;

    boolean showModules = true;

    ArrayList<FrameButton> buttons = new ArrayList<>();
    Minecraft mc = Minecraft.getMinecraft();

    public Frame(Module.Category category, int x, int y, int width, int height) {
        this.category = category;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        int mOffset = 0;
        for (Module module : ZWare.moduleManager.getModules()) {
            if (category == module.getCategory()) {
                buttons.add(new FrameButton(this, module, x, y + height + mOffset, 100, 20));
                mOffset += height; /* + 2 */
            }
        }
    }

    public void render (int mouseX, int mouseY) {
        mc.fontRenderer.drawStringWithShadow(category.toString(), x + 5, y + 6 , new Color(255,255,255).getRGB());
        Gui.drawRect(x,y,x + width,y + height, new Color(63,255,63,127).getRGB());

        if (showModules) {
            for (FrameButton button : buttons) {
                button.render(mouseX, mouseY);
            }
        }
    }

    public void click (int mX, int mY, int mB) {
        for (FrameButton frameButton : buttons) {
            frameButton.click(mX, mY, mB);
        }

        if (MathUtil.inBetween(mX, x, x + width) && MathUtil.inBetween(mY, y, y + height)) {
            if (mB == 1) {
                this.showModules = !this.showModules;
            }
        }
    }

}
