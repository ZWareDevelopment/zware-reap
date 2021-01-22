package me.zihasz.zware.impl.command;

import me.yagel15637.venture.command.AbstractCommand;
import me.zihasz.zware.ZWare;
import me.zihasz.zware.api.module.ModuleManager;
import net.minecraft.client.Minecraft;

public class ToggleCommand extends AbstractCommand {
    public ToggleCommand() {
        super("Toggles a module", "toggle/t <module name>", "toggle", "t");
    }

    @Override
    public void execute(String[] args) {
        ZWare.moduleManager.getModuleByName(args[0]).toggle();
        Minecraft.getMinecraft().player.sendChatMessage("[DEBUG] Input: " + args[0]);
        Minecraft.getMinecraft().player.sendChatMessage("[DEBUG] Module name: " + ZWare.moduleManager.getModuleByName(args[0]).getName());
        Minecraft.getMinecraft().player.sendChatMessage("[DEBUG] Module state: " + ZWare.moduleManager.getModuleByName(args[0]).getEnabled());
    }
}
