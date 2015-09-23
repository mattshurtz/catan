/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model.map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

/**
 *
 * @author JanPaul
 */
public class MapTest {
    
    public MapTest() {
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
     * Test of canPlaceRoadAtLoc method, of class Map.
     */
    @Test
    public void testCanPlaceRoadAtLoc() {
        System.out.println("canPlaceRoadAtLoc");
        EdgeLocation location = null;
        Map instance = new Map();
        boolean expResult = false;
        boolean result = instance.canPlaceRoadAtLoc(location);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of placeRoadAtLocation method, of class Map.
     */
    @Test
    public void testPlaceRoadAtLocation() {
        System.out.println("placeRoadAtLocation");
        Road road = null;
        Map instance = new Map();
        instance.placeRoadAtLocation(road);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canPlaceCityAtLoc method, of class Map.
     */
    @Test
    public void testCanPlaceCityAtLoc() {
        System.out.println("canPlaceCityAtLoc");
        VertexLocation location = null;
        Map instance = new Map();
        boolean expResult = false;
        boolean result = instance.canPlaceCityAtLoc(location);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of placeCityAtLocation method, of class Map.
     */
    @Test
    public void testPlaceCityAtLocation() {
        System.out.println("placeCityAtLocation");
        City city = null;
        VertexLocation location = null;
        Map instance = new Map();
        instance.placeCityAtLocation(city, location);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canPlaceSettlementAtLoc method, of class Map.
     */
    @Test
    public void testCanPlaceSettlementAtLoc() {
        System.out.println("canPlaceSettlementAtLoc");
        VertexLocation location = null;
        Map instance = new Map();
        boolean expResult = false;
        boolean result = instance.canPlaceSettlementAtLoc(location);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of placeSettlmentAtLoc method, of class Map.
     */
    @Test
    public void testPlaceSettlmentAtLoc() {
        System.out.println("placeSettlmentAtLoc");
        Settlement settlement = null;
        VertexLocation location = null;
        Map instance = new Map();
        instance.placeSettlmentAtLoc(settlement, location);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of distributeResources method, of class Map.
     */
    @Test
    public void testDistributeResources() {
        System.out.println("distributeResources");
        int rolledNumber = 0;
        Map instance = new Map();
        instance.distributeResources(rolledNumber);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
