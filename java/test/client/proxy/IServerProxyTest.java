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
import shared.exceptions.ServerException;

/**
 *
 * @author JanPaul
 */
public class IServerProxyTest {
    
    IServerProxy instance = null;
    
    public IServerProxyTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new MockServerProxy();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of login method, of class IServerProxy.
     */
    @Test
    public void testLogin() throws Exception {
        System.out.println("login");
        
        // first, login without credentials -- should fail
        Credentials userCredentials = new Credentials();
        boolean expResult = false;
        boolean result = instance.login(userCredentials);
        assertEquals(expResult, result);
        
        // Now login for real
        userCredentials.setUsername("Sam");
        userCredentials.setPassword("sam");
        expResult = true;
        result = instance.login(userCredentials);
        assertEquals(expResult, result);
    }

    /**
     * Test of register method, of class IServerProxy.
     */
    @Test
    public void testRegister() throws Exception {
        System.out.println("register");
        Credentials userCredentials = null;
        
        boolean expResult = false;
        boolean result = instance.register(userCredentials);
        assertEquals(expResult, result);
        
        userCredentials = new Credentials();
        result = instance.register(userCredentials);
        assertEquals( expResult, result );
        
        // valid
        userCredentials.setUsername("abc");
        userCredentials.setPassword("abcde");
        expResult = true;
        result = instance.register(userCredentials);
        assertEquals( expResult, result );
        
        // invalid username
        userCredentials.setUsername("ab");
        userCredentials.setPassword("abcde");
        expResult = false;
        result = instance.register(userCredentials);
        assertEquals( expResult, result );
        
        // invalid password
        userCredentials.setUsername("jan1");
        userCredentials.setPassword("alkjc()*&#");
        expResult = false;
        result = instance.register(userCredentials);
        assertEquals( expResult, result );
        
        // valid
        userCredentials.setUsername("Sammers");
        userCredentials.setPassword("sammers");
        expResult = true;
        result = instance.register(userCredentials);
        assertEquals( expResult, result );
        
        // Uncomment this when using the real server
//        // Now try to log in with the user we just registered
//        expResult = true;
//        result = instance.login( userCredentials );
//        assertEquals( expResult, result );
    }

    /**
     * Test of getGamesList method, of class IServerProxy.
     */
    @Test
    public void testGetGamesList() throws Exception {
        System.out.println("getGamesList");
        IServerProxy instance = new IServerProxyImpl();
        List<GameResponse> expResult = null;
        List<GameResponse> result = instance.getGamesList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createGame method, of class IServerProxy.
     */
    @Test
    public void testCreateGame() throws Exception {
        System.out.println("createGame");
        CreateGameRequest gameRequests = null;
        IServerProxy instance = new IServerProxyImpl();
        CreateGameResponse expResult = null;
        CreateGameResponse result = instance.createGame(gameRequests);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of joinGame method, of class IServerProxy.
     */
    @Test
    public void testJoinGame() throws Exception {
        System.out.println("joinGame");
        JoinGameRequest joinRequest = null;
        IServerProxy instance = new IServerProxyImpl();
        boolean expResult = false;
        boolean result = instance.joinGame(joinRequest);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveGame method, of class IServerProxy.
     */
    @Test
    public void testSaveGame() throws Exception {
        System.out.println("saveGame");
        SaveGameRequest saveRequest = null;
        IServerProxy instance = new IServerProxyImpl();
        boolean expResult = false;
        boolean result = instance.saveGame(saveRequest);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadGame method, of class IServerProxy.
     */
    @Test
    public void testLoadGame() throws Exception {
        System.out.println("loadGame");
        LoadGameRequest loadRequest = null;
        IServerProxy instance = new IServerProxyImpl();
        boolean expResult = false;
        boolean result = instance.loadGame(loadRequest);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGameModel method, of class IServerProxy.
     */
    @Test
    public void testGetGameModel() throws Exception {
        System.out.println("getGameModel");
        int version = 0;
        IServerProxy instance = new IServerProxyImpl();
        String expResult = "";
        String result = instance.getGameModel(version);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetGame method, of class IServerProxy.
     */
    @Test
    public void testResetGame() throws Exception {
        System.out.println("resetGame");
        IServerProxy instance = new IServerProxyImpl();
        String expResult = "";
        String result = instance.resetGame();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of postCommands method, of class IServerProxy.
     */
    @Test
    public void testPostCommands() throws Exception {
        System.out.println("postCommands");
        PostCommandsRequest postCommandsRequest = null;
        IServerProxy instance = new IServerProxyImpl();
        String expResult = "";
        String result = instance.postCommands(postCommandsRequest);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCommands method, of class IServerProxy.
     */
    @Test
    public void testGetCommands() throws Exception {
        System.out.println("getCommands");
        IServerProxy instance = new IServerProxyImpl();
        String expResult = "";
        String result = instance.getCommands();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAi method, of class IServerProxy.
     */
    @Test
    public void testAddAi() throws Exception {
        System.out.println("addAi");
        IServerProxy instance = new IServerProxyImpl();
        String expResult = "";
        String result = instance.addAi();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listAi method, of class IServerProxy.
     */
    @Test
    public void testListAi() throws Exception {
        System.out.println("listAi");
        IServerProxy instance = new IServerProxyImpl();
        String expResult = "";
        String result = instance.listAi();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buildRoad method, of class IServerProxy.
     */
    @Test
    public void testBuildRoad() throws Exception {
        System.out.println("buildRoad");
        IServerProxy instance = new IServerProxyImpl();
        String expResult = "";
        String result = instance.buildRoad();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of offerTrade method, of class IServerProxy.
     */
    @Test
    public void testOfferTrade() throws Exception {
        System.out.println("offerTrade");
        IServerProxy instance = new IServerProxyImpl();
        instance.offerTrade();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of acceptTrade method, of class IServerProxy.
     */
    @Test
    public void testAcceptTrade() throws Exception {
        System.out.println("acceptTrade");
        IServerProxy instance = new IServerProxyImpl();
        instance.acceptTrade();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of maritimeTrade method, of class IServerProxy.
     */
    @Test
    public void testMaritimeTrade() throws Exception {
        System.out.println("maritimeTrade");
        IServerProxy instance = new IServerProxyImpl();
        instance.maritimeTrade();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buyDevCard method, of class IServerProxy.
     */
    @Test
    public void testBuyDevCard() throws Exception {
        System.out.println("buyDevCard");
        IServerProxy instance = new IServerProxyImpl();
        instance.buyDevCard();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of playYearOfPlenty method, of class IServerProxy.
     */
    @Test
    public void testPlayYearOfPlenty() throws Exception {
        System.out.println("playYearOfPlenty");
        IServerProxy instance = new IServerProxyImpl();
        instance.playYearOfPlenty();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of playRoadBuilding method, of class IServerProxy.
     */
    @Test
    public void testPlayRoadBuilding() throws Exception {
        System.out.println("playRoadBuilding");
        IServerProxy instance = new IServerProxyImpl();
        instance.playRoadBuilding();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of playSoldier method, of class IServerProxy.
     */
    @Test
    public void testPlaySoldier() throws Exception {
        System.out.println("playSoldier");
        IServerProxy instance = new IServerProxyImpl();
        instance.playSoldier();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of playMonopoly method, of class IServerProxy.
     */
    @Test
    public void testPlayMonopoly() throws Exception {
        System.out.println("playMonopoly");
        IServerProxy instance = new IServerProxyImpl();
        instance.playMonopoly();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buildSettlement method, of class IServerProxy.
     */
    @Test
    public void testBuildSettlement() throws Exception {
        System.out.println("buildSettlement");
        IServerProxy instance = new IServerProxyImpl();
        instance.buildSettlement();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buildCity method, of class IServerProxy.
     */
    @Test
    public void testBuildCity() throws Exception {
        System.out.println("buildCity");
        IServerProxy instance = new IServerProxyImpl();
        instance.buildCity();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendChat method, of class IServerProxy.
     */
    @Test
    public void testSendChat() throws Exception {
        System.out.println("sendChat");
        IServerProxy instance = new IServerProxyImpl();
        instance.sendChat();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rollNumber method, of class IServerProxy.
     */
    @Test
    public void testRollNumber() throws Exception {
        System.out.println("rollNumber");
        IServerProxy instance = new IServerProxyImpl();
        instance.rollNumber();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of robPlayer method, of class IServerProxy.
     */
    @Test
    public void testRobPlayer() throws Exception {
        System.out.println("robPlayer");
        IServerProxy instance = new IServerProxyImpl();
        instance.robPlayer();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of finishTurn method, of class IServerProxy.
     */
    @Test
    public void testFinishTurn() throws Exception {
        System.out.println("finishTurn");
        IServerProxy instance = new IServerProxyImpl();
        instance.finishTurn();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class IServerProxyImpl implements IServerProxy {

        public boolean login(Credentials userCredentials) throws ServerException {
            return false;
        }

        public boolean register(Credentials userCredentials) throws ServerException {
            return false;
        }

        public List<GameResponse> getGamesList() throws ServerException {
            return null;
        }

        public CreateGameResponse createGame(CreateGameRequest gameRequests) throws ServerException {
            return null;
        }

        public boolean joinGame(JoinGameRequest joinRequest) throws ServerException {
            return false;
        }

        public boolean saveGame(SaveGameRequest saveRequest) throws ServerException {
            return false;
        }

        public boolean loadGame(LoadGameRequest loadRequest) throws ServerException {
            return false;
        }

        public String getGameModel(int version) throws ServerException {
            return "";
        }

        public String resetGame() throws ServerException {
            return "";
        }

        public String postCommands(PostCommandsRequest postCommandsRequest) throws ServerException {
            return "";
        }

        public String getCommands() throws ServerException {
            return "";
        }

        public String addAi() throws ServerException {
            return "";
        }

        public String listAi() throws ServerException {
            return "";
        }

        public String buildRoad() throws ServerException {
            return "";
        }

        public void offerTrade() throws ServerException {
        }

        public void acceptTrade() throws ServerException {
        }

        public void maritimeTrade() throws ServerException {
        }

        public void buyDevCard() throws ServerException {
        }

        public void playYearOfPlenty() throws ServerException {
        }

        public void playRoadBuilding() throws ServerException {
        }

        public void playSoldier() throws ServerException {
        }

        public void playMonopoly() throws ServerException {
        }

        public void buildSettlement() throws ServerException {
        }

        public void buildCity() throws ServerException {
        }

        public void sendChat() throws ServerException {
        }

        public void rollNumber() throws ServerException {
        }

        public void robPlayer() throws ServerException {
        }

        public void finishTurn() throws ServerException {
        }
    }
    
}
