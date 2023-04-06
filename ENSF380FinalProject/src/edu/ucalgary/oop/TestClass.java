package edu.ucalgary.oop;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import static org.junit.Assert.*;

public class TestClass {

    public TestClass() {
    }

    @Test
    public void testAnimalGetId() {
        int expectedResult1 = 1;
        int expectedResult2 = 202;
        int expectedResult3 = 13;
        int expectedResult4 = 103423523;
        int expectedResult5 = -100;

        Beaver animal1 = new Beaver(expectedResult1, "ExampleName");
        Coyote animal2 = new Coyote(expectedResult2, "ExampleName");
        Fox animal3 = new Fox(expectedResult3, "ExampleName");
        Porcupine animal4 = new Porcupine(expectedResult4, "ExampleName");
        Raccoon animal5 = new Raccoon(expectedResult5, "ExampleName");

        int result1 = animal1.getAnimalID();
        int result2 = animal2.getAnimalID();
        int result3 = animal3.getAnimalID();
        int result4 = animal4.getAnimalID();
        int result5 = animal5.getAnimalID();

        assertEquals("The getAnimalID() method in the Beaver class doesn't work as expected", expectedResult1, result1);
        assertEquals("The getAnimalID() method in the Coyote class doesn't work as expected", expectedResult2, result2);
        assertEquals("The getAnimalID() method in the Fox class doesn't work as expected", expectedResult3, result3);
        assertEquals("The getAnimalID() method in the Porcupine class doesn't work as expected", expectedResult4, result4);
        assertEquals("The getAnimalID() method in the Raccoon class doesn't work as expected", expectedResult5, result5);

    }

    @Test
    public void testAnimalGetName() {
        String expectedResult1 = "Chad";
        String expectedResult2 = "Gigachad";
        String expectedResult3 = "Dorothy";
        String expectedResult4 = "wiee r d spELLing";
        String expectedResult5 = "name1, name2, and name3";

        Beaver animal1 = new Beaver(1, expectedResult1);
        Coyote animal2 = new Coyote(1, expectedResult2);
        Fox animal3 = new Fox(1, expectedResult3);
        Porcupine animal4 = new Porcupine(1, expectedResult4);
        Raccoon animal5 = new Raccoon(1, expectedResult5);

        String result1 = animal1.getName();
        String result2 = animal2.getName();
        String result3 = animal3.getName();
        String result4 = animal4.getName();
        String result5 = animal5.getName();

        assertEquals("The getName() method in the Beaver class doesn't work as expected", expectedResult1, result1);
        assertEquals("The getName() method in the Coyote class doesn't work as expected", expectedResult2, result2);
        assertEquals("The getName() method in the Fox class doesn't work as expected", expectedResult3, result3);
        assertEquals("The getName() method in the Porcupine class doesn't work as expected", expectedResult4, result4);
        assertEquals("The getName() method in the Raccoon class doesn't work as expected", expectedResult5, result5);
    }

    @Test
    public void testAnimalSetAndGetOrphanStatus() {
        boolean expectedResult1 = true;
        boolean expectedResult2 = false;
        boolean expectedResult3 = false;
        boolean expectedResult4 = false;
        boolean expectedResult5 = true;

        Beaver animal1 = new Beaver(1, "ExampleName");
        Coyote animal2 = new Coyote(1, "ExampleName");
        Fox animal3 = new Fox(1, "ExampleName");
        Porcupine animal4 = new Porcupine(1, "ExampleName");
        Raccoon animal5 = new Raccoon(1, "ExampleName");

        animal1.setOrphanStatus(expectedResult1);
        animal2.setOrphanStatus(expectedResult2);
        animal3.setOrphanStatus(expectedResult3);
        animal4.setOrphanStatus(expectedResult4);
        animal5.setOrphanStatus(expectedResult5);

        boolean result1 = animal1.getOrphanStatus();
        boolean result2 = animal2.getOrphanStatus();
        boolean result3 = animal3.getOrphanStatus();
        boolean result4 = animal4.getOrphanStatus();
        boolean result5 = animal5.getOrphanStatus();

        assertEquals("The getOrphanStatus() method in the Beaver class doesn't work as expected", expectedResult1, result1);
        assertEquals("The getOrphanStatus() method in the Coyote class doesn't work as expected", expectedResult2, result2);
        assertEquals("The getOrphanStatus() method in the Fox class doesn't work as expected", expectedResult3, result3);
        assertEquals("The getOrphanStatus() method in the Porcupine class doesn't work as expected", expectedResult4, result4);
        assertEquals("The getOrphanStatus() method in the Raccoon class doesn't work as expected", expectedResult5, result5);

        //Changing values
        expectedResult1 = false;
        expectedResult2 = true;
        expectedResult3 = true;
        expectedResult4 = false;
        expectedResult5 = false;

        animal1.setOrphanStatus(expectedResult1);
        animal2.setOrphanStatus(expectedResult2);
        animal3.setOrphanStatus(expectedResult3);
        animal4.setOrphanStatus(expectedResult4);
        animal5.setOrphanStatus(expectedResult5);

        result1 = animal1.getOrphanStatus();
        result2 = animal2.getOrphanStatus();
        result3 = animal3.getOrphanStatus();
        result4 = animal4.getOrphanStatus();
        result5 = animal5.getOrphanStatus();

        assertEquals("The getOrphanStatus() method in the Beaver class doesn't work as expected", expectedResult1, result1);
        assertEquals("The getOrphanStatus() method in the Coyote class doesn't work as expected", expectedResult2, result2);
        assertEquals("The getOrphanStatus() method in the Fox class doesn't work as expected", expectedResult3, result3);
        assertEquals("The getOrphanStatus() method in the Porcupine class doesn't work as expected", expectedResult4, result4);
        assertEquals("The getOrphanStatus() method in the Raccoon class doesn't work as expected", expectedResult5, result5);
    }

    @Test
    public void testAnimalGetActiveTime() {
        String expectedResult1 = "diurnal";
        String expectedResult2 = "crepuscular";
        String expectedResult3 = "nocturnal";
        String expectedResult4 = "crepuscular";
        String expectedResult5 = "nocturnal";

        Beaver animal1 = new Beaver(1, "ExampleName");
        Coyote animal2 = new Coyote(1, "ExampleName");
        Fox animal3 = new Fox(1, "ExampleName");
        Porcupine animal4 = new Porcupine(1, "ExampleName");
        Raccoon animal5 = new Raccoon(1, "ExampleName");

        String result1 = animal1.getActiveTime();
        String result2 = animal2.getActiveTime();
        String result3 = animal3.getActiveTime();
        String result4 = animal4.getActiveTime();
        String result5 = animal5.getActiveTime();

        assertEquals("The getActiveTime() method in the Beaver class doesn't work as expected", expectedResult1, result1);
        assertEquals("The getActiveTime() method in the Coyote class doesn't work as expected", expectedResult2, result2);
        assertEquals("The getActiveTime() method in the Fox class doesn't work as expected", expectedResult3, result3);
        assertEquals("The getActiveTime() method in the Porcupine class doesn't work as expected", expectedResult4, result4);
        assertEquals("The getActiveTime() method in the Raccoon class doesn't work as expected", expectedResult5, result5);
    }

    @Test
    public void testAnimalGetSpecies() {
        String expectedResult1 = "beaver";
        String expectedResult2 = "coyote";
        String expectedResult3 = "fox";
        String expectedResult4 = "porcupine";
        String expectedResult5 = "raccoon";

        Beaver animal1 = new Beaver(1, "ExampleName");
        Coyote animal2 = new Coyote(1, "ExampleName");
        Fox animal3 = new Fox(1, "ExampleName");
        Porcupine animal4 = new Porcupine(1, "ExampleName");
        Raccoon animal5 = new Raccoon(1, "ExampleName");

        String result1 = animal1.getSpecies();
        String result2 = animal2.getSpecies();
        String result3 = animal3.getSpecies();
        String result4 = animal4.getSpecies();
        String result5 = animal5.getSpecies();

        assertEquals("The getSpecies() method in the Beaver class doesn't work as expected", expectedResult1, result1);
        assertEquals("The getSpecies() method in the Coyote class doesn't work as expected", expectedResult2, result2);
        assertEquals("The getSpecies() method in the Fox class doesn't work as expected", expectedResult3, result3);
        assertEquals("The getSpecies() method in the Porcupine class doesn't work as expected", expectedResult4, result4);
        assertEquals("The getSpecies() method in the Raccoon class doesn't work as expected", expectedResult5, result5);
    }

    @Test
    public void testAnimalSetName() {
        String originalResult = "ExampleName";

        Beaver animal1 = new Beaver(1, "ExampleName");
        Coyote animal2 = new Coyote(1, "ExampleName");
        Fox animal3 = new Fox(1, "ExampleName");
        Porcupine animal4 = new Porcupine(1, "ExampleName");
        Raccoon animal5 = new Raccoon(1, "ExampleName");

        String expectedResult1 = "Spike";
        String expectedResult2 = "Twilight";
        String expectedResult3 = "Spike";
        String expectedResult4 = "Eraser";
        String expectedResult5 = "Sunshine";
        animal1.setName(expectedResult1);
        animal2.setName(expectedResult2);
        animal3.setName(expectedResult3);
        animal4.setName(expectedResult4);
        animal5.setName(expectedResult5);

        String result1 = animal1.getName();
        String result2 = animal2.getName();
        String result3 = animal3.getName();
        String result4 = animal4.getName();
        String result5 = animal5.getName();

        if (result1 == originalResult) {
            assertTrue("The setName() method in the Beaver class doesn't change the name", false);
        } if (result2 == originalResult) {
            assertTrue("The setName() method in the Coyote class doesn't change the name", false);
        } if (result3 == originalResult) {
            assertTrue("The setName() method in the Fox class doesn't change the name", false);
        } if (result4 == originalResult) {
            assertTrue("The setName() method in the Porcupine class doesn't change the name", false);
        } if (result5 == originalResult) {
            assertTrue("The setName() method in the Raccoon class doesn't change the name", false);
        }
        assertEquals("The setName() method in the Beaver class doesn't work as expected", expectedResult1, result1);
        assertEquals("The setName() method in the Coyote class doesn't work as expected", expectedResult2, result2);
        assertEquals("The setName() method in the Fox class doesn't work as expected", expectedResult3, result3);
        assertEquals("The setName() method in the Porcupine class doesn't work as expected", expectedResult4, result4);
        assertEquals("The setName() method in the Raccoon class doesn't work as expected", expectedResult5, result5);
    }

    @Test
    public void testTaskGetTaskID() {
        Fox newFox = new Fox(6, "Kanye");

        int expectedResult1 = 20;
        Task newTask1 = new Task(expectedResult1, 1, 5, 20, "Kit Feeding", newFox);
        int result1 = newTask1.getTaskID();
        assertEquals(expectedResult1, result1);

        int expectedResult2 = 45;
        Task newTask2 = new Task(expectedResult2, 1, 5, 20, "Kit Feeding", newFox, 10);
        int result2 = newTask2.getTaskID();
        assertEquals(expectedResult2, result2);
    }

    @Test
    public void testTaskGetStartHour() {
        Porcupine newPorcupine = new Porcupine(69, "Jesus");

        int expectedResult1 = 12;
        Task newTask1 = new Task(1, expectedResult1, 10, 30, "Flush Neck Wound", newPorcupine);
        int result1 = newTask1.getStartHour();
        assertEquals(expectedResult1, result1);

        int expectedResult2 = 3;
        Task newTask2 = new Task(1, expectedResult2, 10, 30, "Flush Neck Wound", newPorcupine, 12);
        int result2 = newTask2.getStartHour();
        assertEquals(expectedResult2, result2);
    }


    @Test
    public void testTaskGetMaxWindow() {
        Coyote newCoyote = new Coyote(6, "Kanye");

        int expectedResult1 = 3;
        Task newTask1 = new Task(2, 11, expectedResult1, 45, "Cleaning Cage", newCoyote);
        int result1 = newTask1.getMaxWindow();
        assertEquals(expectedResult1, result1);

        int expectedResult2 = 1;
        Task newTask2 = new Task(2, 11, expectedResult2, 45, "Cleaning Cage", newCoyote, 15);
        int result2 = newTask2.getMaxWindow();
        assertEquals(expectedResult2, result2);
    }

    @Test
    public void testTaskGetDuration() {
        Raccoon newRaccoon = new Raccoon(6, "Bob The Builder");

        int expectedResult1 = 25;
        Task newTask1 = new Task(2, 0, 5, expectedResult1, "Administer antibiotics", newRaccoon);
        int result1 = newTask1.getDuration();
        assertEquals(expectedResult1, result1);

        int expectedResult2 = 30;
        Task newTask2 = new Task(2, 0, 5, expectedResult2, "Administer antibiotics", newRaccoon, 5);
        int result2 = newTask2.getDuration();
        assertEquals(expectedResult2, result2);
    }

    @Test
    public void testTaskGetTaskType() {
        Beaver newBeaver = new Beaver(31, "Justin Beaver");

        String expectedResult1 = "Eyedrops";
        Task newTask1 = new Task(2, 6, 5, 10, expectedResult1, newBeaver);
        String result1 = newTask1.getTaskType();
        assertEquals(expectedResult1, result1);

        String expectedResult2 = "Clean cage";
        Task newTask2 = new Task(2, 6, 5, 10, expectedResult2, newBeaver, 3);
        String result2 = newTask2.getTaskType();
        assertEquals(expectedResult2, result2);
    }

    @Test
    public void testTaskGetAnimal() {
        Coyote expectedResult1 = new Coyote(305, "Pitbull");
        Task newTask1 = new Task(2, 7, 5, 10, "Kit feeding", expectedResult1);
        Animal result1 = newTask1.getAnimal();
        assertEquals(expectedResult1, result1);

        Porcupine expectedResult2 = new Porcupine(15, "Spiky");
        Task newTask2 = new Task(2, 7, 5, 10, "Kit feeding", expectedResult2, 0);
        Animal result2 = newTask2.getAnimal();
        assertEquals(expectedResult2, result2);
    }

    @Test
    public void testTaskGetExtraVolunteerStatus() {
        Raccoon newRaccoon = new Raccoon(305, "blue");

        Task newTask1 = new Task(2, 7, 5, 10, "Kit feeding", newRaccoon);
        boolean expectedResult1 = false;
        boolean result1 = newTask1.getExtraVolunteerStatus();
        assertEquals("The value should be false by default when instantiated", expectedResult1, result1);

        Task newTask2 = new Task(2, 7, 5, 10, "Kit feeding", newRaccoon, 10);
        boolean expectedResult2 = false;
        boolean result2 = newTask2.getExtraVolunteerStatus();
        assertEquals("The value should be false by default when instantiated", expectedResult2, result2);
    }

    @Test
    public void testTaskGetPrepTime() {
        Fox newFox = new Fox(305, "Megan Fox");

        Task newTask1 = new Task(2, 7, 5, 10, "Kit feeding", newFox);
        int expectedResult1 = 0;
        int result1 = newTask1.getPrepTime();
        assertEquals("The value should be 0 by default when instantiated using the 6 argument constructor", expectedResult1, result1);

        int expectedResult2 = 15;
        Task newTask2 = new Task(2, 7, 5, 10, "Kit feeding", newFox, expectedResult2);

        int result2 = newTask2.getPrepTime();
        assertEquals(expectedResult2, result2);
    }

    @Test
    public void testTaskSetStartHour() {
        Porcupine newPorcupine = new Porcupine(111, "Porky");

        int originalVal1 = 15;
        Task newTask1 = new Task(2, originalVal1, 5, 10, "Kit feeding", newPorcupine);
        int expectedResult1 = 20;
        newTask1.setStartHour(expectedResult1);
        int result1 = newTask1.getStartHour();
        boolean pass = true;
        if (originalVal1 == result1)
        {
            pass = false;
        }
        assertTrue("The setStartHour() method in the Task class doesn't change the startHour", pass);
        assertEquals("The setStartHour() method doesn't work as intended", expectedResult1, result1);

        int originalVal2 = 1;
        Task newTask2 = new Task(2, originalVal2, 5, 10, "Kit feeding", newPorcupine, 2);
        int expectedResult2 = 3;
        newTask2.setStartHour(expectedResult2);
        int result2 = newTask2.getStartHour();
        pass = true;
        if (originalVal2 == result2)
        {
            pass = false;
        }
        assertTrue("The setStartHour() method in the Task class doesn't change the startHour", pass);
        assertEquals("The setStartHour() method doesn't work as intended", expectedResult2, result2);
    }

    @Test
    public void testTaskSetMaxWindow() {
        Fox newFox = new Fox(10, "What does the fox say");

        int originalVal1 = 15;
        Task newTask1 = new Task(2, 3, originalVal1, 10, "Kit feeding", newFox);
        int expectedResult1 = 5;
        newTask1.setMaxWindow(expectedResult1);
        int result1 = newTask1.getMaxWindow();
        boolean pass = true;
        if (originalVal1 == result1)
        {
            pass = false;
        }
        assertTrue("The setMaxWindow() method in the Task class doesn't change the maxWindow", pass);
        assertEquals("The setMaxWindow() method doesn't work as intended", expectedResult1, result1);

        int originalVal2 = 1;
        Task newTask2 = new Task(2, 3, originalVal2, 10, "Kit feeding", newFox, 20);
        int expectedResult2 = 3;
        newTask2.setMaxWindow(expectedResult2);
        int result2 = newTask2.getMaxWindow();
        pass = true;
        if (originalVal2 == result2)
        {
            pass = false;
        }
        assertTrue("The setMaxWindow() method in the Task class doesn't change the maxWindow", pass);
        assertEquals("The setMaxWindow() method doesn't work as intended", expectedResult2, result2);
    }

    @Test
    public void testTaskSetDuration() {
        Raccoon newRaccoon = new Raccoon(10, "Spiky");

        int originalVal1 = 15;
        Task newTask1 = new Task(1, 3, 10, originalVal1, "Kit feeding", newRaccoon);
        int expectedResult1 = 30;
        newTask1.setDuration(expectedResult1);
        int result1 = newTask1.getDuration();
        boolean pass = true;
        if (originalVal1 == result1)
        {
            pass = false;
        }
        assertTrue("The setDuration() method in the Task class doesn't change the duration", pass);
        assertEquals("The setDuration() method doesn't work as intended", expectedResult1, result1);

        int originalVal2 = 45;
        Task newTask2 = new Task(1, 3, 10, originalVal2, "Kit feeding", newRaccoon, 2);
        int expectedResult2 = 10;
        newTask2.setDuration(expectedResult2);
        int result2 = newTask2.getDuration();
        pass = true;
        if (originalVal2 == result2)
        {
            pass = false;
        }
        assertTrue("The setDuration() method in the Task class doesn't change the duration", pass);
        assertEquals("The setDuration() method doesn't work as intended", expectedResult2, result2);
    }

    @Test
    public void testTaskSetTaskType() {
        Beaver newBeaver = new Beaver(21, "DamBuilder");

        String originalVal1 = "Give vitamin injection";
        Task newTask1 = new Task(2, 6, 5, 10, originalVal1, newBeaver);
        String expectedResult1 = "Apply burn ointment back";
        newTask1.setTaskType(expectedResult1);
        String result1 = newTask1.getTaskType();
        boolean pass = true;
        if (originalVal1 == result1)
        {
            pass = false;
        }
        assertTrue("The setTaskType() method in the Task class doesn't change the taskType", pass);
        assertEquals("The setTaskType() method doesn't work as intended", expectedResult1, result1);

        String originalVal2 = "Bandage wound";
        Task newTask2 = new Task(2, 6, 5, 10, originalVal2, newBeaver, 0 );

        String expectedResult2 = "Cage Cleaning";
        newTask2.setTaskType(expectedResult2);
        String result2 = newTask2.getTaskType();
        pass = true;
        if (originalVal2 == result2)
        {
            pass = false;
        }
        assertTrue("The setTaskType() method in the Task class doesn't change the taskType", pass);
        assertEquals("The setTaskType() method doesn't work as intended", expectedResult2, result2);
    }

    @Test
    public void testTaskSetAnimal() {
        Porcupine originalVal1 = new Porcupine(21, "Spiky");
        Task newTask1 = new Task(5, 23, 10, 25, "Inspect broken leg", originalVal1);
        Porcupine expectedResult1 = new Porcupine(25, "Biter");
        newTask1.setAnimal(expectedResult1);
        Animal result1 = newTask1.getAnimal();
        boolean pass = true;
        if (originalVal1 == result1)
        {
            pass = false;
        }
        assertTrue("The setAnimal() method in the Task class doesn't change the animal", pass);
        assertEquals("The setAnimal() method doesn't work as intended", expectedResult1, result1);

        Animal originalVal2 = new Porcupine(25, "Biter");
        Task newTask2 = new Task(5, 23, 10, 25, "Inspect broken leg", originalVal2);
        Coyote expectedResult2 = new Coyote(25, "Biter");
        newTask2.setAnimal(expectedResult2);
        Animal result2 = newTask2.getAnimal();
        pass = true;
        if (originalVal2 == result2)
        {
            pass = false;
        }
        assertTrue("The setAnimal() method in the Task class doesn't change the animal", pass);
        assertEquals("The setAnimal() method doesn't work as intended", expectedResult2, result2);

        Fox originalVal3 = new Fox(1, "Fernie");
        Task newTask3 = new Task(5, 23, 10, 25, "Inspect broken leg", originalVal3, 10);
        Fox expectedResult3 = new Fox(1, "Fernie");
        newTask3.setAnimal(expectedResult3);
        Animal result3 = newTask3.getAnimal();
        pass = true;
        if (originalVal3 == result3)
        {
            pass = false;
        }
        assertTrue("The setAnimal() method in the Task class doesn't change the animal", pass);
        assertEquals("The setAnimal() method doesn't work as intended", expectedResult3, result3);

    }

    @Test
    public void testTaskSetExtraVolunteerStatus() {
        boolean originalVal = false;
        Coyote newCoyote = new Coyote(305, "Shadow");
        Task newTask = new Task(2, 7, 5, 10, "Kit feeding", newCoyote);

        boolean expectedResult = true;
        newTask.setExtraVolunteerStatus(expectedResult);
        boolean result = newTask.getExtraVolunteerStatus();
        boolean pass = true;
        if (originalVal == result)
        {
            pass = false;
        }
        assertTrue("The setExtraVolunteerStatus() method in the Task class doesn't change the extraVolunteerStatus", pass);
        assertEquals("The setExtraVolunteerStatus() method doesn't work as intended", expectedResult, result);

        expectedResult = true;
        newTask.setExtraVolunteerStatus(expectedResult);
        result = newTask.getExtraVolunteerStatus();
        assertEquals("The setExtraVolunteerStatus() method doesn't work as intended", expectedResult, result);
    }

    @Test
    public void testTaskSetPrepTime() {
        Raccoon newRaccoon = new Raccoon(21, "Stripes");

        Task newTask1 = new Task(2, 6, 5, 10, "kit feeding", newRaccoon);
        int expectedResult1 = 10;
        newTask1.setPrepTime(expectedResult1);
        int result1= newTask1.getPrepTime();
        boolean pass = true;
        if (0 == result1)
        {
            pass = false;
        }
        assertTrue("The setPrepTime() method in the Task class doesn't change the prepTime", pass);
        assertEquals("The setPrepTime() method doesn't work as intended", expectedResult1, result1);

        int originalVal2 = 5;
        Task newTask2 = new Task(2, 6, 5, 10, "kit feeding", newRaccoon, originalVal2);
        int expectedResult2 = 0;
        newTask2.setPrepTime(expectedResult2);
        int result2= newTask2.getPrepTime();
        pass = true;
        if (originalVal2 == result2)
        {
            pass = false;
        }
        assertTrue("The setPrepTime() method in the Task class doesn't change the prepTime", pass);
        assertEquals("The setPrepTime() method doesn't work as intended", expectedResult2, result2);
    }

    @Test
    public void testTaskThrowIllegalArgumentException() {
        Beaver newBeaver = new Beaver(21, "DamBuilder");
        boolean pass = false;
        try {
            Task newTask = new Task(2, -1, 5, 10, "Cage cleaning", newBeaver);
        }
        catch (IllegalArgumentException e) {
            pass = true;
        }
        catch (Exception e) {}
        assertTrue("The constructor in the Task class doesn't throw an exception when the startHour is less than 0", pass);

        pass = false;
        try {
            Task newTask = new Task(2, 24, 5, 10, "Cage cleaning", newBeaver);
        }
        catch (IllegalArgumentException e) {
            pass = true;
        }
        catch (Exception e) {}
        assertTrue("The constructor in the Task class doesn't throw an exception when the startHour is greater than 23", pass);

        Task newTask = new Task(10, 23, 12, 50, "Eyedrops", newBeaver);
        pass = false;
        try {
            newTask.setStartHour(-1);
        }
        catch (IllegalArgumentException e) {
            pass = true;
        }
        catch (Exception e) {}
        assertTrue("The setStartHour() method in the Task class doesn't throw an exception when the startHour is less than 0", pass);

        pass = false;
        try {
            newTask.setStartHour(24);
        }
        catch (IllegalArgumentException e) {
            pass = true;
        }
        catch (Exception e) {}
        assertTrue("The setStartHour() method in the Task class doesn't throw an exception when the startHour is greater than 23", pass);
        }

    @Test
    public void testPrintLogDataToString() {
        TreeMap<Integer, ArrayList<Task>> data = populateHashMap();

        String expectedResult = "Schedule for " + LocalDate.now().toString() + "\n\n" +
                                "0:00\n" +
                                "* Kit feeding - beaver (Justin Beaver) --- Duration: 20 minutes\n" +
                                "* Give vitamin injections - raccoon (Spotter) --- Duration: 25 minutes\n\n" +
                                "1:00\n" +
                                "* Treat wound - raccoon (Racer) --- Duration: 40 minutes\n\n" +
                                "2:00 [+ backup volunteer]\n" +
                                "* Rebandage leg wound - beaver (Boots) --- Duration: 10 minutes\n" +
                                "* Feed fox - fox (Hunter) --- Duration: 20 minutes\n" +
                                "* Cage cleaning - porcupine (Porky) --- Duration: 5 minutes\n" +
                                "* Administer antibiotics - fox (Bob) --- Duration: 20 minutes\n\n" +
                                "5:00\n" +
                                "* Sample task - coyote (Shadow) --- Duration: 40 minutes\n\n" +
                                "12:00\n" +
                                "* Eyedrops - porcupine (Spiky) --- Duration: 25 minutes\n" +
                                "* This is a task - coyote (Annie, Oliver and Mowgli) --- Duration: 40 minutes\n\n" +
                                "22:00\n" +
                                "* Feeding fox - fox (Hunter) --- Duration: 10 minutes --- Prep time: 5 minutes\n"+
                                "* Last task - raccoon (Racer) --- Duration: 20 minutes\n\n";
        String actualResult = PrintLog.dataToString(data);
        assertEquals(expectedResult, actualResult);

    }

    //Helper function for one of the tests
    private TreeMap<Integer, ArrayList<Task>> populateHashMap() {
        TreeMap<Integer, ArrayList<Task>> mapToReturn = new TreeMap<Integer, ArrayList<Task>>();
        Animal animal1 = new Beaver(1, "Justin Beaver");
        Animal animal2 = new Fox(2, "Bob");
        Animal animal3 = new Coyote(3, "Shadow");
        Animal animal4 = new Porcupine(4, "Spiky");
        Animal animal5 = new Raccoon(5, "Spotter");
        Animal animal6 = new Beaver(6, "Boots");
        Animal animal7 = new Fox(7, "Hunter");
        Animal animal8 = new Coyote(8, "Annie, Oliver and Mowgli");
        animal8.setOrphanStatus(true);
        Animal animal9 = new Porcupine(9, "Porky");
        Animal animal10 = new Raccoon(10, "Racer");
        ArrayList<Task> hour0 = new ArrayList<>();
        Task task1 = new Task(1, 0, 5, 20, "Kit feeding", animal1);
        Task task2 = new Task(2, 0, 10, 25, "Give vitamin injections", animal5);
        hour0.add(task1);
        hour0.add(task2);

        ArrayList<Task> hour1 = new ArrayList<>();
        Task task3 = new Task(19, 1, 15, 40, "Treat wound", animal10);
        hour1.add(task3);

        ArrayList<Task> hour2 = new ArrayList<>();
        Task task4 = new Task(5, 2, 5, 10, "Rebandage leg wound", animal6);
        Task task5 = new Task(6, 2, 3, 20, "Feed fox", animal7);
        Task task6 = new Task(9, 2, 5, 5, "Cage cleaning", animal9);
        Task task7 = new Task(15, 2, 5, 20, "Administer antibiotics", animal2);
        task4.setExtraVolunteerStatus(true);
        task5.setExtraVolunteerStatus(true);
        task6.setExtraVolunteerStatus(true);
        task7.setExtraVolunteerStatus(true);
        hour2.add(task4);
        hour2.add(task5);
        hour2.add(task6);
        hour2.add(task7);

        ArrayList<Task> hour5 = new ArrayList<>();
        Task task8 = new Task(3, 5, 5, 40, "Sample task", animal3);
        hour5.add(task8);

        ArrayList<Task> hour12 = new ArrayList<>();
        Task task9 = new Task(8, 12, 10, 25, "Eyedrops", animal4);
        Task task10 = new Task(11, 12, 15, 40, "This is a task", animal8);
        hour12.add(task9);
        hour12.add(task10);

        ArrayList<Task> hour22 = new ArrayList<>();
        Task task11 = new Task(21, 22, 2, 10, "Feeding fox", animal7, 5);
        Task task12 = new Task(19, 22, 10, 20, "Last task", animal10);
        hour22.add(task11);
        hour22.add(task12);

        mapToReturn.put(new Integer(0), hour0);
        mapToReturn.put(new Integer(1), hour1);
        mapToReturn.put(new Integer(2), hour2);
        mapToReturn.put(new Integer(5), hour5);
        mapToReturn.put(new Integer(12), hour12);
        mapToReturn.put(new Integer(22), hour22);

        return mapToReturn;
    }


    /*
    @Test
    public void testTooManyEventsException() {
        boolean success = false;
        Porcupine newPorcupine = new Porcupine(69, "Jesus", false);
        LocalDate date = LocalDate.now();
        try{
            Task newTask = new Task(date, 10, 55, "Cleaning Cage", "67", newPorcupine); 
        }
        catch(Exception e) {
            if(e instanceof TooManyEventsException) {
                success = true;
            }
        }
        assertTrue("Wasn't able to use TooManyEventsException", success);
    }
    */


}
