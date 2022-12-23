package net.flight.client.module;

import com.google.common.eventbus.Subscribe;
import net.flight.client.Flight;
import net.flight.client.event.events.TickEvent;
import net.minecraft.client.MinecraftClient;

public class Module {
    protected MinecraftClient mc = MinecraftClient.getInstance();
    private String name, description;

    private boolean toggled;
    private int key;
    private Category category;

    public Module(String name, String description, Category category){
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return this.key;
    }

    public String getName() {
        return name;
    }

    public boolean isToggled() {
        return toggled;
    }

    public void onEnable(){
        Flight.INSTANCE.getBus().register(this);
    }
    public void onDisable(){
        Flight.INSTANCE.getBus().unregister(this);
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }

    public void toggle(){
        toggled = !toggled;
        if(toggled){
            onEnable();
        }else{
            onDisable();
        }
    }
    @Subscribe
    public void onTick(TickEvent e){

    }
}
