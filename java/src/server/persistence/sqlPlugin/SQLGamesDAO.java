package server.persistence.sqlPlugin;

import java.sql.Connection;
import java.sql.Statement;
import server.persistence.DAO.IGamesDAO;

/**
 *
 */
public class SQLGamesDAO implements IGamesDAO {
    
    private Connection conn;
    
    public SQLGamesDAO( Connection c ) {
        this.conn = c;
    }

    @Override
    public void addGame() throws Exception {
        try ( Statement s = this.conn.createStatement() ) {
            // add the game
        }
    }

    @Override
    public void updateGame() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clearGames() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addCommand() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getGames() throws Exception {
        
    }
    
}
