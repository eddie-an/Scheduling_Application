package edu.ucalgary.oop;

public abstract class Animal {
    private String animalID;
    private String name;
    private boolean isOrphaned;

    public Animal(String id, String name, boolean isOrphaned) {
        this.animalID = id;
        this.name = name;
        this.isOrphaned = isOrphaned;
    }

    public String getName() {
        return this.name;
    }

    public String getAnimalID() {
        return this.animalID;
    }

    public abstract String getActiveTime();

    public boolean getOrphanStatus() {
        return this.isOrphaned;
    }

    public void setAnimalID(String animalID) {
        this.animalID = animalID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrphanStatus(boolean isOrphaned) {
        this.isOrphaned = isOrphaned;
    }
}
