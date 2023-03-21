package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

public class Test {

    public Test() {
    }

    @Test
    public void testAnimalGetters() {
        Beaver newBeaver = new Beaver(3, "ExampleName", true);
        
        int expectedResult = 3;
        int result = newBeaver.getAnimalID();
        assertEquals(expectedResult, result);
    }


    @Test



}








