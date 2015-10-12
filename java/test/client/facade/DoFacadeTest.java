package client.facade;

import client.proxy.IServerProxy;
import client.proxy.MockProxy;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import shared.communication.params.Credentials;
import shared.communication.params.JoinGameRequest;
import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.exceptions.GetPlayerException;
import shared.exceptions.ServerException;
import shared.json.Deserializer;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.model.ResourceList;

/**
 *
 * @author JanPaul
 */
public class DoFacadeTest {
    
    private StateBase instance;
    
    public DoFacadeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException, ServerException {
        IServerProxy mp = new MockProxy();
        
        // Gotta login and join a game before all these move requests work
        Credentials cred = new Credentials();
        cred.setUsername("Sam");
        cred.setPassword("sam");
        mp.login( cred );
        JoinGameRequest joinReq = new JoinGameRequest( 0, CatanColor.RED.toString() );
        mp.joinGame( joinReq );
        
        CatanFacade.setup( mp, new Deserializer().getTestModel() );
        instance = CatanFacade.getDoFacade();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of offerTrade method, of class DoFacade.
     */
    @Test
    public void testOfferTrade() throws ServerException {
        System.out.println("offerTrade");
        // trading 3 brick for 2 wood
        ResourceList offer = new ResourceList(-3, 2, 0, 0, 0);
        int receiverIndex = 3;
        instance.offerTrade( offer, receiverIndex );
    }

    /**
     * Test of acceptTrade method, of class DoFacade.
     */
    @Test
    public void testAcceptTrade() throws ServerException {
        System.out.println("acceptTrade");
        instance.acceptTrade( true );
    }

    /**
     * Test of maritimeTrade method, of class DoFacade.
     */
    @Test
    public void testMaritimeTrade() throws ServerException {
        System.out.println("maritimeTrade");
        int ratio = 3;
        ResourceType input = ResourceType.BRICK;
        ResourceType output = ResourceType.SHEEP;
        instance.maritimeTrade( ratio, input, output );
    }

    /**
     * Test of buyDevCard method, of class DoFacade.
     */
    @Test
    public void testBuyDevCard() throws ServerException {
        System.out.println("buyDevCard");
        instance.buyDevCard();
    }

    /**
     * Test of playYearOfPlenty method, of class DoFacade.
     */
    @Test
    public void testPlayYearOfPlenty() throws ServerException {
        System.out.println("playYearOfPlenty");
        ResourceType resource1 = ResourceType.SHEEP;
        ResourceType resource2 = ResourceType.BRICK;
        instance.playYearOfPlenty( resource1, resource2 );
    }

    /**
     * Test of playRoadBuilding method, of class DoFacade.
     */
    @Test
    public void testPlayRoadBuilding() throws ServerException {
        System.out.println("playRoadBuilding");
        HexLocation hex = new HexLocation( 2, 2 );
        EdgeLocation spot1 = new EdgeLocation( hex, EdgeDirection.NorthEast );
        EdgeLocation spot2 = new EdgeLocation( hex, EdgeDirection.SouthWest );
        instance.playRoadBuilding( spot1, spot2 );
    }

    /**
     * Test of playSoldier method, of class DoFacade.
     */
    @Test
    public void testPlaySoldier() throws ServerException, GetPlayerException {
        System.out.println("playSoldier");
        int victimIndex = -1;
        HexLocation location = new HexLocation(0,0);
        instance.playSoldier( victimIndex, location );
    }

    /**
     * Test of playMonopoly method, of class DoFacade.
     */
    @Test
    public void testPlayMonopoly() throws ServerException {
        System.out.println("playMonopoly");
        ResourceType rs = ResourceType.WHEAT;
        instance.playMonopoly( rs );
    }

    /**
     * Test of buildRoad method, of class DoFacade.
     */
    @Test
    public void testBuildRoad() throws ServerException {
        System.out.println("buildRoad");
        HexLocation hex = new HexLocation( 2, 2 );
        EdgeLocation location = new EdgeLocation( hex, EdgeDirection.North);
        boolean free = true;
        instance.buildRoad( location, free );
    }

    /**
     * Test of buildSettlement method, of class DoFacade.
     */
    @Test
    public void testBuildSettlement() throws ServerException {
        System.out.println("buildSettlement");
        VertexLocation location = new VertexLocation( new HexLocation(1,1), VertexDirection.East );
        instance.buildSettlement(location);
    }

    /**
     * Test of buildCity method, of class DoFacade.
     */
    @Test
    public void testBuildCity() throws ServerException {
        System.out.println("buildCity");
        HexLocation hex = new HexLocation( 2, 2 );
        VertexLocation location = new VertexLocation( hex, VertexDirection.West);
        instance.buildCity( location );
    }

    /**
     * Test of sendChat method, of class DoFacade.
     */
    @Test
    public void testSendChat() throws ServerException {
        System.out.println("sendChat");
        String comment = "oh hey guys";
        instance.sendChat( comment );
    }

    /**
     * Test of rollNumber method, of class DoFacade.
     */
    @Test
    public void testRollNumber() throws ServerException {
        System.out.println("rollNumber");
        instance.rollNumber();
    }

    /**
     * Test of robPlayer method, of class DoFacade.
     */
    @Test
    public void testRobPlayer() throws ServerException {
        System.out.println("robPlayer");
        int victim = 1;
        HexLocation loc = new HexLocation( 1, 1 );
        instance.robPlayer( victim, loc );
    }

    /**
     * Test of finishTurn method, of class DoFacade.
     */
    @Test
    public void testFinishTurn() throws ServerException {
        System.out.println("finishTurn");
        instance.finishTurn();
    }
    
    @Test
    public void testDiscardCards() throws ServerException {
        System.out.println("discardCards");
        ResourceList list = new ResourceList( 1, 2, 3, 4, 5 );
        instance.discardCards( list );
    }
    
}
