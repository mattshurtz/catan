/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.locations;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JanPaul
 */
public class EdgeLocationTest {
    
    public EdgeLocationTest() {
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
     * Test of getHexLoc method, of class EdgeLocation.
     */
    @Test
    public void testGetHexLoc() {
        System.out.println("getHexLoc");
        EdgeLocation instance = null;
        HexLocation expResult = null;
        HexLocation result = instance.getHexLoc();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDir method, of class EdgeLocation.
     */
    @Test
    public void testGetDir() {
        System.out.println("getDir");
        EdgeLocation instance = null;
        EdgeDirection expResult = null;
        EdgeDirection result = instance.getDir();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class EdgeLocation.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        EdgeLocation instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class EdgeLocation.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        EdgeLocation instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class EdgeLocation.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        EdgeLocation instance = null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNormalizedLocation method, of class EdgeLocation.
     */
    @Test
    public void testGetNormalizedLocation() {
        System.out.println("getNormalizedLocation");
        EdgeLocation instance = null;
        EdgeLocation expResult = null;
        EdgeLocation result = instance.getNormalizedLocation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
