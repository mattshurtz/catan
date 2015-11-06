/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.gameinfocontainer;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Scott
 */
public class UserInfoBank {
    
    private static int IDcounter = 0;
    private HashMap<String,String> passwords;
    private HashMap<String,Integer> userIDs;
    private HashMap<String,ArrayList<Integer>> gameIDs;
    
    /**
     * Creates the UserInfoBank. Contains user information such as usernames,
     * passwords, userIDs, currentGames.
     */
    public UserInfoBank(){
        //Different Constructor for loading previously saved server state?
        //update IDcounter if some users already stored. 
    }
    
    /**
     * @param username - user's desired username
     * @param password - user's desired password
     * @return true if successfully registered, else false
     */
    public boolean registerUser(String username, String password) {
        
        //if valid username and password
            passwords.put(username, password);
            userIDs.put(username, IDcounter++);
            gameIDs.put(username, new ArrayList<Integer>());
            //remember to set cookie
            //remember to login player
            
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * 
     * @param username - user's username
     * @param password - user's password
     * @return true if successfully logged in, else false.
     */
    public boolean loginUser(String username, String password) {
        //if valid username password combo in passwords map:
            //set cookie
            //login
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * 
     * @param username - current user's username
     * @return ArrayList of all game IDs the user with username has joined.
     */
    private ArrayList<Integer> getUserGames(String username) {
        //if valid username
            return gameIDs.get(username);
    }
    
    /**
     * 
     * @param username - current user's username
     * @return the servers userID for the registered user
     */
    private int getUserID(String username) {
        //if valid username
            return userIDs.get(username);
    }
    
    /**
     * 
     * @param username - current user's username
     * @return the registered password of the user with username
     */
    private String getPassword(String username) {
        //if valid username
            return passwords.get(username);
    }
    
    /**
     * 
     * @param username - queried username
     * @return true if the username is found in the UserInfoBank
     */
    private boolean isRegisteredUser(String username) {
        if(passwords.containsKey(username)){
            return true;
        }
        return false;
    }
}
