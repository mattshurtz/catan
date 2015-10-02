
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * Class to kick off all the other jUnit tests.
 */
public class AllTests {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
    public static void main(String args[]) {
        String[] testClasses = new String[] {
            "client.facade.CanDoFacadeMyTurnTest",
            "client.facade.CanDoFacadeNotMyTurnTest",
            "client.facade.CanDoFacadeTest",
            "client.facade.DoFacadeTest",
            "client.facade.GameHubFacadeTest",
            "client.paller.ServerPallTaskTest",
            "client.paller.ServerPallerTest",
            "client.proxy.IServerProxyTest",
            "shared.json.DeserializerTest",
            "shared.json.SerializerTest",
            "shared.model.DevCardListTest",
            "shared.model.MessageLineTest",
            "shared.model.MessageListTest",
            "shared.model.ModelTest",
            "shared.model.PlayerTest",
            "shared.model.ResourceListTest",
            "shared.model.TradeOfferTest",
            "shared.model.TurnTrackerTest",
            "shared.model.UserTest",
            "shared.model.map.CityTest",
            "shared.model.map.HexTest",
            "shared.model.map.MapTest",
            "shared.model.map.PortTest",
            "shared.model.map.RoadTest",
            "shared.model.map.SettlementTest",
            "shared.model.map.VertexObjectTest"
        };
        
        org.junit.runner.JUnitCore.main( testClasses );
    }
    
}
