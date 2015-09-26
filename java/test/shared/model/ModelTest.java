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
import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
import shared.definitions.TurnStatus;
import shared.exceptions.GetPlayerException;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.model.map.City;
import shared.model.map.Hex;
import shared.model.map.Map;
import shared.model.map.Port;
import shared.model.map.Road;
import shared.model.map.Settlement;

/**
 *
 */
public class ModelTest {
	
	private Model instance = null;
    
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
        
        
        Hex a = new Hex(new HexLocation(0,-2), null, 0);
        
        Hex b = new Hex(new HexLocation(1,-2), ResourceType.BRICK, 4);
        
        Hex c = new Hex(new HexLocation(2,-2), ResourceType.WOOD, 11);
        
        Hex d = new Hex(new HexLocation(-1,-1), ResourceType.BRICK, 8);
        
        Hex e = new Hex(new HexLocation(0,-1), ResourceType.WOOD, 3);
        
        Hex f = new Hex(new HexLocation(1,-1), ResourceType.ORE, 9);
        
        Hex g = new Hex(new HexLocation(2,-1), ResourceType.SHEEP, 12);
        
        Hex h = new Hex(new HexLocation(-2,0), ResourceType.ORE, 5);
        
        Hex i = new Hex(new HexLocation(-1,0), ResourceType.SHEEP, 10);
        
        Hex j = new Hex(new HexLocation(0,0), ResourceType.WHEAT, 11);
        
        Hex k = new Hex(new HexLocation(1,0), ResourceType.BRICK, 5);
        
        Hex l = new Hex(new HexLocation(2,0), ResourceType.WHEAT, 6);
        
        Hex m = new Hex(new HexLocation(-2,1), ResourceType.WHEAT, 2);
        
        Hex r = new Hex(new HexLocation(-1,1), ResourceType.SHEEP, 9);
        
        Hex n = new Hex(new HexLocation(0,1), ResourceType.WOOD, 4);
        
        Hex s = new Hex(new HexLocation(1,1), ResourceType.SHEEP, 10);
        
        Hex o = new Hex(new HexLocation(-2,2), ResourceType.WOOD, 6);

        Hex p = new Hex(new HexLocation(-1,2), ResourceType.ORE, 3);

        Hex q = new Hex(new HexLocation(0,2), ResourceType.WHEAT, 8);
        
        Road roadOne = new Road(1,new EdgeLocation(new HexLocation(-1,-1),EdgeDirection.South));
        
        Road roadTwo = new Road(3,new EdgeLocation(new HexLocation(-1,1),EdgeDirection.SouthWest));
        
        Road roadThree = new Road(3,new EdgeLocation(new HexLocation(2,-2),EdgeDirection.SouthWest));

        Road roadFour = new Road(2,new EdgeLocation(new HexLocation(1,-1),EdgeDirection.South));
        
        Road roadFive = new Road(0,new EdgeLocation(new HexLocation(0,1),EdgeDirection.South));
        
        Road roadSix = new Road(2,new EdgeLocation(new HexLocation(0,0),EdgeDirection.South));
        
        Road roadSeven = new Road(0,new EdgeLocation(new HexLocation(-2,1),EdgeDirection.SouthWest));

        Road roadEight = new Road(0,new EdgeLocation(new HexLocation(2,0),
                EdgeDirection.SouthWest));
        
        
        Port portOne = new Port(3,null, null,EdgeDirection.NorthWest, new HexLocation(2,1));
        
        Port portTwo = new Port(2,"brick",ResourceType.BRICK ,EdgeDirection.NorthEast, new HexLocation(-2,3));
        
        Port portThree = new Port(3,null, null,EdgeDirection.SouthWest, new HexLocation(3,-3));
        
        Port portFour = new Port(3,null, null,EdgeDirection.North, new HexLocation(0,3));
        
        Port portFive= new Port(2,"wood",ResourceType.WOOD,EdgeDirection.NorthEast, new HexLocation(-3,2));
        
        Port portSix = new Port(3,null, null,EdgeDirection.SouthEast, new HexLocation(-3,0));
        
        Port portSeven = new Port(2,"wheat", ResourceType.WHEAT,EdgeDirection.South, new HexLocation(-1,-2));
        
        Port portEight = new Port(2,"ore", ResourceType.ORE,EdgeDirection.South, new HexLocation(1,-3));
        
        Port portNine = new Port(2,"sheep", ResourceType.SHEEP,EdgeDirection.NorthWest, new HexLocation(3,-1));
        
        Settlement sOne = new Settlement(3, new VertexLocation(new HexLocation(-1,1)
                ,VertexDirection.SouthWest),new ArrayList<Hex>());
        
        Settlement sTwo = new Settlement(3, new VertexLocation(new HexLocation(1,-2)
                ,VertexDirection.SouthEast),new ArrayList<Hex>());
        
        Settlement sThree = new Settlement(2, new VertexLocation(new HexLocation(0,0)
                ,VertexDirection.SouthWest),new ArrayList<Hex>());        

        Settlement sFour = new Settlement(2, new VertexLocation(new HexLocation(1,-1)
                ,VertexDirection.SouthWest),new ArrayList<Hex>());  
        
        Settlement sFive = new Settlement(1, new VertexLocation(new HexLocation(-2,1)
                ,VertexDirection.SouthWest),new ArrayList<Hex>());

        Settlement sSix = new Settlement(0, new VertexLocation(new HexLocation(0,1)
                ,VertexDirection.SouthEast),new ArrayList<Hex>());        
        
        Settlement sSeven = new Settlement(0, new VertexLocation(new HexLocation(2,0)
                ,VertexDirection.SouthWest),new ArrayList<Hex>());
        
        City cOne = new City(1, new VertexLocation(new HexLocation(-1,-1)
                ,VertexDirection.SouthWest),new ArrayList<Hex>()); 
        
        Map toyMap = new Map();
        
        ArrayList<Hex> hexes = new ArrayList<Hex>(Arrays.asList(
        a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s));
        
        ArrayList<Road> roads = new ArrayList<Road>(Arrays.asList(
                roadOne, roadTwo, roadThree, roadFour,
                roadFive, roadSix, roadSeven, roadEight));
        
        ArrayList<Port> ports = new ArrayList<Port>(Arrays.asList(
                portOne, portTwo, portThree, portFour,
                portFive, portSix, portSeven, portEight, portNine));
        
        ArrayList<Settlement> settlements = new ArrayList<Settlement>(Arrays.asList(
                sOne, sTwo, sThree, sFour,
                sFive, sSix, sSeven));
        
        ArrayList<City> cities = new ArrayList<City>(Arrays.asList(cOne));
   
        
        
        
        toyMap.setRadius(3);
        HexLocation robberLocation = new HexLocation(0,2);
        toyMap.setRobber(robberLocation);
        toyMap.setCities(cities);
        toyMap.setHexes(hexes);
        toyMap.setPorts(ports);
        toyMap.setRoads(roads);
        toyMap.setSettlements(settlements);
        
        
        
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
        testModel.setMap(toyMap);
        testModel.setPlayers(players);
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
    	instance = testModel();
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
    public void testGetPlayerWithInvalidIndex() {
        System.out.println("getPlayer");
        
        //Test getting player with invalid index
        //Should throw exception
        try {
        	instance.getPlayer(-1);
        	fail("Should've thrown a GetPlayerException");
        } catch(GetPlayerException e) {
        	//exception thrown as expected
        }
        try {
        	instance.getPlayer(4);
        	fail("Should've thrown a GetPlayerException");
        } catch(GetPlayerException e) {
        	//exception thrown as expected
        }
    }
    /**
     * Test of getPlayer method when Model.players is null or empty
     */
    @Test
    public void testGetPlayerFromEmptyList() {
        //Test getting player from empty list of players
        try {
        	Model emptyModel = new Model();
        	emptyModel.getPlayer(1);
        	fail("Should've thrown a GetPlayerException");
        } catch(GetPlayerException e) {
        	//exception thrown as expected
        }
        
        //Test getting player when list is null
        try {
        	Model otherEmptyModel = new Model();
        	otherEmptyModel.setPlayers(null);
        	otherEmptyModel.getPlayer(1);
        	fail("Should've thrown a GetPlayerException");
        } catch(GetPlayerException e) {
        	//exception thrown as expected
        }
    }
    
    /**
     * Test of getPlayer method where inputs are valid
     */
    @Test
    public void testGetPlayerValid() {
        
        //Test getting player from valid list of players
        //duplicate player at index 0
        int playerIndex = 0;
        
        Player expResult = new Player();
        ResourceList pOneResourceList = new ResourceList();
        pOneResourceList.setBrick(0);
        pOneResourceList.setWood(1);
        pOneResourceList.setWheat(1);
        pOneResourceList.setSheep(1);
        pOneResourceList.setOre(0);
        expResult.setCities(4);
        expResult.setColor(CatanColor.YELLOW);
        expResult.setDiscarded(false);
        expResult.setMonuments(0);
        expResult.setName("Sam");
        expResult.setNewDevCards(new DevCardList());
        expResult.setOldDevCards(new DevCardList());
        expResult.setPlayedDevCard(false);
        expResult.setPlayerID(0);
        expResult.setRoads(13);
        expResult.setSettlements(3);
        expResult.setSoldiers(0);
        expResult.setVictoryPoints(2);
        expResult.setResources(pOneResourceList);
        expResult.setPlayerIndex(playerIndex);
        
        Player result = null;
        try {
			result = instance.getPlayer(playerIndex);
		} catch (GetPlayerException e) {
			fail("Exception thrown when it shouldn't have been");
		}
        
        assertEquals(expResult, result);
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
