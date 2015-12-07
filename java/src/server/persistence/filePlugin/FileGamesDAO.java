/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.persistence.filePlugin;

import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import server.gameinfocontainer.ModelBank;
import server.persistence.DAO.IConnections;
import server.persistence.DAO.IGamesDAO;
import shared.communication.params.CommandParam;
import shared.json.Deserializer;
import shared.model.Model;

/**
 *
 */
public class FileGamesDAO implements IGamesDAO {
    
    FileConnection fc = null;
    
    Deserializer deserialize = new Deserializer();
    
    public FileGamesDAO( FileConnection fc ) {
        this.fc = fc;
    }

    @Override
    public ArrayList<CommandParam> getCommands(int game_id, int version) throws Exception {
        String commandsStr = this.fc.getCommandsString();
        // what a freakin beast of a line. sorry
        ArrayList<CommandParam> ret = (ArrayList<CommandParam>) deserialize.toClass( new TypeToken<ArrayList<CommandParam>>() {}.getType(), commandsStr );
        return ret;
    }
    
    @Override
    public void addCommand(String command, String json, int player_id, int game_id, int version, String randomValue) throws Exception {
//        SerializableCommand sc = 
    }


    @Override
    public IConnections getConnectionUtility() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ModelBank getGames() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addGame(int id, Model model) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clearGames() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateGame(int id, Model game) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
