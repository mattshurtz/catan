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
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author JanPaul
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({shared.model.map.MapSuite.class, shared.model.MessageListTest.class, shared.model.ResourceListTest.class, shared.model.TradeOfferTest.class, shared.model.DevCardListTest.class, shared.model.ModelTest.class, shared.model.MessageLineTest.class, shared.model.PlayerTest.class, shared.model.UserTest.class, shared.model.TurnTrackerTest.class})
public class ModelSuite {

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
