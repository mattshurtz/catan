/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.facade;

import client.proxy.MockProxy;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import shared.locations.EdgeLocation;
import shared.model.Model;

/**
 *
 */
public class CatanFacadeTest {
    
    public CatanFacadeTest() {
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
     * Test of getCanDoFacade method, of class CatanFacade.
     */
    @Test
    public void testGetCanDoFacade() {
        System.out.println("getCanDoFacade");
        CanDoFacade expResult = null;
        CanDoFacade result = CatanFacade.getCanDoFacade();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDoFacade method, of class CatanFacade.
     */
    @Test
    public void testGetDoFacade() {
        System.out.println("getDoFacade");
        DoFacade expResult = null;
        DoFacade result = CatanFacade.getDoFacade();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGameHubFacade method, of class CatanFacade.
     */
    @Test
    public void testGetGameHubFacade() {
        System.out.println("getGameHubFacade");
        GameHubFacade expResult = null;
        GameHubFacade result = CatanFacade.getGameHubFacade();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canBuyRoad method, of class CatanFacade.
     */
    @Test
    public void testCanBuyRoad() throws Exception {
        MockProxy proxy = new MockProxy();
        Model model = new Model();
        
        CatanFacade instance = new CatanFacade(proxy, model);
        
        boolean expResult = false;
        boolean result = instance.canBuyRoad();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canBuildRoad method, of class CatanFacade.
     */
    @Test
    public void testCanBuildRoad() throws Exception {
                MockProxy proxy = new MockProxy();
        Model model = new Model();
        
        System.out.println("canBuildRoad");
        EdgeLocation location = null;
        CatanFacade instance = new CatanFacade(proxy, model);
        boolean expResult = false;
        boolean result = instance.canBuildRoad(location);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buildRoad method, of class CatanFacade.
     */
    @Test
    public void testBuildRoad() {
        System.out.println("buildRoad");
        //CatanFacade instance = new CatanFacade();
        //instance.buildRoad();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
