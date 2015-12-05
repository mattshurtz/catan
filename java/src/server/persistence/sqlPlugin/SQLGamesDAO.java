package server.persistence.sqlPlugin;

import com.sun.rowset.CachedRowSetImpl;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.rowset.CachedRowSet;

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

    static final String updateGamesSql = "update CurrentGames set \n" +
        "	version = ?, \n" +
        "	player0_id = ?, \n" +
        "	player1_id = ?, \n" +
        "	player2_id = ?, \n" +
        "	player3_id = ?, \n" +
        "	state = ?\n" +
        "where id = ?";

    @Override
    public void updateGame( int id, Model game ) throws Exception {
        try ( PreparedStatement ps = this.conn.prepareStatement( updateGamesSql ) ) {
            // update the game
            ps.setInt( 1, game.getVersion() ); // version
            ps.setInt( 2, game.getPlayer( 0 ).getPlayerID() );
            ps.setInt( 3, game.getPlayer( 1 ).getPlayerID() );
            ps.setInt( 4, game.getPlayer( 2 ).getPlayerID() );
            ps.setInt( 5, game.getPlayer( 3 ).getPlayerID() );
            ps.setInt( 7, id );
            
            // Get the blob
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream( baos );
            oos.writeObject( game );
            byte[] bytes = baos.toByteArray();
            oos.close();
            baos.close();
            
            Blob blob = conn.createBlob();
            blob.setBytes( 0, bytes );
            ps.setBlob(6, blob);
            
            ps.execute();
        }
    }

    @Override
    public void clearGames() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addCommand() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static final String getAllGamesSql = "select * from CurrentGames";
    
    @Override
    public ModelBank getGames() throws Exception {
        CachedRowSet crs = new CachedRowSetImpl();
        try ( Statement s = this.conn.createStatement() ) {
            ResultSet rs = s.executeQuery( getAllGamesSql );
            crs.populate( rs );
        }
        
        ModelBank ret = new ModelBank();
        while ( crs.next() ) {
            byte[] stateBytes = (byte[]) crs.getObject("state");
            Model theModel = null;
            try ( ByteArrayInputStream bais = new ByteArrayInputStream( stateBytes ); 
                  ObjectInputStream ois = new ObjectInputStream( bais ) ) {
                theModel = (Model) ois.readObject();
            }
            
            int id = crs.getInt("id");
            ret.addGame( id, theModel );
        }
        
        return ret;
    }
    
}
