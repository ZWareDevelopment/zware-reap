package me.zihasz.zware.impl.command;

import me.yagel15637.venture.command.AbstractCommand;
import me.zihasz.zware.ZWare;
import me.zihasz.zware.api.module.ModuleManager;

public class ToggleCommand extends AbstractCommand {
    public ToggleCommand() {
        super("Toggles a module", "toggle/t <module name>", "toggle", "t");
    }

    @Override
    public void execute(String[] args) {
        ZWare.moduleManager.getModuleByName(args[1]).toggle();
    }
}
