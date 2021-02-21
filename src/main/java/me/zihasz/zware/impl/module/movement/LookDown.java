package me.zihasz.zware.impl.module.movement;

import me.zihasz.zware.api.module.Module;

public class LookDown extends Module {
    public LookDown() {
        super("LookDown", "Looks down when falling", Category.MOVEMENT);
    }

    @Override
    public void onUpdate() {
        if(nullCheck()) return;

        if (!mc.player.onGround) {
            mc.player.rotationPitch = 90f;
        }
    }
}
