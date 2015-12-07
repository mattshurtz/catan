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
    
    
//    private FileGamesDAO instance;
//    private GameInfoContainer gic;
//    private Serializer serializer;
//    private SQLFactory factory;
    
    
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
//        factory = new Factory();
//        instance = (FileGamesDAO) factory.getGameDAO();
//        gic = GameInfoContainer.getInstance();
//        serializer = new Serializer();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getCommands method, of class FileGamesDAO.
     */
    @Test
    public void testGetCommands() throws Exception {
        System.out.println("getCommands");
        int game_id = 0;
        int version = 0;
        FileGamesDAO instance = null;
        ArrayList<CommandParam> expResult = null;
        ArrayList<CommandParam> result = instance.getCommands(game_id, version);
        assertEquals(expResult, result);
    }

    /**
     * Test of addCommand method, of class FileGamesDAO.
     */
    @Test
    public void testAddCommand() throws Exception {
        System.out.println("addCommand");
        String command = "";
        String json = "";
        int player_id = 0;
        int game_id = 0;
        int version = 0;
        String randomValue = "";
        FileGamesDAO instance = null;
        instance.addCommand(command, json, player_id, game_id, version, randomValue);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGames method, of class FileGamesDAO.
     */
    @Test
    public void testGetGames() throws Exception {
        System.out.println("getGames");
        FileGamesDAO instance = null;
        ModelBank expResult = null;
        ModelBank result = instance.getGames();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addGame method, of class FileGamesDAO.
     */
    @Test
    public void testAddGame() throws Exception {
        System.out.println("addGame");
        FileGamesDAO instance = new FileGamesDAO(new FileConnection());
        GameInfoContainer gic = GameInfoContainer.getInstance();
        
//        instance.getConnectionUtility().startTransaction();
        Model expected = gic.getModels().getGame(0);
        instance.addGame(0,expected);
//        instance.getConnectionUtility().endTransaction();
        
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
        FileGamesDAO instance = null;
        instance.clearGames();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateGame method, of class FileGamesDAO.
     */
    @Test
    public void testUpdateGame() throws Exception {
        System.out.println("updateGame");
        int id = 0;
        Model game = null;
        FileGamesDAO instance = null;
        instance.updateGame(id, game);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
