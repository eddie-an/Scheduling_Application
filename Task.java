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
    private int startTime;
    private int maxWindow;
    private int taskTime;
    private String taskType;
    private Animal animalTaskFor;

    public Task(int TASK_ID, int startTime, int maxWindow, int taskTime, String taskType,
                Animal animal) {

        // The above would essentially re-direct the task class to another class which
        // would handle initialization of all Task data members and second volunteer,
        // although this method seems a bit redundant...

        this.TASK_ID = TASK_ID;
        Integer tempInt = 60;
        this.maxWindow = maxWindow;
        this.startTime = startTime;
        this.taskTime = taskTime;
        this.taskType = taskType;

        this.animalTaskFor = animal;

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
    public int getStartTime() {
        return this.startTime;
    }

    /**
     * @return The preparation time of the task.
     */
    public int getPrepTime() {
        return this.startTime;
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

    /**
     * @return The volunteer assigned to the task.
     */


    /**
     * @return The animal the task is for.
     */
    public Animal getAnimal() {
        return this.animalTaskFor;
    }

}

