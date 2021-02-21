package me.zihasz.zware.impl.module.player;

import club.cafedevelopment.blitz.dispatcher.DispatcherEntry;
import me.zihasz.zware.api.event.events.BlockClickEvent;
import me.zihasz.zware.api.module.Module;
import me.zihasz.zware.api.setting.Setting;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class Speedmine extends Module {

    BlockPos blockPos1;
    EnumFacing facing1;

    private Setting<SMModes> sMode = this.register(new Setting<SMModes>("", "", SMModes.CIVBREAK));

    public Speedmine() {
        super("Speedmine", "mine go zooom...", Category.PLAYER);
    }

    @Override
    public void onUpdate() {
        if (nullCheck()) return;

        if (sMode.getValue() == SMModes.CIVBREAK &&
                mc.player.getDistance(blockPos1.x, blockPos1.y, blockPos1.z) < 4 &&
                !(mc.world.isAirBlock(blockPos1)) &&
                canBreak(blockPos1))
        {
            mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockPos1, facing1));
        }

        if (sMode.getValue() == SMModes.DAMAGE) {
            if (mc.playerController.curBlockDamageMP >= 0.7f) {
                mc.playerController.curBlockDamageMP = 1.0f;
            }
        }
    }

    @DispatcherEntry
    public void onBlockClick(BlockClickEvent event) {
        blockPos1 = event.getBlockPosition();
        facing1 = event.getBlockFacing();

        if (sMode.getValue() != SMModes.DAMAGE) {
            mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, blockPos1, facing1));
            mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockPos1, facing1));

            if (sMode.getValue() == SMModes.INSTANT) {
                mc.world.setBlockToAir(blockPos1);
            }
        }
    }

    private boolean canBreak(BlockPos pos) {
        final IBlockState blockState = mc.world.getBlockState(pos);
        final Block block = blockState.getBlock();

        return block.getBlockHardness(blockState, mc.world, pos) != -1;
    }

    private enum SMModes {
        DAMAGE,
        PACKET,
        INSTANT,
        CIVBREAK
    }

}
