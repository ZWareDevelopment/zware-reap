package me.zihasz.zware.api.mixin.mixins;

import net.minecraft.client.gui.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

@Mixin(GuiMainMenu.class)
public class MixinGuiMainMenu extends GuiScreen {
    HashMap<String, Boolean> urls = new HashMap<>();
    int tickDelay = 1000000, tick = 0;

    @Inject(method = "Lnet/minecraft/client/gui/GuiMainMenu;<init>()V", at = @At("RETURN") )
    public void onNewInstance(CallbackInfo ci) {
        urls.put("session.minecraft.net", false);
        urls.put("authserver.mojang.com", false);
        urls.put("account.mojang.com", false);
        urls.put("api.mojang.com", false);
        for (String url : urls.keySet()) urls.put(url, serverOnline(url, 80));
    }

    @Inject(method = "drawScreen", at = @At("TAIL"), cancellable = true)
    public void drawText(int mouseX, int mouseY, float partialTicks, CallbackInfo callbackInfo) {
        FontRenderer fontRenderer = mc.fontRenderer;

        this.drawString(fontRenderer, "Logged in as: "  + mc.session.getUsername(), 10, 40, new Color(255,255,255).getRGB());
        this.drawString(fontRenderer, "Session ID: "    + mc.session.getSessionID(),10, 50, new Color(255,255,255).getRGB());
        this.drawString(fontRenderer, "Token: "         + mc.session.getToken(),    10, 60, new Color(255,255,255).getRGB());

        int y = 80;
        for (Map.Entry<String, Boolean> entry : urls.entrySet()) {
            if (entry.getValue())   this.drawString(fontRenderer, String.format("[%s]", entry.getKey()), 10, y, new Color(0,255,0).getRGB());
            else                    this.drawString(fontRenderer, String.format("[%s]", entry.getKey()), 10, y, new Color(255,0,0).getRGB());
            y += 10;
        }

        if(tick++ < tickDelay)  return;
        else {
            for (String url : urls.keySet()) urls.put(url, serverOnline(url, 80));
            tick = 0;
        }
    }

    public boolean serverOnline(String address, int port) {
        try (Socket s = new Socket(address, port)) { return true; }
        catch (Exception ignored) {}
        return false;
    }
}