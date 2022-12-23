package net.flight.client.event.events;

import net.flight.client.event.Event;

public class KeyEvent extends Event {
    private int key;

    public KeyEvent(int key){
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
