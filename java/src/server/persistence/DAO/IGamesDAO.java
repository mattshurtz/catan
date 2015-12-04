package server.persistence.DAO;

import server.gameinfocontainer.ModelBank;
import shared.model.Model;

public interface IGamesDAO {

    public void addGame() throws Exception;
    
    public void updateGame() throws Exception;
    
    public void clearGames() throws Exception;
    
    public void addCommand() throws Exception;
    
    public ModelBank getGames() throws Exception;

	void addGame(Model model) throws Exception;

	void updateGame(int id, Model game) throws Exception;
    
}
