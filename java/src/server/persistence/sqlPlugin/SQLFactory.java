/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.persistence.sqlPlugin;

import server.persistence.DAO.IGamesDAO;
import server.persistence.DAO.IUsersDAO;
import server.persistence.factory.AbstractFactory;

/**
 *
 */
public class SQLFactory extends AbstractFactory{

    private SQLConnectionUtility connUtility;
    
    public SQLFactory(){
        connUtility = new SQLConnectionUtility();
    }
    
    @Override
    public IGamesDAO getGameDAO() {
        return new SQLGamesDAO( connUtility );
    }

    @Override
    public IUsersDAO getUserDAO() {
        return new SQLUsersDAO( connUtility );
    }
    
}
