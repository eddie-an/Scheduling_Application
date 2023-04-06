package edu.ucalgary.oop;

import java.sql.*;
import java.util.*;

public class Schedule {
    private static TreeMap<Integer, ArrayList<Task>> scheduleTreeMap = new TreeMap<>();
    private static ArrayList<Animal> animalList = new ArrayList<Animal>();
    private static Connection dbConnection;
    private static ResultSet results;

    public static TreeMap<Integer, ArrayList<Task>> getSchedule() {
        return scheduleTreeMap;
    }

    public static ArrayList<Animal> getAnimalList() {
        return animalList;
    }

    public static void populateTreeMap() {
        createConnection();

        animalsReadIn();
        TasksReadIn();

        close();
        addGeneralFeedingAndCleaning();
        try {
            rearrangeTasks();
        }
        catch (TooManyEventsException e) {

        }
    }
    /**
     * creates a connection to the database, this will be different for each user
     */
    public static void createConnection() {

        try {
            // this connection is going to be different for every user change the url user
            // and password for each user
            dbConnection = DriverManager.getConnection("jdbc:mysql://localhost/ewr", "root", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    /**
     * Reads in and instantiates the animals from the database
     */
    public static void animalsReadIn() {
        try {
            Statement myStmt = dbConnection.createStatement();
            results = myStmt.executeQuery("SELECT ANIMALS.*\n" +
                    "FROM ANIMALS\n" +
                    "ORDER BY ANIMALS.AnimalID ASC;");
            while (results.next()) {
                String nickName = results.getString("AnimalNickname");
                String typeOfAnimal = results.getString("AnimalSpecies");
                Integer animalID = Integer.parseInt(results.getString("AnimalID"));
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
    public static void TasksReadIn() {
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
                    scheduleTreeMap.put(startHour, taskArrayList);
                } else { // start hour on current row is the same as previous row
                    scheduleTreeMap.get(startHour).add(newTask);
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method closes the connection to the database
     */
    public static void close() {
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
        for (Map.Entry<Integer, ArrayList<Task>> entry : scheduleTreeMap.entrySet()) {
            ArrayList<Task> taskList = entry.getValue();
            int totalTaskTime = 0;
            for (Task task : taskList) {
                totalTaskTime += task.getDuration() + task.getPrepTime();
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
     * It only adds tasks that don't have preparation time, and have a max window greater
     * than 1.
     *
     * @return TreeMap of (key: startHour, value: ArrayList of task index)
     */
    public static TreeMap<Integer, ArrayList<Integer>> showTasksToBeMoved() {
        TreeMap<Integer, ArrayList<Integer>> tasksToBeMoved = new TreeMap<>();
        scheduleTreeMap.forEach((startHour, taskList) -> {
            int totalTaskTime = 0;
            for (Task task : taskList) {
                totalTaskTime += task.getDuration() + task.getPrepTime(); //add up all the task time
            }
            if ((totalTaskTime > 60 && taskList.get(0).getExtraVolunteerStatus() == false) ||
                    (totalTaskTime > 120 && taskList.get(0).getExtraVolunteerStatus() == true)) { // If total task time
                // exceeds 60 or 120
                // (depending on
                // volunteer status)
                ArrayList<Integer> taskIndexList = new ArrayList<>();
                for (int i = 0; i < taskList.size(); i++) {
                    if ((!taskList.get(i).getTaskType().equals("Fox feeding")) &&
                        (!taskList.get(i).getTaskType().equals("Coyote feeding")) &&
                        (taskList.get(i).getMaxWindow() > 1)) {
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
        Task taskToModify = scheduleTreeMap.get(startHour).get(taskIndex);
        int maxWindow = taskToModify.getMaxWindow(); // max window of task to modify
        int duration = taskToModify.getDuration() + taskToModify.getPrepTime(); // duration of task to modify

        for (int i = 1; i < maxWindow; i++) {
            int hour = (startHour + i) % 24;
            if (scheduleTreeMap.containsKey(hour)) {
                ArrayList<Task> taskList = scheduleTreeMap.get(hour);
                int totalTime = duration;
                for (Task task : taskList) {
                    totalTime += task.getDuration() + task.getPrepTime();
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
        if (!scheduleTreeMap.containsKey(oldStartHour) || taskIndex >= scheduleTreeMap.get(oldStartHour).size()
                || oldStartHour > 23 || oldStartHour < 0 || newStartHour > 23 || oldStartHour < 0) {
            throw new IllegalArgumentException();
        }
        Task taskToModify = scheduleTreeMap.get(oldStartHour).get(taskIndex);
        scheduleTreeMap.get(oldStartHour).remove(taskToModify); // remove task from old start hour
        if (scheduleTreeMap.get(oldStartHour).size() == 0) {
            scheduleTreeMap.remove(oldStartHour); // if there are no more tasks from that hour, remove task
        }

        taskToModify.setStartHour(newStartHour); // set start hour
        if (scheduleTreeMap.containsKey(newStartHour)) // If there are existing tasks in the new start hour
        {
            boolean extraVolunteerStatus = scheduleTreeMap.get(newStartHour).get(0).getExtraVolunteerStatus();
            taskToModify.setExtraVolunteerStatus(extraVolunteerStatus); // update volunteer status
            scheduleTreeMap.get(newStartHour).add(taskToModify); // add the task
        } else { // If there are no tasks in the new start hour
            ArrayList<Task> newList = new ArrayList<>();
            taskToModify.setExtraVolunteerStatus(false); // update volunteer status
            newList.add(taskToModify);
            scheduleTreeMap.put(newStartHour, newList); // add the task
        }

    }


    /**
     * This method rearranges the tasks in the databaseRecords TreeMap.
     * It checks if the total time of the tasks in a given hour exceeds 60 minutes.
     * If it does, it moves the task to another hour within its MaxWindow.
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

        int hour = 0;
        while(hour < 24) {

            ArrayList<Task> tasks = scheduleTreeMap.get(hour);
            int totalTime = 0;

            try {
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

                    //these 2 for loops make it so that only one coyote/fox feeding task has a preptime, and the rest are set to 0
                    //if a coyote/fox feeding task is moved to another hour and its preptime is set to 0, it will be reset to 10/5
                    //to account for the new hour it is in
                    Task prepTask = null;
                    for(Task task : tasks) {
                        if (task.getTaskType().equals("Coyote feeding")) {
                            if (task.getPrepTime() == 0) {
                                task.setPrepTime(10);
                            }
                            totalTime += task.getPrepTime();
                            prepTask = task;
                            break;

                        }  
                        else if (task.getTaskType().equals("Fox feeding")) {
                            if (task.getPrepTime() == 0) {
                                task.setPrepTime(5);
                            }
                            totalTime += task.getPrepTime();
                            prepTask = task;
                            break;
                        }
                    }
                    for(Task otherTask : tasks) {
                        if(prepTask == otherTask) {
                            continue;
                        }
                        else {
                            otherTask.setPrepTime(0);
                        }
                    }

                    // task.getTaskType().equals("Coyote feeding") ? 10 : 5;

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
                                int j = (hour + 1) % 24;
                                boolean exceptionBool = false;

                                while (j != (task.getStartHour() + task.getMaxWindow()) % 24) {
                                    if (scheduleTreeMap.containsKey(j)) {
                                        ArrayList<Task> temp = scheduleTreeMap.get(j);
                                        int time = 0;
                                        for(Task t : temp) {
                                            time += t.getDuration();
                                        }

                                        if (time + task.getDuration() <= 60) {
                                            temp.add(task);
                                            scheduleTreeMap.put(j, temp);
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
                                        scheduleTreeMap.put(j, temp);
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
                    scheduleTreeMap.put(hour, tasks);
                }
                if (totalTime <= 60) {
                    hour++;
                }
            }
            catch (TooManyEventsException e) {
                System.out.println(e.getMessage());
                hour++;
            }

        }
    }

    /**
     * Iterates through the schedule hashmap and assigns a backup volunteer to every
     * group of tasks in the same
     * start hour if the total duration exceeds 60 minutes
     */
    public static void addBackupVolunteer(int startHour) {
        // iterate through the keys of the hashmap
        // look at the tasks
        // then apply the correct math to get the time of each task and if backup is
        // required
        if (scheduleTreeMap.containsKey(startHour))
        {
            ArrayList<Task> taskList = scheduleTreeMap.get(startHour);
            int totalTime = 0;
            for (Task task : taskList) {
                totalTime += task.getDuration() + task.getPrepTime();
                if (totalTime > 60) {
                    break;
                }
            }

            if (totalTime > 60) // At this point of the function,
            // the user should confirm whether or not they want to add another volunteer
            {
                taskList.forEach((task) -> {
                    task.setExtraVolunteerStatus(true);
                });
            }

        }

    }


    /**
     * adds general feeding for non-orphaned animals, and cleaning tasks as well
     */
    public static void addGeneralFeedingAndCleaning() {
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
                if (scheduleTreeMap.containsKey(feeding.getStartHour())) {
                    scheduleTreeMap.get(feeding.getStartHour()).add(feeding);
                } else {
                    ArrayList<Task> taskArrayList = new ArrayList<>();
                    taskArrayList.add(feeding);
                    scheduleTreeMap.put(feeding.getStartHour(), taskArrayList);
                }
            }

            if (scheduleTreeMap.containsKey(cleaning.getStartHour())) {
                scheduleTreeMap.get(cleaning.getStartHour()).add(cleaning);
            } else {
                ArrayList<Task> taskArrayList = new ArrayList<>();
                taskArrayList.add(cleaning);
                scheduleTreeMap.put(cleaning.getStartHour(), taskArrayList);
            }
        }
    }
}
