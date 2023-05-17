# Scheduling_Application
###### Final Group Project for a 2nd year software engineering course
###### Created by Edward An, Evan Barker, Karam Baroud, and Jad Khalil
#### Description
Wildlife rescue centres provide care and rehabilitation for injured and orphaned wild animals. The tasks
associated with caring for the animals depends on the types of animals in residence (e.g., feeding times),
as well as the individual animal's specific care needs (e.g., an open wound requires daily re-bandaging).
Your client, Example Wildlife Rescue (EWR), needs a program which can be used to generate a daily list
of tasks for volunteers to complete in order to support the animals in residence. EWR specializes in
medium-sized animals and refers other cases to other centres. They have facilities to support coyotes,
foxes, porcupines, beavers and raccoons.
<br>
To meet the client needs, a GUI Application is created to read all the tasks from a database and rearranges them according to specifications. If there are conflicts in the schedule, the user has the option to move tasks around. The final schedule is then printed in a text file.

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

# Class relationships

This application implements object oriented programming principles. The relationship between all the classes are represented in a UML diagram as shown in the image below:
![Code to change](/resources/Code_to_change.png)

### .gitignore

There is a gitignore file which ignores the lib folder used for library dependencies, bin folder used to store .class files. These files and folders do not need to be version controlled since it is not a part of the source code.

---
