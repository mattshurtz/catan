package server;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import server.commands.Command;
import server.commands.moves.Monopoly;
import server.commands.moves.Monument;
import server.commands.moves.Year_of_Plenty;
import server.commands.moves.sendChat;
import server.gameinfocontainer.GameInfoContainer;
import shared.communication.params.moves.MoveRequest;
import shared.communication.params.moves.PlayMonopolyRequest;
import shared.communication.params.moves.PlayYearOfPlentyRequest;
import shared.communication.params.moves.SendChatRequest;
import shared.definitions.ResourceType;
import shared.exceptions.GetPlayerException;
import shared.exceptions.HTTPBadRequest;
import shared.json.Serializer;
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
        PlayMonopolyRequest pmr = new PlayMonopolyRequest(ResourceType.WOOD );
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
    	Model m = gic.getModels().getGame(0);
    	int testPlayerIndex = 0;
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
    		sc.execute(serializer.toJson(scReq), 0, p.getPlayerID());
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
    }
}
