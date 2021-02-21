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
    public ArrayList<Module> modules;
    // public HashMap<String, Module> modules;


    public ModuleManager() {
        modules = new ArrayList<Module>();

        // Chat
        addMod(new ChatSuffix());
        addMod(new MessageTest());

        // Client
        addMod(new ClickGUIModule());
        addMod(new DiscordRPCModule());
        // addMod(new HUDEditorModule());

        // Combat
        addMod(new Aura());
        addMod(new AutoAnvil());
        addMod(new AutoCreeper());
        addMod(new AutoCrystal());
        addMod(new Burrow());
        addMod(new PotionAura());
        addMod(new TargetModule());

        // Exploit
        addMod(new HandShakeSpam());
        // addMod(new KeepDead());
        addMod(new OffhandCrash());

        // Misc
        addMod(new AutoPorn());
        addMod(new FakePlayer());
        addMod(new PingBypass());

        // Movement
        addMod(new LookDown());
        addMod(new ReverseStep());
        addMod(new Sprint());

        // Player
        addMod(new FastPlace());
        addMod(new Speedmine());

        // Render
        addMod(new FullBright());
        addMod(new GlowESP());

        ZWare.EVENT_BUS.register(this);
    }

    public void addMod(Module module) {
        //changed
        //this.modules.add(module);
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
        Module module = getModules().stream().filter(mm->mm.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
        assert module != null;
        return module.getEnabled();
    }
}
