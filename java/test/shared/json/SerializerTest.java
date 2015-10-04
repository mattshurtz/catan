/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.json;

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
public class SerializerTest {
    
    public SerializerTest() {
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
     * Test of toJson method, of class Serializer.
     */
    @Test
    public void testToJson() {
        System.out.println("toJson");
        Object o = null;
        Serializer instance = new Serializer();
        String expResult = "";
        String result = instance.toJson(o);
        assertEquals(expResult, result);
    }
    
}
