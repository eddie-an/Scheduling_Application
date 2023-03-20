package edu.ucalgary.oop;

import java.sql.*;

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
    public String animalInfo() {

        StringBuffer animals = new StringBuffer();

        try {
            Statement myStmt = dbConnection.createStatement();
            results = myStmt.executeQuery("SELECT * FROM ANIMALS");

            while (results.next()) {

                animals.append(results.getString("AnimalId") + ", " + results.getString("AnimalNickname") + ", "
                        + results.getString("AnimalSpecies"));
                animals.append('\n');
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return animals.toString();
    }

    public String taskInfo() {
        StringBuffer tasks = new StringBuffer();

        try {
            Statement myStmt = dbConnection.createStatement();
            results = myStmt.executeQuery("SELECT * FROM TASKS");

            while (results.next()) {

                tasks.append(results.getString("TaskID") + ", " + results.getString("Description") + ", "
                        + results.getString("Duration") + results.getString("MaxWindow"));
                tasks.append('\n');
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return tasks.toString();

    }

    public void close() {
        try {
            results.close();
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {

        FetchSQL getTreatments = new FetchSQL();
        getTreatments.createConnection();
        String allAnimals = getTreatments.animalInfo();
        System.out.println(allAnimals);

        getTreatments.close();

    }
}

class SinglyLinkedList {    
    //Represent a node of the singly linked list    
    class Node{    
        int data;    
        Node next;    
            
        public Node(int data) {    
            this.data = data;    
            this.next = null;    
        }    
    }    
     
    //Represent the head and tail of the singly linked list    
    public Node head = null;    
    public Node tail = null;    
        
    //addNode() will add a new node to the list    
    public void addNode(int data) {    
        //Create a new node    
        Node newNode = new Node(data);    
            
        //Checks if the list is empty    
        if(head == null) {    
            //If list is empty, both head and tail will point to new node    
            head = newNode;    
            tail = newNode;    
        }    
        else {    
            //newNode will be added after tail such that tail's next will point to newNode    
            tail.next = newNode;    
            //newNode will become new tail of the list    
            tail = newNode;    
        }    
    }    
    
      public void display() {    
        //Node current will point to head    
        Node current = head;    
            
        if(head == null) {    
            System.out.println("List is empty");    
            return;    
        }    
        System.out.println("Nodes of singly linked list: ");    
        while(current != null) {    
            //Prints each node by incrementing pointer    
            System.out.print(current.data + " ");    
            current = current.next;    
        }    
        System.out.println();    
    }   
        
