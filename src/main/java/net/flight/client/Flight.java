package net.flight.client;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import net.fabricmc.api.ModInitializer;
import net.flight.client.event.events.KeyEvent;
import net.flight.client.event.events.StopEvent;
import net.flight.client.module.Module;
import net.flight.client.module.ModuleManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Flight implements ModInitializer {
	public static Flight INSTANCE = new Flight();
	private EventBus bus;
	private ModuleManager moduleManager;
	public static final Logger LOGGER = LoggerFactory.getLogger("flight");
	public void init(){
		LOGGER.info("Starting gnomenheimer!");
		bus = new EventBus();
		bus.register(this);
		moduleManager = new ModuleManager();
	}

	@Override
	public void onInitialize() {

	}

	public EventBus getBus() {
		return bus;
	}

	@Subscribe
	public void stop(StopEvent e){
		LOGGER.info("Stopping gnomenheimer!");
		bus.unregister(this);
	}
	@Subscribe
	public void onKey(KeyEvent event){
		for(Module module: ModuleManager.INSTANCE.getModules()){
			if(module.getKey() == event.getKey()) module.toggle();
		}
	}
}
