package me.zihasz.zware.impl.module.combat;

import me.zihasz.zware.api.module.Module;
import me.zihasz.zware.api.setting.Setting;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

import java.util.Comparator;

public class TargetModule extends Module {

    private static EntityLivingBase target;

    Setting<Boolean> players = register(new Setting<Boolean>("Players", "Should we target players?", true));
    Setting<Boolean> others  = register(new Setting<Boolean>("Others",  "Should we target other mobs?", false));
    Setting<Integer> range   = register(new Setting<Integer>("Range",   "The range to target entities", 5, 3, 6));

    public TargetModule() {
        super("Target", "Module for choosing targets ", Category.COMBAT);
    }

    @Override
    public void onUpdate() {
        if (nullCheck()) return;

        if (players.getValue() && getNearestTarget(range.getValue()) instanceof EntityPlayer)
            target = getNearestTarget(range.getValue());
        else if (others.getValue() && !(getNearestTarget(range.getValue()) instanceof EntityPlayer))
            target = getNearestTarget(range.getValue());
        else
            target = null;
    }

    @Override
    public void onDisable() {
        enable();
    }

    public EntityLivingBase getTarget() {
        return target;
    }
    public EntityLivingBase getNearestTarget(int range) {
        return mc.world.loadedEntityList.stream()
                .filter(entity -> entity != mc.player)
                .filter(entity -> mc.player.getDistance(entity) <= range)
                .filter(entity -> !entity.isDead)
                .filter(entity -> entity instanceof EntityLivingBase)
                .map(entity -> (EntityLivingBase) entity)
                .sorted(Comparator.comparing(e -> mc.player.getDistance(e)))
                .min(Comparator.comparing(entity -> mc.player.getDistance(entity)))
                .orElse(null);
    }

}