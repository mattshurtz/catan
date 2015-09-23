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
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author JanPaul
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({shared.model.map.PortTest.class, shared.model.map.RoadTest.class, shared.model.map.MapTest.class, shared.model.map.SettlementTest.class, shared.model.map.VertexObjectTest.class, shared.model.map.CityTest.class, shared.model.map.HexTest.class})
public class MapSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
