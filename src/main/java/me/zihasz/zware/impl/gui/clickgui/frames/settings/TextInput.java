package me.zihasz.zware.impl.gui.clickgui.frames.settings;

import me.zihasz.zware.api.setting.Setting;
import me.zihasz.zware.impl.gui.clickgui.frames.FrameButton;
import me.zihasz.zware.impl.gui.clickgui.frames.FrameSetting;
import net.minecraft.client.gui.Gui;

import java.awt.*;

public class TextInput extends FrameSetting {

    private Setting<? extends String> setting;
    private boolean active = false;
    private String content = "";

    public TextInput(FrameButton parent, int x, int y, Setting<? extends String> setting) {
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
        Gui.drawRect(x, y, x + width, y + height, new Color(63,63,63,96).getRGB());
    }

}
