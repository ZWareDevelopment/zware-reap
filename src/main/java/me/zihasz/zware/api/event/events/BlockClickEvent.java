package me.zihasz.zware.api.event.events;

import club.cafedevelopment.blitz.event.Event;
import club.cafedevelopment.blitz.modifiers.EventEra;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class BlockClickEvent extends Event {

    BlockPos blockPosition;
    EnumFacing blockFacing;

    public BlockClickEvent(EventEra era, BlockPos posIn, EnumFacing faceIn) {
        super(era);
        blockPosition = posIn;
        blockFacing = faceIn;
    }

    public BlockPos getBlockPosition() {
        return blockPosition;
    }

    public EnumFacing getBlockFacing() {
        return blockFacing;
    }
}
