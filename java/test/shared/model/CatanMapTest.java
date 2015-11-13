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
import shared.definitions.HexType;
import shared.definitions.PortType;
import shared.locations.HexLocation;
import shared.model.map.CatanMap;

/**
 *
 * @author JanPaul
 */
public class CatanMapTest {
	
	DevCardList instance = null;
    
    public CatanMapTest() {
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
    
    @Test
    public void testCreateMap() {
        // first test to make sure that we can create one with the default arrangement
        // (no random anything)
        CatanMap mapDefault = new CatanMap( false, false, false );
        // Check a few of the resources
        assert ( mapDefault.getHexAt( new HexLocation(-2, 0) ).getHexType() == HexType.ORE  );
        assert ( mapDefault.getHexAt( new HexLocation(0, 1) ).getHexType() == HexType.WOOD  );
        assert ( mapDefault.getHexAt( new HexLocation(2, 0) ).getHexType() == HexType.WHEAT );
        // Check some numbers
        assert ( mapDefault.getHexAt( new HexLocation(-2, 0) ).getNumber() == 5 );
        assert ( mapDefault.getHexAt( new HexLocation(0, -1) ).getNumber() == 3 );
        assert ( mapDefault.getHexAt( new HexLocation(0, 2) ).getNumber() == 8 );
        // check ports
        assert ( mapDefault.getPortAt( new HexLocation(1,-3) ).getPortType() == PortType.ORE );
        assert ( mapDefault.getPortAt( new HexLocation(3,-3) ).getPortType() == PortType.THREE );
        assert ( mapDefault.getPortAt( new HexLocation(3,-1) ).getPortType() == PortType.SHEEP );
        
        // Then test to see if random is working
        CatanMap mapRandom  = new CatanMap( true, true, true );
        // Check a few spots
        assert ( mapRandom.getHexAt( new HexLocation(-2, 0) ).getHexType() != HexType.ORE  ) ||
               ( mapRandom.getHexAt( new HexLocation(0, 1) ).getHexType() != HexType.WOOD  ) ||
               ( mapRandom.getHexAt( new HexLocation(2, 0) ).getHexType() != HexType.WHEAT );
        // Check some numbers
        assert ( mapDefault.getHexAt( new HexLocation(-2, 0) ).getNumber() == 5 ) ||
               ( mapDefault.getHexAt( new HexLocation(0, -1) ).getNumber() == 3 ) ||
               ( mapDefault.getHexAt( new HexLocation(0, 2) ).getNumber() == 8 );
        // ports
        assert ( mapDefault.getPortAt( new HexLocation(1,-3) ).getPortType() == PortType.ORE ) ||
               ( mapDefault.getPortAt( new HexLocation(3,-3) ).getPortType() == PortType.THREE ) ||
               ( mapDefault.getPortAt( new HexLocation(3,-1) ).getPortType() == PortType.SHEEP );
    }
    
}
