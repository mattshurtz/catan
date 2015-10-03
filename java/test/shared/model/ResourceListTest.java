/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import shared.definitions.ResourceType;
import shared.exceptions.GetPlayerException;

/**
 *
 */
public class ResourceListTest {
    Model model;
    public ResourceListTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.model = ModelTest.testModel();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of canAcceptTrade method, of class ResourceList.
     */
    @Test
    public void testCanAcceptTrade() {
        System.out.println("canAcceptTrade");
        ResourceList accept = null;
        ResourceList instance = new ResourceList();
        boolean expResult = false;
        boolean result = instance.canAcceptTrade(accept);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canOfferTade method, of class ResourceList.
     */
    @Test
    public void testCanOfferTade() {
        int amount = 1;
        ResourceType resource = ResourceType.BRICK;
        try {
            assertEquals(false,model.getPlayer(0).getResources().canOfferResource(resource,amount));
            assertEquals(true, model.getPlayer(0).getResources().canOfferResource(ResourceType.WOOD,amount));
        } catch (GetPlayerException ex) {
            Logger.getLogger(ResourceListTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    /**
     * Test of canDistribute method, of class ResourceList.
     */
    @Test
    public void testCanDistribute() {
        System.out.println("canDistribute");
        ResourceType resource = null;
        ResourceList instance = new ResourceList();
        boolean expResult = false;
        boolean result = instance.canDistribute(resource);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
