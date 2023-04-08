package edu.ucalgary.oop;


/**
 * Task is a class which represents an individual task.
 * It contains information about the task which are provided in the database
 * @author     Edward An, Karam Baroud, Evan Barker, Jad Khalil
 * @version    1.7
 * @since      1.0
 */
public class Task {
    private final int TASK_ID;
    private int startHour;
    private int maxWindow;
    private int duration;
    private String taskType;
    private Animal animal;
    private boolean extraVolunteerStatus;
    private int prepTime;


    /**
     * Constructor for the Task class.
     * @param TASK_ID   The ID of the task.
     * @param startHour The start hour of the task.
     * @param maxWindow The max window that the task can be completed in.
     * @param duration  The time it takes to complete the task.
     * @param taskType  The description of the task.
     * @param animal    The animal that the task is for.
     * @exception IllegalArgumentException
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
        this.prepTime = 0; // 0 minutes by default
    }

    /**
     * Constructor for the Task class.
     * @param TASK_ID   The ID of the task.
     * @param startHour The start hour of the task.
     * @param maxWindow The max window that the task can be completed in.
     * @param duration  The time it takes to complete the task.
     * @param taskType  The description of the task.
     * @param animal    The animal that the task is for.
     * @param prepTime  The time it takes to prepare for a feeding task
     * @exception IllegalArgumentException
     */
    public Task(int TASK_ID, int startHour, int maxWindow, int duration, String taskType,
                Animal animal, int prepTime) throws IllegalArgumentException{

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
        this.prepTime = prepTime;
    }


    /**
     * @param startHour The start time of the task.
     * @exception IllegalArgumentException
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
     * @param prepTime The time it takes to prepare for the feeding task.
     */
    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
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
     * @return The animal the task is for.
     */
    public Animal getAnimal() { return this.animal;}

    /**
     * @return The status of extra volunteer
     */
    public boolean getExtraVolunteerStatus() {
        return extraVolunteerStatus;
    }

    /**
     * @return The time it takes to prepare for the feeding task.
     */
    public int getPrepTime() { return prepTime; }

}

