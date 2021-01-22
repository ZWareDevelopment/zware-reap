package me.zihasz.zware.impl.gui.clickgui.frames;

import me.zihasz.zware.api.module.Module;
import me.zihasz.zware.api.setting.Setting;
import me.zihasz.zware.api.util.MathUtil;
import me.zihasz.zware.impl.gui.clickgui.frames.settings.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.settings.KeyBinding;

import java.awt.*;
import java.lang.String;
import java.util.ArrayList;

public class FrameButton {

    Frame parent;
    Module module;
    int x, y;
    int width, height;
    boolean showSettings = false;

    Minecraft mc;

    ArrayList<FrameSetting> settings = new ArrayList<>();

    public FrameButton(Frame parent, Module module, int x, int y, int width, int height) {
        this.parent = parent;
        this.module = module;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        mc = parent.mc;

        settings.add(new Bind(this, this.x + width, this.y));

        int sOffset = 20;
        for (Setting<?> setting : module.getSettings()) {
            // if (setting.getValue() instanceof Color)        settings.add(new RGB        ());
            if (setting.getValue() instanceof String)       settings.add(new TextInput  (this, this.x + this.width, this.y + sOffset, (Setting<? extends String>)  setting));
            if (setting.getValue() instanceof Enum<?>)      settings.add(new Mode       (this, this.x + this.width, this.y + sOffset, (Setting<? extends Enum<?>>) setting));
            if (setting.getValue() instanceof Number)       settings.add(new Slider     (this, this.x + this.width, this.y + sOffset, (Setting<? extends Number>)  setting));
            if (setting.getValue() instanceof Boolean)      settings.add(new Toggle     (this, this.x + this.width, this.y + sOffset, (Setting<? extends Boolean>) setting));
            sOffset += 20;
        }
    }

    public void render (int mouseX, int mouseY) {
        Gui.drawRect(x,y,x + width,y + height, new Color(63,63,63,96).getRGB());

        Color color = module.getEnabled() ? new Color(63,255,63) : new Color(255,255,255);
        mc.fontRenderer.drawStringWithShadow(module.getName(),x + 5, y + 6, color.getRGB());

        if (showSettings) {
            for (FrameSetting sFrame : settings) {
                sFrame.render(mouseX, mouseY);
            }
        }
    }

    public void click (int mX, int mY, int mB) {
        for (FrameSetting frameSetting : settings) {
            frameSetting.click(mX, mY, mB);
        }

        if (MathUtil.inBetween(mX, x, x + width) && MathUtil.inBetween(mY, y, y + height)) {
            if (mB == 0) {
                module.toggle();
            }

            if (mB == 1) {
                this.showSettings = !this.showSettings;
            }
        }
    }

    public Module getModule() {
        return module;
    }
}