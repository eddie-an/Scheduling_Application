/**
@author     Group5
@version    1.0
@since      1.0
*/

/*
 * App is the class that contains the main method. It is the entry point of the program.
 * App will have a GUI that will allow the user to interact with the program for these purposes:
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

package edu.ucalgary.oop;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App implements ActionListener {

    /**
     * This is the main method of the program.
     * @param args          The command line arguments.
     * @throws Exception    If an exception is thrown.
     */
    public static void main(String[] args) throws Exception {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame(" Example Wildlife Rescue Scheduler");
            frame.setSize(400, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            JPanel buttonsPanel = new JPanel();
            JButton myButton = new JButton("Build Schedule Based On Database");

            App buttonListener = new App();
            myButton.addActionListener(buttonListener);
            buttonsPanel.add(myButton);
            frame.getContentPane().add(BorderLayout.NORTH, buttonsPanel);
            frame.setVisible(true);

        });
    }

    public void actionPerformed(ActionEvent event){

        //Here call the method that will initiate the schedule building process
        //Exceptions which require calling in a backup volunteer should be handled here

        //add try block that calls the method that builds the schedule


        //add catch block that confirms backup volunteer and makes changes accordingly to the schedule

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

}
