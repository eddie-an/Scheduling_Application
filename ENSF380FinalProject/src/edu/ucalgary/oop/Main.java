package edu.ucalgary.oop;
/**
 @author     Group5
 @version    1.1
 @since      1.0
 */

/*
 * Main is the class that contains the main method and establishes connection with the SQL database.
 * Main will have a edu.ucalgary.oop.GUI that will allow the user to interact with the program for these purposes:
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

    private static TreeMap<Integer, ArrayList<Task>> databaseRecords = new TreeMap<>();
    private static ArrayList<Animal> animalList = new ArrayList<Animal>();
    private static String data; // created a static variable that will contain schedule String

    private Connection dbConnection;
    private ResultSet results;

    // had to make alot of global varialbes it may look ugly but its the only way
    // >:(
    private JFrame frame;
    private JButton backupVolenteerButton, generateSchedule;
    private JButton refactorSchedule;
    private JLabel hoursBooked;

    private JPanel buttonsPanel;

    // used for the modify schedule button
    TreeMap<Integer, ArrayList<Integer>> tasksToChange = new TreeMap<>();
    // ArrayList<JTextField> textFieldIn = new ArrayList<JTextField>();
    HashMap<JTextField, ArrayList<Integer>> textFieldIn = new HashMap<JTextField, ArrayList<Integer>>();

    private JButton submit = new JButton("submit modified hours"); // used to submit the modified hours

    public void InitGUI() {
        final Boolean addVolunteerFinal = isValidSchedule();

        frame = new JFrame(" Example Wildlife Rescue Scheduler");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttonsPanel = new JPanel();
        JButton display = new JButton("Build Schedule Based On Database");

        JButton addVolunteerPrompt = new JButton("add Volunteer");

        // buttonsPanel.add(submit);
        submit.addActionListener(this);

        Main buttonListener = new Main();
        display.addActionListener(buttonListener);
        addVolunteerPrompt.addActionListener(buttonListener);

        if (addVolunteerFinal == true)
            buttonsPanel.add(display);
        else {
            // buttonsPanel.add(addVolunteerPrompt);

            tasksToChange = showTasksToBeMoved(); //this is what should be called
            //ArrayList<Integer> newTask = new ArrayList<>(); // delete everything here fro actual code
            //newTask.add(3);
            //tasksToChange.put(1, newTask);
            //tasksToChange.put(2, newTask);
            // tasksToChange.put(3, newTask);


            StringBuilder temp = new StringBuilder();

            if (tasksToChange.size() == 1) {
                temp.append("hour ");
            } else {
                temp.append("hours ");
            }

            frame.setLayout(new GridLayout());

            // this loop will create a an array list of all the hourse that may require a
            // backup volenteer
            for (Map.Entry<Integer, ArrayList<Integer>> entry : tasksToChange.entrySet()) {
                temp.append(entry.getKey() + " ");
            }

            temp.append(" are overbooked call backup volenteer(s) ?");
            hoursBooked = new JLabel(temp.toString());
            backupVolenteerButton = new JButton("call backup volenteer(s)");

            buttonsPanel.add(hoursBooked);
            buttonsPanel.add(backupVolenteerButton);

            buttonsPanel.add(hoursBooked);
            backupVolenteerButton.addActionListener(this);
            buttonsPanel.add(backupVolenteerButton);
            // set it so if backup is pressed addBackupVolunteer() is called

            generateSchedule = new JButton("generate fixed schedule");
            generateSchedule.addActionListener(this);
            generateSchedule.setVisible(false);
            buttonsPanel.add(generateSchedule, BorderLayout.NORTH);

            refactorSchedule = new JButton("Modify schedule ");
            refactorSchedule.addActionListener(this);
            buttonsPanel.add(refactorSchedule);

        }
        frame.getContentPane().add(BorderLayout.NORTH, buttonsPanel);
        // frame.setVisible(true);
    }

    public void showGUI() {
        EventQueue.invokeLater(() -> {
            frame.setVisible(true);
        });
    }

    /**
     * The main method which connects to the database and creates the
     * edu.ucalgary.oop.GUI
     *
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {

        Main getTreatments = new Main();
        getTreatments.createConnection();

        getTreatments.animalsReadIn();
        getTreatments.TasksReadIn();

        getTreatments.close();
        rearrangeTasks();
        addBackupVolunteer();
        data = PrintLog.dataToString(databaseRecords);
        System.out.println(data);

    }

    public static void CreateScheduleOnClick() {
        PrintLog.writeToSchedule(data);
    }

    /**
     * adds general feeding for non-orphaned animals, and cleaning tasks as well
     */
    public void addGeneralFeedingAndCleaning() {
        // TaskID = -1 for general feeding
        // TaskID = -2 for general cleaning
        // so it doesn't conflict with the actual taskID's in the database
        for (Animal animal : animalList) {

            Task cleaning = new Task(-2, 0, 24, animal.getSpecies().equals("porcupine") ? 10 : 5, "general cleaning",
                    animal);
            Task feeding = null;

            if (animal.getOrphanStatus() == false) {

                if (animal.getSpecies().equals("coyote")) {
                    feeding = new Task(-1, 19, 3, 5, "Coyote feeding", animal, 10);
                }
                else if(animal.getSpecies().equals("fox")) {
                    feeding = new Task(-1, 0, 3, 5, "Fox feeding", animal, 5);
                }
                else if(animal.getSpecies().equals("beaver")) {
                    feeding = new Task(-1, 8, 3, 5, "Beaver feeding", animal);
                }
                else if(animal.getSpecies().equals("porcupine")) {
                    feeding = new Task(-1, 19, 3, 5, "Porcupine feeding", animal);
                }
                else { //must be a raccoon
                    feeding = new Task(-1, 0, 3, 5, "Raccoon feeding", animal);
                }
                if (databaseRecords.containsKey(feeding.getStartHour())) {
                    this.databaseRecords.get(feeding.getStartHour()).add(feeding);
                } else {
                    ArrayList<Task> taskArrayList = new ArrayList<>();
                    taskArrayList.add(feeding);
                    this.databaseRecords.put(feeding.getStartHour(), taskArrayList);
                }
            }

            if (databaseRecords.containsKey(cleaning.getStartHour())) {
                this.databaseRecords.get(cleaning.getStartHour()).add(cleaning);
            } else {
                ArrayList<Task> taskArrayList = new ArrayList<>();
                taskArrayList.add(cleaning);
                this.databaseRecords.put(cleaning.getStartHour(), taskArrayList);
            }
        }
    }

    /**
     * Needs to be implemented but is not yet
     *
     * @param event The event that triggers the action
     */
    public void actionPerformed(ActionEvent event) {

        // Here call the method that will initiate the schedule building process
        // Exceptions which require calling in a backup volunteer should be handled here

        // add try block that calls the method that builds the schedule

        // add catch block that confirms backup volunteer and makes changes accordingly
        // to the schedule

        // System.out.println(data);

        if (event.getSource() == backupVolenteerButton) {
            System.out.println("backup volenteers called");
            addBackupVolunteer();
            backupVolenteerButton.setVisible(false);
            hoursBooked.setVisible(false);
            refactorSchedule.setVisible(false);

            buttonsPanel.add(generateSchedule);
            generateSchedule.setVisible(true);
        }

        else if (event.getSource() == generateSchedule) {
            // if this button is pressed then generate the schedule into the text file
            System.out.println("schedule generated in schedule.txt");
            JLabel schedule = new JLabel("Schedule has now been generated in schedule.txt");
            buttonsPanel.add(schedule);
            hoursBooked.setVisible(false);
            generateSchedule.setVisible(false);
            schedule.setVisible(true);
            CreateScheduleOnClick();
        }

        else if (event.getSource() == refactorSchedule) {
            backupVolenteerButton.setVisible(false);
            hoursBooked.setVisible(false);
            System.out.println(tasksToChange);
            refactorSchedule.setVisible(false);

            StringBuilder StrDisplay = new StringBuilder();
            StringBuilder taskToMove = new StringBuilder();
            Boolean unableToMove = false;

            for (Map.Entry<Integer, ArrayList<Integer>> entry : tasksToChange.entrySet()) {
                StrDisplay.append("hour " + entry.getKey() + " is overbooked and contains the following tasks: ");

                for (int i = 0; i < entry.getValue().size(); i++) {
                    StrDisplay.append(entry.getValue().get(i) + " ");
                }

                JLabel tasksToModify = new JLabel(StrDisplay.toString());
                buttonsPanel.add(tasksToModify);
                tasksToModify.setVisible(true);

                for (int k = 0; k < entry.getValue().size(); k++) {

                    ArrayList<Integer> taskCanBeMoved = new ArrayList<Integer>();
                    try {
                        taskCanBeMoved = showEmptyTimeSlots(entry.getKey(), entry.getValue().get(k));
                    } catch (NullPointerException e) {
                        taskToMove
                                .append("Task " + entry.getValue().get(k) + " does not have any valid places to move");
                    }

                    if (taskCanBeMoved.size() != 0) {
                        taskToMove.append("Task " + entry.getValue().get(k) + " can be moved to hour");
                        for (int j = 0; j < taskCanBeMoved.size(); j++) {
                            taskToMove.append(" " + taskCanBeMoved.get(j));

                        }
                    } else {
                        // if a task does not have any valid places to move a backup volenteer will
                        // be required so this prompt will display saying x task at hour y NEEDS a
                        // backup volenteer
                        // then the button will be pressed and generate schedule button will be pressed
                        buttonsPanel.removeAll();
                        taskToMove.setLength(0);
                        taskToMove.append("Task " + entry.getValue().get(k) + " is overbooked at hour " + entry.getKey()
                                + " and cannot be moved anywhere" +
                                "\n" + " Backup Volenteer is required");
                        unableToMove = true;

                        break;
                    }

                    JLabel whereToMoveTask = new JLabel(taskToMove.toString());
                    buttonsPanel.add(whereToMoveTask);
                    whereToMoveTask.setVisible(true);
                    taskToMove.setLength(0);

                    JTextField textIn = new JTextField(2);
                    buttonsPanel.add(textIn);
                    textIn.setVisible(true);

                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(entry.getKey());
                    temp.add(entry.getValue().get(k));
                    textFieldIn.put(textIn, temp);

                }

                if (unableToMove) {
                    JLabel backupNeed = new JLabel(taskToMove.toString());
                    buttonsPanel.add(backupNeed);
                    backupNeed.setVisible(true);
                    buttonsPanel.add(backupVolenteerButton);
                    backupVolenteerButton.setVisible(true);

                }
                // tasksToModify.add(frame);

                StrDisplay.setLength(0);

            }

            if (!unableToMove) {

                buttonsPanel.add(submit);

                submit.setVisible(true);

            }

        }
        if (event.getSource() == submit) {
            System.out.println("submit pressed!");
            for (Map.Entry<JTextField, ArrayList<Integer>> entry : textFieldIn.entrySet()) {
                int newH = Integer.parseInt(entry.getKey().getText());
                // call each function
                modifyStartHour(entry.getValue().get(0), entry.getValue().get(1), newH);
                System.out.println(entry.getValue());
            }

            buttonsPanel.removeAll();
            buttonsPanel.revalidate();
            buttonsPanel.repaint();

            JLabel conformation = new JLabel("Tasks have now been moved accordingly");
            buttonsPanel.add(conformation);
            conformation.setVisible(true);

            // make the printschedule button visible now
            buttonsPanel.add(generateSchedule);
            generateSchedule.setVisible(true);

        }

        if (isValidSchedule()) {
            // Steps:

            // 1) Attain all the tasks for each hour of the day; traverse through the
            // dataBaseRecords Data structurer.
            Integer toStart = 0;

            int numRows = 0;
            while (toStart <= 23) {
                ArrayList<Task> storeForNow = databaseRecords.get(toStart);
                System.out.println(storeForNow);
                try {
                    for (Task t : storeForNow) {
                        numRows++;
                    }
                } catch (NullPointerException e) {
                    toStart++;
                }

                toStart++;
            }

            String[][] myTempStringArray = new String[numRows][5];
            int justBeforeAllInCount = 0;
            toStart = 0;
            boolean newFlag = false;
            int allInCount = 0;
            while (toStart <= 23) {
                ArrayList<Task> storeForNow = databaseRecords.get(toStart);
                if (toStart == 5) {
                    System.out.println(storeForNow);
                }
                justBeforeAllInCount = allInCount;
                try {
                    allInCount += storeForNow.size();
                } catch (NullPointerException e) {
                    toStart++;
                    newFlag = false;
                }
                try {
                    for (Task newTask : storeForNow) {
                        if (!newFlag) {
                            newFlag = true;
                            myTempStringArray[justBeforeAllInCount][0] = toStart.toString();
                        } else {
                            myTempStringArray[justBeforeAllInCount][0] = " ";
                        }
                        myTempStringArray[justBeforeAllInCount][1] = newTask.getTaskType();
                        if (newTask.getTaskType().equals("Kit feeding") || newTask.getTaskType().equals("Flush neck wound")) {
                            myTempStringArray[justBeforeAllInCount][2] = "-";
                        } else {
                            myTempStringArray[justBeforeAllInCount][2] = "1";
                        }
                        myTempStringArray[justBeforeAllInCount][3] = String.valueOf(newTask.getDuration());
                        myTempStringArray[justBeforeAllInCount][4] = "cool";
                        justBeforeAllInCount++;
                    }
                } catch (NullPointerException e) {
                    toStart++;
                    newFlag = false;
                }
                toStart++;
                newFlag = false;
            }

            String[][] data2 = {
                    { "Kundan Kumar Jha", "4031", "CSE", "Temp", "Temp" },
                    { "Anand Jha", "6014", "IT", "Temp", "Temp" }
            };
            String[] columnNames = { "Time", "Task", "Qty", "Time Spent", "Time Available" };
            // System.out.println(data);

            JFrame newFrame = new JFrame();
            newFrame.setTitle("Current Full Schedule");
            JTable j = new JTable(myTempStringArray, columnNames);
            j.setBounds(30, 40, 200, 300);
            JScrollPane sp = new JScrollPane(j);
            newFrame.add(sp);
            newFrame.setSize(500, 500);
            newFrame.setVisible(true);

        } else {
            // add text to the gui saying hours x, y, and z are overbooked
            // would you like to call a backup volenteer

        }
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
            while (results.next()) {
                String nickName = results.getString("AnimalNickname");
                String typeOfAnimal = results.getString("AnimalSpecies");
                Integer animalID = Integer.parseInt(results.getString("AnimalID"));
                // Integer key = Integer.parseInt(results.getString("StartHour"));
                if (typeOfAnimal.equals("beaver")) {
                    Beaver newAnimal = new Beaver(animalID, nickName);
                    animalList.add(newAnimal);
                } else if (typeOfAnimal.equals("raccoon")) {
                    Raccoon newAnimal = new Raccoon(animalID, nickName);
                    animalList.add(newAnimal);
                } else if (typeOfAnimal.equals("fox")) {
                    Fox newAnimal = new Fox(animalID, nickName);
                    animalList.add(newAnimal);
                } else if (typeOfAnimal.equals("coyote")) {
                    Coyote newAnimal = new Coyote(animalID, nickName);
                    animalList.add(newAnimal);
                } else if (typeOfAnimal.equals("porcupine")) {
                    Animal newAnimal = new Porcupine(animalID, nickName);
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method reads-in and parses the data, and instantiates new Task objects
     * accordingly
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
                } else { // start hour on current row is the same as previous row
                    this.databaseRecords.get(startHour).add(newTask);
                }

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

    /**
     * This method checks to see if the current schedule is valid or not based on
     * total task duration for every hour
     * 
     * @return the boolean value indicating the validity of the schedule
     */
    public static boolean isValidSchedule() {
        boolean isValid = true;
        for (Map.Entry<Integer, ArrayList<Task>> entry : databaseRecords.entrySet()) {
            ArrayList<Task> taskList = entry.getValue();
            int totalTaskTime = 0;
            for (Task task : taskList) {
                totalTaskTime += task.getDuration(); // + task.getPrepTime();
            }
            if (taskList.get(0).getExtraVolunteerStatus() == true && totalTaskTime > 120) {
                isValid = false; // if there is an extra volunteer the total duration can be up to 120 minutes
            } else if (taskList.get(0).getExtraVolunteerStatus() == false && totalTaskTime > 60) {
                isValid = false; // if there is no extra volunteer the total duration can be up to 60 minutes
            }
        }
        return isValid;
    }

    /**
     * This method shows all the tasks that exceed 60 or 120 combined minutes (based
     * on volunteer status)
     * in a given hour.
     * It only adds tasks that aren't general feeding, and have a max window greater
     * than 1.
     * 
     * @return TreeMap of (key: startHour, value: ArrayList of task index)
     */
    public static TreeMap<Integer, ArrayList<Integer>> showTasksToBeMoved() {
        TreeMap<Integer, ArrayList<Integer>> tasksToBeMoved = new TreeMap<>();
        databaseRecords.forEach((startHour, taskList) -> {
            int totalTaskTime = 0;
            for (Task task : taskList) {
                totalTaskTime += task.getDuration(); // + task.getPrepTime(); add up all the task time
            }
            if ((totalTaskTime > 60 && taskList.get(0).getExtraVolunteerStatus() == false) ||
                    (totalTaskTime > 120 && taskList.get(0).getExtraVolunteerStatus() == true)) { // If total task time
                                                                                                  // exceeds 60 or 120
                                                                                                  // (depending on
                                                                                                  // volunteer status)
                ArrayList<Integer> taskIndexList = new ArrayList<>();
                for (int i = 0; i < taskList.size(); i++) {
                    if ((taskList.get(i).getTaskID() != -1) && (taskList.get(i).getMaxWindow() > 1)) {
                        taskIndexList.add(i); // adds index of the task
                    }
                }
                if (taskIndexList.size() > 0) { // put the task start hour and the index array into the new treemap
                    tasksToBeMoved.put(startHour, taskIndexList);
                }
            }
        });
        return tasksToBeMoved;
    }

    /**
     * For a given task at a specified startHour, return a list of empty time slots
     * the task can be placed in
     * It takes the task's max window into consideration and finds a list of
     * startHours that will fit the constraints
     * 
     * @param startHour
     * @param taskIndex
     * @return
     */
    public static ArrayList<Integer> showEmptyTimeSlots(int startHour, int taskIndex) {
        ArrayList<Integer> emptyTimeSlots = new ArrayList<>();
        Task taskToModify = databaseRecords.get(startHour).get(taskIndex);
        int maxWindow = taskToModify.getMaxWindow(); // max window of task to modify
        int duration = taskToModify.getDuration(); // + taskToModify.getPrepTime(); duration of task to modify

        for (int i = 1; i < maxWindow; i++) {
            int hour = (startHour + i) % 24;
            if (databaseRecords.containsKey(hour)) {
                ArrayList<Task> taskList = databaseRecords.get(hour);
                int totalTime = duration;
                for (Task task : taskList) {
                    totalTime += task.getDuration(); // + task.getPrepTime();
                }
                if ((totalTime <= 60 && taskList.get(0).getExtraVolunteerStatus() == false) ||
                        (totalTime <= 120 && taskList.get(0).getExtraVolunteerStatus() == true)) {
                    emptyTimeSlots.add(hour);
                }
            } else { // if there are no tasks in the current hour
                emptyTimeSlots.add(hour);
            }
        }
        return emptyTimeSlots;
    }

    /**
     * This method moves the given task from its old start hour to the new start
     * hour
     * It doesn't check whether moving the task makes the schedule more efficient.
     * All it does is move the task
     * 
     * @param oldStartHour
     * @param taskIndex
     * @param newStartHour
     * @throws IllegalArgumentException
     */
    public static void modifyStartHour(int oldStartHour, int taskIndex, int newStartHour)
            throws IllegalArgumentException {
        if (!databaseRecords.containsKey(oldStartHour) || taskIndex >= databaseRecords.get(oldStartHour).size()
                || oldStartHour > 23 || oldStartHour < 0 || newStartHour > 23 || oldStartHour < 0) {
            throw new IllegalArgumentException();
        }
        Task taskToModify = databaseRecords.get(oldStartHour).get(taskIndex);
        databaseRecords.get(oldStartHour).remove(taskToModify); // remove task from old start hour
        if (databaseRecords.get(oldStartHour).size() == 0) {
            databaseRecords.remove(oldStartHour); // if there are no more tasks from that hour, remove task
        }

        taskToModify.setStartHour(newStartHour); // set start hour
        if (databaseRecords.containsKey(newStartHour)) // If there are existing tasks in the new start hour
        {
            boolean extraVolunteerStatus = databaseRecords.get(newStartHour).get(0).getExtraVolunteerStatus();
            taskToModify.setExtraVolunteerStatus(extraVolunteerStatus); // update volunteer status
            databaseRecords.get(newStartHour).add(taskToModify); // add the task
        } else { // If there are no tasks in the new start hour
            ArrayList<Task> newList = new ArrayList<>();
            taskToModify.setExtraVolunteerStatus(false); // update volunteer status
            newList.add(taskToModify);
            databaseRecords.put(newStartHour, newList); // add the task
        }

    }


    /**
     * This method recursively rearranges the tasks in the databaseRecords TreeMap.
     * It checks if the total time of the tasks in a given hour exceeds 60 minutes.
     * If it does, it moves the task to another hour within its MaxWindow.
     * @param key                       the key of the TreeMap corresponding to the start hour
     * @throws TooManyEventsException   if there are too many events in the schedule
     */
    public static void rearrangeTasks() throws TooManyEventsException {
        // iterate through the keys of the treemap
        // check if the time is greater than 60
        // if it is, move the task to the next hour
        // if the next hour is not empty, check if the time is greater than 60
        // if it is, move the task to the next hour
        // and so on

        // Uses modulo to wrap around midnight
        // Throws an exception when trying to move a task which has a max window of 1
        // Throws an exception when a task is not able to be moved within its max window

        //add preptimes for fox and coyote
        // fix rearrangeTasks, look at comments

        int key = 0;

        while(key < 24) {

            ArrayList<Task> tasks = databaseRecords.get(key);
            int totalTime = 0;

            if(tasks != null) {

                //order tasks based on animal species, mainly puts coyotes together so that the preptime is as optimized as possible
                Collections.sort(tasks, (o1, o2) -> (o1.getAnimal().getSpecies().compareTo(o2.getAnimal().getSpecies())));

                //order the tasks from greatest smallest max window to greatest max window
                // so that the tasks with the largest max window are moved first
                Collections.sort(tasks, new Comparator<Task>() {
                    @Override
                    public int compare(Task o1, Task o2) {
                        return o1.getMaxWindow() - o2.getMaxWindow();
                    }
                });

                for(Task task : tasks) {
                    if (task.getTaskType().equals("Coyote feeding") || task.getTaskType().equals("Fox feeding")) {
                        totalTime += task.getPrepTime();
                        break;
                    }
                }

                Iterator<Task> it = tasks.iterator();

                // Maybe if volunteer status is true, check less than 120 minutes instead of 60

                while (it.hasNext()) {
                    Task task = it.next();
                    // Only include preptime in totalTime if the task is a fox feeding or coyote feeding
                    // Only add the preptime once for each hour that contains a fox feeding or coyote feeding

                    totalTime += task.getDuration();

                    if (totalTime > 60) {
                        // maybe implement in a way so that tasks that have an empty hour in their max window are moved to those empty hours first

                        if (task.getMaxWindow() == 1) {
                            // if the task has a max window of 1, it cannot be moved
                            throw new TooManyEventsException("Attempting to move a task with a max window of 1");
                        }
                        else {

                            //maybe get current hour instead
                            int j = (key + 1) % 24;
                            boolean exceptionBool = false;

                            while (j != (task.getStartHour() + task.getMaxWindow()) % 24) {
                                if (databaseRecords.containsKey(j)) {
                                    ArrayList<Task> temp = databaseRecords.get(j);
                                    int time = 0;
                                    for(Task t : temp) {
                                        time += t.getDuration();
                                    }

                                    if (time + task.getDuration() <= 60) {
                                        temp.add(task);
                                        databaseRecords.put(j, temp);
                                        exceptionBool = false;
                                        break;
                                    }
                                    else {
                                        exceptionBool = true;
                                    }
                                }
                                else {
                                    ArrayList<Task> temp = new ArrayList<>();
                                    temp.add(task);
                                    databaseRecords.put(j, temp);
                                    exceptionBool = false;
                                    break;
                                }

                                j = (j + 1) % 24;
                            }

                            if(exceptionBool) {
                                //implies that no rearrangement could be made
                                throw new TooManyEventsException("Task could not be placed any time within its given window");
                            }
                            else {
                                tasks.remove(task);
                                break;
                            }
                        }
                    }
                }
                databaseRecords.put(key, tasks);
            }

            if (totalTime <= 60) {
                key++;
            }

        }


        // else if (key < 23) {
        //     rearrangeTasks(key + 1);
        // }
    }

    /**
     * Iterates through the schedule hashmap and assigns a backup volunteer to every
     * group of tasks in the same
     * start hour if the total duration exceeds 60 minutes
     */
    private static void addBackupVolunteer() {
        // iterate through the keys of the hashmap
        // look at the tasks
        // then apply the correct math to get the time of each task and if backup is
        // required
        databaseRecords.forEach((startHour, tasks) -> {
            int totalTime = 0;
            for (Task task : tasks) {
                totalTime += task.getDuration();
                if (totalTime > 60) {
                    break;
                }
            }
            if (totalTime > 60) // At this point of the function,
            // the user should confirm whether or not they want to add another volunteer
            {
                tasks.forEach((task) -> {
                    task.setExtraVolunteerStatus(true);
                });
            }
        });
    }

}
