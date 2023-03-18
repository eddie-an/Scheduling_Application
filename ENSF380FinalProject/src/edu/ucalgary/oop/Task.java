package edu.ucalgary.oop;

import java.time.LocalDate;



public class Task {
    private LocalDate startTime;
    private int prepTime;
    private int taskTime;
    private String taskType;
    private VolunteerGeneric<String, Integer> volunteers;  // object binded to Task object
    private Animal animalTaskFor;

    public Task(LocalDate startTime, int prepTime, int taskTime, String taskType, String volunteerId, Animal animal) {
        if(prepTime + taskTime > 60) {
            throw new ExtraVolunterException();   // deal with this later, this exception handles Task object creation
        }
        else {
            Integer tempInt = 60;
            this.startTime = startTime;
            this.prepTime = prepTime;
            this.taskTime = taskTime;
            this.taskType = taskType;
            this.volunteers = new VolunteerGeneric<String, Integer>(volunteerId, tempInt);
            this.animalTaskFor = animal;
        }

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
