package edu.ucalgary.oop;

import java.time.LocalDate;



public class Task {
    private LocalDate startTime;
    private int prepTime;
    private int taskTime;
    private String taskType;
    private VolunteerGeneric<String, Integer> volunteerOne;  // object binded to Task object
    private VolunteerGeneric<String, Integer> volunteerTwo;  // On - standby in case it needs to be used / accessed.
    private Animal animalTaskFor;

    public Task(LocalDate startTime, int prepTime, int taskTime, String taskType, String volunteerId, Animal animal) {
        
            
          
            
        //  The above would essentially re-direct the task class to another class which would handle initialization of all Task data members and second volunteer,
        //  although this method seems a bit redundant...
        
        

        Integer tempInt = 60;
        this.startTime = startTime;
        this.prepTime = prepTime;
        this.taskTime = taskTime;
        this.taskType = taskType;
        this.volunteerOne = new VolunteerGeneric<String, Integer>(volunteerId, tempInt);
        this.animalTaskFor = animal;
        

    }

    public LocalDate getStartTime() {
        return this.startTime;
    }

    public int getPrepTime() {
        return this.prepTime;
    }

    public int getTaskTime() {
        return this.taskTime;
    }

    public String getTaskType() {
        return this.taskType;
    }

    public Volunteer getAssignedVolunteer() {
        return this.assignedVolunteer;
    }

    public Animal getAnimal() {
        return this.animalTaskFor;
    }

}

class VolunteerGeneric<K, T> {   // this class is binded strictly to the Task class, during all instances of Task.
    private K volunteerID;
    private T timeLeft;
    
    public VolunteerGeneric (K first, T second) {
        this.volunteerID = first;
        this.timeLeft = second;
    }

    public T getTimeLeft(){
        return timeLeft;
    }

    public K getVolunteerID() {
        return volunteerID;
    }


}
