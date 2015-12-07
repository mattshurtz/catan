/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.persistence.filePlugin;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import server.gameinfocontainer.GameInfoContainer;
import server.gameinfocontainer.ModelBank;
import server.gameinfocontainer.UserInfoBank;
import server.persistence.DAO.IConnections;
import shared.model.Model;

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
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getConnectionUtility method, of class FileUsersDAO.
     */
    @Test
    public void testGetConnectionUtility() {
        System.out.println("getConnectionUtility");
        FileUsersDAO instance = null;
        IConnections expResult = null;
        IConnections result = instance.getConnectionUtility();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        System.out.println("clearUsers");
        FileUsersDAO instance = null;
        instance.clearUsers();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsers method, of class FileUsersDAO.
     */
    @Test
    public void testGetUsers() throws Exception {
        System.out.println("getUsers");
        FileUsersDAO instance = null;
        UserInfoBank expResult = null;
        UserInfoBank result = instance.getUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toBytes method, of class FileUsersDAO.
     */
    @Test
    public void testToBytes() {
        System.out.println("toBytes");
        Object o = null;
        FileUsersDAO instance = null;
        byte[] expResult = null;
        byte[] result = instance.toBytes(o);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
