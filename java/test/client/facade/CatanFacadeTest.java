/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.facade;

import client.proxy.IServerProxy;
import client.proxy.MockProxy;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import shared.model.Model;
import shared.model.ModelTest;

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
        Model model = ModelTest.testModel();
        MockProxy mockProxy = new MockProxy();
        CatanFacade.setup(mockProxy,model);        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setup method, of class CatanFacade.
     */
    @Test
    public void testSetup() {
        System.out.println("setup");
        IServerProxy proxy = null;
        Model model = null;
        CatanFacade.setup(proxy, model);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getModel method, of class CatanFacade.
     */
    @Test
    public void testGetModel() {
        System.out.println("getModel");
        Model expResult = null;
        Model result = CatanFacade.getModel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setModel method, of class CatanFacade.
     */
    @Test
    public void testSetModel() {
        System.out.println("setModel");
        Model model = null;
        CatanFacade.setModel(model);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
     * Test of isIsServer method, of class CatanFacade.
     */
    @Test
    public void testIsIsServer() {
        System.out.println("isIsServer");
        boolean expResult = false;
        boolean result = CatanFacade.isIsServer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIsServer method, of class CatanFacade.
     */
    @Test
    public void testSetIsServer() {
        System.out.println("setIsServer");
        boolean isServer = false;
        CatanFacade.setIsServer(isServer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMyPlayerIndex method, of class CatanFacade.
     */
    @Test
    public void testGetMyPlayerIndex() {
        System.out.println("getMyPlayerIndex");
        int expResult = 0;
        int result = CatanFacade.getMyPlayerIndex();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMyPlayerIndex method, of class CatanFacade.
     */
    @Test
    public void testSetMyPlayerIndex() {
        System.out.println("setMyPlayerIndex");
        int myPlayerIndex = 0;
        CatanFacade.setMyPlayerIndex(myPlayerIndex);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMyTurn method, of class CatanFacade.
     */
    @Test
    public void testGetMyTurn() {
        System.out.println("getMyTurn");
        CanDoFacadeMyTurn expResult = null;
        CanDoFacadeMyTurn result = CatanFacade.getMyTurn();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNotMyTurn method, of class CatanFacade.
     */
    @Test
    public void testGetNotMyTurn() {
        System.out.println("getNotMyTurn");
        CanDoFacadeNotMyTurn expResult = null;
        CanDoFacadeNotMyTurn result = CatanFacade.getNotMyTurn();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProxy method, of class CatanFacade.
     */
    @Test
    public void testGetProxy() {
        System.out.println("getProxy");
        IServerProxy expResult = null;
        IServerProxy result = CatanFacade.getProxy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    
    
}
