package me.zihasz.zware.api.setting;

import net.minecraft.network.EnumConnectionState;

public class Setting<T> {
    private final String name, description;
    private T value;
    private final T min, max;

    public Setting(String name, String description, T value, T min, T max) {
        this.name = name;
        this.description = description;
        this.value = value;
        this.min = min;
        this.max = max;
    }
    public Setting(String name, String description, T value) {
        this(name, description, value, null, null);
    }
    public Setting(String name, T value, T min, T max) {
        this(name, "", value, min, max);
    }
    public Setting(String name, T value) {
        this(name, "", value);
    }

    public String getName() { return name; }
    public String getDescription() { return description; }

    public T getValue() { return value; }
    public void setValue(T value) { this.value = value; }

    public T getMin() { return min; }
    public T getMax() { return max; }
}
