package me.zihasz.zware.impl.module.combat;

import me.zihasz.zware.ZWare;
import me.zihasz.zware.api.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class AutoCreeper extends Module {
    public AutoCreeper() {
        super("AutoCreeper", "Creepers go brr", Category.COMBAT);
    }

    Entity target;
    BlockPos placeBlock;

    @Override
    public void onUpdate() {
        target = ((TargetModule) ZWare.moduleManager.getModuleByName("Target")).getTarget();
        if (target == null) return;
        placeBlock = new BlockPos(target.posX, target.posY , target.posZ);

        mc.player.connection.sendPacket(new CPacketEntityAction(mc.player, CPacketEntityAction.Action.START_SNEAKING));

        mc.playerController.processRightClickBlock(mc.player, mc.world, placeBlock, EnumFacing.DOWN, new Vec3d(0,0,0), EnumHand.MAIN_HAND);

        mc.player.connection.sendPacket(new CPacketEntityAction(mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
    }
}
