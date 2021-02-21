package me.zihasz.zware.impl.module.movement;

import me.zihasz.zware.ZWare;
import me.zihasz.zware.api.module.Module;
import me.zihasz.zware.api.setting.Setting;

public class ReverseStep extends Module {
    public ReverseStep() {
        super("ReverseStep", "Steps down, makes you 10x better at pvp", Category.MOVEMENT);
    }

    Setting<Float> height = register(new Setting<Float>("Height", "What height should we step you down", 2.5f, 1.0f, 2.5f));

    @Override
    public void onUpdate() {
        if (nullCheck() || mc.player.isInWater() || mc.player.isInLava() || mc.player.isOnLadder()
                || mc.gameSettings.keyBindJump.isKeyDown()) return;

        /*
        if (ZWare.moduleManager.getModuleByName("Speed").getEnabled()
                || ZWare.moduleManager.getModuleByName("Strafe").getEnabled()) return;
         */

        if (mc.player != null && mc.player.onGround && !mc.player.isInWater() && !mc.player.isOnLadder()) {
            for (double y = 0.0; y < this.height.getValue() + 0.5; y += 0.01) {
                if (!mc.world.getCollisionBoxes(mc.player, mc.player.getEntityBoundingBox().offset(0.0, -y, 0.0)).isEmpty()) {
                    mc.player.motionY = -10.0;
                    break;
                }
            }
        }
    }
}
