/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.persistence.filePlugin;

import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JanPaul
 */
public class FileConnectionTest {
    
    FileConnection instance = new FileConnection();
    
    public FileConnectionTest() {
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
     * Test of endTransaction method, of class FileConnection.
     */
    @Test
    public void testEndTransaction() {
        // init the files
        byte[] oldBytes = new byte [] { 0, 1, 2, 3 };
        instance.writeGamesBytes( oldBytes );
        String oldCommands = "12345";
        instance.writeCommandFile(oldCommands);
        
        instance.startTransaction();
        byte[] newBytes = new byte[] { 4, 5, 6, 7, 8 };
        instance.writeGamesBytes(newBytes);
        String newCommands = "678";
        instance.appendCommandString(newCommands);
        instance.endTransaction();
        
        assertTrue( Arrays.equals( newBytes, instance.getGamesBytes() ) );
        assertEquals( oldCommands + newCommands, instance.getCommandsString() );
    }

    /**
     * Test of rollBack method, of class FileConnection.
     */
    @Test
    public void testRollBack() {
        // init the files
        byte[] oldBytes = new byte [] { 0, 1, 2, 3 };
        instance.writeGamesBytes( oldBytes );
        String oldCommands = "12345";
        instance.writeCommandFile(oldCommands);
        
        instance.startTransaction();
        byte[] newBytes = new byte[] { 4, 5, 6, 7, 8 };
        instance.writeGamesBytes(newBytes);
        String newCommands = "678";
        instance.appendCommandString(newCommands);
        instance.rollBack();
        
        assertTrue( Arrays.equals( oldBytes, instance.getGamesBytes() ) );
        assertEquals( oldCommands, instance.getCommandsString() );
    }
    
    @Test
    public void testClearFiles() {
        // init the files
        byte[] oldBytes = new byte [] { 0, 1, 2, 3 };
        instance.writeGamesBytes( oldBytes );
        String oldCommands = "12345";
        instance.writeCommandFile(oldCommands);
        
        instance.clearCommandsFile();
        instance.clearGamesFile();
        
        byte[] gamesBytes = instance.getGamesBytes();
        boolean cleared = ( gamesBytes == null || gamesBytes.length == 0 );
        assertTrue( cleared );
        
        String commands = instance.getCommandsString();
        cleared = ( commands == null || commands.length() == 0 );
        assertTrue( cleared );
    }
    
}
