/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.persistence.filePlugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import server.gameinfocontainer.GameInfoContainer;
import server.gameinfocontainer.ModelBank;
import shared.communication.params.CommandParam;
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
        try {
            Files.deleteIfExists( Paths.get( "persistence/games.dat" ) );
            Files.deleteIfExists( Paths.get( "persistence/games.dat.tmp" ) );
            Files.deleteIfExists( Paths.get( "persistence/games.dat.old" ) );
            Files.deleteIfExists( Paths.get( "persistence/commands.dat" ) );
            Files.deleteIfExists( Paths.get( "persistence/commands.dat.tmp" ) );
            Files.deleteIfExists( Paths.get( "persistence/commands.dat.old" ) );
        } catch (IOException ex) {
            Logger.getLogger(FileConnectionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addCommand method, of class FileGamesDAO.
     */
    @Test
    public void testAddCommand() throws Exception {
        FileGamesDAO instance = new FileGamesDAO(new FileConnection());
        GameInfoContainer gic = GameInfoContainer.getInstance();
        
        Model model = gic.getModels().getGame(0);
        instance.addGame(0,model);
        instance.clearCommands();
        
        instance.addCommand("hey", "json", 1, 0, 1, "random");
        instance.addCommand("hey2", "json2", 2, 0, 2, "random2");
        
        ArrayList<CommandParam> list = instance.getCommands(0, 0);
        
        assertTrue( "command list size",  list.size() == 2 );
        
        CommandParam cp = list.get(0);
        assertEquals( "hey", cp.getCommand() );
        assertEquals( "json", cp.getJson());
        assertEquals( 1, cp.getPlayerId() );
        assertEquals( "random", cp.getRandom());
        
        cp = list.get(1);
        assertEquals( "hey2", cp.getCommand() );
        assertEquals( "json2", cp.getJson());
        assertEquals( 2, cp.getPlayerId() );
        assertEquals( "random2", cp.getRandom());
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
