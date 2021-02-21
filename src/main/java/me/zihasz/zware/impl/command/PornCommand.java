package me.zihasz.zware.impl.command;


import me.yagel15637.venture.command.AbstractCommand;
import me.yagel15637.venture.exceptions.VentureException;

import java.awt.*;
import java.net.URI;

public class PornCommand extends AbstractCommand {
    public PornCommand() {
        super("Allows you to search PornHub", "porn <search>", "porn", "pornhub");
    }

    @Override
    public void execute(String[] args) throws VentureException {
        if (args.length < 1) return;

        String url = String.format("https://www.pornhub.com/video/search?search=%s", args[0].toLowerCase());
        try {
            Desktop.getDesktop().browse(URI.create(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
