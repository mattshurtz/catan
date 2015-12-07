/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.persistence.filePlugin;

import com.google.gson.reflect.TypeToken;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.gameinfocontainer.GameInfoContainer;
import server.gameinfocontainer.ModelBank;
import server.persistence.DAO.IConnections;
import server.persistence.DAO.IGamesDAO;
import shared.communication.params.CommandParam;
import shared.communication.params.SerializableCommand;
import shared.json.Deserializer;
import shared.json.Serializer;
import shared.model.Model;

/**
 *
 */
public class FileGamesDAO implements IGamesDAO {
    
    FileConnection fc = null;
    
    Deserializer deserialize = new Deserializer();
    Serializer serialize = new Serializer();
    
    public FileGamesDAO( FileConnection fc ) {
        this.fc = fc;
    }

    @Override
    public ArrayList<CommandParam> getCommands(int game_id, int version) throws Exception {
        String commandsStr = this.fc.getCommandsString();
        String processed = "[" + commandsStr.substring(1) + "]";
        // what a freakin beast of a line. sorry
        ArrayList<SerializableCommand> com = (ArrayList<SerializableCommand>) deserialize.toClass( new TypeToken<ArrayList<SerializableCommand>>() {}.getType(), processed );
        
        // Only return the ones from that list for the right game id & version
        ArrayList<CommandParam> ret = new ArrayList<>();
        for ( SerializableCommand sc : com ) {
            if ( sc.getGameId() == game_id && sc.getVersion() > version ) {
                ret.add( sc.toCommandParam() );
            }
        }
        
        return ret;
    }
    
    @Override
    public void addCommand(String command, String json, int player_id, int game_id, int version, String randomValue) throws Exception {
        SerializableCommand sc = new SerializableCommand( command, json, player_id, game_id, version, randomValue );
        String fileAppend = serialize.toJson(sc);
        // Add a comma on the front of it to make it a real JSON list.
        fileAppend = "," + fileAppend;
        fc.appendCommandString(fileAppend);
    }
    
    public static GameInfoContainer getGameInfoContainer( FileConnection fc ) {
        byte[] bytes = fc.getGamesBytes();
        if ( bytes == null || bytes.length == 0 ) 
            return new GameInfoContainer( false );
        
        try ( ByteArrayInputStream bais = new ByteArrayInputStream( bytes ); 
            ObjectInputStream ois = new ObjectInputStream( bais ) ) {
            GameInfoContainer gic = (GameInfoContainer) ois.readObject();
            return gic;
        } catch (IOException ex) {
            Logger.getLogger(FileGamesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FileGamesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new GameInfoContainer( false );
    }

    @Override
    public IConnections getConnectionUtility() {
        return this.fc;
    }

    @Override
    public ModelBank getGames() throws Exception {
        return getGameInfoContainer( fc ).getModels();
    }

    @Override
    public void addGame(int id, Model model) throws Exception {
        GameInfoContainer gic = getGameInfoContainer( fc );
        gic.getModels().addGame(id, model);
        fc.writeGamesBytes( toBytes(gic) );
    }

    @Override
    public void clearGames() throws Exception {
        GameInfoContainer gic = getGameInfoContainer( fc );
        gic.setGames(new ModelBank(false));
        byte[] bytes = toBytes( gic );
        
        fc.writeGamesBytes(bytes);
        
        fc.clearCommandsFile();
    }
    
    public byte[] toBytes( Object o ) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream( baos );
            oos.writeObject( o );
            byte[] bytes = baos.toByteArray();
            oos.close();
            baos.close();

            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateGame(int id, Model game) throws Exception {
        GameInfoContainer gic = getGameInfoContainer( fc );
        gic.getModels().setGame(id, game);
        fc.writeGamesBytes( toBytes(gic) );
    }
}
