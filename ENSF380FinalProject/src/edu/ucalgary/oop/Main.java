package edu.ucalgary.oop;

/**
@author     Group5
@version    1.0
@since      1.0
*/

/*
 * Main is the class that contains the main method. It is the entry point of the program.
 * Main will have a GUI that will allow the user to interact with the program for these purposes:
 *     - The program should also display all scheduling information and require a confirmation from the user for
 *       each instance that the backup volunteer needs to be contacted.
 * 
 *     - Users should be provided with meaningful error messages appropriate to the audience
 *       (end user, rather than programmer). Error messages must explain what was wrong and
 *       how it can be corrected (e.g., "It was impossible to complete the schedule due to too
 *       many events scheduled at 11 AM. Please shift some of the following activities:
 *       rebandage head wound (Spike), administer antibiotics (Spike, Shadow)."
 * 
 *     - Users should be given the opportunity to correct their mistakes, and opportunities for
 *       mistakes should be limited. 
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class Main implements ActionListener {

    private Connection dbConnection;
    private ResultSet results;

    public static void main(String args[]) throws Exception {

        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame(" Example Wildlife Rescue Scheduler");
            frame.setSize(400, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel buttonsPanel = new JPanel();
            JButton myButton = new JButton("Build Schedule Based On Database");

            Main buttonListener = new Main();
            myButton.addActionListener(buttonListener);
            buttonsPanel.add(myButton);
            frame.getContentPane().add(BorderLayout.NORTH, buttonsPanel);
            frame.setVisible(true);

        });

        Main getTreatments = new Main();
        getTreatments.createConnection();
        ArrayList<ArrayList<String>> allAnimals = getTreatments.animalInfo();
        ArrayList<ArrayList<String>> allTasks = getTreatments.taskInfo();
        ArrayList<ArrayList<String>> allTreatments = getTreatments.TreatmentInfo();

        CreateObjects(allAnimals, allTasks, allTreatments);

        getTreatments.close();

    }

    public void actionPerformed(ActionEvent event) {

        // Here call the method that will initiate the schedule building process
        // Exceptions which require calling in a backup volunteer should be handled here

        // add try block that calls the method that builds the schedule

        // add catch block that confirms backup volunteer and makes changes accordingly
        // to the schedule

        JPanel backupPanel = new JPanel();
        JButton backupButton = new JButton("Confirm Backup Volunteer");

        backupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(null, "Backup volunteer confirmed");

                // Remove the backup button from its parent container
                backupPanel.remove(backupButton);
                backupPanel.revalidate();
                backupPanel.repaint();
            }
        });

        backupPanel.add(backupButton);
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((Component) event.getSource());
        frame.getContentPane().add(BorderLayout.SOUTH, backupPanel);
        frame.pack();

        JButton myButton = (JButton) event.getSource();
        myButton.setVisible(false);
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
    public ArrayList<ArrayList<String>> animalInfo() {

        // StringBuffer animals = new StringBuffer();
        ArrayList<ArrayList<String>> animals = new ArrayList<ArrayList<String>>();
        ArrayList<String> currAnimals = new ArrayList<String>();

        try {
            Statement myStmt = dbConnection.createStatement();
            results = myStmt.executeQuery("SELECT * FROM ANIMALS");

            while (results.next()) {

                currAnimals.add(results.getString("AnimalId"));
                currAnimals.add(results.getString("AnimalNickname"));
                currAnimals.add(results.getString("AnimalSpecies"));

                animals.add(currAnimals);
                currAnimals.clear();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return animals;
    }

    public ArrayList<ArrayList<String>> taskInfo() {
        // StringBuffer tasks = new StringBuffer();
        ArrayList<ArrayList<String>> tasks = new ArrayList<ArrayList<String>>();
        ArrayList<String> currTasks = new ArrayList<String>();

        try {
            Statement myStmt = dbConnection.createStatement();
            results = myStmt.executeQuery("SELECT * FROM TASKS");

            while (results.next()) {

                currTasks.add(results.getString("TaskID"));
                currTasks.add(results.getString("Description"));
                currTasks.add(results.getString("Duration"));
                currTasks.add(results.getString("MaxWindow"));

                tasks.add(currTasks);
                currTasks.clear();

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return tasks;

    }

    public ArrayList<ArrayList<String>> TreatmentInfo() {

        ArrayList<ArrayList<String>> treatments = new ArrayList<ArrayList<String>>();
        ArrayList<String> currTreatments = new ArrayList<String>();

        // StringBuffer treatments = new StringBuffer();

        try {
            Statement myStmt = dbConnection.createStatement();
            results = myStmt.executeQuery("SELECT * FROM TREATMENTS");

            while (results.next()) {

                currTreatments.add(results.getString("AnimalID"));
                currTreatments.add(results.getString("TaskID"));
                currTreatments.add(results.getString("StartHour"));
                treatments.add(currTreatments);
                currTreatments.clear();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return treatments;
    }

    public void close() {
        try {
            results.close();
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void CreateObjects(ArrayList<ArrayList<String>> animals, ArrayList<ArrayList<String>> tasks,
            ArrayList<ArrayList<String>> treatments) {

        for (int i = 0; i < treatments.size(); i++) {

        }

    }

}