package edu.ucalgary.oop;


/**
 * Coyote is a class which extends the Animal class and represents a coyote.
 * It has a static field ACTIVE_TIME which is set to "crepuscular".
 * @author     Edward An, Karam Baroud, Evan Barker, Jad Khalil
 * @version    1.4
 * @since      1.0
 */
public class Coyote extends Animal {
    private final static String ACTIVE_TIME = "crepuscular";
    private final static String SPECIES = "coyote";

    /**
     * This is the constructor for the Coyote class.
     *
     * @param id         The ID of the animal.
     * @param name       The name of the animal.
     */
    public Coyote(int id, String name) {
        super(id, name);
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
