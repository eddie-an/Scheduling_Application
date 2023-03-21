package edu.ucalgary.oop;

import java.sql.*;
import java.util.ArrayList;

public class FetchSQL {

    private Connection dbConnection;
    private ResultSet results;
    private SinglyLinkedList storage = new SinglyLinkedList();

    public FetchSQL() {

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
    public ArrayList<ArrayList<String>> animalInfo() {

        // StringBuffer animals = new StringBuffer();
        ArrayList<ArrayList<String>> animals = new ArrayList<ArrayList<String>>();
        ArrayList<String> currAnimals = new ArrayList<String>();

        try {
            Statement myStmt = dbConnection.createStatement();
            results = myStmt.executeQuery("SELECT * FROM ANIMALS");

            while (results.next()) {

                currAnimals.add(results.getString("AnimalId"));
                currAnimals.add(results.getString("AnimalNickname"));
                currAnimals.add(results.getString("AnimalSpecies"));

                animals.add(currAnimals);
                currAnimals.clear();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return animals;
    }

    public ArrayList<ArrayList<String>> taskInfo() {
        // StringBuffer tasks = new StringBuffer();
        ArrayList<ArrayList<String>> tasks = new ArrayList<ArrayList<String>>();
        ArrayList<String> currTasks = new ArrayList<String>();

        try {
            Statement myStmt = dbConnection.createStatement();
            results = myStmt.executeQuery("SELECT * FROM TASKS");

            while (results.next()) {

                currTasks.add(results.getString("TaskID"));
                currTasks.add(results.getString("Description"));
                currTasks.add(results.getString("Duration"));
                currTasks.add(results.getString("MaxWindow"));

                tasks.add(currTasks);
                currTasks.clear();

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return tasks;

    }

    public ArrayList<ArrayList<String>> TreatmentInfo() {

        ArrayList<ArrayList<String>> treatments = new ArrayList<ArrayList<String>>();
        ArrayList<String> currTreatments = new ArrayList<String>();

        // StringBuffer treatments = new StringBuffer();

        try {
            Statement myStmt = dbConnection.createStatement();
            results = myStmt.executeQuery("SELECT * FROM TREATMENTS");

            while (results.next()) {

                currTreatments.add(results.getString("AnimalID"));
                currTreatments.add(results.getString("TaskID"));
                currTreatments.add(results.getString("StartHour"));
                treatments.add(currTreatments);
                currTreatments.clear();
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

    public static void CreateObjects(ArrayList<ArrayList<String>> animals, ArrayList<ArrayList<String>> tasks,
            ArrayList<ArrayList<String>> treatments) {

        for (int i = 0; i < treatments.size(); i++) {
                
        }

    }

    public static void main(String args[]) {

        FetchSQL getTreatments = new FetchSQL();
        getTreatments.createConnection();
        ArrayList<ArrayList<String>> allAnimals = getTreatments.animalInfo();
        ArrayList<ArrayList<String>> allTasks = getTreatments.taskInfo();
        ArrayList<ArrayList<String>> allTreatments = getTreatments.TreatmentInfo();

        CreateObjects(allAnimals, allTasks, allTreatments);

        getTreatments.close();

    }

}

class SinglyLinkedList {
    // Represent a node of the singly linked list
    class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Represent the head and tail of the singly linked list
    public Node head = null;
    public Node tail = null;

    // addNode() will add a new node to the list
    public void addNode(int data) {
        // Create a new node
        Node newNode = new Node(data);

        // Checks if the list is empty
        if (head == null) {
            // If list is empty, both head and tail will point to new node
            head = newNode;
            tail = newNode;
        } else {
            // newNode will be added after tail such that tail's next will point to newNode
            tail.next = newNode;
            // newNode will become new tail of the list
            tail = newNode;
        }
    }

    public void display() {
        // Node current will point to head
        Node current = head;

        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        System.out.println("Nodes of singly linked list: ");
        while (current != null) {
            // Prints each node by incrementing pointer
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

}
