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
import shared.definitions.TurnStatus;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

/**
 *
 */
public class ModelTest {
    
    public Model testModel(){
        Model testModel = new Model();
        
        Player playerOne = new Player();
        ResourceList pOneResourceList = new ResourceList();
        pOneResourceList.setBrick(0);
        pOneResourceList.setWood(1);
        pOneResourceList.setWheat(1);
        pOneResourceList.setSheep(1);
        pOneResourceList.setOre(0);
        playerOne.setCities(4);
        playerOne.setColor(CatanColor.YELLOW);
        playerOne.setDiscarded(false);
        playerOne.setMonuments(0);
        playerOne.setName("Sam");
        playerOne.setNewDevCards(new DevCardList());
        playerOne.setOldDevCards(new DevCardList());
        playerOne.setPlayedDevCard(false);
        playerOne.setPlayerID(0);
        playerOne.setRoads(13);
        playerOne.setSettlements(3);
        playerOne.setSoldiers(0);
        playerOne.setVictoryPoints(2);
        playerOne.setResources(pOneResourceList);
        playerOne.setPlayerIndex(0);
        
        Player playerTwo = new Player();
        ResourceList pTwoResourceList = new ResourceList();
        pTwoResourceList.setBrick(1);
        pTwoResourceList.setWood(0);
        pTwoResourceList.setSheep(1);
        pTwoResourceList.setWheat(0);
        pTwoResourceList.setOre(1);
        playerTwo.setResources(pTwoResourceList);
        playerTwo.setOldDevCards(new DevCardList());
        playerTwo.setNewDevCards(new DevCardList());
        playerTwo.setRoads(13);
        playerTwo.setCities(3);
        playerTwo.setSettlements(4);
        playerTwo.setSoldiers(0);
        playerTwo.setVictoryPoints(2);
        playerTwo.setMonuments(0);
        playerTwo.setPlayedDevCard(false);
        playerTwo.setDiscarded(false);
        playerTwo.setPlayerID(1);
        playerTwo.setPlayerIndex(1);
        playerTwo.setName("Brooke");
        playerTwo.setColor(CatanColor.BLUE);

        Player playerThree = new Player();
        ResourceList pThreeResourceList = new ResourceList();
        pThreeResourceList.setBrick(0);
        pThreeResourceList.setWood(1);
        pThreeResourceList.setSheep(1);
        pThreeResourceList.setWheat(1);
        pThreeResourceList.setOre(0);
        playerThree.setResources(pThreeResourceList);
        playerThree.setOldDevCards(new DevCardList());
        playerThree.setNewDevCards(new DevCardList());
        playerThree.setRoads(13);
        playerThree.setCities(4);
        playerThree.setSettlements(3);
        playerThree.setSoldiers(0);
        playerThree.setVictoryPoints(2);
        playerThree.setMonuments(0);
        playerThree.setPlayedDevCard(false);
        playerThree.setDiscarded(false);
        playerThree.setPlayerID(10);
        playerThree.setPlayerIndex(2);
        playerThree.setName("Pete");
        playerThree.setColor(CatanColor.RED);
       
        Player playerFour = new Player();
        ResourceList pFourResourceList = new ResourceList();
        pFourResourceList.setBrick(0);
        pFourResourceList.setWood(1);
        pFourResourceList.setSheep(1);
        pFourResourceList.setWheat(0);
        pFourResourceList.setOre(1);
        playerFour.setResources(pFourResourceList);
        playerFour.setOldDevCards(new DevCardList());
        playerFour.setNewDevCards(new DevCardList());
        playerFour.setRoads(13);
        playerFour.setCities(4);
        playerFour.setSettlements(3);
        playerFour.setSoldiers(0);
        playerFour.setVictoryPoints(2);
        playerFour.setMonuments(0);
        playerFour.setPlayedDevCard(false);
        playerFour.setDiscarded(false);
        playerFour.setPlayerID(11);
        playerFour.setPlayerIndex(3);
        playerFour.setName("Mark");
        playerFour.setColor(CatanColor.GREEN);
        
        ArrayList<Player> players = new ArrayList<Player>(Arrays.asList(
                playerOne, playerTwo, playerThree, playerFour));
        
        testModel.setPlayers(players);
        ResourceList bank = new ResourceList();
        bank.setBrick(23);
        bank.setWood(21);
        bank.setSheep(20);
        bank.setWheat(22);
        bank.setOre(22);
        
        //Make the test chat
        ArrayList<MessageLine> chatLines = new ArrayList<MessageLine>();
        MessageList toyChat = new MessageList(chatLines);
        
        //Make the test Log
        ArrayList<MessageLine> logLines = new ArrayList<MessageLine>();
        logLines.add(new MessageLine("Sam", "Sam built a road"));
        logLines.add(new MessageLine("Sam", "Sam built a settlement"));
        logLines.add(new MessageLine("Sam", "Sam\u0027s turn just ended"));
        logLines.add(new MessageLine("Brooke", "Brooke built a road"));
        logLines.add(new MessageLine("Brooke", "Brooke built a settlement"));
        logLines.add(new MessageLine("Brooke", "Brooke\u0027s turn just ended"));
        logLines.add(new MessageLine("Pete", "Pete built a road"));
        logLines.add(new MessageLine("Pete", "Pete built a settlement"));
        logLines.add(new MessageLine("Pete", "Pete\u0027s turn just ended"));
        logLines.add(new MessageLine("Mark", "Mark built a road"));
        logLines.add(new MessageLine("Mark", "Mark built a settlement"));
        logLines.add(new MessageLine("Mark", "Mark\u0027s turn just ended"));
        logLines.add(new MessageLine("Mark", "Mark built a road"));
        logLines.add(new MessageLine("Mark", "Mark built a settlement"));
        logLines.add(new MessageLine("Mark", "Mark\u0027s turn just ended"));
        logLines.add(new MessageLine("Pete", "Pete built a road"));
        logLines.add(new MessageLine("Pete", "Pete built a settlement"));
        logLines.add(new MessageLine("Pete", "Pete\u0027s turn just ended"));
        logLines.add(new MessageLine("Brooke", "Brooke built a road"));
        logLines.add(new MessageLine("Brooke", "Brooke built a settlement"));
        logLines.add(new MessageLine("Brooke", "Brooke\u0027s turn just ended"));
        logLines.add(new MessageLine("Sam", "Sam built a road"));
        logLines.add(new MessageLine("Sam", "Sam built a settlement"));
        logLines.add(new MessageLine("Sam", "Sam\u0027s turn just ended"));
        MessageList toyLog = new MessageList(logLines);
        
        //Make new devcard list for Deck
        DevCardList deck = new DevCardList();
        deck.setYearOfPlenty(2);
        deck.setMonopoly(2);
        deck.setSoldier(14);
        deck.setRoadBuilding(2);
        deck.setMonument(5);
        
        //Make tradeOffer
        ResourceList resourceList = new ResourceList(1, -1, 0, 0, 0);
        TradeOffer toyTradeOffer = new TradeOffer(1, 2, resourceList);
        
        //Make turnTracker
        TurnTracker toyTurnTracker = new TurnTracker();
        toyTurnTracker.setStatus(TurnStatus.ROLLING);
        toyTurnTracker.setCurrentTurn(0);
        toyTurnTracker.setLongestRoad(-1);
        toyTurnTracker.setLargestArmy(-1);
        
        testModel.setBank(bank);
        testModel.setChat(toyChat);
        testModel.setLog(toyLog);
        testModel.setMap(null);
        testModel.setDeck(deck);
        testModel.setTradeOffer(toyTradeOffer);
        testModel.setTurnTracker(new TurnTracker());
        testModel.setVersion(0);
        testModel.setWinner(-1);
        
        return testModel;
        
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
