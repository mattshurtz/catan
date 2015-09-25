/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.proxy;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import shared.model.MessageLine;
import shared.model.Model;

/**
 *
 * @author JanPaul
 */
public class DeserializerTest {
    
    public DeserializerTest() {
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
     * Test of toJavaModel method, of class Deserializer.
     */
    @Test
    public void testToJavaModel() {
        System.out.println("toJavaModel");
        String json = "";
        Deserializer instance = new Deserializer();
        Model expResult = null;
        Model result = instance.toJavaModel(json);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toJavaMessage method, of class Deserializer.
     */
    @Test
    public void testToJavaMessage() {
        System.out.println("toJavaMessage");
        String json = "";
        Deserializer instance = new Deserializer();
        MessageLine expResult = null;
        MessageLine result = instance.toJavaMessage(json);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
