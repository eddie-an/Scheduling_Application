package edu.ucalgary.oop;

import java.sql.*;

public class FetchSQL {

    private Connection dbConnection;
    private ResultSet results;

    public FetchSQL() {

    }

    public void createConnection() {

        try {
            // this connection is going to be different for every user change the url user
            // and password for each user
            dbConnection = DriverManager.getConnection("jdbc:mysql://localhost/treatment", "root", "Fuckemail");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // generic method to get animal id and information
    public String animalInfo() {

        StringBuffer animals = new StringBuffer();

        try {
            Statement myStmt = dbConnection.createStatement();
            results = myStmt.executeQuery("SELECT * FROM ANIMALS");

            while (results.next()) {

                animals.append(results.getString("AnimalId") + ", " + results.getString("AnimalNickname") + ", "
                        + results.getString("AnimalSpecies"));
                animals.append('\n');
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return animals.toString();
    }

    public String taskInfo() {
        StringBuffer tasks = new StringBuffer();

        try {
            Statement myStmt = dbConnection.createStatement();
            results = myStmt.executeQuery("SELECT * FROM TASKS");

            while (results.next()) {

                tasks.append(results.getString("TaskID") + ", " + results.getString("Description") + ", "
                        + results.getString("Duration") + results.getString("MaxWindow"));
                tasks.append('\n');
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return tasks.toString();

    }

    public void close() {
        try {
            results.close();
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {

        FetchSQL getTreatments = new FetchSQL();
        getTreatments.createConnection();
        String allAnimals = getTreatments.animalInfo();
        System.out.println(allAnimals);

        getTreatments.close();

    }
}