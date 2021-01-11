package me.zihasz.zware.impl.command;

import me.yagel15637.venture.command.AbstractCommand;
import me.yagel15637.venture.exceptions.VentureException;
import scala.collection.immutable.IntMap;

public class BindCommand extends AbstractCommand {
    public BindCommand() {
        super("Binds a module","bind/b <module name> <key>","bind", "b");
    }

    @Override
    public void execute(String[] args) throws VentureException {

    }
}
