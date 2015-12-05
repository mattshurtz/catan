/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.persistence.sqlPlugin;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import server.gameinfocontainer.GameInfoContainer;
import server.gameinfocontainer.ModelBank;
import shared.json.Serializer;

/**
 *
 * @author JanPaul
 */
public class SQLGamesDAOTest {

    private SQLGamesDAO instance;
    private GameInfoContainer gic;
    private Serializer serializer;
    private SQLFactory factory;
    
    public SQLGamesDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        factory = new SQLFactory();
        factory.init();
        instance = (SQLGamesDAO) factory.getGameDAO();
        gic = GameInfoContainer.getInstance();
        serializer = new Serializer();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addGame method, of class SQLGamesDAO.
     */
    @Test
    public void testAddGame() throws Exception {
        System.out.println("addGame");
        SQLGamesDAO instance = new SQLGamesDAO(factory.getConnection());
        instance.addGame(0,gic.getModels().getGame(0));
        ModelBank theGame = instance.getGames();
        assertNotNull( theGame );
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of updateGame method, of class SQLGamesDAO.
     */
    @Test
    public void testUpdateGame() throws Exception {
        System.out.println("updateGame");
        SQLGamesDAO instance = null;
//        instance.updateGame();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearGames method, of class SQLGamesDAO.
     */
    @Test
    public void testClearGames() throws Exception {
        System.out.println("clearGames");
        SQLGamesDAO instance = null;
        instance.clearGames();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCommand method, of class SQLGamesDAO.
     */
    @Test
    public void testAddCommand() throws Exception {
        System.out.println("addCommand");
        SQLGamesDAO instance = null;
        instance.addCommand();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGames method, of class SQLGamesDAO.
     */
    @Test
    public void testGetGames() throws Exception {
        System.out.println("getGames");
        
        ModelBank theGames = instance.getGames();
        assertNotNull( theGames );
    }
    
}
