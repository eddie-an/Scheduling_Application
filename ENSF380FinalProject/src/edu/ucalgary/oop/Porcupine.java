package edu.ucalgary.oop;

public class Porcupine extends Animal {
    private final static String ACTIVE_TIME = "crepuscular";

    public Porcupine(String id, String name, boolean isOrphaned) {
        super(id, name, isOrphaned);
    }

    @Override
    public String getActiveTime() {
        return ACTIVE_TIME;
    };
}
