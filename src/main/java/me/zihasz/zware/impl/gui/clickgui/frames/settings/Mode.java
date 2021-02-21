package me.zihasz.zware.impl.gui.clickgui.frames.settings;

import me.zihasz.zware.api.setting.Setting;
import me.zihasz.zware.api.util.MathUtil;
import me.zihasz.zware.impl.gui.clickgui.frames.FrameButton;
import me.zihasz.zware.impl.gui.clickgui.frames.FrameSetting;
import me.zihasz.zware.impl.module.chat.ChatSuffix;
import net.minecraft.client.gui.Gui;
import net.minecraft.network.EnumConnectionState;

import java.awt.*;

public class Mode extends FrameSetting {

    private Setting<Enum<?>> setting;

    public Mode(FrameButton parent, int x, int y, Setting<Enum<?>> setting) {
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
        mc.fontRenderer.drawStringWithShadow(
                setting.getValue().toString(),
                x + width - mc.fontRenderer.getStringWidth(setting.getValue().toString()),
                y + 6,
                new Color(127,127,127).getRGB());
        Gui.drawRect(x, y, x + width, y + height, new Color(63,63,63, 96).getRGB());

    }

    @Override
    public void click(int mX, int mY, int mB) {
        super.click(mX, mY, mB);
        if (MathUtil.inBetween(mX, x, x + width) && MathUtil.inBetween(mY, y, y + height)) {
            if (mB == 0) {
                increment();
            }
        }
    }

    public void increment() {
        Enum<?>[] array = setting.getValue().getDeclaringClass().getEnumConstants();
        int index = setting.getValue().ordinal()+1;
        if (index >= array.length) index=0;
        setting.setValue(array[index]);
    }
}
