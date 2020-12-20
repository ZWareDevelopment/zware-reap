package me.zihasz.zware.impl.command;

import me.yagel15637.venture.command.AbstractCommand;

public class ExampleCommand extends AbstractCommand {
    public ExampleCommand() {
        super("An Example Command!", "alias1/alias2/alias3/etc", "alias1", "alias2", "alias3", "etc");
    }

    @Override
    public void execute(String[] args) {
        System.out.println("This is an Example Command!");
    }
}
