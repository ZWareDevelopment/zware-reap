package me.zihasz.zware.impl.gui.clickgui.frames.settings;

import me.zihasz.zware.api.setting.Setting;
import me.zihasz.zware.api.util.MathUtil;
import me.zihasz.zware.impl.gui.clickgui.frames.FrameButton;
import me.zihasz.zware.impl.gui.clickgui.frames.FrameSetting;
import net.minecraft.client.gui.Gui;

import java.awt.*;

public class Toggle extends FrameSetting {

    private final Setting<Boolean> setting;

    public Toggle(FrameButton parent, int x, int y, Setting<Boolean> setting) {
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

        mc.fontRenderer.drawStringWithShadow(setting.getName(), x, y + 6, new Color(255,255,255).getRGB());
        Color color = setting.getValue() ? new Color(63,255,63,63) : new Color(63,63,63,63);
        Gui.drawRect(x, y, x + width, y + height, color.getRGB());
    }

    @Override
    public void click(int mX, int mY, int mB) {
        super.click(mX, mY, mB);
        if (MathUtil.inBetween(mX, x, x + width) && MathUtil.inBetween(mY, y, y + height)) {
            setting.setValue(!setting.getValue());
        }
    }
}
