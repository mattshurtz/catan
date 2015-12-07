/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.gameinfocontainer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import shared.model.User;

/**
 * 
 * @author Scott
 */
public class UserInfoBank {
    
    private List<shared.model.User> users;
    
    /**
     * Creates the UserInfoBank. Contains user information such as usernames,
     * passwords, userIDs, currentGames.
     */
    public UserInfoBank(boolean hasDefaultUsers){
        //Different Constructor for loading previously saved server state?
        //update IDcounter if some users already stored. 
    	users = new ArrayList<shared.model.User>();
    	if(hasDefaultUsers){
                    addDefaultUsers();
        }
    }
    
    
    
    public void addDefaultUsers() {
    	addUser("Matt","matt");
    	addUser("Scott","scott");
    	addUser("Jan","jan");
    	addUser("Garrett","garrett");
    	addUser("Alex", "alex");
    }
    
    /**
     * @param username - user's desired username
     * @param password - user's desired password
     * @return userid, else -1 if did not add
     */
    public int addUser(String username, String password) {
    	//check if user already exists
    	if(this.getUserID(username)!=-1) {
    		return -1;
    	}
    	
    	if(!(username == "Matt" || username=="Jan" || username=="Alex")) {
    		//check if valid username
        	if(username.length() < 3 || username.length() > 7){
        		return -1;
        	}
        	//check if valid password
        	String regex = "[\\w\\d_-]*";
        	if(password.length() < 5 || !password.matches(regex)){
        		return -1;
        	}
    	}
    	
    	//add user
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
    public int login(String username, String password) {
    	for (int i=0; i < users.size();i++){
    		if (users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(password)) {
    			return i;
    		}
    	}
    	return -1;
    }
    
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
    
    public String getPlayerName( int id ) {
        return users.get(id).getUsername();
    }
}
