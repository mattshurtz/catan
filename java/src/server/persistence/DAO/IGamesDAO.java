package server.persistence.DAO;

import server.gameinfocontainer.ModelBank;

public interface IGamesDAO {

    public void addGame() throws Exception;
    
    public void updateGame() throws Exception;
    
    public void clearGames() throws Exception;
    
    public void addCommand() throws Exception;
    
    public ModelBank getGames() throws Exception;
    
}
