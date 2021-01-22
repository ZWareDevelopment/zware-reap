package me.zihasz.zware.impl.gui.clickgui.frames;

import net.minecraft.client.Minecraft;

public abstract class FrameSetting {

    protected FrameButton parent;
    protected int x, y, width, height;

    protected Minecraft mc = Minecraft.getMinecraft();

    public void render(int MouseX, int MouseY) {}
    public void click (int mX, int mY, int mB) {}
}
