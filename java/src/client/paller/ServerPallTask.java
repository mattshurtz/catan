package client.paller;

import client.facade.CatanFacade;
import java.util.TimerTask;

import client.proxy.IServerProxy;
import shared.model.Model;


/**
 * This is the task that the ServerPaller will execute every half second to ask for new data from
 * the server.
 */
public class ServerPallTask extends TimerTask {
    
    private Model model;
    private IServerProxy proxy;
    
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
    }

    /**
     * Ask the server for new data and update the model.
     */
    @Override
    public void run() {
        this.parent.incrementTimesPalled();
        CatanFacade.updateGameModel();
    }
}