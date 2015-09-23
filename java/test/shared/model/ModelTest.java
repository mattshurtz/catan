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
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

/**
 *
 * @author JanPaul
 */
public class ModelTest {
    
    public ModelTest() {
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
     * Test of parseModel method, of class Model.
     */
    @Test
    public void testParseModel() {
        System.out.println("parseModel");
        String json = "";
        Model expResult = null;
        Model result = Model.parseModel(json);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayer method, of class Model.
     */
    @Test
    public void testGetPlayer() {
        System.out.println("getPlayer");
        int playerIndex = 0;
        Model instance = new Model();
        Player expResult = null;
        Player result = instance.getPlayer(playerIndex);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of receiveNewResources method, of class Model.
     */
    @Test
    public void testReceiveNewResources() {
        System.out.println("receiveNewResources");
        Model instance = new Model();
        instance.receiveNewResources();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canBuildRoad method, of class Model.
     */
    @Test
    public void testCanBuildRoad() throws Exception {
        System.out.println("canBuildRoad");
        EdgeLocation location = null;
        Model instance = new Model();
        boolean expResult = false;
        boolean result = instance.canBuildRoad(location);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buildRoad method, of class Model.
     */
    @Test
    public void testBuildRoad() {
        System.out.println("buildRoad");
        EdgeLocation location = null;
        Model instance = new Model();
        instance.buildRoad(location);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canBuildSettlement method, of class Model.
     */
    @Test
    public void testCanBuildSettlement() throws Exception {
        System.out.println("canBuildSettlement");
        VertexLocation location = null;
        int playerIndex = 0;
        Model instance = new Model();
        boolean expResult = false;
        boolean result = instance.canBuildSettlement(location, playerIndex);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buildSettlement method, of class Model.
     */
    @Test
    public void testBuildSettlement() {
        System.out.println("buildSettlement");
        EdgeLocation location = null;
        int playerIndex = 0;
        Model instance = new Model();
        instance.buildSettlement(location, playerIndex);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canBuildCity method, of class Model.
     */
    @Test
    public void testCanBuildCity() throws Exception {
        System.out.println("canBuildCity");
        VertexLocation location = null;
        int playerIndex = 0;
        Model instance = new Model();
        boolean expResult = false;
        boolean result = instance.canBuildCity(location, playerIndex);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buildCity method, of class Model.
     */
    @Test
    public void testBuildCity() {
        System.out.println("buildCity");
        VertexLocation location = null;
        int playerIndex = 0;
        Model instance = new Model();
        instance.buildCity(location, playerIndex);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of distributeResources method, of class Model.
     */
    @Test
    public void testDistributeResources() {
        System.out.println("distributeResources");
        int rolledNumber = 0;
        Model instance = new Model();
        instance.distributeResources(rolledNumber);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
