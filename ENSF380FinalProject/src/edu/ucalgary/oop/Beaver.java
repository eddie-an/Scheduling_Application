package edu.ucalgary.oop;

public class Beaver extends Animal {
    private final static String ACTIVE_TIME = "diurnal";

    public Beaver(String id, String name, boolean isOrphaned) {
        super(id, name, isOrphaned);
    }

    @Override
    public String getActiveTime() {
        return ACTIVE_TIME;
    };

}
