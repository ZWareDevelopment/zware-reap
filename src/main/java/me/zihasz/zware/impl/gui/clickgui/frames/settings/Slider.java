package me.zihasz.zware.impl.gui.clickgui.frames.settings;

import me.zihasz.zware.api.setting.Setting;
import me.zihasz.zware.impl.gui.clickgui.frames.FrameButton;
import me.zihasz.zware.impl.gui.clickgui.frames.FrameSetting;
import net.minecraft.client.gui.Gui;

import java.awt.*;

public class Slider extends FrameSetting {

    private Setting<? extends Number> setting;

    public Slider(FrameButton parent, int x, int y, Setting<? extends Number> setting) {
        this.x = x;
        this.y = y;
        this.width = 100;
        this.height = 20;

        this.parent = parent;
        this.setting = setting;
    }

    @Override
    public void render(int MouseX, int MouseY) {
        super.render(MouseX, MouseY);
        Gui.drawRect(x, y, x + width, y + height, new Color(63, 63, 63, 96).getRGB());
        Gui.drawRect(x, y, x + width, y + height, new Color(63, 255, 63).getRGB());
        mc.fontRenderer.drawStringWithShadow(setting.getName(), x, y + 5, new Color(255,255,255).getRGB());
    }

}
