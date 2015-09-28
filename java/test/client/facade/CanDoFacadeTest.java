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
import shared.definitions.ResourceType;
import shared.exceptions.InsufficentSupplies;
import shared.locations.VertexLocation;
import shared.model.Model;
import shared.model.ModelTest;
import shared.model.Player;
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
        boolean expResult = false;
        boolean result = instance.canBuyDevCard();
        assertEquals(expResult, result);

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
        boolean expResult = false;
        boolean result = instance.canPlayYearOfPlenty();
        assertEquals(expResult, result);
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
        boolean expResult = false;
        boolean result = instance.canPlayRoadBuilding();
        assertEquals(expResult, result);
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
        boolean expResult = false;
        boolean result = instance.canPlaySoldier();
        assertEquals(expResult, result);
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
        boolean expResult = false;
        boolean result = instance.canPlayMonopoly();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canBuildRoad method, of class CanDoFacade.
     */
    @Test
    public void testCanBuildRoad() throws Exception {
        Model model = ModelTest.testModel();
       
        //model.canBuildRoad(null)
        

        
    }

    /**
     * Test of canBuyRoad method, of class CanDoFacade.
     */
    @Test
    public void testCanBuyRoad() throws Exception {
        Model model = ModelTest.testModel();
        // current player has no brick so this test should fail. 
        try{
            model.canBuyRoad();
            fail("but it didn't throw an exception!");
        }catch(InsufficentSupplies e){
            // This player did not have these supplies. 
        }
        
        int currentPlayer = model.getTurnTracker().getCurrentTurn();
        // the current player in the testModel is the first player
        assertEquals(currentPlayer, 0);
        
        Player player = model.getPlayer(currentPlayer);
        player.getResources().addResource(ResourceType.BRICK);
        
        assertEquals(player.getResources().getBrick(), 1);
        assertEquals(player.getResources().getWood(),1);
        
        try{
            model.canBuyRoad();
        }catch(InsufficentSupplies e){
            fail(e.getMessage()); 
        }
        
        player.setRoads(0);
        
        assertEquals(player.getRoads(),0);
        // Player has no roads so an exception should be thrown
        try{
            model.canBuyRoad();
            fail("did not have road");
        }catch(InsufficentSupplies e){
            
        }

    }
    
    @Test
    public void testCanBuyRoadPieces() throws Exception{
        Model model = ModelTest.testModel();
        
        Player player = model.getPlayer(model.getTurnTracker().getCurrentTurn());
        player.setRoads(0);
        
        assertEquals(player.getRoads(),0);
        
        try{
            model.canBuyRoad();
            fail("did not have road");
        }catch(InsufficentSupplies e){
            
        }
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
        boolean expResult = false;
        boolean result = instance.canSendChat();
        assertEquals(expResult, result);
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
        boolean expResult = false;
        boolean result = instance.canRollNumber();
        assertEquals(expResult, result);
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
        boolean expResult = false;
        boolean result = instance.canRobPlayer();
        assertEquals(expResult, result);
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
        boolean expResult = false;
        boolean result = instance.canFinishTurn();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class CanDoFacadeImpl extends CanDoFacade {
        public CanDoFacadeImpl() {
            super(null, null);
        }
    }
    
}
