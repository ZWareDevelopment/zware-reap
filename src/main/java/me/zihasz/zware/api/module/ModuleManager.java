package me.zihasz.zware.api.module;

import me.zihasz.zware.ZWare;

import java.util.ArrayList;

public class ModuleManager {
    public static ArrayList<Module> modules = new ArrayList<>();

    public ModuleManager() {
        ZWare.EVENT_BUS.register(this);
    }

    public void addMod(Module module) {
        modules.add(module);
    }


    public static void onUpdate() {
        modules.stream().filter(Module::getEnabled).forEach(Module::onUpdate);
    }

    public static ArrayList<Module> getModules() {
        return modules;
    }

    public static Module getModuleByName(String name) {
        return modules.stream().filter(module -> module.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public static boolean isModuleEnabled(String name) {
        Module module = getModules().stream().filter(mm->mm.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
        assert module != null;
        return module.getEnabled();
    }
}
