package me.zihasz.zware.api.module;

import me.zihasz.zware.ZWare;
import me.zihasz.zware.impl.module.chat.*;
import me.zihasz.zware.impl.module.client.*;
// import me.zihasz.zware.impl.module.combat.*;
import me.zihasz.zware.impl.module.combat.*;
import me.zihasz.zware.impl.module.exploit.*;
import me.zihasz.zware.impl.module.misc.*;
import me.zihasz.zware.impl.module.movement.*;
import me.zihasz.zware.impl.module.player.*;
import me.zihasz.zware.impl.module.render.FullBright;
import me.zihasz.zware.impl.module.render.GlowESP;
// import me.zihasz.zware.impl.module.render.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ModuleManager {

    private final ArrayList<Module> modules = new ArrayList<Module>();
    // public HashMap<String, Module> modules;


    public ModuleManager() {

        // Chat
        modules.add(new ChatSuffix());
        modules.add(new MessageTest());

        // Client
        modules.add(new ClickGUIModule());
        modules.add(new DiscordRPCModule());
        // modules.add(new HUDEditorModule());

        // Combat
        modules.add(new Aura());
        modules.add(new AutoAnvil());
        modules.add(new AutoCreeper());
        modules.add(new AutoCrystal());
        modules.add(new Burrow());
        modules.add(new PotionAura());
        modules.add(new TargetModule());

        // Exploit
        modules.add(new HandShakeSpam());
        // modules.add(new KeepDead());
        modules.add(new OffhandCrash());

        // Misc
        modules.add(new AutoPorn());
        modules.add(new FakePlayer());
        modules.add(new PingBypass());

        // Movement
        modules.add(new LookDown());
        modules.add(new ReverseStep());
        modules.add(new Sprint());

        // Player
        modules.add(new FastPlace());
        modules.add(new Speedmine());

        // Render
        modules.add(new FullBright());
        modules.add(new GlowESP());
    }


    public void onUpdate() {
        modules.stream().filter(Module::getEnabled).forEach(Module::onUpdate);
    }

    public void onRender() {
        modules.stream().filter(Module::getEnabled).forEach(Module::onRender);
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public Module getModuleByName(String name) {
        return modules.stream().filter(module -> module.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public Module getModuleByClass(Class classIn) {
        return modules.stream().filter(module -> module.getClass() == classIn).findFirst().orElse(null);
    }

    public boolean isModuleEnabled(String name) {
        Module module = getModules().stream().filter(mm -> mm.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
        assert module != null;
        return module.getEnabled();
    }
}
