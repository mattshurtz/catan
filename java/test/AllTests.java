
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
            "client.paller.ServerPallerTest",
            "client.proxy.IServerProxyTest",
            "shared.json.DeserializerTest",
            "shared.json.SerializerTest",
            "shared.model.DevCardListTest",
            "shared.model.ClientModelTest",
            "shared.model.ResourceListTest",
            "shared.model.UserTest",
            "server.CommandTests",
            "server.persistence.filePlugin.FileConnectionTest",
            "server.persistence.filePlugin.FileGamesDAOTest",
            "server.persistence.filePlugin.FileUsersDAOTest",
            "server.persistence.sqlPlugin.SQLGamesDAOTest",
            "server.persistence.sqlPlugin.SQLUsersDAOTest"
        };
        
        org.junit.runner.JUnitCore.main( testClasses );
    }
    
}
