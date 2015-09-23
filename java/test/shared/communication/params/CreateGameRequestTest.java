/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.communication.params;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JanPaul
 */
public class CreateGameRequestTest {
    
    public CreateGameRequestTest() {
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
     * Test of isRandomTiles method, of class CreateGameRequest.
     */
    @Test
    public void testIsRandomTiles() {
        System.out.println("isRandomTiles");
        CreateGameRequest instance = new CreateGameRequest();
        boolean expResult = false;
        boolean result = instance.isRandomTiles();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRandomTiles method, of class CreateGameRequest.
     */
    @Test
    public void testSetRandomTiles() {
        System.out.println("setRandomTiles");
        boolean randomTiles = false;
        CreateGameRequest instance = new CreateGameRequest();
        instance.setRandomTiles(randomTiles);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isRandomNumbers method, of class CreateGameRequest.
     */
    @Test
    public void testIsRandomNumbers() {
        System.out.println("isRandomNumbers");
        CreateGameRequest instance = new CreateGameRequest();
        boolean expResult = false;
        boolean result = instance.isRandomNumbers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRandomNumbers method, of class CreateGameRequest.
     */
    @Test
    public void testSetRandomNumbers() {
        System.out.println("setRandomNumbers");
        boolean randomNumbers = false;
        CreateGameRequest instance = new CreateGameRequest();
        instance.setRandomNumbers(randomNumbers);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isRandomPorts method, of class CreateGameRequest.
     */
    @Test
    public void testIsRandomPorts() {
        System.out.println("isRandomPorts");
        CreateGameRequest instance = new CreateGameRequest();
        boolean expResult = false;
        boolean result = instance.isRandomPorts();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRandomPorts method, of class CreateGameRequest.
     */
    @Test
    public void testSetRandomPorts() {
        System.out.println("setRandomPorts");
        boolean randomPorts = false;
        CreateGameRequest instance = new CreateGameRequest();
        instance.setRandomPorts(randomPorts);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isName method, of class CreateGameRequest.
     */
    @Test
    public void testIsName() {
        System.out.println("isName");
        CreateGameRequest instance = new CreateGameRequest();
        boolean expResult = false;
        boolean result = instance.isName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class CreateGameRequest.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        boolean name = false;
        CreateGameRequest instance = new CreateGameRequest();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
