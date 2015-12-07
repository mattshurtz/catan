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
    
    private FileConnection fc = new FileConnection();
    
    public FileFactory() {
    }

    @Override
    public IGamesDAO getGameDAO() {
        return new FileGamesDAO(fc);
    }

    @Override
    public IUsersDAO getUserDAO() {
        return new FileUsersDAO(fc);
    }
    
}
