/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.facade;

    /**
     * Handler delegates all operations that aren't related to HTTP requests to the
     * ServerFacade, then the server facade performs these operations on the model
     * through the command classes. 
     */
public interface IServerFacade {
    
    public void doFunction();
    
}
