package client.facade;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 
 */
public class CatanFacadeTest {
    
    public CatanFacadeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getCanDoFacade method, of class CatanFacade.
     */
    @Test
    public void testGetCanDoFacade() {
        System.out.println("getCanDoFacade");
        CanDoFacade expResult = null;
        CanDoFacade result = CatanFacade.getCanDoFacade();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDoFacade method, of class CatanFacade.
     */
    @Test
    public void testGetDoFacade() {
        System.out.println("getDoFacade");
        DoFacade expResult = null;
        DoFacade result = CatanFacade.getDoFacade();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGameHubFacade method, of class CatanFacade.
     */
    @Test
    public void testGetGameHubFacade() {
        System.out.println("getGameHubFacade");
        GameHubFacade expResult = null;
        GameHubFacade result = CatanFacade.getGameHubFacade();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
