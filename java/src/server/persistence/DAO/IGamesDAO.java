package server.persistence.DAO;

import server.gameinfocontainer.ModelBank;
import shared.model.Model;

public interface IGamesDAO {


    public void addGame(int id, Model model) throws Exception;
    
    public void clearGames() throws Exception;
    
    public void addCommand() throws Exception;
    
    public ModelBank getGames() throws Exception;

	void updateGame(int id, Model game) throws Exception;
    
}
