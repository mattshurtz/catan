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
import static quicktime.util.QTBuild.version;
import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
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
        toyMap.setCities(null);
        toyMap.setHexes(hexes);
        toyMap.setPorts(null);
        toyMap.setRoads(roads);
        toyMap.setSettlements(null);
        
        
        
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
        testModel.setWinner(0);
        testModel.setPlayers(players);
        
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
