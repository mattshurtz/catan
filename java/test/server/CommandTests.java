package server;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import server.commands.Command;
import server.commands.moves.*;
import server.gameinfocontainer.GameInfoContainer;
import shared.communication.params.moves.*;
import shared.definitions.ResourceType;
import shared.exceptions.GetPlayerException;
import shared.exceptions.HTTPBadRequest;
import shared.json.Serializer;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.model.MessageLine;
import shared.model.MessageList;
import shared.model.Model;
import shared.model.Player;

/**
 *
 * @author JanPaul
 */
public class CommandTests {
    
    private GameInfoContainer gic;
    private Serializer serializer;
    
    public CommandTests() {
    }
    
    @BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
        gic = GameInfoContainer.getInstance();
        serializer = new Serializer();
	}

	@After
	public void tearDown() {
	}

    @Test
    public void testMonument() {
        Model m = gic.getModels().getGame(0);
        int testPlayerIndex = 0;
        Player p = null;
        try {
            p = m.getPlayer( testPlayerIndex );
        } catch (GetPlayerException ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        
        int oldVictoryPoints = p.getVictoryPoints();
        int oldVersion = m.getVersion();
        p.getOldDevCards().AddMonument();
        p.setPlayedDevCard(false);
        
        // Play the monopoly
        MoveRequest pmr = new MoveRequest();
        pmr.setType("Monument");
        pmr.setPlayerIndex(testPlayerIndex);
        
        // Actually execute the command
        Command mon = new Monument();
        try {
            mon.execute( serializer.toJson( pmr ), 0, p.getPlayerID() );
        } catch (HTTPBadRequest ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        
        int newVic = p.getVictoryPoints();
        int newVersion = m.getVersion();
        assertEquals( oldVictoryPoints+1, newVic );
        assertEquals( oldVersion + 1, newVersion );
    }
    
    @Test
    public void testBuildCity() throws GetPlayerException {
        Command cmd = new buildCity();
        
        //model is default post setup
        Model m = gic.getModels().getGame(1);
        int testPlayerIndex = 0;
        Player p = null;
        VertexLocation validVertex = new VertexLocation(new HexLocation(-2,0), VertexDirection.West);
        VertexLocation emptyVertex = new VertexLocation(new HexLocation(-2,0), VertexDirection.NorthWest);
        VertexLocation opponentVertex = new VertexLocation(new HexLocation(0,-1), VertexDirection.West);
        
        int historyLength = m.getLog().getLength();
        int version = m.getVersion();
        int bankOre = m.getBank().getOre();
        int bankWheat = m.getBank().getWheat();
        
        
        //test if NOT ENOUGH RESOURCES (3 ore 2 wheat)
        p = m.getPlayer(0);
        int numOre = p.getResources().getOre();
        int numWheat = p.getResources().getWheat();
        int numCities = p.getCities();
        
        if(numOre >= 3) {
            int excess = numOre - 3;
            p.getResources().setOre(2);
            numOre = 2;
            m.getBank().addResource(ResourceType.ORE, excess + 1);
            bankOre += (excess + 1);
        }
        
        if(numWheat >= 2) {
            int excess = numWheat - 2;
            p.getResources().setWheat(1);
            numWheat = 1;
            m.getBank().addResource(ResourceType.WHEAT, excess + 1);
            bankWheat += (excess + 1);
        }
        
        BuildCityRequest req = new BuildCityRequest(validVertex);
        req.setType("buildCity");
        req.setPlayerIndex(0);
        try {
            cmd.execute(serializer.toJson(req),1,p.getPlayerID());
        } catch (HTTPBadRequest ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        assertEquals(numOre, m.getPlayer(0).getResources().getOre());
        assertEquals(numWheat, m.getPlayer(0).getResources().getWheat());
        assertEquals(bankOre, m.getBank().getOre());
        assertEquals(bankWheat, m.getBank().getWheat());        
        assertEquals(historyLength, m.getLog().getLength());
        assertEquals(numCities, m.getPlayer(0).getCities());
        assertEquals(version, m.getVersion());
        
        //ENOUGH RESOURCES
        if(numOre < 3) {
            int needed = 3 - numOre;
            p.getResources().setOre(3);
            m.getBank().subtractResource(ResourceType.ORE, needed);
            numOre = 3;
            bankOre -= needed;
        }
        if(numWheat < 2) {
            int needed = 2 - numWheat;
            p.getResources().setWheat(2);
            m.getBank().subtractResource(ResourceType.WHEAT, needed);
            numWheat = 2;
            bankWheat -= needed;
        }
        
        //test not own settlement
        req = new BuildCityRequest(opponentVertex);
        req.setType("buildCity");
        req.setPlayerIndex(0);
        try {
            cmd.execute(serializer.toJson(req),1,p.getPlayerID());
        } catch (HTTPBadRequest ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        assertEquals(numOre, m.getPlayer(0).getResources().getOre());
        assertEquals(numWheat, m.getPlayer(0).getResources().getWheat());
        assertEquals(bankOre, m.getBank().getOre());
        assertEquals(bankWheat, m.getBank().getWheat());        
        assertEquals(historyLength, m.getLog().getLength());
        assertEquals(numCities, m.getPlayer(0).getCities());
        assertEquals(version, m.getVersion());
        
        //test no settlement on vertex
        req = new BuildCityRequest(emptyVertex);
        req.setType("buildCity");
        req.setPlayerIndex(0);
        try {
            cmd.execute(serializer.toJson(req),1,p.getPlayerID());
        } catch (HTTPBadRequest ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        assertEquals(numOre, m.getPlayer(0).getResources().getOre());
        assertEquals(numWheat, m.getPlayer(0).getResources().getWheat());
        assertEquals(bankOre, m.getBank().getOre());
        assertEquals(bankWheat, m.getBank().getWheat());
        assertEquals(historyLength, m.getLog().getLength());
        assertEquals(numCities, m.getPlayer(0).getCities());
        assertEquals(version, m.getVersion());
        
        //test if no more cities but on valid vertex
        assertEquals(4,numCities);
        p.setCities(0);
        numCities = p.getCities(); //should be 0
        assertEquals(0, numCities);
        
        req = new BuildCityRequest(validVertex);
        req.setType("buildCity");
        req.setPlayerIndex(0);
        try {
            cmd.execute(serializer.toJson(req),1,p.getPlayerID());
        } catch (HTTPBadRequest ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        assertEquals(numOre, m.getPlayer(0).getResources().getOre());
        assertEquals(numWheat, m.getPlayer(0).getResources().getWheat());
        assertEquals(bankOre, m.getBank().getOre());
        assertEquals(bankWheat, m.getBank().getWheat());
        assertEquals(historyLength, m.getLog().getLength());
        assertEquals(numCities, m.getPlayer(0).getCities());
        assertEquals(version, m.getVersion());
        
        p.setCities(4);
        numCities = p.getCities();
        assertEquals(4, numCities);
        
        //test not your turn
        m.getTurnTracker().setCurrentTurn(1);
        
        assertTrue(numOre >= 3);
        assertTrue(numWheat >= 2);
        
        req = new BuildCityRequest(validVertex);
        req.setType("buildCity");
        req.setPlayerIndex(0);
        try {
            cmd.execute(serializer.toJson(req),1,p.getPlayerID());
        } catch (HTTPBadRequest ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        assertEquals(numOre, m.getPlayer(0).getResources().getOre());
        assertEquals(numWheat, m.getPlayer(0).getResources().getWheat());
        assertEquals(bankOre, m.getBank().getOre());
        assertEquals(bankWheat, m.getBank().getWheat());
        assertEquals(historyLength, m.getLog().getLength());
        assertEquals(numCities, m.getPlayer(0).getCities());
        assertEquals(version, m.getVersion());
        
        m.getTurnTracker().setCurrentTurn(0);
        
        //test if valid
        assertTrue(numOre >= 3);
        assertTrue(numWheat >= 2);
        
        req = new BuildCityRequest(validVertex);
        req.setType("buildCity");
        req.setPlayerIndex(0);
        try {
            cmd.execute(serializer.toJson(req),1,p.getPlayerID());
        } catch (HTTPBadRequest ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        assertEquals(numOre - 3, m.getPlayer(0).getResources().getOre());
        assertEquals(numWheat - 2, m.getPlayer(0).getResources().getWheat());
        assertEquals(bankOre + 3, m.getBank().getOre());
        assertEquals(bankWheat + 2, m.getBank().getWheat());
        assertEquals(historyLength + 1, m.getLog().getLength());
        assertEquals(numCities - 1, m.getPlayer(0).getCities());
        assertEquals(++version, m.getVersion());
    }

    @Test
    public void testMonopoly() {
        Model m = gic.getModels().getGame(0);
        int testPlayerIndex = 0;
        Player p = null;
        try {
            p = m.getPlayer( testPlayerIndex );
        } catch (GetPlayerException ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        int oldAmountWood = p.getResources().getWood();
        int oldVersion = m.getVersion();
        p.getOldDevCards().AddMonopoly();
        p.setPlayedDevCard(false);
        
        // Give each player some wood
        int totalWoodDistributed = 0;
        for ( int i = 0; i < 4; i++ ) {
            Player p2 = null;
            try {
                p2 = m.getPlayer( i );
            } catch (GetPlayerException ex) {
                Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
                fail();
            }
            int amountWoodGiving = i + 1; // just a random number basically
            p2.getResources().setWood( amountWoodGiving );
            totalWoodDistributed += amountWoodGiving;
        }
        
        // Play the monopoly
        PlayMonopolyRequest pmr = new PlayMonopolyRequest(ResourceType.WOOD);
        pmr.setPlayerIndex(testPlayerIndex);
        
        // Actually execute the command
        Command mon = new Monopoly();
        try {
            mon.execute( serializer.toJson( pmr ), 0, p.getPlayerID() );
        } catch (HTTPBadRequest ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        
        int newAmountWood = p.getResources().getWood();
        int newVersion = m.getVersion();
        assertEquals( null, totalWoodDistributed, newAmountWood );
        
        // make sure all the other players have no wood left
        for ( int i = 1; i < 4; i++ ) {
            Player p2 = null;
            try {
                p2 = m.getPlayer( i );
            } catch (GetPlayerException ex) {
                Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
                fail();
            }
            assertEquals( 0, p2.getResources().getWood() );
        }

        assertEquals( null, oldVersion + 1, newVersion );
    }
    
    @Test
    public void testYearOfPlenty() {
        Model m = gic.getModels().getGame(0);
        int testPlayerIndex = 0;
        Player p = null;
        try {
            p = m.getPlayer( testPlayerIndex );
        } catch (GetPlayerException ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        int amountWood = p.getResources().getWood();
        int oldVersion = m.getVersion();
        p.getOldDevCards().AddYearOfPlenty();
        p.setPlayedDevCard(false);
        PlayYearOfPlentyRequest pyorp = new PlayYearOfPlentyRequest( testPlayerIndex, ResourceType.WOOD, ResourceType.WOOD);
        
        // Actually execute the command
        Command yop = new Year_of_Plenty();
        try {
            yop.execute( serializer.toJson( pyorp ), 0, p.getPlayerID() );
        } catch (HTTPBadRequest ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        
        int newAmountWood = p.getResources().getWood();
        int newVersion = m.getVersion();
        assertEquals( null, amountWood + 2, newAmountWood );
        assertEquals( null, oldVersion + 1, newVersion );
        
    }
    
    @Test
    public void testSendChat() {
    	//TEST VALID CASE
    	Model m = gic.getModels().getGame(0);
    	int testPlayerIndex = 0;
    	int gameID = 0;
        Player p = null;
        try {
            p = m.getPlayer( testPlayerIndex );
        } catch (GetPlayerException ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
    	
    	MessageList mlOld = m.getChat();
    	int oldVersion = m.getVersion();
    	
    	String message = "Hello world";
    	SendChatRequest scReq = new SendChatRequest(testPlayerIndex, message);
    	
    	//Execute the command
    	Command sc = new sendChat();
    	try {
    		sc.execute(serializer.toJson(scReq), gameID, p.getPlayerID());
    	} catch (HTTPBadRequest ex) {
    		Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
    		fail();
    	}
    	
    	MessageList mlNew = m.getChat();
    	int newVersion = m.getVersion();
    	
    	//manually update old messageList and see if they match
    	MessageLine line = new MessageLine(p.getName(), message);
    	mlOld.getLines().add(line);
    	
    	assertEquals(mlOld, mlNew);
    	assertEquals(oldVersion + 1, newVersion);
    	
    	//TEST WITH EMPTY INPUT
    	mlOld = mlNew;
    	oldVersion = newVersion;
    	
    	message = "";
    	scReq = new SendChatRequest(testPlayerIndex, message);
    	
    	//Execute command
    	try {
    		sc.execute(serializer.toJson(scReq), gameID, p.getPlayerID());
    	} catch( HTTPBadRequest ex) {
    		Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
    		fail();
    	}
    	
    	mlNew = m.getChat();
    	newVersion = m.getVersion();
    	
    	//manually update and see if they match
    	line = new MessageLine(p.getName(), message);
    	mlOld.getLines().add(line);
    	
    	assertEquals(mlOld, mlNew);
    	assertEquals(oldVersion + 1, newVersion);
    }
}
