package edu.ucalgary.oop;
/**
 @author     Group5
 @version    1.1
 @since      1.0
 */

/*
 * Main is the class that contains the main method and establishes connection with the SQL database.
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

    private static HashMap<Integer, ArrayList<Task>> databaseRecords = new HashMap<>();
    private static ArrayList<Animal> animalList = new ArrayList<Animal>();
 
    private Connection dbConnection;
    private ResultSet results;

    /**
     * The main method which connects to the database and creates the GUI
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {

        Main getTreatments = new Main();
        getTreatments.createConnection();

        getTreatments.animalsReadIn();
        getTreatments.TasksReadIn();

        // CreateObjects(databaseRecords);

        getTreatments.close();


        rearrangeTasks(0);
        String data = PrintLog.dataToString(databaseRecords);
        System.out.println(data);


        addBackupVolunteer();
        data = PrintLog.dataToString(databaseRecords);
        System.out.println(data);

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


    /**
     * adds general feeding for non-orphaned animals, and cleaning tasks as well
     */
    public void addGeneralFeedingAndCleaning() {
    //TaskID = -1 for general feeding
    //TaskID = -2 for general cleaning
    //so it doesn't conflict with the actual taskID's in the database
        for(Animal animal : animalList) {

            Task cleaning = new Task(-2, 0, 24, animal.getSpecies() == "porcupine" ? 10 : 5, "general cleaning", animal);
            Task feeding = null;

            if(animal.getOrphanStatus() == false) {

                if(animal.getActiveTime() == "diurnal") {

                    feeding = new Task(-1, 8, 3, 5, "general feeding", animal);
                } 
                else if(animal.getActiveTime() == "crepuscular") {
                    feeding = new Task(-1, 19, 3, 5, "general feeding", animal);
                }
                //only thing left is nocturnal
                else {
                    feeding = new Task(-1, 0, 3, 5, "general feeding", animal);
                }

                if(databaseRecords.containsKey(feeding.getStartHour())) {
                    this.databaseRecords.get(feeding.getStartHour()).add(feeding);
                }
                else {
                    ArrayList<Task> taskArrayList = new ArrayList<>();
                    taskArrayList.add(feeding);
                    this.databaseRecords.put(feeding.getStartHour(), taskArrayList);
                }
            }



            if(databaseRecords.containsKey(cleaning.getStartHour())) {
                this.databaseRecords.get(cleaning.getStartHour()).add(cleaning);
            }
            else {
                ArrayList<Task> taskArrayList = new ArrayList<>();
                taskArrayList.add(cleaning);
                this.databaseRecords.put(cleaning.getStartHour(), taskArrayList);
            }
        }
    }

    /** Helper Method **/ //Do we really need this? we figured out a logic to create objects inside TasksReadIn
    public Task helper(int TASK_ID, int maxWindow, int prepTime, int taskTime, String taskType, Animal animal) {
        Task newTaskObject = new Task(TASK_ID, maxWindow, prepTime, taskTime, taskType, animal);
        return newTaskObject;
    }

    /**
     * Needs to be implemented but is not yet
     * @param event     The event that triggers the action
     */
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

    /**
     * creates a connection to the database, this will be different for each user
     */
    public void createConnection() {

        try {
            // this connection is going to be different for every user change the url user
            // and password for each user
            dbConnection = DriverManager.getConnection("jdbc:mysql://localhost/ewr", "root", "SQL123456");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Reads in and instantiates the animals from the database
     */
    public void animalsReadIn() {
        StringBuffer treatmentsReader = new StringBuffer();

        try {
            Statement myStmt = dbConnection.createStatement();
            results = myStmt.executeQuery("SELECT ANIMALS.*\n" +
                    "FROM ANIMALS\n" +
                    "ORDER BY ANIMALS.AnimalID ASC;");
            while (results.next())
            {
                String nickName = results.getString("AnimalNickname");
                String typeOfAnimal = results.getString("AnimalSpecies");
                Integer animalID = Integer.parseInt(results.getString("AnimalID"));
                //Integer key = Integer.parseInt(results.getString("StartHour"));
                if(typeOfAnimal.equals("beaver")) {
                    Beaver newAnimal = new Beaver(animalID, nickName);
                    animalList.add(newAnimal);
                } else if (typeOfAnimal.equals("raccoon")) {
                    Raccoon newAnimal= new Raccoon(animalID, nickName);
                    animalList.add(newAnimal);
                } else if (typeOfAnimal.equals("fox")) {
                    Fox newAnimal= new Fox(animalID, nickName);
                    animalList.add(newAnimal);
                } else if (typeOfAnimal.equals("coyote")) {
                    Coyote newAnimal= new Coyote(animalID, nickName);
                    animalList.add(newAnimal);
                } else if (typeOfAnimal.equals("porcupine")) {
                    Animal newAnimal= new Porcupine(animalID, nickName);
                    animalList.add(newAnimal);
                }


            }

            myStmt = dbConnection.createStatement();
            results = myStmt.executeQuery("SELECT ANIMALS.*, TREATMENTS.TreatmentId\n" +
                    "FROM ANIMALS\n" +
                    "JOIN TREATMENTS ON ANIMALS.AnimalID = TREATMENTS.AnimalID;");
            while (results.next()) {
                int animalID = results.getInt("AnimalID");
                int treatmentID = results.getInt("TreatmentID");
                if (treatmentID == 1) // animals with treatmentID of 1 is always an orphan
                {
                    animalList.get(animalID - 1).setOrphanStatus(true);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** 
     * This method reads-in and parses the data, and instantiates new Task objects accordingly 
     */
    public void TasksReadIn() {
        StringBuffer treatmentsReader = new StringBuffer();

        try {
            Statement myStmt = dbConnection.createStatement();
            results = myStmt.executeQuery("SELECT ANIMALS.*, TASKS.*, TREATMENTS.StartHour\n" +
                    "FROM TREATMENTS\n" +
                    "JOIN ANIMALS ON ANIMALS.AnimalID = TREATMENTS.AnimalID\n" +
                    "JOIN TASKS ON TREATMENTS.TaskID = TASKS.TaskID\n" +
                    "ORDER BY TREATMENTS.StartHour ASC;");
            Integer prevStartHour = -1;

            while (results.next()) {

                System.out.println("\n ---- \n");
                Integer startHour = Integer.parseInt(results.getString("StartHour"));
                int animalID = results.getInt("AnimalID");
                String nickName = results.getString("AnimalNickname");
                String typeOfAnimal = results.getString("AnimalSpecies");
                int taskID = results.getInt("TaskID");
                String taskDescription = results.getString("Description");
                int duration = results.getInt("Duration");
                int maxWindow = results.getInt("MaxWindow");
                Animal newAnimal = animalList.get(animalID - 1);
                Task newTask = new Task(taskID, startHour, maxWindow, duration, taskDescription, newAnimal);

                if (!(prevStartHour.equals(startHour))) { // start hour on current row is different from previous row
                    prevStartHour = startHour;
                    ArrayList<Task> taskArrayList = new ArrayList<>();
                    taskArrayList.add(newTask);
                    this.databaseRecords.put(startHour, taskArrayList);
                }
                else { // start hour on current row is the same as previous row
                    this.databaseRecords.get(startHour).add(newTask);
                }

                //Task instantiatedTask = helper(taskID, startHour, maxWindow, duration, taskDescription, newAnimal);
                System.out.println("Results: " + results.getString("AnimalNickname") + ", " + results.getString("AnimalSpecies") + "\n");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        addGeneralFeedingAndCleaning();
    }


    /**
     * This method closes the connection to the database
     */
    public void close() {
        try {
            results.close();
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // do we really need this?
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

    public static void rearrangeTasks(int key) {
        // iterate through the keys of the hashmap
        // look at the tasks
        // then apply the correct math to get the time of each task and if backup is
        // required

        // iterate through the keys of the hashmap
        // check if the time is greater than 60
        // if it is, move the task to the next hour
        // if the next hour is not empty, check if the time is greater than 60
        // if it is, move the task to the next hour

        // NEEDS TO BE ABLE ACCOUNT FOR 22 -> 23 -> 0 
        // NEEDS TO THROW AN EXCEPTION IF ALL HOURS WITHIN THE MAXWINDOW ARE FULL

        ArrayList<Task> tasks = databaseRecords.get(key);
        int totalTime = 0;

        if(tasks != null) {

            Iterator<Task> it = tasks.iterator();

            while (it.hasNext()) {
                Task task = it.next();
                totalTime += task.getDuration();

                if (totalTime > 60) {
                    // what if it goes 22, 23, 24? should go 22, 23, 0, maybe modulo
                    for(int j = task.getStartHour() + 1; j < task.getStartHour() + task.getMaxWindow(); j++) {
                        if (databaseRecords.containsKey(j)) {
                            ArrayList<Task> temp = databaseRecords.get(j);
                            int time = 0;
                            for(Task t : temp) {
                                time += t.getDuration();
                            }

                            if (time + task.getDuration() <= 60) {
                                temp.add(task);
                                databaseRecords.put(j, temp);      
                                break;
                            }
                        }
                        else {
                            ArrayList<Task> temp = new ArrayList<>();
                            temp.add(task);
                            databaseRecords.put(j, temp);
                            break;
                        }
                    }
                    tasks.remove(task);
                    break;
                }
            }
            databaseRecords.put(key, tasks);
        }
        
        if (totalTime > 60 ) {
            rearrangeTasks(key);
        }
        else if (key < 23) {
            rearrangeTasks(key + 1);
        }

    }

    /**
     * Performs the necessary calculations to determine whether or not a backup volunteer is required
     */
    private static void addBackupVolunteer() {
        // iterate through the keys of the hashmap
        // look at the tasks
        // then apply the correct math to get the time of each task and if backup is
        // required
        databaseRecords.forEach((startHour, tasks)-> {
            int totalTime = 0;
            for (Task task: tasks)
            {
                totalTime += task.getDuration();
                if (totalTime > 60) {
                    break;
                }
            }
            if (totalTime > 60) // At this point of the function,
                // the user should confirm whether or not they want to add another volunteer
            {
                tasks.forEach((task)-> {
                    task.setExtraVolunteerStatus(true);
                });
            }
        });
    }

}