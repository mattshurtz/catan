/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.persistence.filePlugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import server.gameinfocontainer.GameInfoContainer;
import server.gameinfocontainer.UserInfoBank;

/**
 *
 * @author karahartley
 */
public class FileUsersDAOTest {
    
    public FileUsersDAOTest() {
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
     * Test of addUser method, of class FileUsersDAO.
     */
    @Test
    public void testAddUser() throws Exception {
        System.out.println("addUser");
        FileUsersDAO instance = new FileUsersDAO(new FileConnection());
        GameInfoContainer gic = new GameInfoContainer(false);
        
        instance.getConnectionUtility().startTransaction();
        UserInfoBank expected = gic.getUsers();
        expected.addUser("testing", "password");
        instance.addUser(0,"testing","password");
        instance.getConnectionUtility().endTransaction();
        
        UserInfoBank actual = instance.getUsers();
        assertEquals( expected, actual );
    }

    /**
     * Test of clearUsers method, of class FileUsersDAO.
     */
    @Test
    public void testClearUsers() throws Exception {
        FileUsersDAO instance = new FileUsersDAO(new FileConnection());
        GameInfoContainer gic = new GameInfoContainer(false);
        
        instance.getConnectionUtility().startTransaction();
        instance.addUser(0,"testing","password");
        instance.clearUsers();
        instance.getConnectionUtility().endTransaction();
        
        UserInfoBank expected = new UserInfoBank(false);
        UserInfoBank actual = instance.getUsers();
        assertEquals( expected, actual );
    }
    
}
