# Scheduling_Application

A GUI Application that reads from a database and rearranges tasks to meet specifications. The final schedule is printed in a text file.

# What I learned

- UML diagram design according to specifications defined by a client
- Object-oriented programming principles such as inheritance, abstraction, encapsulation, and polymorphism
- SQL queries to fetch items from the database
- GUI using AWT and Swing in Java
- Use of data structures such as a TreeMap, ArrayLists to organize data
- Unit test creation
- Java documentation

# How to run the program

Clone the repository on your local machine and run the `treatments.sql` file to create the SQL database.
Creating the database is important as this program's functionality depends on the database input.

On the command line, use the `cd` command to navigate to the `Scheduling_Application` folder.

<br>

The MySQL database connection username and password is hard coded in this application due to the specifications given by the professor.
For the code to run properly, the `public static void createConnection()` method in the `Schedule.java` class must be changed.
The MySQL connection is different for every user due to each local database on their local machine having different MySQL username and password.
If the MySQL root user is being used, change the code from `oop` to `root`
The code to change is highlighted in the image below:
![Code to change](/resources/Code_to_change.png)

###### To run the program on Windows

Compile the application using the commands `javac -cp .;mysql-connector-java-8.0.23.jar edu/ucalgary/oop/GUI.java`
Run the application using the commands `java -cp .;mysql-connector-java-8.0.23.jar edu.ucalgary.oop.GUI`

###### To run the program on Mac

Compile the application using the commands `javac -cp .:mysql-connector-java-8.0.23.jar edu/ucalgary/oop/GUI.java`
Run the application using the commands `java -cp .:mysql-connector-java-8.0.23.jar edu.ucalgary.oop.GUI`

### .gitignore

There is a gitignore file which ignores the lib folder used for library dependencies, bin folder used to store .class files. These files and folders do not need to be version controlled since it is not a part of the source code.

---
