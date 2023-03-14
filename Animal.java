package edu.ucalgary.oop;

public abstract class Animal {
    private String name;
    private String animalID;
    private boolean isOrphaned;

    public String getName() {
        return this.name;
    }

    public String getAnimalID() {
        return this.animalID;
    }

    public String getActiveTime() {
    }

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
