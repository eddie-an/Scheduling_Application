/**
@author     Group5
@version    1.4
@since      1.0
*/

/*
 * Task is a class which represents a task that a volunteer can do.
 * VolunteerGeneric is a generic class which is used to bind a volunteer to a task.
*/

package edu.ucalgary.oop;

import java.time.LocalDate;

public class Task {
    private LocalDate startTime;
    private int prepTime;
    private int taskTime;
    private String taskType;
    private VolunteerGeneric<String, Integer> volunteerOne; // object binded to Task object
    private VolunteerGeneric<String, Integer> volunteerTwo; // On - standby in case it needs to be used / accessed.
    private Animal animalTaskFor;

    /**
     * This is the constructor for the Task class.
     * 
     * @param startTime   The start time of the task.
     * @param prepTime    The preparation time of the task. Some tasks don't require
     *                    preparation time.
     * @param taskTime    The time it takes to complete the task.
     * @param taskType    The type of task.
     * @param volunteerId The ID of the volunteer assigned to the task.
     * @param animal      The animal the task is for.
     */
    public Task(LocalDate startTime, int prepTime, int taskTime, String taskType, String volunteerId, Animal animal) {

        // The above would essentially re-direct the task class to another class which
        // would handle initialization of all Task data members and second volunteer,
        // although this method seems a bit redundant...

        Integer tempInt = 60;
        this.startTime = startTime;
        this.prepTime = prepTime;
        this.taskTime = taskTime;
        this.taskType = taskType;
        this.volunteerOne = new VolunteerGeneric<String, Integer>(volunteerId, tempInt);
        this.animalTaskFor = animal;

    }

    /**
     * @return The start time of the task.
     */
    public LocalDate getStartTime() {
        return this.startTime;
    }

    /**
     * @return The preparation time of the task.
     */
    public int getPrepTime() {
        return this.prepTime;
    }

    /**
     * @return The time it takes to complete the task.
     */
    public int getTaskTime() {
        return this.taskTime;
    }

    /**
     * @return The type of task.
     */
    public String getTaskType() {
        return this.taskType;
    }

    /**
     * @return The volunteer assigned to the task.
     */
    public VolunteerGeneric<String, Integer> getPrimaryVolunteer() {
        return this.volunteerOne;
    }

    /**
     * @return The volunteer assigned to the task.
     */
    public VolunteerGeneric<String, Integer> getSecondaryVolunteer() {
        return this.volunteerTwo;
    }

    /**
     * @return The animal the task is for.
     */
    public Animal getAnimal() {
        return this.animalTaskFor;
    }

}

class VolunteerGeneric<K, T> { // this class is binded strictly to the Task class, during all instances of
                               // Task.
    private K volunteerID;
    private T timeLeft;

    /**
     * This is the constructor for the VolunteerGeneric class.
     * 
     * @param first  The ID of the volunteer.
     * @param second The time left for the volunteer.
     */
    public VolunteerGeneric(K first, T second) {
        this.volunteerID = first;
        this.timeLeft = second;
    }

    /**
     * @return The time left for the volunteer.
     */
    
    public T getTimeLeft() {
        return timeLeft;
    }

    /**
     * @return The ID of the volunteer.
     */
    public K getVolunteerID() {
        return volunteerID;
    }

}
