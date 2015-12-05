package server.persistence.DAO;

import server.gameinfocontainer.ModelBank;
import server.persistence.sqlPlugin.SQLConnectionUtility;
import shared.model.Model;

public interface IGamesDAO {


    public void addGame(int id, Model model) throws Exception;
    
    public void clearGames() throws Exception;
    
    public void addCommand(String command, String json, int player_id, int game_id, int version) throws Exception;
    
    public void getCommands(int game_id, int version);
    
    public ModelBank getGames() throws Exception;

	void updateGame(int id, Model game) throws Exception;
    
    public SQLConnectionUtility getConnectionUtility();
}
