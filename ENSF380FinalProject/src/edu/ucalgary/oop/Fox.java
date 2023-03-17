package edu.ucalgary.oop;

public class Fox extends Animal {
    private final static String ACTIVE_TIME = "nocturnal";

    public Fox(String id, String name, boolean isOrphaned) {
        super(id, name, isOrphaned);
    }

    @Override
    public String getActiveTime() {
        return ACTIVE_TIME;
    };
}
