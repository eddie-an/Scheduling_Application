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
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.*;
import java.sql.Array;
import java.util.regex.*;
import java.util.*;
import java.util.Arrays.*;

public class Main implements ActionListener {

    private HashMap<Integer, ArrayList<Task>> fullArray = new HashMap<>();

    private Connection dbConnection;
    private ResultSet results;

    public static void main(String args[]) throws Exception {

        Main getTreatments = new Main();
        getTreatments.createConnection();

        HashMap<Integer, ArrayList<Task>> TasksReadIn = getTreatments.TasksReadIn();

        CreateObjects(TasksReadIn);

        getTreatments.close();

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

    }

    /** Helper Method **/
    public Task helper(int TASK_ID, int maxWindow, int prepTime, int taskTime, String taskType, Animal animal) {
        Task newTaskObject = new Task(TASK_ID, maxWindow, prepTime, taskTime, taskType, animal);
        return newTaskObject;
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
            dbConnection = DriverManager.getConnection("jdbc:mysql://localhost/ewr", "root", "SQL123456");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /** This method reads-in and parses the data, and instantiates new Task objects accordingly **/
    public HashMap<Integer, ArrayList<Task>> TasksReadIn() {
        int key;
        HashMap<Integer, ArrayList<Task>> fullArrayList = new HashMap<Integer, ArrayList<Task>>();

        StringBuffer treatmentsReader = new StringBuffer();

        try {
            Statement myStmt = dbConnection.createStatement();
            results = myStmt.executeQuery("SELECT ANIMALS.*, TASKS.*, TREATMENTS.StartHour\n" +
                    "FROM TREATMENTS\n" +
                    "JOIN ANIMALS ON ANIMALS.AnimalID = TREATMENTS.AnimalID\n" +
                    "JOIN TASKS ON TREATMENTS.TaskID = TASKS.TaskID\n" +
                    "ORDER BY TREATMENTS.StartHour ASC;");

            while (results.next()) {
                System.out.println("\n ---- \n");
                Animal newAnimal = null;
                String nickName = results.getString("AnimalNickname");
                String typeOfAnimal = results.getString("AnimalSpecies");
                Integer animalID = Integer.parseInt(results.getString("AnimalID"));
                if(typeOfAnimal=="Beaver") {
                    newAnimal = new Beaver(animalID, typeOfAnimal, nickName);
                } else if (typeOfAnimal=="Raccoon") {
                    newAnimal = new Raccoon(animalID, typeOfAnimal, nickName);
                } else if (typeOfAnimal=="Fox") {
                    newAnimal = new Fox(animalID, typeOfAnimal, nickName);
                } else if (typeOfAnimal=="Coyote") {
                    newAnimal = new Coyote(animalID, typeOfAnimal, nickName);
                } else if (typeOfAnimal=="Porcupine") {
                    newAnimal = new Porcupine(animalID, typeOfAnimal, nickName);
                }
                key = Integer.parseInt(results.getString("StartHour"));
                Task instantiatedTask = helper(Integer.parseInt(results.getString("TaskID")), Integer.parseInt(results.getString("StartHour")) , Integer.parseInt(results.getString("MaxWindow")),
                        Integer.parseInt(results.getString("Duration")), results.getString("Description"), newAnimal);
                System.out.println("Results: " + results.getString("AnimalNickname") + ", " + results.getString("AnimalSpecies") + "\n");
                ArrayList<Task> tempArrayList = this.fullArray.get(key);
                this.fullArray.put(key, tempArrayList);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return fullArrayList;
    }

    public void close() {
        try {
            results.close();
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static void CreateObjects(HashMap<Integer, ArrayList<Task>> databaseAllRecords) {

        /*
        ArrayList<String> tmp = new ArrayList<String>();
        ArrayList<String> toInsert = new ArrayList<>();

        tmp = databaseAllRecords.get(0);
        String prevH = tmp.get(0);
        toInsert.addAll(tmp.subList(0, 6));
        String hour = "";

        for (int i = 1; i < databaseAllRecords.size(); i++) {

            hour = tmp.get(5);

            if (hour != prevH) {
                fullArray.put(hour, toInsert);
                toInsert.clear();
            }
            toInsert.addAll(tmp.subList(0, 6));
            prevH = hour;

        }

        fullArray.put(hour, toInsert);
        */
    }

    private static void createDemoSchedule() {
        // iterate through the keys of the hashmap
        // look at the tasks
        // then apply the correct math to get the time of each task and if backup is
        // required

    }

}