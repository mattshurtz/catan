/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.persistence.sqlPlugin;

import java.sql.Connection;
import server.persistence.DAO.IUsersDAO;

/**
 *
 */
public class SQLUsersDAO implements IUsersDAO {
    private Connection conn;
    
    public SQLUsersDAO( Connection c ) {
        this.conn = c;
    }

    @Override
    public void addUser() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clearUsers() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getUsers() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
