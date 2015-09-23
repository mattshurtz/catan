/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.facade;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;
import shared.model.ResourceList;

/**
 *
 * @author JanPaul
 */
public class CanDoFacadeTest {
    
    public CanDoFacadeTest() {
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
     * Test of canOfferTrade method, of class CanDoFacade.
     */
    @Test
    public void testCanOfferTrade() {
        System.out.println("canOfferTrade");
        int playerIndex = 0;
        ResourceList resourceList = null;
        CanDoFacade instance = null;
        boolean expResult = false;
        boolean result = instance.canOfferTrade(playerIndex, resourceList);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canAcceptTrade method, of class CanDoFacade.
     */
    @Test
    public void testCanAcceptTrade() {
        System.out.println("canAcceptTrade");
        int playerIndex = 0;
        CanDoFacade instance = null;
        boolean expResult = false;
        boolean result = instance.canAcceptTrade(playerIndex);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canMaritimeTrade method, of class CanDoFacade.
     */
    @Test
    public void testCanMaritimeTrade() {
        System.out.println("canMaritimeTrade");
        String inputResource = "";
        String ouputResouce = "";
        double ratio = 0.0;
        CanDoFacade instance = null;
        boolean expResult = false;
        boolean result = instance.canMaritimeTrade(inputResource, ouputResouce, ratio);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canBuyDevCard method, of class CanDoFacade.
     */
    @Test
    public void testCanBuyDevCard() {
        System.out.println("canBuyDevCard");
        CanDoFacade instance = null;
        instance.canBuyDevCard();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canPlayYearOfPlenty method, of class CanDoFacade.
     */
    @Test
    public void testCanPlayYearOfPlenty() {
        System.out.println("canPlayYearOfPlenty");
        CanDoFacade instance = null;
        instance.canPlayYearOfPlenty();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canPlayRoadBuilding method, of class CanDoFacade.
     */
    @Test
    public void testCanPlayRoadBuilding() {
        System.out.println("canPlayRoadBuilding");
        CanDoFacade instance = null;
        instance.canPlayRoadBuilding();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canPlaySoldier method, of class CanDoFacade.
     */
    @Test
    public void testCanPlaySoldier() {
        System.out.println("canPlaySoldier");
        CanDoFacade instance = null;
        instance.canPlaySoldier();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canPlayMonopoly method, of class CanDoFacade.
     */
    @Test
    public void testCanPlayMonopoly() {
        System.out.println("canPlayMonopoly");
        CanDoFacade instance = null;
        instance.canPlayMonopoly();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canBuildRoad method, of class CanDoFacade.
     */
    @Test
    public void testCanBuildRoad() {
        System.out.println("canBuildRoad");
        EdgeLocation roadLocation = null;
        CanDoFacade instance = null;
        boolean expResult = false;
        boolean result = instance.canBuildRoad(roadLocation);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canBuildSettlement method, of class CanDoFacade.
     */
    @Test
    public void testCanBuildSettlement() {
        System.out.println("canBuildSettlement");
        VertexLocation vertex = null;
        CanDoFacade instance = null;
        boolean expResult = false;
        boolean result = instance.canBuildSettlement(vertex);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canBuildCity method, of class CanDoFacade.
     */
    @Test
    public void testCanBuildCity() {
        System.out.println("canBuildCity");
        VertexLocation vertex = null;
        CanDoFacade instance = null;
        boolean expResult = false;
        boolean result = instance.canBuildCity(vertex);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canSendChat method, of class CanDoFacade.
     */
    @Test
    public void testCanSendChat() {
        System.out.println("canSendChat");
        CanDoFacade instance = null;
        instance.canSendChat();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canRollNumber method, of class CanDoFacade.
     */
    @Test
    public void testCanRollNumber() {
        System.out.println("canRollNumber");
        CanDoFacade instance = null;
        instance.canRollNumber();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canRobPlayer method, of class CanDoFacade.
     */
    @Test
    public void testCanRobPlayer() {
        System.out.println("canRobPlayer");
        CanDoFacade instance = null;
        instance.canRobPlayer();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canFinishTurn method, of class CanDoFacade.
     */
    @Test
    public void testCanFinishTurn() {
        System.out.println("canFinishTurn");
        CanDoFacade instance = null;
        instance.canFinishTurn();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class CanDoFacadeImpl extends CanDoFacade {

        public CanDoFacadeImpl() {
            super(null, null);
        }
    }
    
}
