package me.zihasz.zware.impl.module.misc;

import me.zihasz.zware.api.module.Module;
import me.zihasz.zware.api.setting.Setting;

import java.awt.*;
import java.net.URI;

public class AutoPorn extends Module {
    Setting<AutoPornMode> mode = register(new Setting<AutoPornMode>("Mode", "Whyt porn do you like?", AutoPornMode.Lesbian));

    public AutoPorn() {
        super("AutoPorn", "Opens porn in enw browser", Category.MISC);
    }



    @Override
    public void onEnable() {
        String url = String.format("https://www.pornhub.com/video/search?search=%s", mode.getValue().toString().toLowerCase());
        try {
            Desktop.getDesktop().browse(URI.create(url));
        } catch (Exception e) {}
        disable();
    }

    public enum AutoPornMode {
        Straight,
        Lesbian,
        Step,
        MILF,
        Hentai,
        Feet,
        BDSM,
        Teas,
        Creampie,
        Squirt,
        Gangbang,
        Teen,
        Cumshot,
    }

}
