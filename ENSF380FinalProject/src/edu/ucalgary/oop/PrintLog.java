/**
@author     Group5
@version    1.2
@since      1.0
*/

/*
 * PrintLog is a class which writes to the schedule.txt file.
*/

package edu.ucalgary.oop;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class PrintLog {

    /**
     * This is the constructor for the PrintLog class. It has no parameters.
     */
    public PrintLog() {

    }
    
    /**
     * This method formats the data to be written to the file from a HashMap of finalized data.
     * @param finalizedData
     * @return The formatted string to be written to the file.
     */
    public static String dataToString(TreeMap<Integer, ArrayList<Task>> finalizedData) {
        StringBuilder str = new StringBuilder();
        str.append("Schedule for ");
        LocalDate today = LocalDate.now();
        str.append(today.toString());
        str.append("\n\n");

        finalizedData.forEach((startHour, tasks) -> {
            if (tasks.size() > 0) {
                str.append(startHour);
                str.append(":00");
                if (tasks.get(0).getExtraVolunteerStatus() == true) {
                    str.append(" [+ backup volunteer]");
                }
                str.append("\n");
                tasks.forEach((task) -> {
                    str.append("* " + task.getTaskType() + " - ");
                    str.append(task.getAnimal().getSpecies() + " (" + task.getAnimal().getName() + ")");
                    str.append(" --- duration: " + task.getDuration() + " minutes");
                    str.append("\n");
                });
                str.append("\n");
            }
        });
        String returnVal = str.toString();
        return returnVal;
    }

    /**
     * This method writes to the schedule.txt file.
     * @param segment   The string segment to be written to the file. This segement must be formatted before being passed to this method.
     */
    public static void writeToSchedule(String segment) {
        FileWriter schedule = null;
        String fileName = "schedule.txt";
        char[] charArray = segment.toCharArray();

        try {
            schedule = new FileWriter(fileName);

            for (char c : charArray) {
                schedule.write(c);
            }

        } catch (IOException e) {
            System.out.println("Error writing to file " + fileName);
            e.printStackTrace();
        }

        finally {
            if (schedule != null) {
                try {
                    schedule.close();
                } catch (IOException e) {
                    System.out.println("Couldn't close file " + fileName);
                    e.printStackTrace();
                }
            }
        }
    }
}