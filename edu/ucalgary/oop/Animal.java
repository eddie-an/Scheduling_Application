package edu.ucalgary.oop;


/**
 * Animal is an abstract class for the subclasses Fox, Beaver, Porcupine, Coyote, and Raccoon.
 * It contains information that are common to all types of animals such as Animal ID, animal name, and its orphan status
 * @author     Edward An, Karam Baroud, Evan Barker, Jad Khalil
 * @version    1.4
 * @since      1.0
 */
public abstract class Animal {
    private final int ANIMAL_ID;
    private String name;
    private boolean isOrphaned;


    /**
     * This is the constructor for the Animal class.
     *
     * @param id         The ID of the animal.
     * @param name       The name of the animal.
     */
    public Animal(int id, String name) {
        this.ANIMAL_ID = id;
        this.name = name;
        this.isOrphaned = false; // animal is not an orphan by default
    }

    /**
     * @return The ID of the animal.
     */
    public int getAnimalID() {
        return this.ANIMAL_ID;
    }

    /**
     * @return The name of the animal.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return Whether or not the animal is an orphan.
     */
    public boolean getOrphanStatus() {
        return this.isOrphaned;
    }

    /**
     * @return The active time of the animal.
     */
    public abstract String getActiveTime();

    /**
     * @return The species of the animal.
     */
    public abstract String getSpecies();

    /**
     * @param name The name of the animal.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param isOrphaned Whether or not the animal is an orphan.
     */
    public void setOrphanStatus(boolean isOrphaned) {
        this.isOrphaned = isOrphaned;
    }
}
