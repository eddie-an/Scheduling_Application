package edu.ucalgary.oop;

public class Porcupine extends Animal {
    private final static String ACTIVE_TIME = "crepuscular";

    public Porcupine(int id, String name, boolean isOrphaned) {
        super(id, name, isOrphaned);
    }

    public int getAnimalID() {
        return super.getAnimalID();
    }

    public String getName() {
        return super.getName();
    }

    public boolean getOrphanStatus() {
        return super.getOrphanStatus();
    }

    @Override
    public String getActiveTime() {
        return ACTIVE_TIME;
    };

    public void setName(String name) {
        super.setName(name);
    }

    public void setOrphanStatus(boolean isOrphaned) {
        super.setOrphanStatus(isOrphaned);
    }
}
