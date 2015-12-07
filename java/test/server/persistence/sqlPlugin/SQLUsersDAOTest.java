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
import server.gameinfocontainer.UserInfoBank;
import shared.json.Serializer;

/**
 *
 */
public class SQLUsersDAOTest {
    
    private SQLUsersDAO instance;
    private GameInfoContainer gic;
    private Serializer serializer;
    private SQLFactory factory;
    
    public SQLUsersDAOTest() {
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
        instance = (SQLUsersDAO) factory.getUserDAO();
        gic = GameInfoContainer.getInstance();
        serializer = new Serializer();
    }
    
    @After
    public void tearDown() {
                        instance.getConnectionUtility().closeConnection();

    }

    /**
     * Test of addUser method, of class SQLUsersDAO.
     */
    @Test
    public void testAddUser() throws Exception {
        System.out.println("addUser");
        SQLUsersDAO instance = new SQLUsersDAO(new SQLConnectionUtility());;
        instance.getConnectionUtility().startTransaction();
        
        instance.addUser(2,"kimberly","Jenkins");
        
        instance.getConnectionUtility().endTransaction();

    }

    /**
     * Test of clearUsers method, of class SQLUsersDAO.
     */
    @Test
    public void testClearUsers() throws Exception {
        System.out.println("addUser");
        SQLUsersDAO instance = new SQLUsersDAO(new SQLConnectionUtility());;
        instance.getConnectionUtility().startTransaction();
        
        instance.clearUsers();
        
        instance.getConnectionUtility().endTransaction();
    }

    /**
     * Test of getUsers method, of class SQLUsersDAO.
     */
    @Test
    public void testGetUsers() throws Exception {
        System.out.println("addUser");
        SQLUsersDAO instance = new SQLUsersDAO(new SQLConnectionUtility());;
        instance.getConnectionUtility().startTransaction();
        UserInfoBank bank = new UserInfoBank();
        bank = instance.getUsers();
        
        instance.getConnectionUtility().endTransaction();
    }

    /**
     * Test of getConnectionUtility method, of class SQLUsersDAO.
     */
    @Test
    public void testGetConnectionUtility() {
        System.out.println("getConnectionUtility");
        SQLUsersDAO instance = null;
        SQLConnectionUtility expResult = null;
        SQLConnectionUtility result = instance.getConnectionUtility();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
