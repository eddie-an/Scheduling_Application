package edu.ucalgary.oop;

import org.junit.Test;

import static org.junit.Assert.*;
import java.time.LocalDate;

public class TestClass {

    public TestClass() {
    }

    @Test
    public void testAnimalGetId() {
        int expectedResult1 = 1;
        int expectedResult2 = 202;
        int expectedResult3 = 13;
        int expectedResult4 = 10;
        int expectedResult5 = 100;

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

        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);
        assertEquals(expectedResult4, result4);
        assertEquals(expectedResult5, result5);

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

        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);
        assertEquals(expectedResult4, result4);
        assertEquals(expectedResult5, result5);
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

        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);
        assertEquals(expectedResult4, result4);
        assertEquals(expectedResult5, result5);

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

        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);
        assertEquals(expectedResult4, result4);
        assertEquals(expectedResult5, result5);
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

        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);
        assertEquals(expectedResult4, result4);
        assertEquals(expectedResult5, result5);
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

        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);
        assertEquals(expectedResult4, result4);
        assertEquals(expectedResult5, result5);
    }

    @Test
    public void testAnimalSetName() {
        String expectedResult1 = "Spike";
        String expectedResult2 = "Twilight";
        String expectedResult3 = "Spike";
        String expectedResult4 = "Eraser";
        String expectedResult5 = "Sunshine";

        Beaver animal1 = new Beaver(1, "ExampleName");
        Coyote animal2 = new Coyote(1, "ExampleName");
        Fox animal3 = new Fox(1, "ExampleName");
        Porcupine animal4 = new Porcupine(1, "ExampleName");
        Raccoon animal5 = new Raccoon(1, "ExampleName");

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

        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);
        assertEquals(expectedResult4, result4);
        assertEquals(expectedResult5, result5);
    }

    @Test
    public void testTaskGetTaskID() {
        int expectedResult = 20;
        Fox newFox = new Fox(6, "Kanye");
        Task newTask = new Task(expectedResult, 1, 5, 20, "Kit Feeding", newFox);
        int result = newTask.getTaskID();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testTaskGetStartHour() {
        int expectedResult = 12;
        Porcupine newPorcupine = new Porcupine(69, "Jesus");
        Task newTask = new Task(1, expectedResult, 10, 30, "Flush Neck Wound", newPorcupine);
        int result = newTask.getStartHour();
        assertEquals(expectedResult, result);
    }


    @Test
    public void testTaskGetMaxWindow() {
        int expectedResult = 3;
        Coyote newCoyote = new Coyote(6, "Kanye");
        Task newTask = new Task(2, 11, expectedResult, 45, "Cleaning Cage", newCoyote);

        int result = newTask.getMaxWindow();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testTaskGetDuration() {
        int expectedResult = 25;
        Raccoon newRaccoon = new Raccoon(6, "Bob The Builder");
        Task newTask = new Task(2, 0, 5, expectedResult, "Administer antibiotics", newRaccoon);

        int result = newTask.getDuration();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testTaskGetTaskType() {
        String expectedResult = "Eyedrops";
        Beaver newBeaver = new Beaver(31, "Justin Beaver");
        Task newTask = new Task(2, 6, 5, 10, expectedResult, newBeaver);

        String result = newTask.getTaskType();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testTaskGetAnimal() {
        Coyote expectedResult = new Coyote(305, "Pitbull");
        Task newTask = new Task(2, 7, 5, 10, "Kit feeding", expectedResult);

        Animal result = newTask.getAnimal();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testTaskGetExtraVolunteerStatus() {
        Raccoon newRaccoon = new Raccoon(305, "blue");
        Task newTask = new Task(2, 7, 5, 10, "Kit feeding", newRaccoon);

        boolean expectedResult = false;
        boolean result = newTask.getExtraVolunteerStatus();
        assertEquals("The value should be false by default when instantiated", expectedResult, result);
    }

    @Test
    public void testTaskSetStartHour() {
        int originalVal = 15;
        Porcupine newPorcupine = new Porcupine(111, "Porky");
        Task newTask = new Task(2, originalVal, 5, 10, "Kit feeding", newPorcupine);

        int expectedResult = 20;
        newTask.setStartHour(expectedResult);
        int result = newTask.getStartHour();
        boolean pass = true;
        if (originalVal == result)
        {
            pass = false;
        }
        assertTrue("The setStartHour() method in the Task class doesn't change the startHour", pass);
        assertEquals("The setStartHour() method doesn't work as intended", expectedResult, result);
    }

    @Test
    public void testTaskSetMaxWindow() {
        int originalVal = 15;
        Fox newFox = new Fox(10, "What does the fox say");
        Task newTask = new Task(2, 3, originalVal, 10, "Kit feeding", newFox);

        int expectedResult = 5;
        newTask.setMaxWindow(expectedResult);
        int result = newTask.getMaxWindow();
        boolean pass = true;
        if (originalVal == result)
        {
            pass = false;
        }
        assertTrue("The setMaxWindow() method in the Task class doesn't change the maxWindow", pass);
        assertEquals("The setMaxWindow() method doesn't work as intended", expectedResult, result);
    }

    @Test
    public void testTaskSetDuration() {
        int originalVal = 15;
        Raccoon newRaccoon = new Raccoon(10, "Spiky");
        Task newTask = new Task(1, 3, 10, originalVal, "Kit feeding", newRaccoon);

        int expectedResult = 30;
        newTask.setDuration(expectedResult);
        int result = newTask.getDuration();
        boolean pass = true;
        if (originalVal == result)
        {
            pass = false;
        }
        assertTrue("The setDuration() method in the Task class doesn't change the duration", pass);
        assertEquals("The setDuration() method doesn't work as intended", expectedResult, result);
    }

    @Test
    public void testTaskSetTaskType() {
        String originalVal = "Give vitamin injection";
        Beaver newBeaver = new Beaver(21, "DamBuilder");
        Task newTask = new Task(2, 6, 5, 10, originalVal, newBeaver);

        String expectedResult = "Apply burn ointment back";
        newTask.setTaskType(expectedResult);
        String result = newTask.getTaskType();
        boolean pass = true;
        if (originalVal == result)
        {
            pass = false;
        }
        assertTrue("The setTaskType() method in the Task class doesn't change the taskType", pass);
        assertEquals("The setTaskType() method doesn't work as intended", expectedResult, result);
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
        Task newTask3 = new Task(5, 23, 10, 25, "Inspect broken leg", originalVal3);
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
