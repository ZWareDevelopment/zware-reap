package me.zihasz.zware.impl.module.render;

import me.zihasz.zware.api.module.Module;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class FullBright extends Module {
    public FullBright() {
        super("FullBright", "Discord light theme", Category.RENDER);
    }

    @Override
    public void onUpdate() {
        final PotionEffect potionEffect = new PotionEffect(Potion.getPotionById(16), 123456789, 5);
        potionEffect.setPotionDurationMax(true);
        mc.player.addPotionEffect(potionEffect);
    }

    @Override
    public void onDisable() {
        mc.player.removePotionEffect(Potion.getPotionById(16));
    }
}
