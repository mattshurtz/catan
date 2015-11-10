/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.gameinfocontainer;

import java.util.ArrayList;
import java.util.HashMap;

import shared.model.User;

/**
 * 
 * @author Scott
 */
public class UserInfoBank {
    
    private static int IDcounter = 0;
    private ArrayList<shared.model.User> users;
    
    /**
     * Creates the UserInfoBank. Contains user information such as usernames,
     * passwords, userIDs, currentGames.
     */
    public UserInfoBank(){
        //Different Constructor for loading previously saved server state?
        //update IDcounter if some users already stored. 
    	users = new ArrayList<shared.model.User>();
    }
    
    /**
     * @param username - user's desired username
     * @param password - user's desired password
     * @return userid, else -1 if did not add
     */
    public int addUser(String username, String password) {
    	if(this.getUserID(username)!=-1) {
    		return -1;
    	}
    	if(users.add(new User(username, password))) {
    		return users.size()-1;
    	}
    	return -1;
    }
    
    /**
     * 
     * @param username - user's username
     * @param password - user's password
     * @return true if successfully logged in, else false.
     */
    public boolean login(String username, String password) {
    	if(users.contains(new User(username, password))) {
    		return true;
    	}
    	return false;
    }
    
//    /**
//     * 
//     * @param username - current user's username
//     * @return ArrayList of all game IDs the user with username has joined.
//     */
//    private ArrayList<Integer> getUserGames(String username) {
//        //if valid username
//            return gameIDs.get(username);
//    }
    
    /**
     * 
     * @param username - current user's username
     * @return the servers userID for the registered user
     */
    private int getUserID(String username) {
        //if valid username
    	for (int i=0; i < users.size();i++){
    		if (users.get(i).getUsername().equals(username)) {
    			return i;
    		}
    	}
        return -1;
    }
    
//    /**
//     * 
//     * @param username - current user's username
//     * @return the registered password of the user with username
//     */
//    private String getPassword(String username) {
//        //if valid username
//            return passwords.get(username);
//    }
    
//    /**
//     * 
//     * @param username - queried username
//     * @return true if the username is found in the UserInfoBank
//     */
//    private boolean isRegisteredUser(String username) {
//        if(passwords.containsKey(username)){
//            return true;
//        }
//        return false;
//    }
}
