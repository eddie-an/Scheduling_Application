/**
 @author     Group5
 @version    1.4
 @since      1.0
 */

/*
 * Coyote is a class which extends the Animal class and represents a coyote.
 * It has has a static field ACTIVE_TIME which is set to "crepescular".
 */

package edu.ucalgary.oop;

public class Coyote extends Animal {
    private final static String ACTIVE_TIME = "crepuscular";
    private final static String SPECIES = "coyote";

    /**
     * This is the constructor for the Coyote class.
     *
     * @param id         The ID of the animal.
     * @param name       The name of the animal.
     * @param isOrphaned Whether or not the animal is an orphan.
     */
    public Coyote(int id, String name, String nickName) {
        super(id, name, nickName);
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
