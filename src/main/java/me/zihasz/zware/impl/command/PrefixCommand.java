package me.zihasz.zware.impl.command;

import me.yagel15637.venture.command.AbstractCommand;
import me.yagel15637.venture.exceptions.VentureException;
import me.zihasz.zware.ZWare;

public class PrefixCommand extends AbstractCommand {
    public PrefixCommand() {
        super("Sets the prefix","prefix/p <character>","prefix", "p");
    }

    @Override
    public void execute(String[] args) throws VentureException {
        ZWare.commandsPrefix = args[0];
    }
}

