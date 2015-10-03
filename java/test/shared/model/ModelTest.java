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
import shared.exceptions.InsufficientSupplies;
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

	// private Map<Integer, HexLocation> numberToHexMap = new Map<Integer,
	// HexLocation>();

	public static Model testModel() {
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

		ArrayList<Player> players = new ArrayList<Player>(Arrays.asList(playerOne, playerTwo, playerThree, playerFour));

		Hex a = new Hex(new HexLocation(0, -2), null, 0);

		Hex b = new Hex(new HexLocation(1, -2), ResourceType.BRICK, 4);

		Hex c = new Hex(new HexLocation(2, -2), ResourceType.WOOD, 11);

		Hex d = new Hex(new HexLocation(-1, -1), ResourceType.BRICK, 8);

		Hex e = new Hex(new HexLocation(0, -1), ResourceType.WOOD, 3);

		Hex f = new Hex(new HexLocation(1, -1), ResourceType.ORE, 9);

		Hex g = new Hex(new HexLocation(2, -1), ResourceType.SHEEP, 12);

		Hex h = new Hex(new HexLocation(-2, 0), ResourceType.ORE, 5);

		Hex i = new Hex(new HexLocation(-1, 0), ResourceType.SHEEP, 10);

		Hex j = new Hex(new HexLocation(0, 0), ResourceType.WHEAT, 11);

		Hex k = new Hex(new HexLocation(1, 0), ResourceType.BRICK, 5);

		Hex l = new Hex(new HexLocation(2, 0), ResourceType.WHEAT, 6);

		Hex m = new Hex(new HexLocation(-2, 1), ResourceType.WHEAT, 2);

		Hex r = new Hex(new HexLocation(-1, 1), ResourceType.SHEEP, 9);

		Hex n = new Hex(new HexLocation(0, 1), ResourceType.WOOD, 4);

		Hex s = new Hex(new HexLocation(1, 1), ResourceType.SHEEP, 10);

		Hex o = new Hex(new HexLocation(-2, 2), ResourceType.WOOD, 6);

		Hex p = new Hex(new HexLocation(-1, 2), ResourceType.ORE, 3);

		Hex q = new Hex(new HexLocation(0, 2), ResourceType.WHEAT, 8);

		Road roadOne = new Road(1, new EdgeLocation(new HexLocation(-1, -1), EdgeDirection.South));

		Road roadTwo = new Road(3, new EdgeLocation(new HexLocation(-1, 1), EdgeDirection.SouthWest));

		Road roadThree = new Road(3, new EdgeLocation(new HexLocation(2, -2), EdgeDirection.SouthWest));

		Road roadFour = new Road(2, new EdgeLocation(new HexLocation(1, -1), EdgeDirection.South));

		Road roadFive = new Road(0, new EdgeLocation(new HexLocation(0, 1), EdgeDirection.South));

		Road roadSix = new Road(2, new EdgeLocation(new HexLocation(0, 0), EdgeDirection.South));

		Road roadSeven = new Road(1, new EdgeLocation(new HexLocation(-2, 1), EdgeDirection.SouthWest));

		Road roadEight = new Road(0, new EdgeLocation(new HexLocation(2, 0), EdgeDirection.SouthWest));

		Port portOne = new Port(3, null, null, EdgeDirection.NorthWest, new HexLocation(2, 1));

		Port portTwo = new Port(2, "brick", ResourceType.BRICK, EdgeDirection.NorthEast, new HexLocation(-2, 3));

		Port portThree = new Port(3, null, null, EdgeDirection.SouthWest, new HexLocation(3, -3));

		Port portFour = new Port(3, null, null, EdgeDirection.North, new HexLocation(0, 3));

		Port portFive = new Port(2, "wood", ResourceType.WOOD, EdgeDirection.NorthEast, new HexLocation(-3, 2));

		Port portSix = new Port(3, null, null, EdgeDirection.SouthEast, new HexLocation(-3, 0));

		Port portSeven = new Port(2, "wheat", ResourceType.WHEAT, EdgeDirection.South, new HexLocation(-1, -2));

		Port portEight = new Port(2, "ore", ResourceType.ORE, EdgeDirection.South, new HexLocation(1, -3));

		Port portNine = new Port(2, "sheep", ResourceType.SHEEP, EdgeDirection.NorthWest, new HexLocation(3, -1));

		Settlement sOne = new Settlement(3, new VertexLocation(new HexLocation(-1, 1), VertexDirection.SouthWest),
				new ArrayList<Hex>());

		Settlement sTwo = new Settlement(3, new VertexLocation(new HexLocation(1, -2), VertexDirection.SouthEast),
				new ArrayList<Hex>());

		Settlement sThree = new Settlement(2, new VertexLocation(new HexLocation(0, 0), VertexDirection.SouthWest),
				new ArrayList<Hex>());

		Settlement sFour = new Settlement(2, new VertexLocation(new HexLocation(1, -1), VertexDirection.SouthWest),
				new ArrayList<Hex>());

		Settlement sFive = new Settlement(1, new VertexLocation(new HexLocation(-2, 1), VertexDirection.SouthWest),
				new ArrayList<Hex>());

		Settlement sSix = new Settlement(0, new VertexLocation(new HexLocation(0, 1), VertexDirection.SouthEast),
				new ArrayList<Hex>());

		Settlement sSeven = new Settlement(0, new VertexLocation(new HexLocation(2, 0), VertexDirection.SouthWest),
				new ArrayList<Hex>());

		City cOne = new City(1, new VertexLocation(new HexLocation(-1, -1), VertexDirection.SouthWest),
				new ArrayList<Hex>());

		Map toyMap = new Map();

		ArrayList<Hex> hexes = new ArrayList<Hex>(
				Arrays.asList(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s));

		ArrayList<Road> roads = new ArrayList<Road>(Arrays.asList(roadOne, roadTwo, roadThree, roadFour, roadFive,
				roadSix, roadSeven, roadEight));

		ArrayList<Port> ports = new ArrayList<Port>(Arrays.asList(portOne, portTwo, portThree, portFour, portFive,
				portSix, portSeven, portEight, portNine));

		ArrayList<Settlement> settlements = new ArrayList<Settlement>(Arrays.asList(sOne, sTwo, sThree, sFour, sFive,
				sSix, sSeven));

		ArrayList<City> cities = new ArrayList<City>(Arrays.asList(cOne));

		toyMap.setRadius(3);
		HexLocation robberLocation = new HexLocation(0, 2);
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

		// Make the test chat
		ArrayList<MessageLine> chatLines = new ArrayList<MessageLine>();
		MessageList toyChat = new MessageList(chatLines);

		// Make the test Log
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

		// Make new devcard list for Deck
		DevCardList deck = new DevCardList();
		deck.setYearOfPlenty(2);
		deck.setMonopoly(2);
		deck.setSoldier(14);
		deck.setRoadBuilding(2);
		deck.setMonument(5);

		// Make tradeOffer
		ResourceList resourceList = new ResourceList(1, -1, 0, 0, 0);
		TradeOffer toyTradeOffer = new TradeOffer(1, 2, resourceList);

		// Make turnTracker
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
	 * INVALID GETPLAYER TESTS
	 */

	/**
	 * Test of getPlayer method when index is > 3 or < 0 (out of bounds)
	 */
	@Test
	public void testGetPlayerWithInvalidIndex() {
		System.out.println("getPlayer");

		// Test getting player with invalid index
		// Should throw exception
		try {
			instance.getPlayer(-1);
			fail("Should've thrown a GetPlayerException");
		} catch (GetPlayerException e) {
			// exception thrown as expected
		}
		try {
			instance.getPlayer(4);
			fail("Should've thrown a GetPlayerException");
		} catch (GetPlayerException e) {
			// exception thrown as expected
		}
	}

	/**
	 * Test of getPlayer method when Model.players is null or empty
	 */
	@Test
	public void testGetPlayerFromEmptyList() {
		// Test getting player from empty list of players
		try {
			Model emptyModel = new Model();
			emptyModel.getPlayer(1);
			fail("Should've thrown a GetPlayerException");
		} catch (GetPlayerException e) {
			// exception thrown as expected
		}

		// Test getting player when list is null
		try {
			Model otherEmptyModel = new Model();
			otherEmptyModel.setPlayers(null);
			otherEmptyModel.getPlayer(1);
			fail("Should've thrown a GetPlayerException");
		} catch (GetPlayerException e) {
			// exception thrown as expected
		}
	}

	/**
	 * VALID GETPLAYER TESTS
	 */

	/**
	 * Test of getPlayer method where inputs are valid
	 */
	@Test
	public void testGetPlayerValid() {

		// Test getting player from valid list of players
		// duplicate player at index 0
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
	 * INVALID CANBUYROAD TESTS
	 */

	/**
	 * Test of canBuyRoad when the current player doesn't have enough brick
	 */
	@Test
	public void testCanBuyRoadInsufficientBrick() throws Exception {
		//Current player doesn't have supplies, should return false
		assertEquals(false, instance.canBuyRoad());
	}

	/**
	 * Test of canBuyRoad when the current player doesn't have enough roads
	 */
	@Test
	public void testCanBuyRoadInsufficientRoads() throws Exception {
		int currentPlayerIndex = instance.getTurnTracker().getCurrentTurn();
		// current player is first player
		assertEquals(currentPlayerIndex, 0);

		// Set current player's roads to 0 so they don't have any roads to place
		Player player = instance.getPlayer(currentPlayerIndex);
		player.setRoads(0);

		// validate the roads were set to 0
		assertEquals(0, player.getRoads());

		// Try to buy a road with zero roads - should return false
		assertEquals(false, instance.canBuyRoad());
	}

	/**
	 * VALID CANBUYROAD TESTS
	 */

	/**
	 * 
	 */
	@Test
	public void testCanBuyRoadValid() throws Exception {
		int currentPlayerIndex = instance.getTurnTracker().getCurrentTurn();
		// The current player in the testModel is the first player
		assertEquals(currentPlayerIndex, 0);

		// Set current player's bricks so that they can afford a road
		Player player = instance.getPlayer(currentPlayerIndex);
		player.getResources().addResource(ResourceType.BRICK, 1);

		// check if the current player has sufficient resources to buy a road
		assertEquals(player.getResources().getBrick(), 1);
		assertEquals(player.getResources().getWood(), 1);

		// this player has sufficient resources to buy a road so this should
		// pass
		assertEquals(true, instance.canBuyRoad());
	}

	/**
	 * INVALID CANBUILDROAD TESTS
	 */

	/**
	 * Test of canBuildRoad method when there's already a road there
	 */
	@Test
	public void testCanBuildRoadAtFilledEdge() throws Exception {
            
            // this is an edge location that already has a road so it should fail. 
            assertEquals(false,instance.canBuildRoad(new EdgeLocation(new HexLocation(1,-1),EdgeDirection.NorthEast)));
            // this is an edge location that already has a road so it should fail. 
            assertEquals(false,instance.canBuildRoad(new EdgeLocation(new HexLocation(0,2),EdgeDirection.North)));
            // Any other cases are covered in the other tests
	}

	/**
	 * Test of canBuildRoad method when there's no connecting road or
	 * settlement/city
	 */
	@Test
	public void testCanBuildRoadIsolated() throws Exception {
            // this is an edge location that has no adjacent pieces 3 on Wood
            assertEquals(false,instance.canBuildRoad(new EdgeLocation(new HexLocation(0,-1),EdgeDirection.NorthEast)));
            // this is an edge location that has no adjacent pieces 3 on Wood
            assertEquals(false,instance.canBuildRoad(new EdgeLocation(new HexLocation(0,-1),EdgeDirection.North)));
            // this is an edge location that has no adjacent pieces 3 on Wood
            assertEquals(false,instance.canBuildRoad(new EdgeLocation(new HexLocation(1,-1),EdgeDirection.NorthWest)));
            
	}

	/**
	 * Test of canBuildRoad method when road connects to a settlement/city or
	 * road, but the settlement/city/road doesn't belong to current player
	 */
	@Test
	public void testCanBuildRoadNotNextToCurrentPlayer() throws Exception {
		
            // this is a nothern edge location that has adjacent pieces but none of them
            // belong to the current player. 6 on Wood
            assertEquals(false,instance.canBuildRoad(new EdgeLocation(new HexLocation(-2,-2),EdgeDirection.North)));
            
            // this is a nothern edge location that has adjacent pieces but none of them
            // belong to the current player. 9 on sheep
            assertEquals(false,instance.canBuildRoad(new EdgeLocation(new HexLocation(-1,1),EdgeDirection.NorthEast)));
          
            // this is a nothern edge location that has adjacent pieces but none of them
            // belong to the current player. 2 on Wheat
            assertEquals(false,instance.canBuildRoad(new EdgeLocation(new HexLocation(-2,1),EdgeDirection.NorthWest)));

	}

	/**
	 * Test of canBuildRoad method where the edge is in between two ocean hexes
	 */
	@Test
	public void testCanBuildRoadInOcean() throws Exception {
            
            // this edge is an invalid ocean edge
            assertEquals(false, new EdgeLocation(new HexLocation(0,-3), EdgeDirection.NorthWest));
	}

	/**
	 * VALID CANBUILDROAD TESTS
	 */

	/**
	 * Test of canBuildRoad connecting to an existing road of the current player
	 */
	@Test
	public void testCanBuildRoadNearRoad() throws Exception {
        // this is a valid edgelocation for the player sam whose turn it is. 
        assertEquals (true, instance.canBuildRoad(new EdgeLocation(new HexLocation(2,0),EdgeDirection.NorthWest)));
	
        // this is a valid edgelocation for the player sam whose turn it is. 
        assertEquals (true, instance.canBuildRoad(new EdgeLocation(new HexLocation(0,2),EdgeDirection.NorthEast)));
        
        // this is a valid edgelocation for the player sam whose turn it is. 
        assertEquals (true, instance.canBuildRoad(new EdgeLocation(new HexLocation(1,1),EdgeDirection.North)));
        
        }
	
	/**
	 * Test of canBuildRoad connecting to an existing settlement of current player
     * This is for when the game is starting and players are placing their first roads
	 */
	@Test
	public void testCanBuildRoadNearSettlement() throws Exception {
            fail("stub");
	}

	/**
	 * Test of canBuildRoad connecting to an existing city of current player
         * This is for when the game is starting and players are placing their first roads
	 */
	@Test
	public void testCanBuildRoadNearCity() throws Exception {
            fail("stub");
	}
	
	/**
	 * INVALID CANBUYSETTLEMENT TESTS
	 */
	
	/**
	 * Test of canBuySettlement when there aren't enough resources
	 */
	@Test
	public void testCanBuySettlementInsufficientResources() throws Exception {
		try {
			instance.canBuySettlement();
			fail("Didn't throw an exception, but should've");
		} catch (InsufficientSupplies e) {
			// This player did not have these supplies.
		}
	}
	
	/**
	 * Test of canBuySettlement when player doesn't have settlements left
	 */
	@Test
	public void testCanBuySettlementNoSettlements() throws Exception {
		//Get the current player and add brick so they have enough to buy settlement
		int currentPlayerIndex = instance.getTurnTracker().getCurrentTurn();
		assertEquals(currentPlayerIndex, 0);
		Player player = instance.getPlayer(currentPlayerIndex);
		player.getResources().addResource(ResourceType.BRICK, 1);
		
		//Check if the current player has sufficient resources
		assertEquals(player.getResources().getBrick(), 1);
		assertEquals(player.getResources().getWood(), 1);
		assertEquals(player.getResources().getWheat(), 1);
		assertEquals(player.getResources().getSheep(), 1);
		
		//This player has sufficient resources to buy a settlement,
		//so this should pass
		try {
			instance.canBuySettlement();
		} catch (InsufficientSupplies e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCanBuySettlement() throws Exception {

		int currentPlayer = instance.getTurnTracker().getCurrentTurn();
		// the current player in the testModel is the first player
		assertEquals(currentPlayer, 0);
		// add a brick to the current players resource list so they have enough
		// to buy
		// a settlement.
		Player player = instance.getPlayer(currentPlayer);
		// player.getResources().addResource(ResourceType.BRICK,1);

		// check if the current player has sufficient resources to buy a road
		assertEquals(player.getResources().getBrick(), 1);
		assertEquals(player.getResources().getWood(), 1);
		assertEquals(player.getResources().getWheat(), 1);
		assertEquals(player.getResources().getSheep(), 1);
		// this player has sufficient resources to buy a settlement so this
		// should pass
		try {
			instance.canBuySettlement();
		} catch (InsufficientSupplies e) {
			fail(e.getMessage());
		}

		// remove all of the players settlements to test that they must have a
		// road in order
		// to buy a settlement.
		player.setSettlements(0);
		assertEquals(player.getSettlements(), 0);
		// Player has no roads so an exception should be thrown
		try {
			instance.canBuySettlement();
			fail("did not have settlement");
		} catch (InsufficientSupplies e) {

		}
	}

	/**
	 * Test of canBuildSettlement method, of class Model.
	 */
	@Test
	public void testCanBuildSettlement() throws Exception {
fail("stub");
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
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	@Test
	public void testCanBuyCity() throws Exception {
		Model model = ModelTest.testModel();
		// current player has 1 wheat and 0 ore. So model.canBuyCity() should
		// fail
		try {
			model.canBuyCity();
			fail("but it didn't throw an exception!");
		} catch (InsufficientSupplies e) {
			// This player did not have these supplies.
		}

		int currentPlayer = model.getTurnTracker().getCurrentTurn();
		// the current player in the testModel is the first player
		assertEquals(currentPlayer, 0);
		// add a wheat and 3 ore to the current players resource list so they
		// have enough to buy
		// a settlement.
		Player player = model.getPlayer(currentPlayer);
		// player.getResources().addResource(ResourceType.WHEAT,1);
		// player.getResources().addResource(ResourceType.ORE,3);

		// check if the current player has sufficient resources to buy a road
		assertEquals(player.getResources().getWheat(), 2);
		assertEquals(player.getResources().getOre(), 3);
		// this player has sufficient resources to buy a city so this should
		// pass
		try {
			model.canBuyCity();
		} catch (InsufficientSupplies e) {
			fail(e.getMessage());
		}

		// remove all of the players settlements to test that they must have a
		// road in order
		// to buy a road.
		player.setCities(0);
		assertEquals(player.getCities(), 0);
		// Player has no roads so an exception should be thrown
		try {
			model.canBuyCity();
			fail("did not have settlement");
		} catch (InsufficientSupplies e) {

		}
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
		// TODO review the generated test code and remove the default call to
		// fail.
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
		// TODO review the generated test code and remove the default call to
		// fail.
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
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	@Test
	public void canOfferResource() {
fail("stub");
	}

}
