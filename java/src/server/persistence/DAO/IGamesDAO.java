package server.persistence.DAO;

import shared.model.Model;

public interface IGamesDAO {

    public void addGame(Model model) throws Exception;
    
    public void updateGame() throws Exception;
    
    public void clearGames() throws Exception;
    
    public void addCommand() throws Exception;
    
    public void getGames() throws Exception;
    
}
