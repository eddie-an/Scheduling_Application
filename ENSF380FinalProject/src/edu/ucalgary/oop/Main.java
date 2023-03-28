

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
import java.io.*;
import java.sql.Array;
import java.util.regex.*;
import java.util.*;
import java.util.Arrays.*;

public class Main implements ActionListener {

    private ArrayList<ArrayList<ArrayList<String>>> fullArrayList = new ArrayList<ArrayList<ArrayList<String>>>();

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

        ArrayList<ArrayList<String>> TasksReadIn = getTreatments.TasksReadIn();

        CreateObjects(TasksReadIn);

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




    public ArrayList<ArrayList<String>> TasksReadIn() {

        ArrayList<ArrayList<String>> treatments = new ArrayList<ArrayList<String>>();
        ArrayList<String> curr = new ArrayList<String>();

        // StringBuffer treatments = new StringBuffer();

        try {
            Statement myStmt = dbConnection.createStatement();
            results = myStmt.executeQuery("SELECT ANIMALS.*, TASKS.*, TREATMENTS.StartHour\n" +
                    "FROM TREATMENTS\n" +
                    "JOIN ANIMALS ON ANIMALS.AnimalID = TREATMENTS.AnimalID\n" +
                    "JOIN TASKS ON TREATMENTS.TaskID = TASKS.TaskID\n" +
                    "ORDER BY TREATMENTS.StartHour ASC;");

            while (results.next()) {
                curr.add(results.getString("AnimalNickname"));
                curr.add(results.getString("AnimalSpecies"));
                curr.add(results.getString("Description"));
                curr.add(results.getString("Duration"));
                curr.add(results.getString("MaxWindow"));
                curr.add(results.getString("AnimalID"));
                curr.add(results.getString("TaskID"));
                curr.add(results.getString("StartHour"));
                treatments.add(curr);
                curr.clear();
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

    private static HashMap<String, ArrayList<String>> fullArray = new HashMap<>();

    public static void CreateObjects(ArrayList<ArrayList<String>> databaseAllRecords) {
        ArrayList<String> tmp = new ArrayList<String>();
        for (int i=0; i<databaseAllRecords.size(); i++) {
            tmp = databaseAllRecords.get(i);
            fullArray.put(tmp.get(7), tmp);
        }
    }

}
