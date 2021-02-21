package me.zihasz.zware.impl.gui.hud.components;

import java.awt.*;

public abstract class Component {
    int x, y, width, height;
    Color foreground, background;

    public void render() {}
}
