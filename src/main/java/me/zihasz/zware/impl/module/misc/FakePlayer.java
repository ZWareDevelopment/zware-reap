package me.zihasz.zware.impl.module.misc;

import com.mojang.authlib.GameProfile;
import me.zihasz.zware.api.module.Module;
import net.minecraft.client.entity.EntityOtherPlayerMP;

import java.util.UUID;

public class FakePlayer extends Module {
    public FakePlayer() {
        super("FakePlayer", "Creates a fake player", Category.MISC);
    }

    @Override
    public void onEnable() {
        if(nullCheck()) return;

        final EntityOtherPlayerMP fakePlayer = new EntityOtherPlayerMP(mc.world, new GameProfile(UUID.fromString("fdee323e-7f0c-4c15-8d1c-0f277442342a"), "Fit"));
        fakePlayer.copyLocationAndAnglesFrom(mc.player);
        fakePlayer.rotationYawHead = FakePlayer.mc.player.rotationYawHead;
        FakePlayer.mc.world.addEntityToWorld(-420, fakePlayer);
    }

    @Override
    public void onDisable() {
        mc.world.removeEntityFromWorld(-420);
    }
}
