/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.definitions;

import java.awt.Color;
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
public class CatanColorTest {
    
    public CatanColorTest() {
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
     * Test of values method, of class CatanColor.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        CatanColor[] expResult = null;
        CatanColor[] result = CatanColor.values();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valueOf method, of class CatanColor.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "";
        CatanColor expResult = null;
        CatanColor result = CatanColor.valueOf(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getJavaColor method, of class CatanColor.
     */
    @Test
    public void testGetJavaColor() {
        System.out.println("getJavaColor");
        CatanColor instance = null;
        Color expResult = null;
        Color result = instance.getJavaColor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
