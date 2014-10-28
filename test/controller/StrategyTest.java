/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Elevator;
import model.Request;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nhuan
 */
public class StrategyTest {
    
    public StrategyTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of findOptimalElevator method, of class Strategy.
     */
    @Test
    public void testFindOptimalElevator() {
        System.out.println("findOptimalElevator");
        Request request = null;
        Elevator[] elevators = null;
        Strategy instance = null;
        int expResult = 0;
        int result = instance.findOptimalElevator(request, elevators);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
