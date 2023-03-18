package edu.ucalgary.oop;

public abstract class Animal {
    private final int ANIMAL_ID;
    private String name;
    private boolean isOrphaned;

    public Animal(int id, String name, boolean isOrphaned) {
        this.ANIMAL_ID = id;
        this.name = name;
        this.isOrphaned = isOrphaned;
    }

    public int getAnimalID() {
        return this.ANIMAL_ID;
    }

    public String getName() {
        return this.name;
    }

    public boolean getOrphanStatus() {
        return this.isOrphaned;
    }

    public abstract String getActiveTime();

    public void setName(String name) {
        this.name = name;
    }

    public void setOrphanStatus(boolean isOrphaned) {
        this.isOrphaned = isOrphaned;
    }
}
