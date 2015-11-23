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
import server.gameinfocontainer.GameInfoContainer;
import shared.communication.params.moves.PlayYearOfPlentyRequest;
import shared.definitions.ResourceType;
import shared.exceptions.GetPlayerException;
import shared.model.Model;
import shared.model.Player;

/**
 *
 * @author JanPaul
 */
public class CommandTests {
    
    private GameInfoContainer gic;
    
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
	}

	@After
	public void tearDown() {
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
        m.playYearOfPlenty(pyorp);
        
        int newAmountWood = p.getResources().getWood();
        int newVersion = m.getVersion();
        assertEquals( null, amountWood + 2, newAmountWood );
        assertEquals( null, oldVersion + 1, newVersion );
        
    }
}
