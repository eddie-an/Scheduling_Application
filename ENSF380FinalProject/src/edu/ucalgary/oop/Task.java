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
    private Animal animal;
    private boolean extraVolunteerStatus;


    /**
     * Constructor for the Task class.
     * @param TASK_ID   The ID of the task.
     * @param startHour The start hour of the task.
     * @param maxWindow The max window that the task can be completed in.
     * @param duration  The time it takes to complete the task.
     * @param taskType  The description of the task.
     * @param animal    The animal that the task is for.
     */
    public Task(int TASK_ID, int startHour, int maxWindow, int duration, String taskType,
                Animal animal) throws IllegalArgumentException{

        if (startHour < 0 || startHour > 23)
        {
            throw new IllegalArgumentException();
        }
        this.TASK_ID = TASK_ID;
        this.startHour = startHour;
        this.maxWindow = maxWindow;
        this.duration = duration;
        this.taskType = taskType;
        this.animal = animal;
        this.extraVolunteerStatus = false; // false by default
    }

    /**
     * @param startHour The start time of the task.
     */
    public void setStartHour(int startHour) throws IllegalArgumentException {
        if (startHour < 0 || startHour > 23)
        {
            throw new IllegalArgumentException();
        }
        this.startHour = startHour;
    }

    /**
     * @param maxWindow The max window of the task.
     */
    public void setMaxWindow(int maxWindow) {
        this.maxWindow = maxWindow;
    }

    /**
     * @param duration The time it takes to complete the task.
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * @param taskType The type of task being done.
     */
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    /**
     * @param animal The animal being taken care of.
     */
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    /**
     * @param extraVolunteerStatus The status of the extra volunteer.
     */
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
        return this.animal;
    }

}

