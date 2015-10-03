package client.paller;

import client.facade.CatanFacade;
import java.util.TimerTask;

import client.proxy.IServerProxy;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.exceptions.ServerException;
import shared.model.Model;


/**
 * This is the task that the ServerPaller will execute every half second to ask for new data from
 * the server.
 */
public class ServerPallTask extends TimerTask {
    
    private Model model;
    private IServerProxy proxy;
    private int currVersion;
    
    private ServerPaller parent = null;

    public void setParent(ServerPaller parent) {
        this.parent = parent;
    }

    /**
     * This constructor is to support dependency injection so that the Poller can take in a real Proxy or Mock Proxy
     * @param proxy This proxy will either be a mock proxy or real proxy
     * @param model 
     */
    ServerPallTask(IServerProxy proxy, Model model){
        this.proxy = proxy;
        this.model = model;
        this.currVersion = 0;
    }

    /**
     * Ask the server for new data and update the model.
     */
    @Override
    public void run() {
        this.parent.incrementTimesPalled();
        
        try {
            this.model = this.proxy.getGameModel( this.currVersion );
        } catch (ServerException ex) {
            Logger.getLogger(ServerPallTask.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Update current version number
        if ( this.model != null )
            this.currVersion = this.model.getVersion();
        
        // Replace old model with new one
        CatanFacade.setModel(this.model);
    }
}