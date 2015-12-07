/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.persistence.sqlPlugin;

import java.sql.Connection;
import java.sql.ResultSet;
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
        	ps.setString(3, password);
        	
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
        ResultSet rs = s.executeQuery(selectUsers);
        UserInfoBank users = new UserInfoBank();
        while(rs.next()){
          users.addUser(rs.getString(1), rs.getString(2));  
        }
        return users;
    }
    
    @Override
    public SQLConnectionUtility getConnectionUtility() {
        return connectionUtility;
    }
}
