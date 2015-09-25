/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import shared.definitions.CatanColor;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

/**
 *
 */
public class ModelTest {
    
    public Model testModel(){
        Model testModel = new Model();
        
        Player playerOne = new Player();
        DevCardList pOneDevCardList = new DevCardList();
        ResourceList pOneResourceList = new ResourceList();
        pOneResourceList.setBrick(0);
        pOneResourceList.setWood(0);
        pOneResourceList.setWheat(0);
        pOneResourceList.setSheep(0);
        pOneResourceList.setOre(0);
        pOneDevCardList.setMonopoly(0);
        pOneDevCardList.setMonument(0);
        pOneDevCardList.setRoadBuilding(0);
        pOneDevCardList.setSoldier(0);
        pOneDevCardList.setYearOfPlenty(0);
        playerOne.setCities(4);
        playerOne.setColor(CatanColor.BLUE);
        playerOne.setDiscarded(true);
        playerOne.setMonuments(0);
        playerOne.setName("Alex");
        playerOne.setNewDevCards(pOneDevCardList);
        playerOne.setOldDevCards(pOneDevCardList);
        playerOne.setPlayedDevCard(false);
        playerOne.setPlayerID(1);
        playerOne.setRoads(13);
        playerOne.setSettlements(3);
        playerOne.setSoldiers(0);
        playerOne.setVictoryPoints(2);
        playerOne.setResources(pOneResourceList);
        playerOne.setPlayerIndex(0);
        
        Player playerTwo = new Player();
        DevCardList pTwoDevCardList = new DevCardList();
        ResourceList pTwoResourceList = new ResourceList();
        pTwoResourceList.setBrick(1);
        pTwoResourceList.setWood(1);
        pTwoResourceList.setWheat(2);
        pTwoResourceList.setSheep(1);
        pTwoResourceList.setOre(3);
        pTwoDevCardList.setMonopoly(1);
        pTwoDevCardList.setMonument(1);
        pTwoDevCardList.setRoadBuilding(1);
        pTwoDevCardList.setSoldier(1);
        pTwoDevCardList.setYearOfPlenty(1);
        playerTwo.setCities(4);
        playerTwo.setColor(CatanColor.BLUE);
        playerTwo.setDiscarded(true);
        playerTwo.setMonuments(1);
        playerTwo.setName("Jan");
        playerTwo.setNewDevCards(pTwoDevCardList);
        playerTwo.setOldDevCards(pTwoDevCardList);
        playerTwo.setPlayedDevCard(true);
        playerTwo.setPlayerID(0);
        playerTwo.setRoads(3);
        playerTwo.setSettlements(3);
        playerTwo.setSoldiers(1);
        playerTwo.setVictoryPoints(2);
        playerTwo.setResources(pTwoResourceList);
        playerTwo.setPlayerIndex(1);
        
            
        Player playerThree = new Player();
        DevCardList pThreeDevCardList = new DevCardList();
        ResourceList pThreeResourceList = new ResourceList();
        pThreeResourceList.setBrick(2);
        pThreeResourceList.setWood(2);
        pThreeResourceList.setWheat(2);
        pThreeResourceList.setSheep(2);
        pThreeResourceList.setOre(2);
        pThreeDevCardList.setMonopoly(1);
        pThreeDevCardList.setMonument(1);
        pThreeDevCardList.setRoadBuilding(1);
        pThreeDevCardList.setSoldier(1);
        pThreeDevCardList.setYearOfPlenty(1);
        playerThree.setCities(4);
        playerThree.setColor(CatanColor.BLUE);
        playerThree.setDiscarded(true);
        playerThree.setMonuments(3);
        playerThree.setName("Scott");
        playerThree.setNewDevCards(null);
        playerThree.setOldDevCards(pThreeDevCardList);
        playerThree.setPlayedDevCard(true);
        playerThree.setPlayerID(1);
        playerThree.setRoads(3);
        playerThree.setSettlements(2);
        playerThree.setSoldiers(2);
        playerThree.setVictoryPoints(5);
        playerThree.setResources(pThreeResourceList);
        playerThree.setPlayerIndex(2);
       
        
        Player playerFour = new Player();
        DevCardList pFourDevCardList = new DevCardList();
        ResourceList pFourResourceList = new ResourceList();
        pFourResourceList.setBrick(5);
        pFourResourceList.setWood(5);
        pFourResourceList.setWheat(5);
        pFourResourceList.setSheep(5);
        pFourResourceList.setOre(5);
        pFourDevCardList.setMonopoly(1);
        pFourDevCardList.setMonument(1);
        pFourDevCardList.setRoadBuilding(1);
        pFourDevCardList.setSoldier(1);
        pFourDevCardList.setYearOfPlenty(1);
        playerFour.setCities(4);
        playerFour.setColor(CatanColor.BLUE);
        playerFour.setDiscarded(true);
        playerFour.setMonuments(3);
        playerFour.setName("Matt");
        playerFour.setNewDevCards(null);
        playerFour.setOldDevCards(pFourDevCardList);
        playerFour.setPlayedDevCard(true);
        playerFour.setPlayerID(1);
        playerFour.setRoads(3);
        playerFour.setSettlements(2);
        playerFour.setSoldiers(2);
        playerFour.setVictoryPoints(5);
        playerFour.setResources(pFourResourceList);
        playerFour.setPlayerIndex(3);
        
        ArrayList<Player> players = new ArrayList<Player>(Arrays.asList(
                playerOne, playerTwo, playerThree, playerFour));
        
        testModel.setPlayers(players);
        ResourceList bank = new ResourceList();
        bank.setBrick(10);
        bank.setSheep(10);
        bank.setOre(10);
        bank.setWheat(10);
        bank.setWood(10);
        
        
        
        testModel.setBank(bank);
        testModel.setChat(null);
        testModel.setLog(null);
        testModel.setMap(null);
        testModel.setRemainingDevCards(pOneDevCardList);
        testModel.setTradeOffer(null);
        testModel.setTurnTracker(null);
        testModel.setVersion(version);
        testModel.setWinner(winner);
        
        
        
    }
    
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
