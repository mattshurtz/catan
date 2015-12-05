/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.persistence.DAO;

/**
 *
 */
public interface IConnections {
    
    public void startTransaction();
    
    public void endTransaction();
    
    public void rollBack();
    
    public void openConnection();
    
    public void closeConnection();
    
}
