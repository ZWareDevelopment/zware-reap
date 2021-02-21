package me.zihasz.zware.impl.module.player;

import me.zihasz.zware.api.module.Module;

public class FastPlace extends Module {

    public FastPlace() {
        super("FastPlace", "places fast", Category.PLAYER);
    }

    @Override
    public void onUpdate() {
        mc.rightClickDelayTimer = 0;
    }
}
