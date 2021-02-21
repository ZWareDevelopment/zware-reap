package me.zihasz.zware.impl.module.movement;

import me.zihasz.zware.api.module.Module;

public class Sprint extends Module {

    public Sprint() {
        super("Sprint", "Sprints", Category.MOVEMENT);
    }

    @Override
    public void onUpdate() {
        if (nullCheck()) return;

        if (mc.player.foodStats.getFoodLevel() > 6) mc.player.setSprinting(true);
        else mc.player.setSprinting(false);
    }
}
