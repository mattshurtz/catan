package client.facade;

import client.proxy.IServerProxy;
import shared.model.Model;

/**
 * A singleton that gives out the actual facades.
 */
public class CatanFacade {
    
    // the singleton facades
    private static CanDoFacadeMyTurn myTurn;
    private static CanDoFacadeNotMyTurn notMyTurn;
    private static DoFacade doFacade;
    private static GameHubFacade gameHubFacade;
    private static IServerProxy proxy;
    private static Model model;
    
    private static boolean isServer = false;
    private static int myPlayerIndex = 0;
    
    public CatanFacade(IServerProxy proxy, Model model){
        this.model = model;
        this.proxy = proxy;
        this.myTurn = new CanDoFacadeMyTurn(proxy, model);
        this.notMyTurn = new CanDoFacadeNotMyTurn(proxy, model);
        this.doFacade = new DoFacade(proxy, model);
        this.gameHubFacade = new GameHubFacade(proxy, model);
    }
    
    private static boolean isMyTurn() {
        return model.getTurnTracker().isPlayersTurn( myPlayerIndex );
    }

    public static Model getModel() {
        return model;
    }

    public static void setModel(Model model) {
        CatanFacade.model = model;
    }
    
    public static CanDoFacade getCanDoFacade() {
        if ( isMyTurn() )
            return myTurn;
        else
            return notMyTurn;
    }
    
    public static DoFacade getDoFacade() {
        return doFacade;
    }
    
    public static GameHubFacade getGameHubFacade() {
        return gameHubFacade;
    }
}
