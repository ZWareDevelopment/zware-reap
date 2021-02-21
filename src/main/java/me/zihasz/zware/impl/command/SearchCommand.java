package me.zihasz.zware.impl.command;

import me.yagel15637.venture.command.AbstractCommand;
import me.yagel15637.venture.exceptions.VentureException;

import java.awt.*;
import java.net.URI;

// https://duckduckgo.com/?q=

public class SearchCommand extends AbstractCommand {
    public SearchCommand() {
        super("Allows you to search DuckDuck go", "search <search>", "search", "duckduckgo", "ddg");
    }

    @Override
    public void execute(String[] args) throws VentureException {
        if (args.length < 1) return;

        String url = String.format("https://duckduckgo.com/?q=%s", args[0].toLowerCase());
        try {
            Desktop.getDesktop().browse(URI.create(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
