package me.zihasz.zware.api.module;

import me.zihasz.zware.api.setting.Setting;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;

public abstract class Module {

    private final String name;
    private final String description;
    private final Category category;
    private int bind;

    private boolean enabled = false;
    private boolean drawn = true;

    private ArrayList<Setting<?>> settings = new ArrayList<>();

    public Minecraft mc = Minecraft.getMinecraft();

    public Module(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public Module(String name, Category category) {
        this(name, "", category);
    }

    // Changing enabled
    public void enable() {
        enabled = true;
        onEnable();
    }
    public void disable() {
        enabled = false;
        onDisable();
    }
    public void toggle() {
        if (enabled)
            disable();
        else
            enable();
    }

    // Getters
    public String   getName() {
        return name;
    }
    public String   getDescription() {
        return description;
    }
    public Category getCategory() {
        return category;
    }
    public int      getBind() {
        return this.bind;
    }
    public boolean  getEnabled() {
        return enabled;
    }
    public boolean  getDrawn() {
        return drawn;
    }

    // Setters
    public void setBind(int bind){
        this.bind = bind;
    }
    public void setDrawn(boolean drawn){
        this.drawn = drawn;
    }

    // "Events"
    public void onEnable() {}
    public void onDisable()  {}
    public void onUpdate() {}
    public void onRender() {}

    // Misc
    public String   getHudInfo() {
        return "";
    }
    public boolean  nullCheck() {
        return mc.player == null || mc.world == null;
    }

    // Category
    public enum Category {
        CHAT,
        COMBAT,
        PLAYER,
        MOVEMENT,
        EXPLOIT,
        MISC,
        CLIENT
    }
}
