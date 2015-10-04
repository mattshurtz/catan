/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model;

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
public class UserTest {
    
    public UserTest() {
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
     * Test of getUsername method, of class User.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        User instance = new User();
        String expResult = "";
        String result = instance.getUsername();
        assertEquals(expResult, result);

    }

    /**
     * Test of setUsername method, of class User.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "";
        User instance = new User();
        instance.setUsername(username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPassword method, of class User.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        User instance = new User();
        String expResult = "";
        String result = instance.getPassword();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPassword method, of class User.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "";
        User instance = new User();
        instance.setPassword(password);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testIsValidUsername() {
        System.out.println("isValidUsername");
        String[] usernames = new String[] {
            "a",  // false -- too short
            "ab", // false -- too short
            "abc",
            "abE",
            "ab123",
            "123abc",
            "1-2",
            "1_2",
            "a-_",
            "ABC123_-", // false -- too long
            "'hello'", // false -- disallowed characters
            "\"abc", // false -- disallowed characters
            "a+b" // false -- disallowed characters
        };
        boolean[] expectedResults = new boolean[] {
            false, false,
            true, true, true, true, true, true, true,
            false, false, false, false
        };
        boolean result;
        for ( int i = 0; i < usernames.length; i++ ) {
            result = User.isValidUsername( usernames[i] );
            if ( expectedResults[i] != result )
                System.out.println("isValidUsername failed on username: " + usernames[i] );
            assertEquals( expectedResults[i], result );
        }
    }
    
    @Test
    public void testIsValidPassword() {
        System.out.println("isValidPassword");
        String[] passwords = new String[] {
            "a",  // false -- too short
            "ab", // false -- too short
            "abc", // false -- too short
            "abEd", // false -- too short
            "abdef", 
            "ab123",
            "123abc",
            "1-234",
            "1_234",
            "a-_bd",
            "ABC123_-",
            "'hello'", // false -- disallowed characters
            "\"abc", // false -- disallowed characters
            "a+b" // false -- disallowed characters
        };
        boolean[] expectedResults = new boolean[] {
            false, false, false, false,
            true, true, true, true, true, true, true,
            false, false, false
        };
        boolean result;
        for ( int i = 0; i < passwords.length; i++ ) {
            result = User.isValidPassword( passwords[i] );
            if ( expectedResults[i] != result )
                System.out.println("isValidPassword failed on username: " + passwords[i] );
            assertEquals( expectedResults[i], result );
        }
    }
}
