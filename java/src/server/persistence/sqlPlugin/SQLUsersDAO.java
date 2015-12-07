/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.persistence.sqlPlugin;

import java.sql.Connection;
import java.sql.ResultSet;
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
    
    

    @Override
    public void addUser() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static final String clearUsers = "DELETE FROM users;";
    
    @Override
    public void clearUsers() throws Exception {
    	Statement s = this.conn.createStatement();
        s.execute(clearUsers);
    }

    static final String selectUsers = "SELECT * FROM users;";
    
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
