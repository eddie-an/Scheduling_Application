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

public class PrintLog {

    /**
     * This is the constructor for the PrintLog class. It has no parameters.
     */
    public PrintLog() {

    }

    /**
     * This method writes to the schedule.txt file.
     * @param segment   The string segment to be written to the file. This segement must be formatted before being passed to this method.
     */
    public void writeToSchedule(String segment) {
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
