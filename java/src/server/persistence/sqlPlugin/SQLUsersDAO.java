/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.persistence.sqlPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import server.gameinfocontainer.UserInfoBank;
import server.persistence.DAO.IUsersDAO;

/**
 *
 */
public class SQLUsersDAO implements IUsersDAO {
    private Connection conn;
    private  SQLConnectionUtility connectionUtility;
    
    public SQLUsersDAO( SQLConnectionUtility connectionUtility ) {
        this.connectionUtility = connectionUtility;
        this.conn = connectionUtility.getConnection();
    }

    static final String addUser = "INSERT INTO Users VALUES (?,?,?)";//id username password
    
    @Override
    public void addUser(int userID, String username, String password) throws Exception {
        try ( PreparedStatement ps = this.conn.prepareStatement(addUser)) {
        	ps.setInt(1, userID);
        	ps.setString(2, username);
        	ps.setString(2, password);
        	
        	ps.execute();
        }
    }

    static final String clearUsers = "DELETE FROM Users;";
    
    @Override
    public void clearUsers() throws Exception {
    	Statement s = this.conn.createStatement();
        s.execute(clearUsers);
    }

    static final String selectUsers = "SELECT * FROM Users;";
    
    @Override
    public UserInfoBank getUsers() throws Exception {
    	Statement s = this.conn.createStatement();
        s.execute(selectUsers);
        
        UserInfoBank users = new UserInfoBank();
        //users.addUser(username, password);
        return users;
    }
    
    @Override
    public SQLConnectionUtility getConnectionUtility() {
        return connectionUtility;
    }
}
