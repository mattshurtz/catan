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
public class EdgeDirectionTest {
    
    public EdgeDirectionTest() {
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
     * Test of values method, of class EdgeDirection.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        EdgeDirection[] expResult = null;
        EdgeDirection[] result = EdgeDirection.values();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valueOf method, of class EdgeDirection.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "";
        EdgeDirection expResult = null;
        EdgeDirection result = EdgeDirection.valueOf(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOppositeDirection method, of class EdgeDirection.
     */
    @Test
    public void testGetOppositeDirection() {
        System.out.println("getOppositeDirection");
        EdgeDirection instance = null;
        EdgeDirection expResult = null;
        EdgeDirection result = instance.getOppositeDirection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
