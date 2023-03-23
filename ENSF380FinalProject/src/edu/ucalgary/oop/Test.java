package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDate;

public class Test {

    public Test() {
    }

    @Test
    public void testAnimalGetId() {
        Beaver newBeaver = new Beaver(3, "ExampleName", true);

        int expectedResult = 3;
        int result = newBeaver.getAnimalID();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAnimalGetName() {
        Coyote newCoyote = new Coyote(10, "Chad", false);

        String expectedResult = "Chad";
        String result = newCoyote.getName();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAnimalGetOrphanStatus() {
        Porcupine newPorcupine = new Porcupine(99, "Dorothy", true);

        boolean expectedResult = true;
        boolean result = newPorcupine.getOrphanStatus();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAnimalGetActiveTime() {
        Raccoon newRaccoon = new Raccoon(10, "Bob", false);

        String expectedResult = "nocturnal";
        String result = newRaccoon.getActiveTime();
        assertEquals(expectedResult, result);
    }

    public void testAnimalGetSpecies() {
        Fox newFox = new Fox(420, "poppy", false);

        String expectedResult = "fox";
        String result = newFox.getSpecies();
        assertEquals(expectedResult, result);
    }

    public void testAnimalSetName() {
        Coyote newCoyote = new Coyote(10, "Chad", false);

        String expectedResult = "Gigachad";
        newCoyote.setName(expectedResult);

        String result = newCoyote.getName();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAnimalSetOrphanStatus() {
        Porcupine newPorcupine = new Porcupine(99, "Dorothy", true);

        boolean expectedResult = false;
        newPorcupine.setOrphanStatus(expectedResult);

        boolean result = newPorcupine.getOrphanStatus();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testTaskGetStartTime() {
        Porcupine newPorcupine = new Porcupine(69, "Jesus", false);
        LocalDate date = LocalDate.now();
        Task newTask = new Task(date, 10, 40, "Cleaning Cage", "67", newPorcupine);

        LocalDate expectedResult = date;
        LocalDate result = newTask.getStartTime();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testTaskGetPrepTime() {
        Fox newFox = new Fox(6, "Kanye", true);
        LocalDate date = LocalDate.now();
        Task newTask = new Task(date, 15, 40, "Cleaning Cage", "11", newFox);

        int expectedResult = 15;
        int result = newTask.getPrepTime();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testTaskGetTaskTime() {
        Fox newFox = new Fox(6, "Kanye", true);
        LocalDate date = LocalDate.now();
        Task newTask = new Task(date, 15, 40, "Cleaning Cage", "11", newFox);

        int expectedResult = 40;
        int result = newTask.getTaskTime();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testTaskGetTaskType() {
        Porcupine newPorcupine = new Porcupine(69, "Jesus", false);
        LocalDate date = LocalDate.now();
        Task newTask = new Task(date, 10, 40, "Cleaning Cage", "67", newPorcupine);

        String expectedResult = "Cleaning Cage";
        String result = newTask.getTaskType();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testDBConnectionMade() {
        FetchSQL test = new FetchSQL();
        test.createConnection();

        assertNotEquals(null, test.dbConnection)
    }

    @Test
    public void testRetrieveTreatments() {
        FetchSQL test = new FetchSQL();
        test.createConnection();

        ArrayList<ArrayList<String>> actualResult = test.treatmentInfo();

        assertNotEquals(null, actualResult);
    }

    @Test
    public void testRetrieveTasks() {
        FetchSQL test = new FetchSQL();
        test.createConnection();

        ArrayList<ArrayList<String>> actualResult = test.taskInfo();

        assertNotEquals(null, actualResult);

    }

    @Test
    public void testRetrieveAnimals() {
        FetchSQL test = new FetchSQL();
        test.createConnection();

        ArrayList<ArrayList<String>> actualResult = test.animalInfo();

        assertNotEquals(null, actualResult);
    }

    @Test
    public void testPrintLogWrites() {
        PrintLog test = new PrintLog();

        test.writeToSchedule("19:00\n*Rebandage leg wound (Slinky)");
        //not done
    }
    
    @Test
    public void testTooManyEventsException() {
        Porcupine newPorcupine = new Porcupine(69, "Jesus", false);
        LocalDate date = LocalDate.now();
        try{
            Task newTask = new Task(date, 10, 55, "Cleaning Cage", "67", newPorcupine); 
        }
        catch(Exception e) {
            if(e instanceof NotAnInt) {
                System.out.println("Unable to sucessfully use the exception...");
                System.exit(1);
            }
            else if (e instanceof ParseError) {
                System.out.println("Parse error");
                Systm.exit(1);
            }
        }
 
}
