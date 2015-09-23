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
public class ServerProxyTest {
    
    public ServerProxyTest() {
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
     * Test of getGameModel method, of class ServerProxy.
     */
    @Test
    public void testGetGameModel() throws Exception {
        System.out.println("getGameModel");
        int version = 0;
        ServerProxy instance = new ServerProxy();
        String expResult = "";
        String result = instance.getGameModel(version);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listAi method, of class ServerProxy.
     */
    @Test
    public void testListAi() throws Exception {
        System.out.println("listAi");
        ServerProxy instance = new ServerProxy();
        String expResult = "";
        String result = instance.listAi();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of postCommands method, of class ServerProxy.
     */
    @Test
    public void testPostCommands() throws Exception {
        System.out.println("postCommands");
        PostCommandsRequest postCommandsRequest = null;
        ServerProxy instance = new ServerProxy();
        String expResult = "";
        String result = instance.postCommands(postCommandsRequest);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCommands method, of class ServerProxy.
     */
    @Test
    public void testGetCommands() throws Exception {
        System.out.println("getCommands");
        ServerProxy instance = new ServerProxy();
        String expResult = "";
        String result = instance.getCommands();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAi method, of class ServerProxy.
     */
    @Test
    public void testAddAi() throws Exception {
        System.out.println("addAi");
        ServerProxy instance = new ServerProxy();
        String expResult = "";
        String result = instance.addAi();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buildRoad method, of class ServerProxy.
     */
    @Test
    public void testBuildRoad() throws Exception {
        System.out.println("buildRoad");
        ServerProxy instance = new ServerProxy();
        String expResult = "";
        String result = instance.buildRoad();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of offerTrade method, of class ServerProxy.
     */
    @Test
    public void testOfferTrade() throws Exception {
        System.out.println("offerTrade");
        ServerProxy instance = new ServerProxy();
        instance.offerTrade();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of acceptTrade method, of class ServerProxy.
     */
    @Test
    public void testAcceptTrade() throws Exception {
        System.out.println("acceptTrade");
        ServerProxy instance = new ServerProxy();
        instance.acceptTrade();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of maritimeTrade method, of class ServerProxy.
     */
    @Test
    public void testMaritimeTrade() throws Exception {
        System.out.println("maritimeTrade");
        ServerProxy instance = new ServerProxy();
        instance.maritimeTrade();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buyDevCard method, of class ServerProxy.
     */
    @Test
    public void testBuyDevCard() throws Exception {
        System.out.println("buyDevCard");
        ServerProxy instance = new ServerProxy();
        instance.buyDevCard();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of playYearOfPlenty method, of class ServerProxy.
     */
    @Test
    public void testPlayYearOfPlenty() throws Exception {
        System.out.println("playYearOfPlenty");
        ServerProxy instance = new ServerProxy();
        instance.playYearOfPlenty();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of playRoadBuilding method, of class ServerProxy.
     */
    @Test
    public void testPlayRoadBuilding() throws Exception {
        System.out.println("playRoadBuilding");
        ServerProxy instance = new ServerProxy();
        instance.playRoadBuilding();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of playSoldier method, of class ServerProxy.
     */
    @Test
    public void testPlaySoldier() throws Exception {
        System.out.println("playSoldier");
        ServerProxy instance = new ServerProxy();
        instance.playSoldier();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of playMonopoly method, of class ServerProxy.
     */
    @Test
    public void testPlayMonopoly() throws Exception {
        System.out.println("playMonopoly");
        ServerProxy instance = new ServerProxy();
        instance.playMonopoly();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buildSettlement method, of class ServerProxy.
     */
    @Test
    public void testBuildSettlement() throws Exception {
        System.out.println("buildSettlement");
        ServerProxy instance = new ServerProxy();
        instance.buildSettlement();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buildCity method, of class ServerProxy.
     */
    @Test
    public void testBuildCity() throws Exception {
        System.out.println("buildCity");
        ServerProxy instance = new ServerProxy();
        instance.buildCity();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendChat method, of class ServerProxy.
     */
    @Test
    public void testSendChat() throws Exception {
        System.out.println("sendChat");
        ServerProxy instance = new ServerProxy();
        instance.sendChat();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rollNumber method, of class ServerProxy.
     */
    @Test
    public void testRollNumber() throws Exception {
        System.out.println("rollNumber");
        ServerProxy instance = new ServerProxy();
        instance.rollNumber();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of robPlayer method, of class ServerProxy.
     */
    @Test
    public void testRobPlayer() throws Exception {
        System.out.println("robPlayer");
        ServerProxy instance = new ServerProxy();
        instance.robPlayer();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of finishTurn method, of class ServerProxy.
     */
    @Test
    public void testFinishTurn() throws Exception {
        System.out.println("finishTurn");
        ServerProxy instance = new ServerProxy();
        instance.finishTurn();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of login method, of class ServerProxy.
     */
    @Test
    public void testLogin() throws Exception {
        System.out.println("login");
        Credentials userCredentials = null;
        ServerProxy instance = new ServerProxy();
        boolean expResult = false;
        boolean result = instance.login(userCredentials);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of register method, of class ServerProxy.
     */
    @Test
    public void testRegister() throws Exception {
        System.out.println("register");
        Credentials userCredentials = null;
        ServerProxy instance = new ServerProxy();
        boolean expResult = false;
        boolean result = instance.register(userCredentials);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGamesList method, of class ServerProxy.
     */
    @Test
    public void testGetGamesList() throws Exception {
        System.out.println("getGamesList");
        ServerProxy instance = new ServerProxy();
        List<GameResponse> expResult = null;
        List<GameResponse> result = instance.getGamesList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createGame method, of class ServerProxy.
     */
    @Test
    public void testCreateGame() throws Exception {
        System.out.println("createGame");
        CreateGameRequest gameRequests = null;
        ServerProxy instance = new ServerProxy();
        CreateGameResponse expResult = null;
        CreateGameResponse result = instance.createGame(gameRequests);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of joinGame method, of class ServerProxy.
     */
    @Test
    public void testJoinGame() throws Exception {
        System.out.println("joinGame");
        JoinGameRequest joinRequest = null;
        ServerProxy instance = new ServerProxy();
        boolean expResult = false;
        boolean result = instance.joinGame(joinRequest);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveGame method, of class ServerProxy.
     */
    @Test
    public void testSaveGame() throws Exception {
        System.out.println("saveGame");
        SaveGameRequest saveRequest = null;
        ServerProxy instance = new ServerProxy();
        boolean expResult = false;
        boolean result = instance.saveGame(saveRequest);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadGame method, of class ServerProxy.
     */
    @Test
    public void testLoadGame() throws Exception {
        System.out.println("loadGame");
        LoadGameRequest loadRequest = null;
        ServerProxy instance = new ServerProxy();
        boolean expResult = false;
        boolean result = instance.loadGame(loadRequest);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetGame method, of class ServerProxy.
     */
    @Test
    public void testResetGame() throws Exception {
        System.out.println("resetGame");
        ServerProxy instance = new ServerProxy();
        String expResult = "";
        String result = instance.resetGame();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
