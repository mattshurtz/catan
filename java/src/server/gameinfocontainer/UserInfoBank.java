/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.gameinfocontainer;

import java.util.ArrayList;

import shared.model.Model;

/**
 * Stores the users for the games
 */
/**
 * @author Shurt
 *
 */
/**
 * @author Shurt
 *
 */
public class UserInfoBank {
	private ArrayList<User> users;

	public UserInfoBank() {
		
	}
	
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public ArrayList<User> getUsers() {
		return users;
	}
	
	/**
	 * Returns the User object
	 * @param userId is the id of the user
	 * @return
	 */
	public User getUser(int userId) {
		return null;
	}
	
	/**
	 * Add a user to the array
	 * @param username is the username given from client
	 * @param password is the password given from client
	 * @return
	 */
	public int addUser(String username, String password) {
		return -1;
	}
	
	
	/**
	 * Given a username and password, see if there is a user that meets these
	 * @param username is given from the client
	 * @param password is given from the client
	 * @return -1 if not found, else return the player's id
	 */
	public int validateUser(String username, String password) {
		return -1;
	}
	
}
