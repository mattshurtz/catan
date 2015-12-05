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
import java.util.ArrayList;
import javax.sql.rowset.CachedRowSet;
import server.commands.Command;

import server.gameinfocontainer.ModelBank;
import server.persistence.DAO.IGamesDAO;
import shared.communication.params.CommandParam;
import shared.model.Model;

/**
 *
 */
public class SQLGamesDAO implements IGamesDAO {




    
    private SQLConnectionUtility connectionUtility;
    private Connection conn;
    
    public SQLGamesDAO( SQLConnectionUtility c ) {
        this.connectionUtility = c;
        this.conn = c.getConnection();
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
    
    
    static final String clearGames = "DELETE FROM currentGames;";

    @Override
    public void clearGames() throws Exception {
        Statement s = this.conn.createStatement();
        s.execute(clearGames);
    }
    
    static final String addCommand = "INSERT INTO commands (command,json,player_id,game_id, version, randomValue) Values (?,?,?,?,?,?)";

    @Override
    public void addCommand(String command, String json, int player_id, int game_id, int version, int randomValue) throws Exception {
        try ( PreparedStatement ps = this.conn.prepareStatement(addCommand) ) {  
            ps.setString(1, command);
            ps.setString(2, json);
            ps.setInt(3, player_id);
            ps.setInt(4, game_id);
            ps.setInt(5, version);
            ps.setInt(6,randomValue);
            
            ps.execute();
        }
    
    }
    
    static final String getCommands = "SELECT * FROM Commands WHERE version > ? and game_id = ?";
    @Override
    public ArrayList<CommandParam> getCommands(int game_id, int version) throws Exception {
        ArrayList<CommandParam> commands = new ArrayList<CommandParam>();
        try ( PreparedStatement ps = this.conn.prepareStatement(getCommands) ) {  
            ps.setInt(1, version);
            ps.setInt(2, game_id);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                CommandParam commandParam = new CommandParam(rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getString(7));
                commands.add(commandParam);
            }
        }
        return commands;
    }
    
    @Override
    public SQLConnectionUtility getConnectionUtility() {
        return connectionUtility;
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
