package net.flight.client.module;

public enum Category {
    MOTION("Motion");
    private final String name;
    Category(String name) {
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
}
