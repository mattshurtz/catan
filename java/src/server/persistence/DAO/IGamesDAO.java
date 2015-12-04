package server.persistence.DAO;

public interface IGamesDAO {

    public void addGame() throws Exception;
    
    public void updateGame() throws Exception;
    
    public void clearGames() throws Exception;
    
    public void addCommand() throws Exception;
    
    public void getGames() throws Exception;
    
}
