package me.zihasz.zware.impl.command;

import me.yagel15637.venture.command.AbstractCommand;
import me.yagel15637.venture.exceptions.VentureException;
import me.zihasz.zware.ZWare;
import me.zihasz.zware.api.module.Module;
import org.lwjgl.input.Keyboard;

public class BindCommand extends AbstractCommand {
    public BindCommand() {
        super("Binds a module","bind/b <module name> <key>","bind", "b");
    }

    @Override
    public void execute(String[] args) throws VentureException {
        if (args.length != 2) return;
        for (Module module : ZWare.moduleManager.getModules()) {
            if (module.getName().equalsIgnoreCase(args[0])) {
                try {
                    module.setBind(Keyboard.getKeyIndex(args[1].toUpperCase()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
