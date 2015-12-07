/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.persistence.filePlugin;

import server.persistence.DAO.IGamesDAO;
import server.persistence.DAO.IUsersDAO;
import server.persistence.factory.AbstractFactory;

/**
 *
 */
public class FileFactory extends AbstractFactory {

    @Override
    public IGamesDAO getGameDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IUsersDAO getUserDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
