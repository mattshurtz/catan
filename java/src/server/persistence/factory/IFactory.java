package server.persistence.factory;

import server.persistence.DAO.IGamesDAO;
import server.persistence.DAO.IUsersDAO;

public interface IFactory {
    
    public void init();

    public IGamesDAO getGameDAO();
    
    public IUsersDAO getUserDAO();
}
