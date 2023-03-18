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

    public String getName() {
        return this.name;
    }

    public int getAnimalID() {
        return this.ANIMAL_ID;
    }

    public abstract String getActiveTime();

    public boolean getOrphanStatus() {
        return this.isOrphaned;
    }
    
//  Remove setAnimalID since animalID is final
//     public void setAnimalID(String animalID) {
//         this.ANIMAL_ID = animalID;
//     }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrphanStatus(boolean isOrphaned) {
        this.isOrphaned = isOrphaned;
    }
}
