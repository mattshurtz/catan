/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model;

import client.facade.CatanFacade;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.definitions.PortType;
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
import shared.model.map.CatanMap;
import shared.model.map.Port;
import shared.model.map.Road;
import shared.model.map.Settlement;

/**
 *
 */
public class ModelTest {

	private Model instance = null;

	// private CatanMap<Integer, HexLocation> numberToHexMap = new CatanMap<Integer,
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

		Port portOne = new Port(PortType.THREE, EdgeDirection.NorthWest, new HexLocation(2, 1));

		Port portTwo = new Port(PortType.BRICK, EdgeDirection.NorthEast, new HexLocation(-2, 3));

		Port portThree = new Port(PortType.THREE, EdgeDirection.SouthWest, new HexLocation(3, -3));

		Port portFour = new Port(PortType.THREE, EdgeDirection.North, new HexLocation(0, 3));

		Port portFive = new Port(PortType.WOOD, EdgeDirection.NorthEast, new HexLocation(-3, 2));

		Port portSix = new Port(PortType.THREE, EdgeDirection.SouthEast, new HexLocation(-3, 0));

		Port portSeven = new Port(PortType.WHEAT, EdgeDirection.South, new HexLocation(-1, -2));

		Port portEight = new Port(PortType.ORE, EdgeDirection.South, new HexLocation(1, -3));

		Port portNine = new Port(PortType.SHEEP, EdgeDirection.NorthWest, new HexLocation(3, -1));

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

		CatanMap toyMap = new CatanMap();

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
		// Current player doesn't have supplies, should return false
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

//	/**
//	 * VALID CANBUYROAD TESTS
//	 */
//
//	/**
//	 * 
//	 */
//	@Test
//	public void testCanBuyRoadValid() throws Exception {
//		int currentPlayerIndex = instance.getTurnTracker().getCurrentTurn();
//		// The current player in the testModel is the first player
//		assertEquals(currentPlayerIndex, 0);
//
//		// Set current player's bricks so that they can afford a road
//		Player player = instance.getPlayer(currentPlayerIndex);
//		player.getResources().addResource(ResourceType.BRICK, 1);
//
//		// check if the current player has sufficient resources to buy a road
//		assertEquals(player.getResources().getBrick(), 1);
//		assertEquals(player.getResources().getWood(), 1);
//
//		// this player has sufficient resources to buy a road so this should
//		// pass
//		assertEquals(true, instance.canBuyRoad());
//	}

	/**
	 * INVALID CANBUILDROAD TESTS
	 */

	/**
	 * Test of canBuildRoad method when there's already a road there
	 */
	@Test
	public void testCanBuildRoadAtFilledEdge() throws Exception {

		// this is an edge location that already has a road so it should fail.
		assertEquals(false, instance.canBuildRoad(new EdgeLocation(new HexLocation(1, -1), EdgeDirection.NorthEast)));
		// this is an edge location that already has a road so it should fail.
		assertEquals(false, instance.canBuildRoad(new EdgeLocation(new HexLocation(0, 2), EdgeDirection.North)));
		// Any other cases are covered in the other tests
	}

	/**
	 * Test of canBuildRoad method when there's no connecting road or
	 * settlement/city
	 */
	@Test
	public void testCanBuildRoadIsolated() throws Exception {

		// this is an edge location that has no adjacent pieces 3 on Wood
		assertEquals(false, instance.canBuildRoad(new EdgeLocation(new HexLocation(0, -1), EdgeDirection.NorthEast)));
		// this is an edge location that has no adjacent pieces 3 on Wood
		assertEquals(false, instance.canBuildRoad(new EdgeLocation(new HexLocation(0, -1), EdgeDirection.North)));
		// this is an edge location that has no adjacent pieces 3 on Wood
		assertEquals(false, instance.canBuildRoad(new EdgeLocation(new HexLocation(1, -1), EdgeDirection.NorthWest)));

	}

	/**
	 * Test of canBuildRoad method when road connects to a settlement/city or
	 * road, but the settlement/city/road doesn't belong to current player
	 */
	@Test
	public void testCanBuildRoadNotNextToCurrentPlayer() throws Exception {

		// this is a nothern edge location that has adjacent pieces but none of
		// them
		// belong to the current player. 6 on Wood
		assertEquals(false, instance.canBuildRoad(new EdgeLocation(new HexLocation(-2, -2), EdgeDirection.North)));

		// this is a nothern edge location that has adjacent pieces but none of
		// them
		// belong to the current player. 9 on sheep
		assertEquals(false, instance.canBuildRoad(new EdgeLocation(new HexLocation(-1, 1), EdgeDirection.NorthEast)));

		// this is a nothern edge location that has adjacent pieces but none of
		// them
		// belong to the current player. 2 on Wheat
		assertEquals(false, instance.canBuildRoad(new EdgeLocation(new HexLocation(-2, 1), EdgeDirection.NorthWest)));

	}

	/**
	 * Test of canBuildRoad method where the edge is in between two ocean hexes
	 */
	@Test
	public void testCanBuildRoadInOcean() throws Exception {

		// this edge is an invalid ocean edge
		EdgeLocation oceanEdge = new EdgeLocation(new HexLocation(0, -3), EdgeDirection.NorthWest);
		assertFalse(instance.canBuildRoad(oceanEdge));
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
		assertEquals(true, instance.canBuildRoad(new EdgeLocation(new HexLocation(2, 0), EdgeDirection.NorthWest)));

		// this is a valid edgelocation for the player sam whose turn it is.
		assertEquals(true, instance.canBuildRoad(new EdgeLocation(new HexLocation(0, 2), EdgeDirection.NorthEast)));

		// this is a valid edgelocation for the player sam whose turn it is.
		assertEquals(true, instance.canBuildRoad(new EdgeLocation(new HexLocation(1, 1), EdgeDirection.North)));

	}

	/**
	 * Test of canBuildRoad connecting to an existing settlement of current
	 * player This is for when the game is starting and players are placing
	 * their first roads
	 */
	@Test
	public void testCanBuildRoadNearSettlement() throws Exception {
		// This removes all of the roads.
		instance.getMap().getRoads().clear();
		// Set the turn to the first turn
		instance.getTurnTracker().setStatus(TurnStatus.FIRST_ROUND);
		// Building a North Edge road near the yellow player 0 settlement at
		// hexLocation 0, 1
		assertEquals(true, instance.canBuildRoad(new EdgeLocation(new HexLocation(0, 2), EdgeDirection.North)));
		// Test Building a NorthEast Edge road
		assertEquals(true, instance.canBuildRoad(new EdgeLocation(new HexLocation(1, 1), EdgeDirection.NorthWest)));
		// Test building a NorthWest Edge road
		assertEquals(true, instance.canBuildRoad(new EdgeLocation(new HexLocation(1, 1), EdgeDirection.NorthEast)));

	}

	/**
	 * INVALID CANBUYSETTLEMENT TESTS
	 */

	/**
	 * Test of canBuySettlement when there aren't enough resources
	 */
	@Test
	public void testCanBuySettlementInsufficientResources() throws Exception {
		// Current player 0 doesn't have enough resources to buy a settlement
		assertEquals(false, instance.canBuySettlement());
	}

	/**
	 * Test of canBuySettlement when player doesn't have settlements left
	 */
	@Test
	public void testCanBuySettlementNoSettlements() throws Exception {
		// Get the current player and add brick so they have enough to buy
		// settlement
		int currentPlayerIndex = instance.getTurnTracker().getCurrentTurn();
		assertEquals(currentPlayerIndex, 0);
		Player player = instance.getPlayer(currentPlayerIndex);
		player.getResources().addResource(ResourceType.BRICK, 1);

		// Check if the current player has sufficient resources
		assertEquals(player.getResources().getBrick(), 1);
		assertEquals(player.getResources().getWood(), 1);
		assertEquals(player.getResources().getWheat(), 1);
		assertEquals(player.getResources().getSheep(), 1);

		// This player has enough resources, so now remove settlements
		player.setSettlements(0);
		assertEquals(player.getSettlements(), 0);

		// Test buying a settlement without any settlements, should fail
		assertEquals(instance.canBuySettlement(), false);
	}

	/**
	 * VALID CANBUYSETTLEMENT TEST
	 */

	/**
	 * Test of canBuySettlement when player has enough resources and settlements
	 */
	@Test
	public void testCanBuySettlementValid() throws Exception {
		// Get the current player
		int currentPlayerIndex = instance.getTurnTracker().getCurrentTurn();
		assertEquals(currentPlayerIndex, 0);
		Player player = instance.getPlayer(currentPlayerIndex);

		// Add resources to the player so that they can afford a settlement
		player.getResources().addResource(ResourceType.BRICK, 1);

		// check if the current player has sufficient resources to buy a road
		assertEquals(player.getResources().getBrick(), 1);
		assertEquals(player.getResources().getWood(), 1);
		assertEquals(player.getResources().getWheat(), 1);
		assertEquals(player.getResources().getSheep(), 1);

		// Check if the player has at least one settlement to place
		assertTrue(player.getSettlements() > 0);

		// Buy the settlement - should return true
		assertEquals(instance.canBuySettlement(), true);
	}

	/**
	 * INVALID CANBUILDSETTLEMENT TESTS
	 */

	/**
	 * Test of canBuildSettlement on a vertex with an existing settlement
	 */
	@Test
	public void testCanBuildSettlementOccupiedVertex() throws Exception {
		// Build on a hex that is occupied by this player's vertexObject
		VertexLocation vertexLoc = new VertexLocation(new HexLocation(0, 1), VertexDirection.SouthEast);
		assertEquals(false, instance.canBuildSettlement(vertexLoc));

		// Attempt to build on a hex that is occupied by another player's
		// vertexObject
		VertexLocation vertexLoc2 = new VertexLocation(new HexLocation(0, 1), VertexDirection.NorthWest);
		assertEquals(false, instance.canBuildSettlement(vertexLoc2));
	}

	/**
	 * Test of canBuildSettlement in isolated area when it's NOT the first round
	 */
	@Test
	public void testCanBuildSettlementIsolated() throws Exception {
		// Current state of the game is 'ROLLING', so this should fail
		assertFalse(instance.canBuildSettlement(new VertexLocation(new HexLocation(0, 2), VertexDirection.East)));
	}

	/**
	 * Test of canBuildSettlement when too near another settlement
	 */
	@Test
	public void testCanBuildSettlementNearOtherSettlement() throws Exception {
		// Test building settlement within one road of the other settlement
		assertFalse(instance.canBuildSettlement(new VertexLocation(new HexLocation(0, 2), VertexDirection.NorthWest)));
	}

	/**
	 * Test of canBuildSettlement when attached to road that's not player's own
	 */
	@Test
	public void testCanBuildSettlementOnOpponentRoad() throws Exception {
		// Reset roads so that there are two roads coming out of a player 0
		// settlement
		// Settlements remain the same
		Road roadOne = new Road(2, new EdgeLocation(new HexLocation(1, 0), EdgeDirection.North));
		Road roadTwo = new Road(2, new EdgeLocation(new HexLocation(1, 0), EdgeDirection.NorthEast));
		ArrayList<Road> roads = new ArrayList<Road>(Arrays.asList(roadOne, roadTwo));

		// Set instance roads to newly created roads
		instance.getMap().setRoads(roads);

		// Now there are only two red roads, but there are still other-colored
		// settlements
		// on nearby vertices

		// Try to build a yellow settlement attached to a red road (it's at
		// least 2
		// edges away from all other settlements) - should fail
		assertFalse(instance.canBuildSettlement(new VertexLocation(new HexLocation(1, 0), VertexDirection.East)));
	}

	/**
	 * VALID CANBUILDSETTLEMENT TESTS
	 */

	/**
	 * Test of canBuildSettlement when there aren't any nearby roads, during
	 * FIRST_ROUND
	 */
	@Test
	public void testCanBuildSettlementValidFirstRound() throws Exception {
		// Set model to be on the FIRST_ROUND
		instance.getTurnTracker().setStatus(TurnStatus.FIRST_ROUND);

		// Try to build a settlement in an isolated space
		VertexLocation vertexLoc = new VertexLocation(new HexLocation(0, -1), VertexDirection.NorthEast);
		assertTrue(instance.canBuildSettlement(vertexLoc));
	}

	/**
	 * Test of canBuildSettlement when connecting to a road, not during
	 * FIRST_ROUND
	 */
	@Test
	public void testCanBuildSettlementValid() throws Exception {
		// Reset yellow roads so that there are two roads between the existing
		// yellow settlement and the target vertex Location
		Road roadOne = new Road(0, new EdgeLocation(new HexLocation(0, 2), EdgeDirection.North));
		Road roadTwo = new Road(0, new EdgeLocation(new HexLocation(0, 2), EdgeDirection.NorthWest));

		// Set the roads within the map of the Model
		instance.getMap().setRoads(new ArrayList<Road>(Arrays.asList(roadOne, roadTwo)));

		// Test if you can build a settlement on the edge of the second road -
		// should return true
		VertexLocation vertexLoc = new VertexLocation(new HexLocation(0, 2), VertexDirection.West);
		assertTrue(instance.canBuildSettlement(vertexLoc));
	}
	
	/**
	 * INVALID CANBUYCITY TESTS
	 */
	
	/**
	 * Test of canBuyCity when player doesn't have resources
	 */
	@Test
	public void testCanBuyCityInsufficientResources() throws Exception {
		//Current player has 1 wheat and 0 ore, so this should return false
		assertFalse(instance.canBuyCity());
	}
	
	/**
	 * Test of canBuyCity where player has resources, but not enough cities
	 */
	@Test
	public void testCanBuyCityInsufficientCities() throws Exception {
		int currentPlayerIndex = instance.getTurnTracker().getCurrentTurn();
		// current player is the first player
		assertEquals(currentPlayerIndex, 0);
		
		//Add resources so they have enough to afford buying a city
		Player player = instance.getPlayer(currentPlayerIndex);
		player.getResources().addResource(ResourceType.WHEAT, 1);
		player.getResources().addResource(ResourceType.ORE, 3);
		
		//Check to make sure player has sufficient resources to buy city
		assertEquals(player.getResources().getWheat(), 2);
		assertEquals(player.getResources().getOre(), 3);
		
		//This player has sufficient resources to buy a city, so now
		//we remove the cities
		
		player.setCities(0);
		assertEquals(player.getCities(), 0);
		
		//Player has no cities, so canBuyCity should return false
		assertFalse(instance.canBuyCity());
	}
	
	/**
	 * VALID CANBUYCITY TESTS
	 */
	
	/**
	 * Test of canBuyCity when player has enough resources and cities
	 */
	@Test
	public void canBuyCityTestValid() throws Exception {
		int currentPlayer = instance.getTurnTracker().getCurrentTurn();
		assertEquals(currentPlayer, 0);
		//add wheat and 3 ore so they can afford a settlement
		Player player = instance.getPlayer(currentPlayer);
		
		player.getResources().addResource(ResourceType.WHEAT, 1);
		player.getResources().addResource(ResourceType.ORE, 3);
		
		//make sure player has enough to buy city
		assertEquals(player.getResources().getWheat(), 2);
		assertEquals(player.getResources().getOre(), 3);
		
		//make sure player has a city to place
		assertTrue(player.getCities() > 0);
		
		assertTrue(instance.canBuyCity());
	}

	/**
	 * Test of canBuildCity method, of class Model.
	 */
	@Test
	public void testCanBuildCity() throws Exception {
		// tests a location of a settlement of the player whose turn it is
		// this is a valid location for a city. it is Sam's turn.
		VertexLocation cityValidLocation = new VertexLocation(new HexLocation(0, 2), VertexDirection.NorthEast);
		assertEquals(instance.canBuildCity(cityValidLocation), true);

		// tests a location of a settlemnt of a player who is not the
		// player whose turn it is
		VertexLocation cityInvalidLocation = new VertexLocation(new HexLocation(1, -1), VertexDirection.NorthEast);
		assertEquals(instance.canBuildCity(cityInvalidLocation), false);

	}

	@Test
	public void testCanRobPlayer() {
		Model model = ModelTest.testModel();

		HexLocation robberLocation = new HexLocation(1, -1);

		boolean brookeFalse = model.canRobPlayer(1, robberLocation, null);
		assertFalse(brookeFalse);

		boolean peteTrue = model.canRobPlayer(2, robberLocation, null);
		assertTrue(peteTrue);

		boolean markTrue = model.canRobPlayer(3, robberLocation, null);
		assertTrue(markTrue);

		robberLocation = new HexLocation(-1, 1);

		brookeFalse = model.canRobPlayer(1, robberLocation, null);
		assertFalse(brookeFalse);

		peteTrue = model.canRobPlayer(2, robberLocation, null);
		assertTrue(peteTrue);

		markTrue = model.canRobPlayer(3, robberLocation, null);
		assertTrue(markTrue);
	}

	/**
	 * Tests whether the current play has sufficient resources to offer for a
	 * trade.
	 */
	@Test
	public void canOfferResource() throws GetPlayerException {

            // These are testing whether the current player sam is able to make the following trades
            // this player has 1 wood in hand
            assertEquals(instance.getPlayer(instance.getTurnTracker().getCurrentTurn()).getResources().getWood(),1);
            assertEquals(instance.canOfferResource(ResourceType.WOOD, 1),true);
            // this player has 1 wheat
            assertEquals(instance.getPlayer(instance.getTurnTracker().getCurrentTurn()).getResources().getWheat(),1);
            assertEquals(instance.canOfferResource(ResourceType.WHEAT, 1),true);
            // this player has 1 ore
            assertEquals(instance.getPlayer(instance.getTurnTracker().getCurrentTurn()).getResources().getOre(),0);
            assertEquals(instance.canOfferResource(ResourceType.ORE, 0),true);
            // this player has 1 sheep
            assertEquals(instance.getPlayer(instance.getTurnTracker().getCurrentTurn()).getResources().getSheep(),1);
            assertEquals(instance.canOfferResource(ResourceType.SHEEP, 3),false);
            // this player has 1 brick
            assertEquals(instance.getPlayer(instance.getTurnTracker().getCurrentTurn()).getResources().getBrick(),0);
            assertEquals(instance.canOfferResource(ResourceType.BRICK, 4),false);
	}
        
        /**
         * Tests whether this player has enough resources
         */
        @Test
        public void canAcceptTrade(){
           CatanFacade.setMyPlayerIndex(0);
           ResourceList tradeOffer = new ResourceList();
           tradeOffer.setBrick(0);
           tradeOffer.setOre(0);
           tradeOffer.setSheep(1);
           tradeOffer.setWheat(1);
           tradeOffer.setWood(1);
           // This tests whether the player can accept the trade resouce
           // in the case that he can
           assertEquals(instance.canAcceptTrade(tradeOffer, 0),true);
           
           tradeOffer.setBrick(4);
           // This tests whether the player can accept the trade resouce
           // in the case that he can not because he has insufficient brick
           assertEquals(instance.canAcceptTrade(tradeOffer, 0),false);
        }
        /**
         * This tests whether or canPlayDevCard works. IF a player owns the devcard
         * and has not already played one this turn they should be able to play 
         * the devcard. 
         */
        @Test
        public void canPlayDevCard(){
            
            Player currentPlayer = instance.getPlayers().get(0);
            // this should be false becasue the 0 index player, sam does not have
            // a soldier card.
            assertFalse(currentPlayer.canPlayDevCard(DevCardType.SOLDIER));
            
            // now we add a DevCard and see if they player can play
            // this should be true because we added a soldier
            currentPlayer.getOldDevCards().AddSoldier();
            assertTrue(currentPlayer.canPlayDevCard(DevCardType.SOLDIER));
            
            // Now we set that the player has played a dev card 
            // so they should not be able to play it even though they already have
            currentPlayer.setPlayedDevCard(true);
            currentPlayer.getOldDevCards().AddSoldier();
            assertFalse(currentPlayer.canPlayDevCard(DevCardType.SOLDIER));
            
        }
        
        
        /**
         * Tests whether a player has sufficient resources to buy a dev card. 
         */
        @Test
        public void canBuyDevCard(){
            // sam, the current player at index 0, has 1 sheep and one wheat but
            // has no ore so this test should fail
            assertFalse(instance.canBuyDevCard(0));
            // it is not player 1 turn so this should fail
            assertFalse(instance.canBuyDevCard(1));
            
            //Now we add a wheat to the current players turn and they should be
            //able to buy a devcard
            instance.getPlayers().get(0).getResources().addResource(ResourceType.WHEAT, 1);
            instance.getPlayers().get(0).getResources().addResource(ResourceType.ORE, 1);
            instance.getPlayers().get(0).getResources().addResource(ResourceType.SHEEP, 1);
            assertEquals(instance.getPlayers().get(0).getResources().getOre(),1);
            assertEquals(instance.getPlayers().get(0).getResources().getSheep(),2);
            assertEquals(instance.getPlayers().get(0).getResources().getWheat(),2);
            assertTrue(instance.canBuyDevCard(0));
            
        
        }
        
        /**
         * Tests whether it is this players turn and if the turn status is rolling.
         * this is all in the model function canRollNumber() function
         */
        @Test
        public void canRollNumber(){
            //First we make sure that the turnStatus is rolling
            instance.getTurnTracker().setStatus(TurnStatus.ROLLING);
            assertEquals(instance.getTurnTracker().getStatus(),TurnStatus.ROLLING);
            //This tests whether a player whos turn it is not can roll
            // This should be false because 0 is the current player as shown
            assertEquals(instance.getTurnTracker().getCurrentTurn(),0);
            CatanFacade.setMyPlayerIndex(1);
            assertFalse(instance.canRollNumber(null));
            //Now we set the CatanFacade player to the player whose turn it is
            // this test will pass because the currentplayer is player 0
            CatanFacade.setMyPlayerIndex(0);
            assertEquals(CatanFacade.getMyPlayerIndex(),0);
            assertTrue(instance.canRollNumber(null));
            
            //Test that it fails if the status is not rolling
            instance.getTurnTracker().setStatus(TurnStatus.ROBBING);
            assertEquals(instance.getTurnTracker().getStatus(),TurnStatus.ROBBING);
            assertFalse(instance.canRollNumber(null));
            
        }
        
        /**
         * Tests if this player can finish his turn. If it is this players current
         * turn then they should be able to finish their turn. 
         */
        @Test
        public void canFinishTurn(){
            //This tests whether a player whos turn it IS NOT can finsih turn
            // This should be false because 0 is the current player as shown
            assertEquals(instance.getTurnTracker().getCurrentTurn(),0);
            CatanFacade.setMyPlayerIndex(1);
            assertFalse(instance.canFinishTurn());
            //This tests whether a player whos turn it IS can finish turn
            // This should be false because 0 is the current player as shown
            assertEquals(instance.getTurnTracker().getCurrentTurn(),0);
            CatanFacade.setMyPlayerIndex(0);
            assertTrue(instance.canFinishTurn());
            
            
        }


}
