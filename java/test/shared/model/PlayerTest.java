/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import shared.locations.HexLocation;

/**
 *
 * @author JanPaul
 */
public class PlayerTest {
    
    public PlayerTest() {
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
     * Test of canPlayDevCard method, of class Player.
     */
    @Test
    public void testCanPlayDevCard() {
        System.out.println("canPlayDevCard");
        Player instance = new Player();
        boolean expResult = false;
        boolean result = instance.canPlayDevCard();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResources method, of class Player.
     */
    @Test
    public void testGetResources() {
        System.out.println("getResources");
        Player instance = new Player();
        ResourceList expResult = null;
        ResourceList result = instance.getResources();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasRoad method, of class Player.
     */
    @Test
    public void testHasRoad() {
        System.out.println("hasRoad");
        Player instance = new Player();
        boolean expResult = false;
        boolean result = instance.hasRoad();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasSettlment method, of class Player.
     */
    @Test
    public void testHasSettlment() {
        System.out.println("hasSettlment");
        Player instance = new Player();
        boolean expResult = false;
        boolean result = instance.hasSettlment();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasCity method, of class Player.
     */
    @Test
    public void testHasCity() {
        System.out.println("hasCity");
        Player instance = new Player();
        boolean expResult = false;
        boolean result = instance.hasCity();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buildCity method, of class Player.
     */
    @Test
    public void testBuildCity() {
        System.out.println("buildCity");
        Player instance = new Player();
        instance.buildCity();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buildRoad method, of class Player.
     */
    @Test
    public void testBuildRoad() {
        System.out.println("buildRoad");
        Player instance = new Player();
        instance.buildRoad();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buildSettlment method, of class Player.
     */
    @Test
    public void testBuildSettlment() {
        System.out.println("buildSettlment");
        Player instance = new Player();
        instance.buildSettlment();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of distributeResources method, of class Player.
     */
    @Test
    public void testDistributeResources() {
        System.out.println("distributeResources");
        int rolledNumber = 0;
        HexLocation robber = null;
        Player instance = new Player();
        instance.distributeResources(rolledNumber, robber);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
