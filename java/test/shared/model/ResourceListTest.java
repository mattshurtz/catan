/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import shared.definitions.ResourceType;

/**
 *
 */
public class ResourceListTest {
    
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
        System.out.println("canOfferTade");
        ResourceList offer = null;
        ResourceList instance = new ResourceList();
        boolean expResult = false;
        boolean result = instance.canOfferTade(offer);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canBuyRoad method, of class ResourceList.
     */
    @Test
    public void testCanBuyRoad() {
        System.out.println("canBuyRoad");
        ResourceList instance = new ResourceList();
        boolean expResult = false;
        boolean result = instance.canBuyRoad();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buyRoad method, of class ResourceList.
     */
    @Test
    public void testBuyRoad() {
        System.out.println("buyRoad");
        ResourceList instance = new ResourceList();
        instance.buyRoad();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canBuySettlement method, of class ResourceList.
     */
    @Test
    public void testCanBuySettlement() {
        System.out.println("canBuySettlement");
        ResourceList instance = new ResourceList();
        boolean expResult = false;
        boolean result = instance.canBuySettlement();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buySettlement method, of class ResourceList.
     */
    @Test
    public void testBuySettlement() {
        System.out.println("buySettlement");
        ResourceList instance = new ResourceList();
        instance.buySettlement();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canBuyCity method, of class ResourceList.
     */
    @Test
    public void testCanBuyCity() {
        System.out.println("canBuyCity");
        ResourceList instance = new ResourceList();
        boolean expResult = false;
        boolean result = instance.canBuyCity();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buyCity method, of class ResourceList.
     */
    @Test
    public void testBuyCity() {
        System.out.println("buyCity");
        ResourceList instance = new ResourceList();
        instance.buyCity();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addResource method, of class ResourceList.
     */
    @Test
    public void testAddResource() {
        System.out.println("addResource");
        ResourceType resourceType = null;
        ResourceList instance = new ResourceList();
        instance.addResource(resourceType,1);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of subtractResource method, of class ResourceList.
     */
    @Test
    public void testSubtractResource() {
        System.out.println("subtractResource");
        ResourceType resourceType = null;
        ResourceList instance = new ResourceList();
        instance.subtractResource(resourceType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
