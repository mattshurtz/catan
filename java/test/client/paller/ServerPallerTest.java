/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.paller;

import client.facade.CatanFacade;
import client.proxy.IServerProxy;
import client.proxy.MockProxy;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import shared.communication.params.Credentials;
import shared.communication.params.JoinGameRequest;
import shared.definitions.CatanColor;
import shared.exceptions.ServerException;
import shared.json.Deserializer;

/**
 *
 * @author JanPaul
 */
public class ServerPallerTest {
    
    public ServerPallerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws ServerException, IOException {
        IServerProxy mp = new MockProxy();
        
        // Gotta login and join a game before all these move requests work
        Credentials cred = new Credentials();
        cred.setUsername("Sam");
        cred.setPassword("sam");
        mp.login( cred );
        JoinGameRequest joinReq = new JoinGameRequest( 0, CatanColor.RED.toString() );
        mp.joinGame( joinReq );
        
        CatanFacade.setup( mp, new Deserializer().getTestModel() );
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of run method, of class ServerPaller.
     */
    @Test
    public void testRun() {
        ServerPaller instance = new ServerPaller();
        instance.start();
        
        // Sleep for 2.5x the delay so that we get 2 events in for sure
        try {
            Thread.sleep( (ServerPaller.PALL_DELAY * 2) + (ServerPaller.PALL_DELAY / 2) );
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerPallerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        assertEquals( 2, instance.getTimesPalled() );
    }
    
}
