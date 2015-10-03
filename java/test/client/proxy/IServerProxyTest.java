package client.proxy;

import java.math.BigInteger;
import java.security.SecureRandom;
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
import shared.communication.params.moves.DiscardCardsRequest;
import shared.communication.params.moves.MoveRequest;
import shared.communication.params.moves.PlayRoadBuildingRequest;
import shared.communication.params.moves.PlayYearOfPlentyRequest;
import shared.communication.params.moves.RobPlayerRequest;
import shared.communication.params.moves.RollNumberRequest;
import shared.communication.params.moves.SendChatRequest;
import shared.communication.responses.CreateGameResponse;
import shared.communication.responses.EmptyPlayerResponse;
import shared.communication.responses.GameResponse;
import shared.exceptions.ServerException;
import shared.json.Deserializer;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.model.Model;
import shared.model.ResourceList;
import shared.definitions.*;

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

    
    
    public boolean testSetup(boolean login, boolean joinGame, boolean reset) throws ServerException {
    	if(login && !instance.login(new Credentials("Sam","sam"))) {return false;}
    	if(joinGame && !instance.joinGame(new JoinGameRequest(0,"green"))) {return false;}
    	if(reset) {
    		Model model = instance.resetGame();
    		if(model==null)
    			return false;
    	}
    	return true;    	
    }
    /**
     * Test of login method, of class IServerProxy.
     */
    @Test
    public void testLogin_invalid() throws Exception {
        System.out.println("login");
        
        // first, login without credentials -- should fail
        Credentials userCredentials = new Credentials();
        userCredentials.setUsername("saflkjsa);lfkjas;ldfkj");
        userCredentials.setPassword("jkl43ltkjretlej4l34444");
        boolean result, expResult;
        try {
            expResult = false;
            result = instance.login(userCredentials);
            assertEquals(expResult, result);
        } catch (ServerException e) {
            fail("should return false " + e.getMessage());
        }
    }
    
    @Test
    public void testLogin_Valid() {
        System.out.println("login");
        
        // first, login without credentials -- should fail
        Credentials userCredentials = new Credentials();
        boolean result, expResult;
        
        // Now login for real
        userCredentials.setUsername("Sam");
        userCredentials.setPassword("sam");
        expResult = true;
        try {
			result = instance.login(userCredentials);
			assertEquals(expResult, result);
		} catch (ServerException e) {
			fail(e.getMessage());
		}
        
    }

    /**
     * Test of register method, of class IServerProxy.
     */
    @Test
    public void testRegister() {
        System.out.println("register");
        Credentials userCredentials = null;
        
        boolean expResult = false;
        boolean result;
		try {
			result = instance.register(userCredentials);
			fail("should not return result");
		} catch (ServerException e) {
			//good!
		}
        
        userCredentials = new Credentials();
        try {
			result = instance.register(userCredentials);
			fail("should not return result");
		} catch (ServerException e) {
				//good!
		}
        
        
        // valid
        userCredentials.setUsername(new BigInteger(20, new SecureRandom()).toString(32));
        userCredentials.setPassword("abcde");
        expResult = true;
        try {
			result = instance.register(userCredentials);
			assertEquals( expResult, result );
		} catch (ServerException e) {
			fail(e.getMessage());
		}
        
        
//        // invalid username
//        userCredentials.setUsername("ab");
//        userCredentials.setPassword("abcde");
//        expResult = false;
//        try {
//			result = instance.register(userCredentials);
//			assertEquals( expResult, result );
//		} catch (ServerException e) {
//			fail(e.getMessage());
//		}
//        
//        
//        // invalid password
//        userCredentials.setUsername("jan1");
//        userCredentials.setPassword("alkjc()*&#");
//        expResult = false;
//        try {
//			result = instance.register(userCredentials);
//			assertEquals( expResult, result );
//		} catch (ServerException e) {
//			fail(e.getMessage());
//		}
        
        
//        // valid
//        userCredentials.setUsername("Sammers");
//        userCredentials.setPassword("sammers");
//        expResult = true;
//        try {
//			result = instance.register(userCredentials);
//			assertEquals( expResult, result );
//		} catch (ServerException e) {
//			fail(e.getMessage());
//		}
        
        
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
        
        List<GameResponse> result = instance.getGamesList();
        if(result == null)
        	fail("Did not return List of GameResponse");
//        boolean found = false;
//        // That GameResponse should be at least one of them
//        for ( GameResponse gamer : result ) {
//            if ( gamer.equals(gr) ) {
//                found = true;
//                break;
//            }
//        }
//        if ( ! found )
//            fail("Default GameResponse not found!");
    }

    /**
     * Test of createGame method, of class IServerProxy.
     */
    @Test
    public void testCreateGame() throws Exception {
        System.out.println("createGame");
        
        CreateGameRequest cgr = new CreateGameRequest();
        String name = new BigInteger(20, new SecureRandom()).toString(32);
        cgr.setName(name);
        cgr.setRandomNumbers(true);
        cgr.setRandomPorts(false);
        cgr.setRandomTiles(true);
        
        CreateGameResponse actual = instance.createGame(cgr);
        
        CreateGameResponse expected = new CreateGameResponse();
        List<EmptyPlayerResponse> epr = new ArrayList<>();
        for (int i = 0; i < 4; i++ )
            epr.add( new EmptyPlayerResponse() );
        expected.setPlayers(epr);
        expected.setTitle(name);
        
        assertEquals(expected, actual);
    }

    /**
     * Test of joinGame method, of class IServerProxy.
     */
    @Test
    public void testJoinGame_notLoggedIn() throws Exception {
        System.out.println("joinGame");
                       
        JoinGameRequest joinRequest = new JoinGameRequest();
        joinRequest.setGameID(2);
        joinRequest.setColor("green");
        boolean expResult = false;
        boolean result = instance.joinGame(joinRequest);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testJoinGame_LoggedIn() throws Exception {
        System.out.println("joinGame");
        
        instance.login(new Credentials("Sam","sam"));
                       
        JoinGameRequest joinRequest = new JoinGameRequest();
        joinRequest.setGameID(2);
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
        loadRequest.setName("Default Game");
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
        testSetup(true,true,false);        
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
        testSetup(true,true,false);
        Model result = instance.resetGame();
        if (result == null)
        	fail("Unable to reset");
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

    }

    /**
     * Test of buildRoad method, of class IServerProxy.
     */
    @Test
    public void testBuildRoad() throws Exception {
        System.out.println("buildRoad");
//        BuildRoadRequest req = new BuildRoadRequest();
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
//        instance.offerTrade();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of acceptTrade method, of class IServerProxy.
     */
    @Test
    public void testAcceptTrade() throws Exception {
        System.out.println("acceptTrade");
//        instance.acceptTrade();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of maritimeTrade method, of class IServerProxy.
     */
    @Test
    public void testMaritimeTrade() throws Exception {
        System.out.println("maritimeTrade");
//        instance.maritimeTrade();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buyDevCard method, of class IServerProxy.
     */
    @Test
    public void testBuyDevCard() throws Exception {
        System.out.println("buyDevCard");
        testSetup(true,true,false);
        Model result = instance.buyDevCard(new MoveRequest("buyDevCard",0));
        if (result == null)
        	fail("Could not buy a dev card");
    }

    /**
     * Test of playYearOfPlenty method, of class IServerProxy.
     */
    @Test
    public void testPlayYearOfPlenty() throws Exception {
        System.out.println("playYearOfPlenty");
        testSetup(true,true,false);
        Model result = instance.playYearOfPlenty(new PlayYearOfPlentyRequest(0,ResourceType.ORE,ResourceType.WHEAT));
        if (result==null)
        	fail("Did not play year of plenty");
    }

    /**
     * Test of playRoadBuilding method, of class IServerProxy.
     */
    @Test
    public void testPlayRoadBuilding() throws Exception {
        System.out.println("playRoadBuilding");
        testSetup(true,true,false);
        Model result = instance.playRoadBuilding(new PlayRoadBuildingRequest(0,new EdgeLocation(new HexLocation(1,1),EdgeDirection.North),new EdgeLocation(new HexLocation(1,2),EdgeDirection.North)));
        if(result == null) {
        	fail("Did not play road");
        }
        
    }

    /**
     * Test of playSoldier method, of class IServerProxy.
     */
    @Test
    public void testPlaySoldier() throws Exception {
        System.out.println("playSoldier");
//        instance.playSoldier();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of playMonopoly method, of class IServerProxy.
     */
    @Test
    public void testPlayMonopoly() throws Exception {
        System.out.println("playMonopoly");
//        instance.playMonopoly();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buildSettlement method, of class IServerProxy.
     */
    @Test
    public void testBuildSettlement() throws Exception {
        System.out.println("buildSettlement");
//        instance.buildSettlement();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buildCity method, of class IServerProxy.
     */
    @Test
    public void testBuildCity() throws Exception {
        System.out.println("buildCity");
//        instance.buildCity();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendChat method, of class IServerProxy.
     */
    @Test
    public void testSendChat() throws Exception {
        System.out.println("sendChat");
        
        testSetup(true,true,false);
        Model response = instance.sendChat(new SendChatRequest(0,"test"));
        if(response==null)
        	fail("Did not recieve a model in return");
    }

    /**
     * Test of rollNumber method, of class IServerProxy.
     */
    @Test
    public void testRollNumber() {
        System.out.println("rollNumber");
        try {
        	testSetup(true,true,false);
			Model result = instance.rollNumber(new RollNumberRequest(1,10));
			if(result == null){
				fail("Did not get a model as result.");
			}
		} catch (ServerException e) {
			fail("Did not get a model as result. " + e.getMessage());
		}
    }

    /**
     * Test of robPlayer method, of class IServerProxy.
     */
    @Test
    public void testRobPlayer() throws Exception {
        System.out.println("robPlayer");
        testSetup(true, true, false);
        Model result = instance.robPlayer(new RobPlayerRequest(0,1,new HexLocation(1,1)));
        if (result == null)
        	fail("Unable to rob player");
    }

    /**
     * Test of finishTurn method, of class IServerProxy.
     */
    @Test
    public void testFinishTurn() throws Exception {
        System.out.println("finishTurn");
        testSetup(true,true,false);
        Model result = instance.finishTurn(new MoveRequest("finishTurn",0));
        if (result == null)
        	fail("Unable to finish turn");
    }
    
    @Test
    public void testDiscardCards() throws Exception {
    	System.out.println("discardCards");
    	testSetup(true,true,false);
    	Model result = instance.discardCards(new DiscardCardsRequest(0,new ResourceList(1,0,3,0,0)));
    	if(result==null)
    		fail("Did not discard Cards");
    	//1 brick 3 sheep
    	
    }
    
}
