package edu.ucalgary.oop;

public class Volunteer {
    private String volunteerID;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Volunteer(String volunteerID, String firstName, String lastName, String phoneNumber) {
        this.volunteerID = volunteerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getVolunteerID() {
        return volunteerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setVolunteerID(String volunteerID) {
        this.volunteerID = volunteerID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
