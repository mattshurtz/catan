/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.persistence.filePlugin;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import server.gameinfocontainer.GameInfoContainer;
import server.gameinfocontainer.ModelBank;
import server.persistence.sqlPlugin.SQLFactory;
import shared.communication.params.CommandParam;
import shared.json.Serializer;
import shared.model.Model;

/**
 *
 * @author JanPaul
 */
public class FileGamesDAOTest {
    
    public FileGamesDAOTest() {
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
     * Test of getCommands method, of class FileGamesDAO.
     */
    @Test
    public void testGetCommands() throws Exception {
    }

    /**
     * Test of addCommand method, of class FileGamesDAO.
     */
    @Test
    public void testAddCommand() throws Exception {
    }

    /**
     * Test of addGame method, of class FileGamesDAO.
     */
    @Test
    public void testAddGame() throws Exception {
        System.out.println("addGame");
        FileGamesDAO instance = new FileGamesDAO(new FileConnection());
        GameInfoContainer gic = GameInfoContainer.getInstance();
        
        instance.getConnectionUtility().startTransaction();
        Model expected = gic.getModels().getGame(0);
        instance.addGame(0,expected);
        instance.getConnectionUtility().endTransaction();
        
        ModelBank mb = instance.getGames();
        Model actual = mb.getGame(0);
        assertEquals( expected, actual );
    }

    /**
     * Test of clearGames method, of class FileGamesDAO.
     */
    @Test
    public void testClearGames() throws Exception {
        System.out.println("clearGames");
        FileGamesDAO instance = new FileGamesDAO(new FileConnection());
        instance.clearGames();
        ModelBank games = instance.getGames();
        assertTrue( games.getGames().isEmpty() );
    }

    /**
     * Test of updateGame method, of class FileGamesDAO.
     */
    @Test
    public void testUpdateGame() throws Exception {
        System.out.println("addGame");
        FileGamesDAO instance = new FileGamesDAO(new FileConnection());
        GameInfoContainer gic = GameInfoContainer.getInstance();
        
        instance.getConnectionUtility().startTransaction();
        Model expected = gic.getModels().getGame(0);
        instance.addGame(0,expected);
        expected.incrementVersion();
        instance.updateGame(0, expected);
        instance.getConnectionUtility().endTransaction();
        
        ModelBank mb = instance.getGames();
        Model actual = mb.getGame(0);
        // checks version number
        assertEquals( expected, actual );
    }
    
}
