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
    private final int TASK_ID;
    private int startHour;
    private int maxWindow;
    private int duration;
    private String taskType;
    private Animal animalTaskFor;
    private boolean extraVolunteerStatus;

    public Task(int TASK_ID, int startHour, int maxWindow, int duration, String taskType,
                Animal animal) {

        // The above would essentially re-direct the task class to another class which
        // would handle initialization of all Task data members and second volunteer,
        // although this method seems a bit redundant...

        this.TASK_ID = TASK_ID;
        this.maxWindow = maxWindow;
        this.startHour = startHour;
        this.duration = duration;
        this.taskType = taskType;
        this.animalTaskFor = animal;

    }

    public void setExtraVolunteerStatus(boolean extraVolunteerStatus) {
        this.extraVolunteerStatus = extraVolunteerStatus;
    }

    /**
     * @return The id of the task.
     */
    public int getTaskID() {
        return this.TASK_ID;
    }

    /**
     * @return The start time of the task.
     */
    public int getStartHour() {
        return this.startHour;
    }


    /**
     * @return The max window of the task.
     */
    public int getMaxWindow() {
        return this.maxWindow;
    }

    /**
     * @return The time it takes to complete the task.
     */
    public int getDuration() {
        return this.duration;
    }

    /**
     * @return The type of task.
     */
    public String getTaskType() {
        return this.taskType;
    }

    /**
     * @return The status of extra volunteer
     */
    public boolean getExtraVolunteerStatus() {
        return extraVolunteerStatus;
    }

    /**
     * @return The animal the task is for.
     */
    public Animal getAnimal() {
        return this.animalTaskFor;
    }

}

