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
public class VertexLocationTest {
    
    public VertexLocationTest() {
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
     * Test of getHexLoc method, of class VertexLocation.
     */
    @Test
    public void testGetHexLoc() {
        System.out.println("getHexLoc");
        VertexLocation instance = null;
        HexLocation expResult = null;
        HexLocation result = instance.getHexLoc();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDir method, of class VertexLocation.
     */
    @Test
    public void testGetDir() {
        System.out.println("getDir");
        VertexLocation instance = null;
        VertexDirection expResult = null;
        VertexDirection result = instance.getDir();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class VertexLocation.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        VertexLocation instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class VertexLocation.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        VertexLocation instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class VertexLocation.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        VertexLocation instance = null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNormalizedLocation method, of class VertexLocation.
     */
    @Test
    public void testGetNormalizedLocation() {
        System.out.println("getNormalizedLocation");
        VertexLocation instance = null;
        VertexLocation expResult = null;
        VertexLocation result = instance.getNormalizedLocation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
