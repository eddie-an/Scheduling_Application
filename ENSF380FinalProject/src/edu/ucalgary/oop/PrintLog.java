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
    
    public static String dataToString(HashMap<Integer, ArrayList<Task>> finalizedData) {
        StringBuilder str = new StringBuilder();
        str.append("Schedule for ");
        LocalDate today = LocalDate.now();
        str.append(today.toString());
        str.append("\n\n");

        finalizedData.forEach((startHour, tasks) -> {
            str.append(startHour);
            str.append(":00 ");
            if (tasks.get(0).getExtraVolunteer() == true) {
                str.append("[+ backup volunteer]");
            }
            str.append("\n");
            tasks.forEach((task)-> {
                str.append("* ");
                str.append(task.getTaskType());
                str.append(" - ");
                str.append(task.getAnimal().getSpecies());
                str.append(" (");
                str.append(task.getAnimal().getName());
                str.append(")");
                str.append("\n");
            });
            str.append("\n")
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
            schedule = new FileWriter(fileName, true);

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
