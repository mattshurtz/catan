/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.locations;

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
@Suite.SuiteClasses({shared.locations.EdgeLocationTest.class, shared.locations.HexLocationTest.class, shared.locations.VertexLocationTest.class, shared.locations.EdgeDirectionTest.class, shared.locations.VertexDirectionTest.class})
public class LocationsSuite {

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
