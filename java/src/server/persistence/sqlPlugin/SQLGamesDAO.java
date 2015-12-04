package server.persistence.sqlPlugin;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;

import server.gameinfocontainer.ModelBank;
import server.persistence.DAO.IGamesDAO;
import shared.model.Model;

/**
 *
 */
public class SQLGamesDAO implements IGamesDAO {
    
    private Connection conn;
    
    public SQLGamesDAO( Connection c ) {
        this.conn = c;
    }
//INSERT INTO CurrentGames (id,version,name,player0_id,player1_id,player2_id,player3_id,state) VALUES (0,0,"text",0,0,0,0,null)
    static final String addGameSql = "INSERT INTO currentGames(id,version,name,"+
                "player0_id, player1_id, player2_id, player3_id,state) VAlues"+
                "(?,?,?,?,?,?,?,?)";
    
    @Override
    public void addGame(int id, Model model) throws Exception {
        try ( PreparedStatement ps = this.conn.prepareStatement(addGameSql) ) {  
            ps.setInt(1,id);
            ps.setInt(2, model.getVersion());
            ps.setString(3, model.getName());
            ps.setInt(4, model.getPlayers().get(0).getPlayerID());
            ps.setInt(5, model.getPlayers().get(1).getPlayerID());
            ps.setInt(6, model.getPlayers().get(2).getPlayerID());
            ps.setInt(7, model.getPlayers().get(3).getPlayerID());
            
            // Get the blob
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream( baos );
            oos.writeObject( model );
            byte[] bytes = baos.toByteArray();
            oos.close();
            baos.close();
            
            ps.setBytes(8, bytes);
            
            ps.execute();
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
    public ModelBank getGames() throws Exception {
		return null;
        
    }

	@Override
	public void addGame(Model model) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateGame(int id, Model game) throws Exception {
		// TODO Auto-generated method stub
		
	}
    
}
