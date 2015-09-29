/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.facade;

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
@Suite.SuiteClasses({ client.facade.CanDoFacadeTest.class, client.facade.GameHubFacadeTest.class, client.facade.CanDoFacadeNotMyTurnTest.class, client.facade.DoFacadeTest.class, client.facade.CanDoFacadeMyTurnTest.class})
public class FacadeSuite {

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
