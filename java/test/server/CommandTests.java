package server;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import server.commands.Command;
import server.commands.game.listAI;
import server.commands.game.model;
import server.commands.games.create;
import server.commands.games.join;
import server.commands.games.list;
import server.commands.moves.*;
import server.commands.user.login;
import server.commands.user.register;
import server.gameinfocontainer.GameInfoContainer;
import shared.communication.params.CreateGameRequest;
import shared.communication.params.Credentials;
import shared.communication.params.JoinGameRequest;
import shared.communication.params.moves.*;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.definitions.TurnStatus;
import shared.exceptions.GetPlayerException;
import shared.exceptions.HTTPBadRequest;
import shared.json.Serializer;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.model.DevCardList;
import shared.model.MessageLine;
import shared.model.MessageList;
import shared.model.Model;
import shared.model.Player;
import shared.model.ResourceList;
import shared.model.map.City;

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
		GameInfoContainer.reset();
	}

	
//USER TESTS	
	@Test
	public void testLogin_valid() {
		
		
		String result = "";
		//test valid login		
		Credentials rqt = new Credentials("Matt","matt");
		try {
			result = (new login()).execute(serializer.toJson(rqt), 0, 0);
		} catch (HTTPBadRequest ex) {
			Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            fail();
		}
		assertEquals("catan.user=%7B%22name%22%3A%22Matt%22%2C%22password%22%3A%22matt%22%2C%22playerID%22%3A0%7D;Path=/;",result);
		
	}
	
	@Test
	public void testLogin_badPassword() {
		
		String result = "";
				
		Credentials rqt = new Credentials("Matt","matt2");
		try {
			result = (new login()).execute(serializer.toJson(rqt), 0, 0);			
			//should throw HTTPBadRequest
			fail();
		} catch (HTTPBadRequest ex) {
			Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);            
		}
	}
	
	@Test
	public void testLogin_badUsername() {
		
		String result = "";
			
		Credentials rqt = new Credentials("Matt2","matt");
		try {
			result = (new login()).execute(serializer.toJson(rqt), 0, 0);			
			//should throw HTTPBadRequest
			fail();
		} catch (HTTPBadRequest ex) {
			Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);            
		}
	}
	
	@Test
	public void testLogin_badUsername_emptyString() {
		
		String result = "";
				
		Credentials rqt = new Credentials("","");
		try {
			result = (new login()).execute(serializer.toJson(rqt), 0, 0);			
			//should throw HTTPBadRequest
			fail();
		} catch (HTTPBadRequest ex) {
			Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);    
			return;
		}
		fail();
	}
	
	@Test
	public void testRegister_valid() {
		String result = "";
		//test valid registration	
		Credentials rqt = new Credentials("random","random");
		try {
			result = (new register()).execute(serializer.toJson(rqt), 0, 0);
		} catch (HTTPBadRequest ex) {
			Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            fail();
		}
		assertEquals("catan.user=%7B%22name%22%3A%22random%22%2C%22password%22%3A%22random%22%2C%22playerID%22%3A5%7D;Path=/;",result);	
	}
	
	@Test
	public void testRegister_userAlreadyExists() {
		String result = "";
	
		Credentials rqt = new Credentials("Matt","123456");
		try {
			result = (new register()).execute(serializer.toJson(rqt), 0, 0);
			fail();
		} catch (HTTPBadRequest ex) {
			Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            
		}
	}
	
	@Test
	public void testRegister_invalidUsername() {
		String result = "";
	
		Credentials rqt = new Credentials("ba","123456");
		try {
			result = (new register()).execute(serializer.toJson(rqt), 0, 0);
			fail();
		} catch (HTTPBadRequest ex) {
			Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            
		}
	}
	
	@Test
	public void testRegister_invalidPassword() {
		String result = "";
	
		Credentials rqt = new Credentials("baby","12");
		try {
			result = (new register()).execute(serializer.toJson(rqt), 0, 0);
			fail();
		} catch (HTTPBadRequest ex) {
			Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            
		}
	}
	
//GAMES TESTS
	@Test
	public void testCreate_valid() {
		String result = "";
		
		CreateGameRequest request= new CreateGameRequest();
		request.setName("game1");
		request.setRandomNumbers(false);
		request.setRandomPorts(false);
		request.setRandomTiles(false);		
		try {
			result = (new create()).execute(serializer.toJson(request), 0, 0);
		} catch (HTTPBadRequest ex) {
			Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            fail();
		}
		assertEquals("{\"title\":\"game1\",\"id\":2,\"players\":[{},{},{},{}]}",result);
	}
	
	@Test
	public void testCreate_AllVersionsOfRandomness() {
		String result = "";
		
		CreateGameRequest request= new CreateGameRequest();
		request.setName("game1");
		request.setRandomNumbers(false);
		request.setRandomPorts(false);
		request.setRandomTiles(false);		
		try {
			result = (new create()).execute(serializer.toJson(request), 0, 0);
		} catch (HTTPBadRequest ex) {
			Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            fail();
		}
		assertEquals("{\"title\":\"game1\",\"id\":2,\"players\":[{},{},{},{}]}",result);
		
		
		
		request.setName("game1");
		request.setRandomNumbers(true);
		request.setRandomPorts(true);
		request.setRandomTiles(true);		
		try {
			result = (new create()).execute(serializer.toJson(request), 0, 0);
		} catch (HTTPBadRequest ex) {
			Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            fail();
		}
		assertEquals("{\"title\":\"game1\",\"id\":3,\"players\":[{},{},{},{}]}",result);
		
	}
	
	@Test
	public void testCreate_emtpyGameName() {
		String result = "";
		
		CreateGameRequest request= new CreateGameRequest();
		request.setName("");
		request.setRandomNumbers(false);
		request.setRandomPorts(false);
		request.setRandomTiles(false);		
		try {
			result = (new create()).execute(serializer.toJson(request), 0, 0);
			fail();
		} catch (HTTPBadRequest ex) {
			Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            
		}
		assertEquals("",result);

	}
	
	@Test
	public void testJoin_valid() {
		String result = "";
		
		JoinGameRequest request = new JoinGameRequest();
		request.setGameID(1);
		request.setColor("blue");
		try {
			result = (new join()).execute(serializer.toJson(request), 0, 0);
		} catch (HTTPBadRequest ex) {
			Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            fail();
		}
		assertEquals("catan.game=1;Path=/;",result);		
	}
	
	@Test
	public void testJoin_invalidUser() {
		String result = "";
		
		JoinGameRequest request = new JoinGameRequest();
		request.setGameID(1);
		request.setColor("blue");
		try {
			result = (new join()).execute(serializer.toJson(request), 0, 50);
			fail();
		} catch (HTTPBadRequest ex) {
			Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex); 
			return;
		}
		fail();		
	}
	
	@Test
	public void testJoin_invalidGame() {
		String result = "";
		
		JoinGameRequest request = new JoinGameRequest();
		request.setGameID(500);
		request.setColor("blue");
		try {
			result = (new join()).execute(serializer.toJson(request), 0, 0);
			fail();
		} catch (HTTPBadRequest ex) {
			Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex); 
			return;
		}
		fail();		
	}
	
	@Test
	public void testList() {
		String result = "";
		try {
			result = (new list()).execute(null, 0, 0);			
		} catch (HTTPBadRequest ex) {
			Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex); 
			fail();
		}
		assertEquals("[{\"title\":\"Default Setup\",\"id\":0,\"players\":[{\"color\":\"blue\",\"name\":\"Matt\",\"id\":0},{\"color\":\"green\",\"name\":\"Scott\",\"id\":1},{\"color\":\"orange\",\"name\":\"Jan\",\"id\":2},{\"color\":\"red\",\"name\":\"Garrett\",\"id\":3}]},{\"title\":\"Default Post Setup\",\"id\":1,\"players\":[{\"color\":\"blue\",\"name\":\"Matt\",\"id\":0},{\"color\":\"green\",\"name\":\"Scott\",\"id\":1},{\"color\":\"orange\",\"name\":\"Jan\",\"id\":2},{\"color\":\"red\",\"name\":\"Garrett\",\"id\":3}]}]",result);
	}
//GAME TESTS	
	@Test
	public void testModel_valid() {
		String result = "";
		try {
			result = (new model()).execute(null, 0, 0);			
		} catch (HTTPBadRequest ex) {
			Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex); 
			fail();
		}
		
		String expect = "{\"bank\":{\"brick\":19,\"ore\":19,\"sheep\":19,\"wheat\":19,\"wood\":19},\"deck\":{\"yearOfPlenty\":2,\"monopoly\":2,\"soldier\":14,\"roadBuilding\":2,\"monument\":5},\"chat\":{\"lines\":[]},\"log\":{\"lines\":[]},\"map\":{\"hexes\":[{\"location\":{\"x\":-2,\"y\":0},\"resource\":\"ore\",\"number\":5},{\"location\":{\"x\":-2,\"y\":1},\"resource\":\"wheat\",\"number\":2},{\"location\":{\"x\":-2,\"y\":2},\"resource\":\"wood\",\"number\":6},{\"location\":{\"x\":-1,\"y\":-1},\"resource\":\"brick\",\"number\":8},{\"location\":{\"x\":-1,\"y\":0},\"resource\":\"sheep\",\"number\":10},{\"location\":{\"x\":-1,\"y\":1},\"resource\":\"sheep\",\"number\":9},{\"location\":{\"x\":-1,\"y\":2},\"resource\":\"ore\",\"number\":3},{\"location\":{\"x\":0,\"y\":-2},\"number\":0},{\"location\":{\"x\":0,\"y\":-1},\"resource\":\"wood\",\"number\":3},{\"location\":{\"x\":0,\"y\":0},\"resource\":\"wheat\",\"number\":11},{\"location\":{\"x\":0,\"y\":1},\"resource\":\"wood\",\"number\":4},{\"location\":{\"x\":0,\"y\":2},\"resource\":\"wheat\",\"number\":8},{\"location\":{\"x\":1,\"y\":-2},\"resource\":\"brick\",\"number\":4},{\"location\":{\"x\":1,\"y\":-1},\"resource\":\"ore\",\"number\":9},{\"location\":{\"x\":1,\"y\":0},\"resource\":\"brick\",\"number\":5},{\"location\":{\"x\":1,\"y\":1},\"resource\":\"sheep\",\"number\":10},{\"location\":{\"x\":2,\"y\":-2},\"resource\":\"wood\",\"number\":11},{\"location\":{\"x\":2,\"y\":-1},\"resource\":\"sheep\",\"number\":12},{\"location\":{\"x\":2,\"y\":0},\"resource\":\"wheat\",\"number\":6}],\"ports\":[{\"resource\":\"ore\",\"location\":{\"x\":1,\"y\":-3},\"direction\":\"S\",\"ratio\":2},{\"resource\":\"three\",\"location\":{\"x\":3,\"y\":-3},\"direction\":\"SW\",\"ratio\":3},{\"resource\":\"sheep\",\"location\":{\"x\":3,\"y\":-1},\"direction\":\"NW\",\"ratio\":2},{\"resource\":\"three\",\"location\":{\"x\":2,\"y\":1},\"direction\":\"NW\",\"ratio\":3},{\"resource\":\"three\",\"location\":{\"x\":0,\"y\":3},\"direction\":\"N\",\"ratio\":3},{\"resource\":\"brick\",\"location\":{\"x\":-2,\"y\":3},\"direction\":\"NE\",\"ratio\":2},{\"resource\":\"wood\",\"location\":{\"x\":-3,\"y\":2},\"direction\":\"NE\",\"ratio\":2},{\"resource\":\"three\",\"location\":{\"x\":-3,\"y\":0},\"direction\":\"SE\",\"ratio\":3},{\"resource\":\"wheat\",\"location\":{\"x\":-1,\"y\":-2},\"direction\":\"S\",\"ratio\":2}],\"roads\":[],\"settlements\":[],\"cities\":[],\"radius\":4,\"robber\":{\"x\":0,\"y\":-2}},\"players\":[{\"cities\":4,\"color\":\"blue\",\"discarded\":false,\"monuments\":0,\"name\":\"Matt\",\"newDevCards\":{\"yearOfPlenty\":0,\"monopoly\":0,\"soldier\":0,\"roadBuilding\":0,\"monument\":0},\"oldDevCards\":{\"yearOfPlenty\":0,\"monopoly\":0,\"soldier\":0,\"roadBuilding\":0,\"monument\":0},\"playerIndex\":0,\"playedDevCard\":false,\"playerID\":0,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"roads\":15,\"settlements\":5,\"soldiers\":0,\"victoryPoints\":0},{\"cities\":4,\"color\":\"green\",\"discarded\":false,\"monuments\":0,\"name\":\"Scott\",\"newDevCards\":{\"yearOfPlenty\":0,\"monopoly\":0,\"soldier\":0,\"roadBuilding\":0,\"monument\":0},\"oldDevCards\":{\"yearOfPlenty\":0,\"monopoly\":0,\"soldier\":0,\"roadBuilding\":0,\"monument\":0},\"playerIndex\":1,\"playedDevCard\":false,\"playerID\":1,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"roads\":15,\"settlements\":5,\"soldiers\":0,\"victoryPoints\":0},{\"cities\":4,\"color\":\"orange\",\"discarded\":false,\"monuments\":0,\"name\":\"Jan\",\"newDevCards\":{\"yearOfPlenty\":0,\"monopoly\":0,\"soldier\":0,\"roadBuilding\":0,\"monument\":0},\"oldDevCards\":{\"yearOfPlenty\":0,\"monopoly\":0,\"soldier\":0,\"roadBuilding\":0,\"monument\":0},\"playerIndex\":2,\"playedDevCard\":false,\"playerID\":2,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"roads\":15,\"settlements\":5,\"soldiers\":0,\"victoryPoints\":0},{\"cities\":4,\"color\":\"red\",\"discarded\":false,\"monuments\":0,\"name\":\"Garrett\",\"newDevCards\":{\"yearOfPlenty\":0,\"monopoly\":0,\"soldier\":0,\"roadBuilding\":0,\"monument\":0},\"oldDevCards\":{\"yearOfPlenty\":0,\"monopoly\":0,\"soldier\":0,\"roadBuilding\":0,\"monument\":0},\"playerIndex\":3,\"playedDevCard\":false,\"playerID\":3,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"roads\":15,\"settlements\":5,\"soldiers\":0,\"victoryPoints\":0}],\"turnTracker\":{\"currentTurn\":0,\"status\":\"FirstRound\",\"longestRoad\":-1,\"largestArmy\":-1},\"version\":0,\"winner\":-1}";
		
		assertEquals(expect,result);

	}
	
	@Test
	public void testModel_badGame() {
		String result = "";
		String expect = null;
		try {
			result = (new model()).execute(null, 500, 0);	
			
		} catch (HTTPBadRequest ex) {
			Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex); 
			fail();
		}
		assertEquals(null,result);
	}
	
	@Test
	public void testListAI() {
		String result = "";
		String expect = "[]";
		try {
			result = (new listAI()).execute(null, 0, 0);				
		} catch (HTTPBadRequest ex) {
			Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex); 
			fail();
		}
		assertEquals(expect,result);
	}
	
//MOVES TESTS
	
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
    public void testBuyDevCard() throws GetPlayerException{
        Command cmd = new buyDevCard();
        Model model = gic.getModels().getGame(1);
        
        int testPlayerHasResources = 3;
        int testPlayerNotHaveResources = 0;
        
        MoveRequest request = new MoveRequest("buyDevCard",testPlayerHasResources);
        ResourceList bankResources = model.getBank();
        
        int initialBrick = bankResources.getBrick();
        int initialOre = bankResources.getOre();
        int initialWheat = bankResources.getWheat();
        int initialSheep = bankResources.getSheep();
        int initialWood = bankResources.getWood();
        
        int initialBankMonopoly = model.getDeck().getMonopoly();
        int initialBankMonument = model.getDeck().getMonument();
        int initialBankRoadBuilding = model.getDeck().getRoadBuilding();
        int initialBankSoldier = model.getDeck().getSoldier();
        int initialBankYearOfPlenty = model.getDeck().getYearOfPlenty();

        int initialPlayerNEWMonopoly = model.getPlayer(3).getNewDevCards().getMonopoly();
        int initialPlayerNEWMonument = model.getPlayer(3).getNewDevCards().getMonument();
        int initialPlayerNEWRoadBuilding = model.getPlayer(3).getNewDevCards().getRoadBuilding();
        int initialPlayerNEWSoldier = model.getPlayer(3).getNewDevCards().getSoldier();
        int initialPlayerNEWYearOfPlenty = model.getPlayer(3).getNewDevCards().getYearOfPlenty();
        
        int initialPlayerMonopoly = model.getPlayer(3).getOldDevCards().getMonopoly();
        int initialPlayerMonument = model.getPlayer(3).getOldDevCards().getMonument();
        int initialPlayerRoadBuilding = model.getPlayer(3).getOldDevCards().getRoadBuilding();
        int initialPlayerSoldier = model.getPlayer(3).getOldDevCards().getSoldier();
        int initialPlayerYearOfPlenty = model.getPlayer(3).getOldDevCards().getYearOfPlenty();
        
        //Check that player does not have Development cards in their old Dev card list
        assertEquals(initialPlayerMonopoly,0);
        assertEquals(initialPlayerMonument,0);
        assertEquals(initialPlayerRoadBuilding,0);
        assertEquals(initialPlayerSoldier,0);
        assertEquals(initialPlayerYearOfPlenty,0);
        
        //Check that player does not have Development cards in their new Dev card list        
        assertEquals(initialPlayerNEWMonopoly,0);
        assertEquals(initialPlayerNEWMonument,0);
        assertEquals(initialPlayerNEWRoadBuilding,0);
        assertEquals(initialPlayerNEWSoldier,0);
        assertEquals(initialPlayerNEWYearOfPlenty,0);
        
        //Check that the Bank starts with the correct Dev Cards
        assertEquals(initialBankMonopoly,2);
        assertEquals(initialBankMonument,5);
        assertEquals(initialBankRoadBuilding,2);
        assertEquals(initialBankSoldier,14);
        assertEquals(initialBankYearOfPlenty,2);
        
        try {
            assertTrue(model.getPlayer(testPlayerHasResources).getResources().hasResources(new ResourceList(0,1,2,2,1)));
            // the version number is initialy 0 after we have initialized the map
            int versionNumber = model.getVersion();
            assertEquals(model.getVersion(),0);
            //Test while is not players turn
            cmd.execute(serializer.toJson(request),1,testPlayerHasResources);
            //Version number is the same because it was not the players turn. 
            assertTrue(versionNumber==0);
            
            //change turn to the correct player
            model.getTurnTracker().setCurrentTurn(3);
            //run again and the buyDevCardRequest should be succsessful
            cmd.execute(serializer.toJson(request),1,testPlayerHasResources);
            //Check that resources were discarded from the player correctly
            assertTrue(model.getPlayer(testPlayerHasResources).getResources().hasResources(new ResourceList(0,1,1,1,0)));
            //Check that resources were added to the bank correctly
            assertTrue(model.getBank().hasResources(new ResourceList(
            initialBrick,initialWood,initialSheep+1,initialWheat+1,initialOre+1)));
            
            //Check that no devcards were added to the OLD devcard list unless it is a monument
            int finalPlayerMonopoly = model.getPlayer(3).getOldDevCards().getMonopoly();
            int finalPlayerRoadBuilding = model.getPlayer(3).getOldDevCards().getRoadBuilding();
            int finalPlayerSoldier = model.getPlayer(3).getOldDevCards().getSoldier();
            int finalPlayerYearOfPlenty = model.getPlayer(3).getOldDevCards().getYearOfPlenty();
            
            assertEquals(finalPlayerMonopoly,0);
            assertEquals(finalPlayerRoadBuilding,0);
            assertEquals(finalPlayerSoldier,0);
            assertEquals(finalPlayerYearOfPlenty,0);
            
            //Get which dev card was added to the players list. by using canPlayDevCard
            // on each type, this isn't want canPlayDevCard would normally be used for but 
            // it comes in handy here. 
            DevCardType typeOfDevCard;
            
            DevCardList oldDevCards = model.getPlayer(3).getOldDevCards();
            DevCardList newDevCards = model.getPlayer(3).getNewDevCards();
            
            // use these ints to keep track of what the players final new devcard list should be.
            int amountSoldier;
            int amountMonument;
            int amountMonopoly;
            int amountRoadBuilding;
            int amountYOP;
            
            //Find which devcard they recieved. 
            if(newDevCards.canPlayDevCard(DevCardType.SOLDIER)){
                typeOfDevCard = DevCardType.SOLDIER;
                amountSoldier =1;
            }else{
               amountSoldier=0; 
            }
            if(oldDevCards.canPlayDevCard(DevCardType.MONUMENT)){
                typeOfDevCard = DevCardType.MONUMENT;
                amountMonument=1;
                // check that Monument was not added to the newDevCard list
                assertEquals(newDevCards.getMonument(),0);
            }else{
                amountMonument=0;
            }
            if(newDevCards.canPlayDevCard(DevCardType.MONOPOLY)){
                typeOfDevCard = DevCardType.MONOPOLY;
                amountMonopoly=1;
            }else{
                amountMonopoly=0;
            }          
            if(newDevCards.canPlayDevCard(DevCardType.YEAR_OF_PLENTY)){
                typeOfDevCard = DevCardType.YEAR_OF_PLENTY;
                amountYOP = 1;
            }else{
                amountYOP=0;
            }          
            if(newDevCards.canPlayDevCard(DevCardType.ROAD_BUILD)){
                typeOfDevCard = DevCardType.ROAD_BUILD;
                amountRoadBuilding = 1;
            }else{
               amountRoadBuilding = 0; 
            }
            
            // makes sure that only one devCard was added
            assertEquals(model.getPlayer(3).getNewDevCards().getMonopoly(),amountMonopoly);
            assertEquals(model.getPlayer(3).getNewDevCards().getMonument(),amountMonument);
            assertEquals(model.getPlayer(3).getNewDevCards().getSoldier(),amountSoldier);
            assertEquals(model.getPlayer(3).getNewDevCards().getYearOfPlenty(),amountYOP);
            assertEquals(model.getPlayer(3).getNewDevCards().getRoadBuilding(),amountRoadBuilding);
            
            // checks that the correct devCard was taken from the bank
            assertEquals(model.getDeck().getMonopoly(),initialBankMonopoly-amountMonopoly);
            assertEquals(model.getDeck().getMonument(),initialBankMonument-amountMonument);
            assertEquals(model.getDeck().getSoldier(),initialBankSoldier-amountSoldier);
            assertEquals(model.getDeck().getYearOfPlenty(),initialBankYearOfPlenty-amountYOP);
            assertEquals(model.getDeck().getRoadBuilding(),initialBankRoadBuilding-amountRoadBuilding);
            
            
   
        } catch (HTTPBadRequest ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GetPlayerException ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
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
        int numSettlements = p.getSettlements();
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
            fail();
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
            fail();
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
            fail();
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
            fail();
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
            fail();
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
            fail();
        }
        
        assertEquals(numOre - 3, m.getPlayer(0).getResources().getOre());
        assertEquals(numWheat - 2, m.getPlayer(0).getResources().getWheat());
        assertEquals(bankOre + 3, m.getBank().getOre());
        assertEquals(bankWheat + 2, m.getBank().getWheat());
        assertEquals(historyLength + 1, m.getLog().getLength());
        assertEquals(numCities - 1, m.getPlayer(0).getCities());
        assertEquals(numSettlements + 1, m.getPlayer(0).getSettlements());
        assertEquals(++version, m.getVersion());
    }

    @Test
    public void testBuildRoad() throws GetPlayerException {
        Command cmd = new buildRoad();
        
        //model is default post setup
        Model m = gic.getModels().getGame(1);
        int testPlayerIndex = 0;
        Player p = null;
        
        //build some extra roads
    	BuildRoadRequest r1 = new BuildRoadRequest(new EdgeLocation(new HexLocation(0,0), EdgeDirection.North), true);
    	r1.setPlayerIndex(0);
    	m.buildRoad(r1);
        
        EdgeLocation emptyEdge = new EdgeLocation(new HexLocation(0,-2), EdgeDirection.North);
        EdgeLocation opponentEdge = new EdgeLocation(new HexLocation(0,-1), EdgeDirection.SouthWest);
        EdgeLocation myEdge = new EdgeLocation(new HexLocation(-3,1), EdgeDirection.NorthEast);
        EdgeLocation waterEdge = new EdgeLocation(new HexLocation(-3,0), EdgeDirection.South);
        EdgeLocation attachOpponentRoadEdge = new EdgeLocation(new HexLocation(-2,2), EdgeDirection.South);
        EdgeLocation attachOpponentSettlementEdge = new EdgeLocation(new HexLocation(-1,0), EdgeDirection.North);
        
        //test with Player Index 1 (green)
        EdgeLocation validEdge = new EdgeLocation(new HexLocation(0, 1), EdgeDirection.South);
        EdgeLocation buildThroughOpponentEdge = new EdgeLocation(new HexLocation(0, -1), EdgeDirection.SouthEast);
        EdgeLocation validFreeEdge = new EdgeLocation(new HexLocation(0,-1), EdgeDirection.NorthWest);
        
        int historyLength = m.getLog().getLength();
        int version = m.getVersion();
        int bankBrick = m.getBank().getBrick();
        int bankWood = m.getBank().getWood();
        
        //test not enough resources ( 1 brick 1 wood)
        p = m.getPlayer(0);
        int numBrick = p.getResources().getBrick();
        int numWood = p.getResources().getWood();
        int numRoads = p.getRoads();
        boolean free = false;
        
        p.getResources().setBrick(0);
        m.getBank().addResource(ResourceType.BRICK, numBrick);
        bankBrick += numBrick;
        numBrick = 0;

        p.getResources().setWood(0);
        m.getBank().addResource(ResourceType.WOOD, numWood);
        bankWood += numWood;
        numWood = 0;
        
        BuildRoadRequest req = new BuildRoadRequest(validEdge, free);
        req.setType("buildRoad");
        req.setPlayerIndex(0);
        try {
            cmd.execute(serializer.toJson(req),1,p.getPlayerID());
        } catch (HTTPBadRequest ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        
        assertEquals(numBrick, m.getPlayer(0).getResources().getBrick());
        assertEquals(numWood, m.getPlayer(0).getResources().getWood());
        assertEquals(bankBrick, m.getBank().getBrick());
        assertEquals(bankWood, m.getBank().getWood());
        assertEquals(historyLength, m.getLog().getLength());
        assertEquals(numRoads, m.getPlayer(0).getRoads());
        assertEquals(version, m.getVersion());
        
        //test enough resources
        p.getResources().setBrick(1);
        numBrick = 1;
        m.getBank().subtractResource(ResourceType.BRICK, numBrick);
        bankBrick -= numBrick;
        
        p.getResources().setWood(1);
        numWood = 1;
        m.getBank().subtractResource(ResourceType.WOOD, numWood);
        bankWood -= numWood;
        
        //test not my turn
        m.getTurnTracker().setCurrentTurn(1);
        
        req = new BuildRoadRequest(validEdge, free);
        req.setType("buildRoad");
        req.setPlayerIndex(0);
        try {
            cmd.execute(serializer.toJson(req),1,p.getPlayerID());
        } catch (HTTPBadRequest ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        
        assertEquals(numBrick, m.getPlayer(0).getResources().getBrick());
        assertEquals(numWood, m.getPlayer(0).getResources().getWood());
        assertEquals(bankBrick, m.getBank().getBrick());
        assertEquals(bankWood, m.getBank().getWood());
        assertEquals(historyLength, m.getLog().getLength());
        assertEquals(numRoads, m.getPlayer(0).getRoads());
        assertEquals(version, m.getVersion());
        
        m.getTurnTracker().setCurrentTurn(0);
        
        //test floating road
        req = new BuildRoadRequest(emptyEdge, free);
        req.setType("buildRoad");
        req.setPlayerIndex(0);
        try {
            cmd.execute(serializer.toJson(req),1,p.getPlayerID());
        } catch (HTTPBadRequest ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        
        assertEquals(numBrick, m.getPlayer(0).getResources().getBrick());
        assertEquals(numWood, m.getPlayer(0).getResources().getWood());
        assertEquals(bankBrick, m.getBank().getBrick());
        assertEquals(bankWood, m.getBank().getWood());
        assertEquals(historyLength, m.getLog().getLength());
        assertEquals(numRoads, m.getPlayer(0).getRoads());
        assertEquals(version, m.getVersion());
        
        //test on top of opponent road
        req = new BuildRoadRequest(opponentEdge, free);
        req.setType("buildRoad");
        req.setPlayerIndex(0);
        try {
            cmd.execute(serializer.toJson(req),1,p.getPlayerID());
        } catch (HTTPBadRequest ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        
        assertEquals(numBrick, m.getPlayer(0).getResources().getBrick());
        assertEquals(numWood, m.getPlayer(0).getResources().getWood());
        assertEquals(bankBrick, m.getBank().getBrick());
        assertEquals(bankWood, m.getBank().getWood());
        assertEquals(historyLength, m.getLog().getLength());
        assertEquals(numRoads, m.getPlayer(0).getRoads());
        assertEquals(version, m.getVersion());
            
        //test on top of own road
        req = new BuildRoadRequest(myEdge, free);
        req.setType("buildRoad");
        req.setPlayerIndex(0);
        try {
            cmd.execute(serializer.toJson(req),1,p.getPlayerID());
        } catch (HTTPBadRequest ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        
        assertEquals(numBrick, m.getPlayer(0).getResources().getBrick());
        assertEquals(numWood, m.getPlayer(0).getResources().getWood());
        assertEquals(bankBrick, m.getBank().getBrick());
        assertEquals(bankWood, m.getBank().getWood());
        assertEquals(historyLength, m.getLog().getLength());
        assertEquals(numRoads, m.getPlayer(0).getRoads());
        assertEquals(version, m.getVersion());
        
        //test in water
        req = new BuildRoadRequest(waterEdge, free);
        req.setType("buildRoad");
        req.setPlayerIndex(0);
        try {
            cmd.execute(serializer.toJson(req),1,p.getPlayerID());
        } catch (HTTPBadRequest ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        
        assertEquals(numBrick, m.getPlayer(0).getResources().getBrick());
        assertEquals(numWood, m.getPlayer(0).getResources().getWood());
        assertEquals(bankBrick, m.getBank().getBrick());
        assertEquals(bankWood, m.getBank().getWood());
        assertEquals(historyLength, m.getLog().getLength());
        assertEquals(numRoads, m.getPlayer(0).getRoads());
        assertEquals(version, m.getVersion());
        
        //test not attached to own road
        req = new BuildRoadRequest(attachOpponentRoadEdge, free);
        req.setType("buildRoad");
        req.setPlayerIndex(0);
        try {
            cmd.execute(serializer.toJson(req),1,p.getPlayerID());
        } catch (HTTPBadRequest ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        
        assertEquals(numBrick, m.getPlayer(0).getResources().getBrick());
        assertEquals(numWood, m.getPlayer(0).getResources().getWood());
        assertEquals(bankBrick, m.getBank().getBrick());
        assertEquals(bankWood, m.getBank().getWood());
        assertEquals(historyLength, m.getLog().getLength());
        assertEquals(numRoads, m.getPlayer(0).getRoads());
        assertEquals(version, m.getVersion());
        
        //test not attached to own settlement
        p.setSettlements(0);
        req = new BuildRoadRequest(attachOpponentSettlementEdge, free);
        req.setType("buildRoad");
        req.setPlayerIndex(0);
        try {
            cmd.execute(serializer.toJson(req),1,p.getPlayerID());
        } catch (HTTPBadRequest ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        
        assertEquals(numBrick, m.getPlayer(0).getResources().getBrick());
        assertEquals(numWood, m.getPlayer(0).getResources().getWood());
        assertEquals(bankBrick, m.getBank().getBrick());
        assertEquals(bankWood, m.getBank().getWood());
        assertEquals(historyLength, m.getLog().getLength());
        assertEquals(numRoads, m.getPlayer(0).getRoads());
        assertEquals(version, m.getVersion());
        
        //SWITCH TO PLAYER INDEX 1
        p = m.getPlayer(1);
        m.getTurnTracker().setCurrentTurn(1);
        numRoads = p.getRoads();
        numBrick = p.getResources().getBrick();
        numWood = p.getResources().getWood();
        
        p.getResources().setBrick(0);
        m.getBank().addResource(ResourceType.BRICK, numBrick);
        bankBrick += numBrick;
        numBrick = 0;

        p.getResources().setWood(0);
        m.getBank().addResource(ResourceType.WOOD, numWood);
        bankWood += numWood;
        numWood = 0;
        
        p.getResources().setBrick(2);
        numBrick = 2;
        m.getBank().subtractResource(ResourceType.BRICK, numBrick);
        bankBrick -= numBrick;
        
        p.getResources().setWood(2);
        numWood = 2;
        m.getBank().subtractResource(ResourceType.WOOD, numWood);
        bankWood -= numWood;
        
        if(numBrick == 0) {
            p.getResources().addResource(ResourceType.BRICK, 1);
            numBrick = 1;
            m.getBank().subtractResource(ResourceType.BRICK, 1);
            bankBrick--;
        }
        
        if(numWood == 0) {
            p.getResources().addResource(ResourceType.WOOD, 1);
            numWood = 1;
            m.getBank().subtractResource(ResourceType.WOOD, 1);
            bankWood--;
        }
        
        //test no more roads
        int remainingRoads = numRoads;
        p.setRoads(0);
        numRoads = 0;
        
        req = new BuildRoadRequest(validEdge, free);
        req.setType("buildRoad");
        req.setPlayerIndex(1);
        try {
            cmd.execute(serializer.toJson(req),1,p.getPlayerID());
        } catch (HTTPBadRequest ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        
        assertEquals(numBrick, m.getPlayer(1).getResources().getBrick());
        assertEquals(numWood, m.getPlayer(1).getResources().getWood());
        assertEquals(bankBrick, m.getBank().getBrick());
        assertEquals(bankWood, m.getBank().getWood());
        assertEquals(historyLength, m.getLog().getLength());
        assertEquals(numRoads, m.getPlayer(1).getRoads());
        assertEquals(version, m.getVersion());
        
        numRoads = remainingRoads;
        p.setRoads(remainingRoads);
        
        //test valid road placement
        req = new BuildRoadRequest(validEdge, free);
        req.setType("buildRoad");
        req.setPlayerIndex(1);
        try {
            cmd.execute(serializer.toJson(req),1,p.getPlayerID());
        } catch (HTTPBadRequest ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        
        assertEquals(--numBrick, m.getPlayer(1).getResources().getBrick());
        assertEquals(--numWood, m.getPlayer(1).getResources().getWood());
        assertEquals(++bankBrick, m.getBank().getBrick());
        assertEquals(++bankWood, m.getBank().getWood());
        assertEquals(++historyLength, m.getLog().getLength());
        assertEquals(--numRoads, m.getPlayer(1).getRoads());
        assertEquals(++version, m.getVersion());
        
        //test build through opponent settlement
        //add resources so that command is invalid because of placement 
        
        req = new BuildRoadRequest(buildThroughOpponentEdge, free);
        req.setType("buildRoad");
        req.setPlayerIndex(1);
        try {
            cmd.execute(serializer.toJson(req),1,p.getPlayerID());
        } catch (HTTPBadRequest ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        
        assertEquals(numBrick, m.getPlayer(1).getResources().getBrick());
        assertEquals(numWood, m.getPlayer(1).getResources().getWood());
        assertEquals(bankBrick, m.getBank().getBrick());
        assertEquals(bankWood, m.getBank().getWood());
        assertEquals(historyLength, m.getLog().getLength());
        assertEquals(numRoads, m.getPlayer(1).getRoads());
        assertEquals(version, m.getVersion());
        
        //test free
        free = true;
        
        req = new BuildRoadRequest(validFreeEdge, free);
        req.setType("buildRoad");
        req.setPlayerIndex(1);
        try {
            cmd.execute(serializer.toJson(req),1,p.getPlayerID());
        } catch (HTTPBadRequest ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        
        assertEquals(numBrick, m.getPlayer(1).getResources().getBrick());
        assertEquals(numWood, m.getPlayer(1).getResources().getWood());
        assertEquals(bankBrick, m.getBank().getBrick());
        assertEquals(bankWood, m.getBank().getWood());
        assertEquals(++historyLength, m.getLog().getLength());
        assertEquals(--numRoads, m.getPlayer(1).getRoads());
        assertEquals(++version, m.getVersion());
    }
    
    @Test
    public void testBuildSettlement() throws GetPlayerException {
        Command cmd = new buildSettlement();
        
        //model is default post setup
        Model m = gic.getModels().getGame(1);
        int testPlayerIndex = 0;
        Player p = null;
        
        //add additional roads for free
    	BuildRoadRequest r1 = new BuildRoadRequest(new EdgeLocation(new HexLocation(-2,0), EdgeDirection.South), true);
    	r1.setPlayerIndex(0);
    	m.buildRoad(r1);
        
        BuildRoadRequest r2 = new BuildRoadRequest(new EdgeLocation(new HexLocation(-2,0), EdgeDirection.SouthEast), true);
    	r1.setPlayerIndex(0);
    	m.buildRoad(r2);
        
        BuildRoadRequest r3 = new BuildRoadRequest(new EdgeLocation(new HexLocation(0,2), EdgeDirection.South), true);
    	r1.setPlayerIndex(0);
    	m.buildRoad(r3);
            
        VertexLocation validVertex = new VertexLocation(new HexLocation(-2,0), VertexDirection.SouthEast);
        VertexLocation opponentRoadVertex = new VertexLocation(new HexLocation(0, 1), VertexDirection.SouthEast);
        VertexLocation emptyVertex = new VertexLocation(new HexLocation(2,0), VertexDirection.East);
        VertexLocation waterVertex = new VertexLocation(new HexLocation(3,0), VertexDirection.East);
        VertexLocation tooCloseVertex = new VertexLocation(new HexLocation(-2,0), VertexDirection.East);
        VertexLocation settlementVertex = new VertexLocation(new HexLocation(0,0), VertexDirection.NorthEast);
        VertexLocation cityVertex = new VertexLocation(new HexLocation(0, 2), VertexDirection.West);
        VertexLocation freeVertex = new VertexLocation(new HexLocation(0, 2), VertexDirection.SouthWest);
          
        int historyLength = m.getLog().getLength();
        int version = m.getVersion();
        int bankBrick = m.getBank().getBrick();
        int bankWood = m.getBank().getWood();   
        int bankSheep = m.getBank().getSheep();
        int bankWheat = m.getBank().getWheat();
        
        //test not enough resources
        p = m.getPlayer(0);
        int numBrick = p.getResources().getBrick();
        int numWheat = p.getResources().getWheat();
        int numWood = p.getResources().getWood();
        int numSheep = p.getResources().getSheep();
        int numSettlements = p.getSettlements();
        boolean free = false;
        
        p.getResources().setBrick(0);
        m.getBank().addResource(ResourceType.BRICK, numBrick);
        bankBrick += numBrick;
        numBrick = 0;
        
        p.getResources().setWheat(0);
        m.getBank().addResource(ResourceType.WHEAT, numWheat);
        bankWheat += numWheat;
        numWheat = 0;
        
        p.getResources().setWood(0);
        m.getBank().addResource(ResourceType.WOOD, numWood);
        bankWood += numWood;
        numWood = 0;
        
        p.getResources().setSheep(0);
        m.getBank().addResource(ResourceType.SHEEP, numSheep);
        bankSheep += numSheep;
        numSheep = 0;
      
        BuildSettlementRequest req = new BuildSettlementRequest(validVertex, free);
        req.setType("buildSettlement");
        req.setPlayerIndex(0);
        try {
            cmd.execute(serializer.toJson(req),1,p.getPlayerID());
        } catch (HTTPBadRequest ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        
        assertEquals(numBrick, m.getPlayer(0).getResources().getBrick());
        assertEquals(numWheat, m.getPlayer(0).getResources().getWheat());
        assertEquals(numSheep, m.getPlayer(0).getResources().getSheep());
        assertEquals(numWood, m.getPlayer(0).getResources().getWood());
        assertEquals(bankBrick, m.getBank().getBrick());
        assertEquals(bankWheat, m.getBank().getWheat());  
        assertEquals(bankSheep, m.getBank().getSheep());
        assertEquals(bankWood, m.getBank().getWood());
        assertEquals(historyLength, m.getLog().getLength());
        assertEquals(numSettlements, m.getPlayer(0).getSettlements());
        assertEquals(version, m.getVersion());
        
        //test enough resources (for 1 road)
        p.getResources().setBrick(1);
        numBrick = 1;
        m.getBank().subtractResource(ResourceType.BRICK, numBrick);
        bankBrick -= numBrick;
        
        p.getResources().setWheat(1);
        numWheat = 1;
        m.getBank().subtractResource(ResourceType.WHEAT, numWheat);
        bankWheat -= numWheat;
        
        p.getResources().setWood(1);
        numWood = 1;
        m.getBank().subtractResource(ResourceType.WOOD, numWood);
        bankWood -= numWood;
        
        p.getResources().setSheep(1);
        numSheep = 1;
        m.getBank().subtractResource(ResourceType.SHEEP, numSheep);
        bankSheep -= numSheep;
            
        //test not my turn
        m.getTurnTracker().setCurrentTurn(1);
        
        req = new BuildSettlementRequest(validVertex, free);
        req.setType("buildSettlement");
        req.setPlayerIndex(0);
        try {
            cmd.execute(serializer.toJson(req),1,p.getPlayerID());
        } catch (HTTPBadRequest ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }

        m.getTurnTracker().setCurrentTurn(0);
        
        assertEquals(numBrick, m.getPlayer(0).getResources().getBrick());
        assertEquals(numWheat, m.getPlayer(0).getResources().getWheat());
        assertEquals(numSheep, m.getPlayer(0).getResources().getSheep());
        assertEquals(numWood, m.getPlayer(0).getResources().getWood());
        assertEquals(bankBrick, m.getBank().getBrick());
        assertEquals(bankWheat, m.getBank().getWheat());  
        assertEquals(bankSheep, m.getBank().getSheep());
        assertEquals(bankWood, m.getBank().getWood());
        assertEquals(historyLength, m.getLog().getLength());
        assertEquals(numSettlements, m.getPlayer(0).getSettlements());
        assertEquals(version, m.getVersion());
        
        //test not on my road
        req = new BuildSettlementRequest(opponentRoadVertex, free);
        req.setType("buildSettlement");
        req.setPlayerIndex(0);
        try {
            cmd.execute(serializer.toJson(req),1,p.getPlayerID());
        } catch (HTTPBadRequest ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        
        assertEquals(numBrick, m.getPlayer(0).getResources().getBrick());
        assertEquals(numWheat, m.getPlayer(0).getResources().getWheat());
        assertEquals(numSheep, m.getPlayer(0).getResources().getSheep());
        assertEquals(numWood, m.getPlayer(0).getResources().getWood());
        assertEquals(bankBrick, m.getBank().getBrick());
        assertEquals(bankWheat, m.getBank().getWheat());  
        assertEquals(bankSheep, m.getBank().getSheep());
        assertEquals(bankWood, m.getBank().getWood());
        assertEquals(historyLength, m.getLog().getLength());
        assertEquals(numSettlements, m.getPlayer(0).getSettlements());
        assertEquals(version, m.getVersion());
        
        //test floating settlement
        req = new BuildSettlementRequest(emptyVertex, free);
        req.setType("buildSettlement");
        req.setPlayerIndex(0);
        try {
            cmd.execute(serializer.toJson(req),1,p.getPlayerID());
        } catch (HTTPBadRequest ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        
        assertEquals(numBrick, m.getPlayer(0).getResources().getBrick());
        assertEquals(numWheat, m.getPlayer(0).getResources().getWheat());
        assertEquals(numSheep, m.getPlayer(0).getResources().getSheep());
        assertEquals(numWood, m.getPlayer(0).getResources().getWood());
        assertEquals(bankBrick, m.getBank().getBrick());
        assertEquals(bankWheat, m.getBank().getWheat());  
        assertEquals(bankSheep, m.getBank().getSheep());
        assertEquals(bankWood, m.getBank().getWood());
        assertEquals(historyLength, m.getLog().getLength());
        assertEquals(numSettlements, m.getPlayer(0).getSettlements());
        assertEquals(version, m.getVersion());
        
        //test on water
        req = new BuildSettlementRequest(waterVertex, free);
        req.setType("buildSettlement");
        req.setPlayerIndex(0);
        try {
            cmd.execute(serializer.toJson(req),1,p.getPlayerID());
        } catch (HTTPBadRequest ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        
        assertEquals(numBrick, m.getPlayer(0).getResources().getBrick());
        assertEquals(numWheat, m.getPlayer(0).getResources().getWheat());
        assertEquals(numSheep, m.getPlayer(0).getResources().getSheep());
        assertEquals(numWood, m.getPlayer(0).getResources().getWood());
        assertEquals(bankBrick, m.getBank().getBrick());
        assertEquals(bankWheat, m.getBank().getWheat());  
        assertEquals(bankSheep, m.getBank().getSheep());
        assertEquals(bankWood, m.getBank().getWood());
        assertEquals(historyLength, m.getLog().getLength());
        assertEquals(numSettlements, m.getPlayer(0).getSettlements());
        assertEquals(version, m.getVersion());
        
        //test too close
        req = new BuildSettlementRequest(tooCloseVertex, free);
        req.setType("buildSettlement");
        req.setPlayerIndex(0);
        try {
            cmd.execute(serializer.toJson(req),1,p.getPlayerID());
        } catch (HTTPBadRequest ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        
        assertEquals(numBrick, m.getPlayer(0).getResources().getBrick());
        assertEquals(numWheat, m.getPlayer(0).getResources().getWheat());
        assertEquals(numSheep, m.getPlayer(0).getResources().getSheep());
        assertEquals(numWood, m.getPlayer(0).getResources().getWood());
        assertEquals(bankBrick, m.getBank().getBrick());
        assertEquals(bankWheat, m.getBank().getWheat());  
        assertEquals(bankSheep, m.getBank().getSheep());
        assertEquals(bankWood, m.getBank().getWood());
        assertEquals(historyLength, m.getLog().getLength());
        assertEquals(numSettlements, m.getPlayer(0).getSettlements());
        assertEquals(version, m.getVersion());
        
        //test on my settlement
        req = new BuildSettlementRequest(settlementVertex, free);
        req.setType("buildSettlement");
        req.setPlayerIndex(0);
        try {
            cmd.execute(serializer.toJson(req),1,p.getPlayerID());
        } catch (HTTPBadRequest ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        
        assertEquals(numBrick, m.getPlayer(0).getResources().getBrick());
        assertEquals(numWheat, m.getPlayer(0).getResources().getWheat());
        assertEquals(numSheep, m.getPlayer(0).getResources().getSheep());
        assertEquals(numWood, m.getPlayer(0).getResources().getWood());
        assertEquals(bankBrick, m.getBank().getBrick());
        assertEquals(bankWheat, m.getBank().getWheat());  
        assertEquals(bankSheep, m.getBank().getSheep());
        assertEquals(bankWood, m.getBank().getWood());
        assertEquals(historyLength, m.getLog().getLength());
        assertEquals(numSettlements, m.getPlayer(0).getSettlements());
        assertEquals(version, m.getVersion());
        
        //test on my city
        p.setCities(p.getCities() - 1);
        p.setSettlements(p.getSettlements() + 1);
        m.getMap().addCity(new City(0, cityVertex));
        numSettlements++;
        
        req = new BuildSettlementRequest(cityVertex, free);
        req.setType("buildSettlement");
        req.setPlayerIndex(0);
        try {
            cmd.execute(serializer.toJson(req),1,p.getPlayerID());
        } catch (HTTPBadRequest ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        
        assertEquals(numBrick, m.getPlayer(0).getResources().getBrick());
        assertEquals(numWheat, m.getPlayer(0).getResources().getWheat());
        assertEquals(numSheep, m.getPlayer(0).getResources().getSheep());
        assertEquals(numWood, m.getPlayer(0).getResources().getWood());
        assertEquals(bankBrick, m.getBank().getBrick());
        assertEquals(bankWheat, m.getBank().getWheat());  
        assertEquals(bankSheep, m.getBank().getSheep());
        assertEquals(bankWood, m.getBank().getWood());
        assertEquals(historyLength, m.getLog().getLength());
        assertEquals(numSettlements, m.getPlayer(0).getSettlements());
        assertEquals(version, m.getVersion());
        
        //test no more settlements
        int remaining = numSettlements;
        p.setSettlements(0);
        numSettlements = 0;
        
        req = new BuildSettlementRequest(validVertex, free);
        req.setType("buildSettlement");
        req.setPlayerIndex(0);
        try {
            cmd.execute(serializer.toJson(req),1,p.getPlayerID());
        } catch (HTTPBadRequest ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        
        assertEquals(numBrick, m.getPlayer(0).getResources().getBrick());
        assertEquals(numWheat, m.getPlayer(0).getResources().getWheat());
        assertEquals(numSheep, m.getPlayer(0).getResources().getSheep());
        assertEquals(numWood, m.getPlayer(0).getResources().getWood());
        assertEquals(bankBrick, m.getBank().getBrick());
        assertEquals(bankWheat, m.getBank().getWheat());  
        assertEquals(bankSheep, m.getBank().getSheep());
        assertEquals(bankWood, m.getBank().getWood());
        assertEquals(historyLength, m.getLog().getLength());
        assertEquals(numSettlements, m.getPlayer(0).getSettlements());
        assertEquals(version, m.getVersion());
        
        p.setSettlements(remaining);
        numSettlements = remaining;
        
        //test valid
        req = new BuildSettlementRequest(validVertex, free);
        req.setType("buildSettlement");
        req.setPlayerIndex(0);
        try {
            cmd.execute(serializer.toJson(req),1,p.getPlayerID());
        } catch (HTTPBadRequest ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        
        assertEquals(--numBrick, m.getPlayer(0).getResources().getBrick());
        assertEquals(--numWheat, m.getPlayer(0).getResources().getWheat());
        assertEquals(--numSheep, m.getPlayer(0).getResources().getSheep());
        assertEquals(--numWood, m.getPlayer(0).getResources().getWood());
        assertEquals(++bankBrick, m.getBank().getBrick());
        assertEquals(++bankWheat, m.getBank().getWheat());  
        assertEquals(++bankSheep, m.getBank().getSheep());
        assertEquals(++bankWood, m.getBank().getWood());
        assertEquals(++historyLength, m.getLog().getLength());
        assertEquals(--numSettlements, m.getPlayer(0).getSettlements());
        assertEquals(++version, m.getVersion());
        
        //test free
        free = true;
        m.getMap().getSettlements().remove(m.getMap().indexOfSettlementAt(validVertex));
        p.setSettlements(p.getSettlements() + 1);
        numSettlements++;
        
        req = new BuildSettlementRequest(validVertex, free);
        req.setType("buildSettlement");
        req.setPlayerIndex(0);
        try {
            cmd.execute(serializer.toJson(req),1,p.getPlayerID());
        } catch (HTTPBadRequest ex) {
            Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        
        assertEquals(numBrick, m.getPlayer(0).getResources().getBrick());
        assertEquals(numWheat, m.getPlayer(0).getResources().getWheat());
        assertEquals(numSheep, m.getPlayer(0).getResources().getSheep());
        assertEquals(numWood, m.getPlayer(0).getResources().getWood());
        assertEquals(bankBrick, m.getBank().getBrick());
        assertEquals(bankWheat, m.getBank().getWheat());  
        assertEquals(bankSheep, m.getBank().getSheep());
        assertEquals(bankWood, m.getBank().getWood());
        assertEquals(++historyLength, m.getLog().getLength());
        assertEquals(--numSettlements, m.getPlayer(0).getSettlements());
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
    	//TEST VALID CASE WITH NORMAL MESSAGE
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
    
    @Test
    public void testDiscardCards() throws HTTPBadRequest, GetPlayerException{
        
        Model model = gic.getModels().getGame(1);
                
        //Creating two cases where players don't need to discard and do need to discard
        int mustDiscardIndex = 3;
        int noNeedToDiscardIndex = 0;
        
        //These are used to be sure the correct resources are added to the bank
        ResourceList bankResources = model.getBank();
        int initialBrick = bankResources.getBrick();
        int initialOre = bankResources.getOre();
        int initialWheat = bankResources.getWheat();
        int initialSheep = bankResources.getSheep();
        int initialWood = bankResources.getWood();
        //These are resources in the hand of the player who needs to discard
        model.getPlayer(mustDiscardIndex).setResources(new ResourceList(0,2,2,3,2));
        ResourceList playerDiscardsResources = model.getPlayer(mustDiscardIndex).getResources();
        ResourceList playerDiscardsResourcesInitial = playerDiscardsResources.copy();
        int initialDiscardBrick = playerDiscardsResources.getBrick();
        int initialDiscadOre = playerDiscardsResources.getOre();
        int initialDiscardWheat = playerDiscardsResources.getWheat();
        int initialDiscardSheep = playerDiscardsResources.getSheep();
        int initialDiscardWood = playerDiscardsResources.getWood();
        

        //Creating two cases where players don't need to discard and do need to discard
        ResourceList discardCards = new ResourceList(0,1,1,1,1);
        ResourceList notDiscardedCards = new ResourceList(0,0,0,1,1);
        
        DiscardCardsRequest  mustDiscardRequest = new DiscardCardsRequest(mustDiscardIndex,discardCards);
        
        //Verify the players Initial Resources, which sum to greater than 7
        assertEquals(initialDiscardBrick,0);
        assertEquals(initialDiscadOre,2);
        assertEquals(initialDiscardWheat,3);
        assertEquals(initialDiscardSheep,2);
        assertEquals(initialDiscardWood,2);
        
        model.getTurnTracker().setStatus(TurnStatus.DISCARDING);
        // Try to discard cards with a player that has more than 7 cards
        Command cmd = new discardCards();
        cmd.execute(serializer.toJson(mustDiscardRequest),1,mustDiscardIndex);
        
        //Check that the correct resources are added to the bank
        assertEquals(initialBrick+discardCards.getBrick(),model.getBank().getBrick());
        assertEquals(initialOre+discardCards.getOre(),model.getBank().getOre());
        assertEquals(initialWheat+discardCards.getWheat(),model.getBank().getWheat());
        assertEquals(initialSheep+discardCards.getSheep(),model.getBank().getSheep());
        assertEquals(initialWood+discardCards.getWood(),model.getBank().getWood());
        
        //check that Correct resources are removed from the player's resources
        assertEquals(playerDiscardsResources,new ResourceList(playerDiscardsResourcesInitial.getBrick()-0,
        playerDiscardsResourcesInitial.getWood()-1,playerDiscardsResourcesInitial.getSheep()-1,
        playerDiscardsResourcesInitial.getWheat()-1,playerDiscardsResourcesInitial.getOre()-1));

        //Check if player tries to discard cards that they do not have
        //set resources in players hand to greater than 7
        model.getPlayer(mustDiscardIndex).setResources(new ResourceList(0,2,2,3,2));
        DiscardCardsRequest  mustDiscardBadRequest = new DiscardCardsRequest(mustDiscardIndex,new ResourceList(1,1,1,1,1));
        // Verify the initial Version number, which is 1 because we have updated once by
        // succesfully discarding cards
        assertEquals(model.getVersion(),1);
        
        //execute bad Command, version number should not update
        cmd.execute(serializer.toJson(mustDiscardBadRequest),1,mustDiscardIndex);

        //Verify that version number does not update
        assertEquals(model.getVersion(),1);
        
        // When the player has less than 7 cards
        DiscardCardsRequest  noNeedToDiscardRequest = new DiscardCardsRequest(noNeedToDiscardIndex,notDiscardedCards);
        //Save to check that the resources are not changed
        ResourceList initialFailToDiscardResources = notDiscardedCards.copy();
        //Execute the bad request
        cmd.execute(serializer.toJson(noNeedToDiscardRequest),1,noNeedToDiscardIndex);
        //check that version number is the same
        assertEquals(model.getVersion(),1);
    }
    
    @Test
    public void testAcceptTrade(){
       fail(); 
    }
    
    @Test
    public void testRoadBuilding(){
       fail(); 
    }
    
    @Test
    public void testSoldier(){
       fail(); 
    }
    
    @Test
    public void testFinishTurn(){
       fail(); 
    }
    
    @Test
    public void testMaritimeTrade(){
       fail(); 
    }
    
    @Test
    public void testOfferTrade(){
       fail(); 
    }
    
    @Test
    public void testRobPlayer(){
       fail(); 
    }
    
    @Test
    public void testRollNumber_valid_twoSettlements_oneHex() {
    	int gameIndex = 1;
    	Model m = gic.getModels().getGame(gameIndex);
        Player matt = null;
        Player scott = null;
        Player jan = null;
        Player garrett = null;
    	
    	try {
	        matt = m.getPlayer(0); //BLUE
	        scott = m.getPlayer(1); //GREEN
	        jan = m.getPlayer(2); //ORANGE
	        garrett = m.getPlayer(3); //RED
	    } catch (GetPlayerException ex) {
	        Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
	        fail();
	    }
    	
    	/**
    	 * ROLL 11
    	 * Garrett gets 2 wheat
    	 * Jan gets 1 wood
    	 */
    	ResourceList mattBefore = matt.getResources().copy();
    	ResourceList garBefore = garrett.getResources().copy();
    	ResourceList scottBefore = scott.getResources().copy();
    	ResourceList janBefore = jan.getResources().copy();
    	int oldVersion = m.getVersion();
    	int currentTurn = m.getTurnTracker().getCurrentTurn();
    	
    	RollNumberRequest rnReq = new RollNumberRequest(currentTurn, 11);
    	
    	//Execute command
    	Command rn = new rollNumber();
    	try {
    		rn.execute(serializer.toJson(rnReq), gameIndex, currentTurn);
    	} catch( HTTPBadRequest ex) {
    		Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
    		fail();
    	}
    	
    	//compare after command execution
    	ResourceList mattAfter = matt.getResources();
    	ResourceList garAfter = garrett.getResources();
    	ResourceList scottAfter = scott.getResources();
    	ResourceList janAfter = jan.getResources();
    	int newVersion = m.getVersion();
    	
    	//manually update resource to match expected result
    	garBefore.addResource(ResourceType.WHEAT, 2);
    	janBefore.addResource(ResourceType.WOOD, 1);
    	
    	assertEquals(mattBefore, mattAfter);
    	assertEquals(garBefore, garAfter);
    	assertEquals(scottBefore, scottAfter);
    	assertEquals(janBefore, janAfter);
    	assertEquals(oldVersion + 1, newVersion);
    }
    
    @Test
    public void testRollNumber_valid_multHexes_onePlayerPerHex() {
    	int gameIndex = 1;
    	Model m = gic.getModels().getGame(gameIndex);
        Player matt = null;
        Player scott = null;
        Player jan = null;
        Player garrett = null;
    	
    	try {
	        matt = m.getPlayer(0); //BLUE
	        scott = m.getPlayer(1); //GREEN
	        jan = m.getPlayer(2); //ORANGE
	        garrett = m.getPlayer(3); //RED
	    } catch (GetPlayerException ex) {
	        Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
	        fail();
	    }
    	
    	/**
    	 * ROLL 9
    	 * Garrett and Scott get one sheep
    	 * Jan and Garrett get one ore
    	 */
    	ResourceList mattBefore = matt.getResources().copy();
    	ResourceList garBefore = garrett.getResources().copy();
    	ResourceList scottBefore = scott.getResources().copy();
    	ResourceList janBefore = jan.getResources().copy();
    	int oldVersion = m.getVersion();
    	int currentTurn = m.getTurnTracker().getCurrentTurn();
    	
    	RollNumberRequest rnReq = new RollNumberRequest(currentTurn, 9);
    	
    	//Execute command
    	Command rn = new rollNumber();
    	try {
    		rn.execute(serializer.toJson(rnReq), gameIndex, currentTurn);
    	} catch( HTTPBadRequest ex) {
    		Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
    		fail();
    	}
    	
    	//compare after command execution
    	ResourceList mattAfter = matt.getResources();
    	ResourceList garAfter = garrett.getResources();
    	ResourceList scottAfter = scott.getResources();
    	ResourceList janAfter = jan.getResources();
    	int newVersion = m.getVersion();
    	
    	//manually update resource to match expected result
    	garBefore.addResource(ResourceType.SHEEP, 1);
    	garBefore.addResource(ResourceType.ORE, 1);
    	
    	scottBefore.addResource(ResourceType.SHEEP, 1);
    	janBefore.addResource(ResourceType.ORE, 1);
    	
    	assertEquals(mattBefore, mattAfter);
    	assertEquals(garBefore, garAfter);
    	assertEquals(scottBefore, scottAfter);
    	assertEquals(janBefore, janAfter);
    	assertEquals(oldVersion + 1, newVersion);
    }
    
    @Test
    public void testRollNumber_valid_rollSeven() {
    	int gameIndex = 1;
    	Model m = gic.getModels().getGame(gameIndex);
        Player matt = null;
        Player scott = null;
        Player jan = null;
        Player garrett = null;
    	
    	try {
	        matt = m.getPlayer(0); //BLUE
	        scott = m.getPlayer(1); //GREEN
	        jan = m.getPlayer(2); //ORANGE
	        garrett = m.getPlayer(3); //RED
	    } catch (GetPlayerException ex) {
	        Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
	        fail();
	    }
    	
    	/**
    	 * Roll a 7, no resources distributed
    	 */
    	ResourceList mattBefore = matt.getResources().copy();
    	ResourceList garBefore = garrett.getResources().copy();
    	ResourceList scottBefore = scott.getResources().copy();
    	ResourceList janBefore = jan.getResources().copy();
    	int oldVersion = m.getVersion();
    	int currentTurn = m.getTurnTracker().getCurrentTurn();
    	
    	RollNumberRequest rnReq = new RollNumberRequest(currentTurn, 7);
    	
    	//Execute command
    	Command rn = new rollNumber();
    	try {
    		rn.execute(serializer.toJson(rnReq), gameIndex, currentTurn);
    	} catch( HTTPBadRequest ex) {
    		Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
    		fail();
    	}
    	
    	//compare after command execution
    	assertEquals(mattBefore, matt.getResources());
    	assertEquals(garBefore, garrett.getResources());
    	assertEquals(scottBefore, scott.getResources());
    	assertEquals(janBefore, jan.getResources());
    	assertEquals(oldVersion + 1, m.getVersion());	
    }
    
    @Test
    public void testRollNumber_invalid_wrongPlayerID() {
    	int gameIndex = 1;
    	Model m = gic.getModels().getGame(gameIndex);
    	
    	int falseCurrentTurn = 3; //actual current turn is 0
    	int rollNum = 11;

    	int oldVersion = m.getVersion();
    	
    	RollNumberRequest rnReq = new RollNumberRequest(falseCurrentTurn, rollNum);
    	
    	//Execute command
    	Command rn = new rollNumber();
    	try {
    		rn.execute(serializer.toJson(rnReq), gameIndex, falseCurrentTurn);
    	} catch( HTTPBadRequest ex) {
    		Logger.getLogger(CommandTests.class.getName()).log(Level.SEVERE, null, ex);
    		fail();
    	}	
    	
    	int newVersion = m.getVersion();
    	//Version should not increment, model should not change
    	assertEquals(oldVersion, newVersion);
    }
}

