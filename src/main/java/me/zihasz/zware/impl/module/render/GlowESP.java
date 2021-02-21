package me.zihasz.zware.impl.module.render;

import me.zihasz.zware.api.module.Module;

import javax.swing.text.html.parser.Entity;

public class GlowESP extends Module {
    public GlowESP() {
        super("GlowESP", "Glow my children", Category.RENDER);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        mc.world.getLoadedEntityList().forEach(entity -> {
            entity.setGlowing(true);
        });
    }

    @Override
    public void onDisable() {
        super.onDisable();
        mc.world.getLoadedEntityList().forEach(entity -> {
            entity.setGlowing(false);
        });
    }
}
