package server.persistence.factory;

import server.persistence.DAO.IGamesDAO;
import server.persistence.DAO.IUsersDAO;

public interface IFactory {

    public IGamesDAO getGameDAO();
    
    public IUsersDAO getUserDAO();
}
