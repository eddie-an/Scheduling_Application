package edu.ucalgary.oop;

import java.io.*;

public class PrintLog {
    public PrintLog() {

    }

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