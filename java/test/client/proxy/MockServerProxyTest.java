/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.proxy;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import shared.communication.params.CreateGameRequest;
import shared.communication.params.Credentials;
import shared.communication.params.JoinGameRequest;
import shared.communication.params.LoadGameRequest;
import shared.communication.params.PostCommandsRequest;
import shared.communication.params.SaveGameRequest;
import shared.communication.responses.CreateGameResponse;
import shared.communication.responses.GameResponse;

/**
 *
 * @author JanPaul
 */
public class MockServerProxyTest {
    
    public MockServerProxyTest() {
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
     * Test of listAi method, of class MockProxy.
     */
    @Test
    public void testListAi() throws Exception {
        System.out.println("listAi");
        MockProxy instance = new MockProxy();
        String expResult = "";
        String result = instance.listAi();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of postCommands method, of class MockProxy.
     */
    @Test
    public void testPostCommands() throws Exception {
        System.out.println("postCommands");
        PostCommandsRequest postCommandsRequest = null;
        MockProxy instance = new MockProxy();
        String expResult = "";
        String result = instance.postCommands(postCommandsRequest);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCommands method, of class MockProxy.
     */
    @Test
    public void testGetCommands() throws Exception {
        System.out.println("getCommands");
        MockProxy instance = new MockProxy();
        String expResult = "";
        String result = instance.getCommands();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAi method, of class MockProxy.
     */
    @Test
    public void testAddAi() throws Exception {
        System.out.println("addAi");
        MockProxy instance = new MockProxy();
        String expResult = "";
        String result = instance.addAi();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buildRoad method, of class MockProxy.
     */
    @Test
    public void testBuildRoad() throws Exception {
        System.out.println("buildRoad");
        MockProxy instance = new MockProxy();
        String expResult = "";
        String result = instance.buildRoad();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of offerTrade method, of class MockProxy.
     */
    @Test
    public void testOfferTrade() throws Exception {
        System.out.println("offerTrade");
        MockProxy instance = new MockProxy();
        instance.offerTrade();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of acceptTrade method, of class MockProxy.
     */
    @Test
    public void testAcceptTrade() throws Exception {
        System.out.println("acceptTrade");
        MockProxy instance = new MockProxy();
        instance.acceptTrade();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of maritimeTrade method, of class MockProxy.
     */
    @Test
    public void testMaritimeTrade() throws Exception {
        System.out.println("maritimeTrade");
        MockProxy instance = new MockProxy();
        instance.maritimeTrade();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buyDevCard method, of class MockProxy.
     */
    @Test
    public void testBuyDevCard() throws Exception {
        System.out.println("buyDevCard");
        MockProxy instance = new MockProxy();
        instance.buyDevCard();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of playYearOfPlenty method, of class MockProxy.
     */
    @Test
    public void testPlayYearOfPlenty() throws Exception {
        System.out.println("playYearOfPlenty");
        MockProxy instance = new MockProxy();
        instance.playYearOfPlenty();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of playRoadBuilding method, of class MockProxy.
     */
    @Test
    public void testPlayRoadBuilding() throws Exception {
        System.out.println("playRoadBuilding");
        MockProxy instance = new MockProxy();
        instance.playRoadBuilding();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of playSoldier method, of class MockProxy.
     */
    @Test
    public void testPlaySoldier() throws Exception {
        System.out.println("playSoldier");
        MockProxy instance = new MockProxy();
        instance.playSoldier();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of playMonopoly method, of class MockProxy.
     */
    @Test
    public void testPlayMonopoly() throws Exception {
        System.out.println("playMonopoly");
        MockProxy instance = new MockProxy();
        instance.playMonopoly();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buildSettlement method, of class MockProxy.
     */
    @Test
    public void testBuildSettlement() throws Exception {
        System.out.println("buildSettlement");
        MockProxy instance = new MockProxy();
        instance.buildSettlement();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buildCity method, of class MockProxy.
     */
    @Test
    public void testBuildCity() throws Exception {
        System.out.println("buildCity");
        MockProxy instance = new MockProxy();
        instance.buildCity();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendChat method, of class MockProxy.
     */
    @Test
    public void testSendChat() throws Exception {
        System.out.println("sendChat");
        MockProxy instance = new MockProxy();
        instance.sendChat();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rollNumber method, of class MockProxy.
     */
    @Test
    public void testRollNumber() throws Exception {
        System.out.println("rollNumber");
        MockProxy instance = new MockProxy();
        instance.rollNumber();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of robPlayer method, of class MockProxy.
     */
    @Test
    public void testRobPlayer() throws Exception {
        System.out.println("robPlayer");
        MockProxy instance = new MockProxy();
        instance.robPlayer();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of finishTurn method, of class MockProxy.
     */
    @Test
    public void testFinishTurn() throws Exception {
        System.out.println("finishTurn");
        MockProxy instance = new MockProxy();
        instance.finishTurn();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of login method, of class MockProxy.
     */
    @Test
    public void testLogin() throws Exception {
        System.out.println("login");
        Credentials userCredentials = new Credentials();
        userCredentials.setUsername("Sam");
        userCredentials.setPassword("sam");
        MockProxy instance = new MockProxy();
        boolean expResult = false;
        boolean result = instance.login(userCredentials);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of register method, of class MockProxy.
     */
    @Test
    public void testRegister() throws Exception {
        System.out.println("register");
        Credentials userCredentials = null;
        MockProxy instance = new MockProxy();
        boolean expResult = false;
        boolean result = instance.register(userCredentials);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGamesList method, of class MockProxy.
     */
    @Test
    public void testGetGamesList() throws Exception {
        System.out.println("getGamesList");
        MockProxy instance = new MockProxy();
        List<GameResponse> expResult = null;
        List<GameResponse> result = instance.getGamesList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createGame method, of class MockProxy.
     */
    @Test
    public void testCreateGame() throws Exception {
        System.out.println("createGame");
        CreateGameRequest gameRequests = null;
        MockProxy instance = new MockProxy();
        CreateGameResponse expResult = null;
        CreateGameResponse result = instance.createGame(gameRequests);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of joinGame method, of class MockProxy.
     */
    @Test
    public void testJoinGame() throws Exception {
        System.out.println("joinGame");
        JoinGameRequest joinRequest = null;
        MockProxy instance = new MockProxy();
        boolean expResult = false;
        boolean result = instance.joinGame(joinRequest);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveGame method, of class MockProxy.
     */
    @Test
    public void testSaveGame() throws Exception {
        System.out.println("saveGame");
        SaveGameRequest saveRequest = null;
        MockProxy instance = new MockProxy();
        boolean expResult = false;
        boolean result = instance.saveGame(saveRequest);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadGame method, of class MockProxy.
     */
    @Test
    public void testLoadGame() throws Exception {
        System.out.println("loadGame");
        LoadGameRequest loadRequest = null;
        MockProxy instance = new MockProxy();
        boolean expResult = false;
        boolean result = instance.loadGame(loadRequest);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGameModel method, of class MockProxy.
     */
    @Test
    public void testGetGameModel() throws Exception {
        System.out.println("getGameModel");
        int version = 0;
        MockProxy instance = new MockProxy();
        String expResult = "";
        String result = instance.getGameModel(version);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetGame method, of class MockProxy.
     */
    @Test
    public void testResetGame() throws Exception {
        System.out.println("resetGame");
        MockProxy instance = new MockProxy();
        String expResult = "";
        String result = instance.resetGame();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
