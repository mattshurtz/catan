package client.proxy;

import java.util.ArrayList;
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
import shared.communication.params.moves.BuildRoadRequest;
import shared.communication.params.moves.RollNumberRequest;
import shared.communication.responses.CreateGameResponse;
import shared.communication.responses.EmptyPlayerResponse;
import shared.communication.responses.GameResponse;
import shared.exceptions.ServerException;
import shared.json.Deserializer;
import shared.model.Model;

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
//        instance = new MockProxy();
        instance = new ServerProxy();
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
        boolean result, expResult;
        try {
            expResult = false;
            result = instance.login(userCredentials);
            assertEquals(expResult, result);
        } catch (ServerException e) {
            // The real server throws an exception, so that's fine
        }
        
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
        
        GameResponse gr = GameResponse.getDefaultSampleGameResponse();
        
        List<GameResponse> result = instance.getGamesList();
        boolean found = false;
        // That GameResponse should be at least one of them
        for ( GameResponse gamer : result ) {
            if ( gamer.equals(gr) ) {
                found = true;
                break;
            }
        }
        if ( ! found )
            fail("Default GameResponse not found!");
    }

    /**
     * Test of createGame method, of class IServerProxy.
     */
    @Test
    public void testCreateGame() throws Exception {
        System.out.println("createGame");
        
        CreateGameRequest cgr = new CreateGameRequest();
        cgr.setName("new game");
        cgr.setRandomNumbers(true);
        cgr.setRandomPorts(false);
        cgr.setRandomTiles(true);
        
        CreateGameResponse actual = instance.createGame(cgr);
        
        CreateGameResponse expected = new CreateGameResponse();
        List<EmptyPlayerResponse> epr = new ArrayList<>();
        for (int i = 0; i < 4; i++ )
            epr.add( new EmptyPlayerResponse() );
        expected.setPlayers(epr);
        expected.setTitle("new game");
        
        assertEquals(expected, actual);
    }

    /**
     * Test of joinGame method, of class IServerProxy.
     */
    @Test
    public void testJoinGame() throws Exception {
        System.out.println("joinGame");
        
        JoinGameRequest joinRequest = new JoinGameRequest();
        joinRequest.setGameID(5);
        joinRequest.setColor("green");
        boolean expResult = true;
        boolean result = instance.joinGame(joinRequest);
        assertEquals(expResult, result);
    }

    /**
     * Test of saveGame method, of class IServerProxy.
     */
    @Test
    public void testSaveGame() throws Exception {
        System.out.println("saveGame");
        SaveGameRequest saveRequest = new SaveGameRequest();
        saveRequest.setId(1);
        saveRequest.setName("savedgame");
        boolean expResult = true;
        boolean result = instance.saveGame(saveRequest);
        assertEquals(expResult, result);
    }

    /**
     * Test of loadGame method, of class IServerProxy.
     */
    @Test
    public void testLoadGame() throws Exception {
        System.out.println("loadGame");
        LoadGameRequest loadRequest = new LoadGameRequest();
        loadRequest.setName("savedgame");
        boolean expResult = true;
        boolean result = instance.loadGame(loadRequest);
        assertEquals(expResult, result);
    }

    /**
     * Test of getGameModel method, of class IServerProxy.
     */
    @Test
    public void testGetGameModel() throws Exception {
        System.out.println("getGameModel");
        int version = 0;
        Model model = instance.getGameModel(version);
        assertNotNull(model);
    }

    /**
     * Test of resetGame method, of class IServerProxy.
     */
    @Test
    public void testResetGame() throws Exception {
        System.out.println("resetGame");
        Model expResult = new Deserializer().getTestModel();
        Model result = instance.resetGame();
        assertEquals(expResult, result);
    }

    /**
     * Test of postCommands method, of class IServerProxy.
     */
    @Test
    public void testPostCommands() throws Exception {
        System.out.println("postCommands");
        PostCommandsRequest postCommandsRequest = null;
        Model expResult = new Deserializer().getTestModel();
        Model result = instance.postCommands(postCommandsRequest);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCommands method, of class IServerProxy.
     */
    @Test
    public void testGetCommands() throws Exception {
        System.out.println("getCommands");
        String result = instance.getCommands();
        assertNotNull(result);
    }

    /**
     * Test of addAi method, of class IServerProxy.
     */
    @Test
    public void testAddAi() throws Exception {
        System.out.println("addAi");
        boolean expResult = true;
        boolean result = instance.addAi( null );
        assertEquals(expResult, result);
    }

    /**
     * Test of listAi method, of class IServerProxy.
     */
    @Test
    public void testListAi() throws Exception {
        System.out.println("listAi");
        String[] expResult = new String[] { "LARGEST_ARMY" };
        String[] result = instance.listAi();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of buildRoad method, of class IServerProxy.
     */
    @Test
    public void testBuildRoad() throws Exception {
        System.out.println("buildRoad");
        BuildRoadRequest req = new BuildRoadRequest();
//        req.set
//        Model result = instance.buildRoad();
//        assertNotNull( result );
    }

    /**
     * Test of offerTrade method, of class IServerProxy.
     */
    @Test
    public void testOfferTrade() throws Exception {
        System.out.println("offerTrade");
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
        instance.rollNumber(new RollNumberRequest(1,15));
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of robPlayer method, of class IServerProxy.
     */
    @Test
    public void testRobPlayer() throws Exception {
        System.out.println("robPlayer");
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
        instance.finishTurn();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
