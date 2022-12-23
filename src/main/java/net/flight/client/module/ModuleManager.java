package net.flight.client.module;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    public static ModuleManager INSTANCE = new ModuleManager();
    public ModuleManager(){
        init();
    }
    List<Module> modules = new ArrayList<>();

    public List<Module> getModules() {
        return modules;
    }

    public void init(){
        add(new Flight());
    }

    public void add(Module module){
        modules.add(module);
    }

    public List<Module> getEnabledModules(){
        List<Module> enabled = new ArrayList<>();
        for(Module module: modules){
            if(module.isToggled()){
                enabled.add(module);
            }
        }
        return enabled;
    }
    public Module getModuleByName(String name){
        for(Module module: modules){
            if(module.getName().equals(name)){
                return module;
            }
        }
        return null;
    }
    public Module getModule(Module module){
        for(Module m : modules){
            if(m.getClass().equals(module.getClass())){
                return module;
            }
        }
        return null;
    }
}
