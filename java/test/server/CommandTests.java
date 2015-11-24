package server;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import server.commands.Command;
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
import shared.communication.params.LoadGameRequest;
import shared.communication.params.moves.*;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.exceptions.GetPlayerException;
import shared.exceptions.HTTPBadRequest;
import shared.json.Serializer;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.model.DevCardList;
import shared.model.MessageLine;
import shared.model.MessageList;
import shared.model.Model;
import shared.model.Player;
import shared.model.ResourceList;

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
		System.out.println(result);
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
	public void testModel() {
		
	}
	
	@Test
	public void testListAI() {
		
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
    public void buyDevCard() throws GetPlayerException{
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
            assertTrue(model.getPlayer(testPlayerHasResources).getResources().hasResources(new ResourceList(0,2,2,3,2)));
            // the version number is initialy 16 after we have initialized the map
            int versionNumber = model.getVersion();
            assertEquals(model.getVersion(),16);
            //Test while is not players turn
            cmd.execute(serializer.toJson(request),1,testPlayerHasResources);
            //Version number is the same because it was not the players turn. 
            assertTrue(versionNumber==16);
            
            //change turn to the correct player
            model.getTurnTracker().setCurrentTurn(3);
            //run again and the buyDevCardRequest should be succsessful
            cmd.execute(serializer.toJson(request),1,testPlayerHasResources);
            //Check that resources were discarded from the player correctly
            assertTrue(model.getPlayer(testPlayerHasResources).getResources().hasResources(new ResourceList(0,2,1,2,1)));
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
    
    @Test
    public void discardCards(){
        fail();
    }
    
    @Test
    public void acceptTrade(){
       fail(); 
    }
    
    @Test
    public void roadBuilding(){
       fail(); 
    }
    
    @Test
    public void soldier(){
       fail(); 
    }
    
    @Test
    public void finishTurn(){
       fail(); 
    }
    
    @Test
    public void maritimeTrade(){
       fail(); 
    }
    
    @Test
    public void offerTrade(){
       fail(); 
    }
    
    @Test
    public void robPlayer(){
       fail(); 
    }
    
    @Test
    public void rollNumber(){
       fail(); 
    }

}

