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

import shared.exceptions.*;

/**
 *
 * @author JanPaul
 */
public class DevCardListTest {
	
	DevCardList instance = null;
    
    public DevCardListTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    	DevCardList instance = new DevCardList();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getMonopoly method, of class DevCardList.
     */
    @Test
    public void testGetMonopoly() {
        System.out.println("getMonopoly");
        DevCardList instance = new DevCardList();
        int expResult = 0;
        int result = instance.getMonopoly();
        assertEquals(expResult, result);
    }

    /**
     * Test of AddMonopoly method, of class DevCardList.
     */
    @Test
    public void testAddMonopoly() {
        System.out.println("AddMonopoly");
        DevCardList instance = new DevCardList();
        instance.AddMonopoly();
        int result = instance.getMonopoly();
        assertEquals(1, result);
        instance.AddMonopoly();
        result = instance.getMonopoly();
        assertEquals(2, result);
    }

    /**
     * Test of removeMonopoly method, of class DevCardList.
     * @throws InsufficientSupplies 
     */
    @Test(expected=InsufficientSupplies.class)
    public void testRemoveMonopoly_0available_throwError() throws InsufficientSupplies {
        System.out.println("removeMonopoly");
        DevCardList instance = new DevCardList();
        instance.removeMonopoly();
        fail();
    }
    
    @Test
    public void testRemoveMonopoly_1Available() {
        System.out.println("removeMonopoly");
        DevCardList instance = new DevCardList();
        instance.setMonopoly(1);
        instance.removeMonopoly();
		assertEquals(0,instance.getMonopoly());
    }

    /**
     * Test of getMonument method, of class DevCardList.
     */
    @Test
    public void testGetMonument() {
        System.out.println("getMonument");
        DevCardList instance = new DevCardList();
        int expResult = 0;
        int result = instance.getMonument();
        assertEquals(expResult, result);
    }

    /**
     * Test of AddMonument method, of class DevCardList.
     */
    @Test
    public void testAddMonument() {
        System.out.println("AddMonument");
        DevCardList instance = new DevCardList();
        instance.AddMonument();
        assertEquals(1,instance.getMonument());
        instance.AddMonument();
        assertEquals(2,instance.getMonument());
    }

    /**
     * Test of getRoadBuilding method, of class DevCardList.
     */
    @Test
    public void testGetRoadBuilding() {
        System.out.println("getRoadBuilding");
        DevCardList instance = new DevCardList();
        int expResult = 0;
        int result = instance.getRoadBuilding();
        assertEquals(expResult, result);
    }

    /**
     * Test of AddRoadBuilding method, of class DevCardList.
     */
    @Test
    public void testAddRoadBuilding() {
        System.out.println("AddRoadBuilding");
        DevCardList instance = new DevCardList();
        instance.AddRoadBuilding();
        assertEquals(1,instance.getRoadBuilding());
        instance.AddRoadBuilding();
        assertEquals(2,instance.getRoadBuilding());
    }

    /**
     * Test of removeRoadBuilding method, of class DevCardList.
     */
    @Test
    public void testRemoveRoadBuilding_0available_throwError() {
        System.out.println("removeRoadBuilding");
        DevCardList instance = new DevCardList();
        instance.removeRoadBuilding();
		fail();
        
    }
    
    /**
     * Test of removeRoadBuilding method, of class DevCardList.
     */
    @Test
    public void testRemoveRoadBuilding_1available() {
        System.out.println("removeRoadBuilding");
        DevCardList instance = new DevCardList();
        instance.AddRoadBuilding();
        instance.removeRoadBuilding();
		assertEquals(0, instance.getRoadBuilding());
        
    }

    /**
     * Test of getSoldier method, of class DevCardList.
     */
    @Test
    public void testGetSoldier() {
        System.out.println("getSoldier");
        DevCardList instance = new DevCardList();
        int expResult = 0;
        int result = instance.getSoldier();
        assertEquals(expResult, result);
    }

    /**
     * Test of AddSoldier method, of class DevCardList.
     */
    @Test
    public void testAddSoldier() {
        System.out.println("AddSoldier");
        DevCardList instance = new DevCardList();
        instance.AddSoldier();
        assertEquals(1,instance.getSoldier());
        instance.AddSoldier();
        assertEquals(2,instance.getSoldier());
    }

    /**
     * Test of removeSoldier method, of class DevCardList.
     */
    @Test
    public void testRemoveSoldier_0available_throwError() {
        System.out.println("removeSoldier");
        DevCardList instance = new DevCardList();
        instance.removeSoldier();
		fail();        
    }
    
    /**
     * Test of removeSoldier method, of class DevCardList.
     */
    @Test
    public void testRemoveSoldier_1available() {
        System.out.println("removeSoldier");
        DevCardList instance = new DevCardList();
        instance.setSoldier(1);
        instance.removeSoldier();
		assertEquals(0,instance.getSoldier());        
    }

    /**
     * Test of getYearOfPlenty method, of class DevCardList.
     */
    @Test
    public void testGetYearOfPlenty() {
        System.out.println("getYearOfPlenty");
        DevCardList instance = new DevCardList();
        int expResult = 0;
        int result = instance.getYearOfPlenty();
        assertEquals(expResult, result);
    }

    /**
     * Test of AddYearOfPlenty method, of class DevCardList.
     */
    @Test
    public void testAddYearOfPlenty() {
        System.out.println("AddYearOfPlenty");
        DevCardList instance = new DevCardList();
        instance.AddYearOfPlenty();
        assertEquals(1,instance.getYearOfPlenty());
        instance.AddYearOfPlenty();
        assertEquals(2,instance.getYearOfPlenty());
    }

    /**
     * Test of removeYearOfPlenty method, of class DevCardList.
     */
    @Test
    public void testRemoveYearOfPlenty_0available_throwError() {
        System.out.println("removeYearOfPlenty");
        DevCardList instance = new DevCardList();
        instance.removeYearOfPlenty();
		fail();
    }
    
    /**
     * Test of removeYearOfPlenty method, of class DevCardList.
     */
    @Test
    public void testRemoveYearOfPlenty_1available() {
        System.out.println("removeYearOfPlenty");
        DevCardList instance = new DevCardList();
        instance.AddYearOfPlenty();
        instance.removeYearOfPlenty();
		assertEquals(0,instance.getYearOfPlenty());
    }

    /**
     * Test of canPlayMonopoly method, of class DevCardList.
     */
    @Test
    public void testCanPlayMonopoly_No() {
        System.out.println("canPlayMonopoly");
        DevCardList instance = new DevCardList();
        boolean expResult = false;
        boolean result = instance.canPlayMonopoly();
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test of canPlayMonopoly method, of class DevCardList.
     */
    @Test
    public void testCanPlayMonopoly_Yes() {
        System.out.println("canPlayMonopoly");
        DevCardList instance = new DevCardList();
        instance.AddMonopoly();
        boolean expResult = true;
        boolean result = instance.canPlayMonopoly();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of canPlayRoadBuilding method, of class DevCardList.
     */
    @Test
    public void testCanPlayRoadBuilding_No() {
        System.out.println("canPlayRoadBuilding");
        DevCardList instance = new DevCardList();
        boolean expResult = false;
        boolean result = instance.canPlayRoadBuilding();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of canPlayRoadBuilding method, of class DevCardList.
     */
    @Test
    public void testCanPlayRoadBuilding_Yes() {
        System.out.println("canPlayRoadBuilding");
        DevCardList instance = new DevCardList();
        instance.AddRoadBuilding();
        boolean expResult = true;
        boolean result = instance.canPlayRoadBuilding();
        assertEquals(expResult, result);
    }

    /**
     * Test of canPlaySoldier method, of class DevCardList.
     */
    @Test
    public void testCanPlaySoldier_No() {
        System.out.println("canPlaySoldier");
        DevCardList instance = new DevCardList();
        boolean expResult = false;
        boolean result = instance.canPlaySoldier();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of canPlaySoldier method, of class DevCardList.
     */
    @Test
    public void testCanPlaySoldier_Yes() {
        System.out.println("canPlaySoldier");
        DevCardList instance = new DevCardList();
        instance.AddSoldier();
        boolean expResult = true;
        boolean result = instance.canPlaySoldier();
        assertEquals(expResult, result);
    }

    /**
     * Test of canPlayYearOfPlenty method, of class DevCardList.
     */
    @Test
    public void testCanPlayYearOfPlenty_No() {
        System.out.println("canPlayYearOfPlenty");
        DevCardList instance = new DevCardList();
        boolean expResult = false;
        boolean result = instance.canPlayYearOfPlenty();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of canPlayYearOfPlenty method, of class DevCardList.
     */
    @Test
    public void testCanPlayYearOfPlenty_Yes() {
        System.out.println("canPlayYearOfPlenty");
        DevCardList instance = new DevCardList();
        instance.AddYearOfPlenty();
        boolean expResult = true;
        boolean result = instance.canPlayYearOfPlenty();
        assertEquals(expResult, result);
    }
    
}
