/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.definitions;

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
@Suite.SuiteClasses({shared.definitions.CatanColorTest.class, shared.definitions.DevCardTypeTest.class, shared.definitions.HexTypeTest.class, shared.definitions.TurnStatusTest.class, shared.definitions.PortTypeTest.class, shared.definitions.PieceTypeTest.class, shared.definitions.ResourceTypeTest.class})
public class DefinitionsSuite {

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
