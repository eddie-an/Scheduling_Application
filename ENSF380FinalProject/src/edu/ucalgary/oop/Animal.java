/**
@author     Group5
@version    1.4
@since      1.0
*/

/*
 * Animal is an abstract class for the subclasses Fox, Beaver, Porcupine, Coyote, Raccoon
 * with info about the Animal ID, animal name, and whether or not the animal is an orphan.
*/


package edu.ucalgary.oop;

public abstract class Animal {
    private final int ANIMAL_ID;
    private String name;
    private boolean isOrphaned;


    /**
     * This is the constructor for the Animal class.
     * @param id            The ID of the animal.
     * @param name          The name of the animal.
     * @param isOrphaned    Whether or not the animal is an orphan.
     */
    public Animal(int id, String name, boolean isOrphaned) {
        this.ANIMAL_ID = id;
        this.name = name;
        this.isOrphaned = isOrphaned;
    }

    
    /**
     * @return  The ID of the animal.
     */
    public int getAnimalID() {
        return this.ANIMAL_ID;
    }

    /**
     * @return  The name of the animal.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return  Whether or not the animal is an orphan.
     */
    public boolean getOrphanStatus() {
        return this.isOrphaned;
    }

    /**
     * @return  The active time of the animal.
     */
    public abstract String getActiveTime();

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param isOrphaned    Whether or not the animal is an orphan.
     */
    public void setOrphanStatus(boolean isOrphaned) {
        this.isOrphaned = isOrphaned;
    }
}
