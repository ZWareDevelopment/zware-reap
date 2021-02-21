package me.zihasz.zware.impl.module.combat;

import me.zihasz.zware.api.module.Module;
import me.zihasz.zware.api.setting.Setting;
import me.zihasz.zware.api.util.BlockUtil;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.input.Keyboard;

public class Burrow extends Module {
    private final Setting<Boolean> packet = this.register(new Setting<Boolean>("PacketPlace", Boolean.FALSE));

    public Burrow() {
        super("SilentBurrow", "Shh better burrow", Module.Category.COMBAT);
        this.setBind(Keyboard.KEY_H);
    }

    @Override
    public void onEnable() {
        Burrow.mc.player.jump();
        Burrow.mc.player.jump();
    }

    @Override
    public void onUpdate() {
        BlockPos pos = new BlockPos(Burrow.mc.player.posX, Burrow.mc.player.posY, Burrow.mc.player.posZ);
        if (Burrow.mc.world.getBlockState(pos.down()).getBlock() == Blocks.AIR && BlockUtil.isPositionPlaceable(pos.down(), false) == 3) {
            BlockUtil.placeBlock(pos.down(), EnumHand.MAIN_HAND, false, this.packet.getValue(), false);
        }
        if (Burrow.mc.world.getBlockState(pos.down()).getBlock() == Blocks.OBSIDIAN) {
            Burrow.mc.player.connection.sendPacket(new CPacketPlayer.Position(Burrow.mc.player.posX, Burrow.mc.player.posY - 1.3, Burrow.mc.player.posZ, false));
            Burrow.mc.player.setPosition(Burrow.mc.player.posX, Burrow.mc.player.posY - 1.3, Burrow.mc.player.posZ);
            this.toggle();
        }
    }
}