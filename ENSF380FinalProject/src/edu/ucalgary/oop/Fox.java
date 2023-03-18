/**
@author     Group5
@version    1.4
@since      1.0
*/

/*
 * Fox is a class which extends the Animal class and represents a fox.
 * It has has a static field ACTIVE_TIME which is set to "nocturnal".
*/

package edu.ucalgary.oop;

public class Fox extends Animal {
    private final static String ACTIVE_TIME = "nocturnal";
    private final static String SPECIES = "fox";

    /**
     * This is the constructor for the Fox class.
     * 
     * @param id         The ID of the animal.
     * @param name       The name of the animal.
     * @param isOrphaned Whether or not the animal is an orphan.
     */
    public Fox(int id, String name, boolean isOrphaned) {
        super(id, name, isOrphaned);
    }

    /**
     * @return The ID of the animal.
     */
    public int getAnimalID() {
        return super.getAnimalID();
    }

    /**
     * @return The name of the animal.
     */
    public String getName() {
        return super.getName();
    }

    /**
     * @return Whether or not the animal is an orphan.
     */
    public boolean getOrphanStatus() {
        return super.getOrphanStatus();
    }

    /**
     * @return The active time of the animal.
     */
    @Override
    public String getActiveTime() {
        return ACTIVE_TIME;
    }

    /**
     * @return The species of the animal.
     */
    @Override
    public String getSpecies() {
        return SPECIES;
    }

    /**
     * @param name The name of the animal.
     */
    public void setName(String name) {
        super.setName(name);
    }

    /**
     * @param isOrphaned Whether or not the animal is an orphan.
     */
    public void setOrphanStatus(boolean isOrphaned) {
        super.setOrphanStatus(isOrphaned);
    }
}
