package edu.ucalgary.oop;

import java.time.LocalDate;

import edu.ucalgary.oop.Animal;

public class Task {
    private LocalDate startTime;
    private int prepTime;
    private int taskTime;
    private String taskType;
    private Volunteer assignedVolunteer;
    private Animal animalTaskFor;

    public Task(LocalDate startTime, int prepTime, int taskTime, String taskType, Volunteer volunteer, Animal animal) {
        this.startTime = startTime;
        this.prepTime = prepTime;
        this.taskTime = taskTime;
        this.taskType = taskType;
        this.assignedVolunteer = volunteer;
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

    }

}
