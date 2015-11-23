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
import shared.model.Model;
import shared.model.ClientModelTest;
import shared.model.ResourceList;

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
        // here we are serializing the bank object and testing to verify that the serializer converts it
        // to the correct json representation
        Model model = ClientModelTest.testModel();
        ResourceList bank = model.getBank();
        Object o = null;
        Serializer instance = new Serializer();
        String expResult = "{\"brick\":23,\"ore\":22,\"sheep\":20,\"wheat\":22,\"wood\":21}";
        String result = instance.toJson(bank);
        System.out.println(result);
        assertEquals(expResult, result);
    }
    
}
